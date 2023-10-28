package io.github.willyanto39.cryptoviewer;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "cryptocurrency_cache")
@Entity
class CryptocurrencyCache {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "cryptocurrency_id")
  private String cryptocurrencyId;

  private String name;

  private String symbol;

  private double marketCap;

  @Column(name = "volume_24h")
  private double volume24Hour;

  @Column(name = "circulating_supply")
  private double circulatingSupply;

  @Column(name = "total_supply")
  private double totalSupply;

  private String currency;

  private double price;

  @Column(name = "valid_until")
  private LocalDateTime validUntil;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCryptocurrencyId() {
    return cryptocurrencyId;
  }

  public void setCryptocurrencyId(String cryptocurrencyId) {
    this.cryptocurrencyId = cryptocurrencyId;
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

  public double getMarketCap() {
    return marketCap;
  }

  public void setMarketCap(double marketCap) {
    this.marketCap = marketCap;
  }

  public double getVolume24Hour() {
    return volume24Hour;
  }

  public void setVolume24Hour(double volume24Hour) {
    this.volume24Hour = volume24Hour;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
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

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public LocalDateTime getValidUntil() {
    return validUntil;
  }

  public void setValidUntil(LocalDateTime validUntil) {
    this.validUntil = validUntil;
  }

}
