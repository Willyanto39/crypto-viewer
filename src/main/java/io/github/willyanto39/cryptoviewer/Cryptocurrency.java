package io.github.willyanto39.cryptoviewer;

import com.fasterxml.jackson.databind.JsonNode;

class Cryptocurrency {
  private String name;
  private String symbol;
  private double price;
  private double volume24Hour;
  private double circulatingSupply;
  private double totalSupply;
  private double marketCap;
  
  public Cryptocurrency(JsonNode cryptocurrencyData, JsonNode quoteData) {
    name = cryptocurrencyData.get("name").asText();
    symbol = cryptocurrencyData.get("symbol").asText();
    circulatingSupply = cryptocurrencyData.get("circulating_supply").asDouble();
    totalSupply = cryptocurrencyData.get("total_supply").asDouble();
    
    price = quoteData.get("price").asDouble();
    volume24Hour = quoteData.get("volume_24h").asDouble();
    marketCap = quoteData.get("market_cap").asDouble();
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getVolume24Hour() {
    return volume24Hour;
  }

  public void setVolume24Hour(double volume24Hour) {
    this.volume24Hour = volume24Hour;
  }

  public double getCirculatingSupply() {
    return circulatingSupply;
  }

  public void setCirculatingSupply(double circulatingSupply) {
    this.circulatingSupply = circulatingSupply;
  }

  public double getTotalSupply() {
    return totalSupply;
  }

  public void setTotalSupply(double totalSupply) {
    this.totalSupply = totalSupply;
  }

  public double getMarketCap() {
    return marketCap;
  }

  public void setMarketCap(double marketCap) {
    this.marketCap = marketCap;
  }
}
