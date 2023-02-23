package io.github.willyanto39.cryptoviewer;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class CryptoviewerController {
  private final CryptoviewerService cryptoviewerService;
  
  public CryptoviewerController(CryptoviewerService cryptoviewerService) {
    this.cryptoviewerService = cryptoviewerService;
  }
  
  @GetMapping("/")
  public String index() {
    return "crypto-viewer.html";
  }
  
  @GetMapping("/api/v1/cryptoviewer")
  @ResponseBody
  public ResponseEntity<CryptoviewerResponse> getCryptocurrencyData(CryptoviewerRequest request) {
    return cryptoviewerService.getCryptocurrencyData(request);
  }
}
