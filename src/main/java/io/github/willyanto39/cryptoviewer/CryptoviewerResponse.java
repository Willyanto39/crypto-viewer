package io.github.willyanto39.cryptoviewer;

class CryptoviewerResponse {
  private Cryptocurrency data;
  private String errorMessage;
  
  private CryptoviewerResponse(Cryptocurrency data, String errorMessage) {
    this.data = data;
    this.errorMessage = errorMessage;
  }
  
  public static CryptoviewerResponse success(Cryptocurrency data) {
    return new CryptoviewerResponse(data, "");
  }
  
  public static CryptoviewerResponse fail(String error) {
    return new CryptoviewerResponse(null, error);
  }
  
  public Cryptocurrency getData() {
    return data;
  }
  
  public String getErrorMessage() {
    return errorMessage;
  }
}
