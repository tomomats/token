package com.lightningstudio.qrcodescanner;

import java.math.BigDecimal;

public class Token {

    private String tokenId;
    private BigDecimal amount;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
