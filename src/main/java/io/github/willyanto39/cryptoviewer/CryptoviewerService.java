package io.github.willyanto39.cryptoviewer;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
class CryptoviewerService {
  private static final String COINMARKETCAP_API_URL = "https://pro-api.coinmarketcap.com";
  private static final int CACHE_DURATION_MINUTE = 1;

  @Value("${apiKey}")
  private String apiKey;

  private final CryptocurrencyCacheRepository cryptocurrencyCacheRepository;

  public CryptoviewerService(CryptocurrencyCacheRepository cryptocurrencyCacheRepository) {
    this.cryptocurrencyCacheRepository = cryptocurrencyCacheRepository;
  }

  public ResponseEntity<CryptoviewerResponse> getCryptocurrencyData(CryptoviewerRequest request) {
    var cache = cryptocurrencyCacheRepository
        .findByCryptocurrencyId(request.getId())
        .orElse(null);

    if (cache != null && LocalDateTime.now().isBefore(cache.getValidUntil())) {
      var cryptocurrency = new Cryptocurrency();
      cryptocurrency.setName(cache.getName());
      cryptocurrency.setSymbol(cache.getSymbol());
      cryptocurrency.setPrice(cache.getPrice());
      cryptocurrency.setMarketCap(cache.getMarketCap());
      cryptocurrency.setVolume24Hour(cache.getVolume24Hour());
      cryptocurrency.setCirculatingSupply(cache.getCirculatingSupply());
      cryptocurrency.setTotalSupply(cache.getTotalSupply());

      return ResponseEntity.ok(CryptoviewerResponse.success(cryptocurrency));
    }

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("X-CMC_PRO_API_KEY", apiKey);
    HttpEntity<CryptoviewerRequest> httpEntity = new HttpEntity<>(request, httpHeaders);

    String url = COINMARKETCAP_API_URL
        + String.format("/v2/cryptocurrency/quotes/latest?id=%s&convert=%s",
            request.getId(),
            request.getConvert());
    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<String> response;
    try {
      response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
    } catch (RestClientResponseException e) {
      return getErrorResponse(e);
    }

    try {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode root = objectMapper.readTree(response.getBody());

      String id = request.getId();
      String convert = request.getConvert();

      JsonNode cryptocurrencyData = root.get("data").get(id);
      JsonNode quoteData = cryptocurrencyData.get("quote").get(convert);
      Cryptocurrency cryptocurrency = new Cryptocurrency(cryptocurrencyData, quoteData);

      if (cache == null) {
        var newCache = new CryptocurrencyCache();
        newCache.setCryptocurrencyId(id);
        newCache.setName(cryptocurrency.getName());
        newCache.setSymbol(cryptocurrency.getSymbol());
        newCache.setPrice(cryptocurrency.getPrice());
        newCache.setVolume24Hour(cryptocurrency.getVolume24Hour());
        newCache.setCurrency(request.getConvert());
        newCache.setMarketCap(cryptocurrency.getMarketCap());
        newCache.setCirculatingSupply(cryptocurrency.getCirculatingSupply());
        newCache.setTotalSupply(cryptocurrency.getTotalSupply());
        newCache.setValidUntil(LocalDateTime.now().plusMinutes(CACHE_DURATION_MINUTE));

        cryptocurrencyCacheRepository.save(newCache);
      } else {
        cache.setPrice(cryptocurrency.getPrice());
        cache.setVolume24Hour(cryptocurrency.getVolume24Hour());
        cache.setMarketCap(cryptocurrency.getMarketCap());
        cache.setCirculatingSupply(cryptocurrency.getCirculatingSupply());
        cache.setTotalSupply(cryptocurrency.getTotalSupply());
        cache.setValidUntil(LocalDateTime.now().plusMinutes(CACHE_DURATION_MINUTE));

        cryptocurrencyCacheRepository.save(cache);
      }

      return ResponseEntity
          .ok(CryptoviewerResponse.success(cryptocurrency));
    } catch (JsonProcessingException e) {
      return ResponseEntity
          .internalServerError()
          .body(CryptoviewerResponse.fail("Unable to get cryptocurrency data"));
    }
  }

  private ResponseEntity<CryptoviewerResponse> getErrorResponse(
      RestClientResponseException restClientResponseException) {
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      JsonNode root = objectMapper
          .readTree(restClientResponseException.getResponseBodyAsString());
      String errorMessage = root.get("status").get("error_message").asText();

      return ResponseEntity
          .status(restClientResponseException.getStatusCode())
          .body(CryptoviewerResponse.fail(errorMessage));
    } catch (JsonProcessingException e) {
      return ResponseEntity
          .internalServerError()
          .body(CryptoviewerResponse.fail("Unable to get cryptocurrency data"));
    }
  }
}
