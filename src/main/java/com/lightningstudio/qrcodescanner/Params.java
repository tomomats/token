package com.lightningstudio.qrcodescanner;

import java.math.BigDecimal;

public class Params {

    final private String status;
    final private String accountId;
    final private String password;
    final private String senderId;
    final private String receiverId;
    final private String tokenId;
    final private BigDecimal amount;

    private Params(Builder builder) {
        status = builder.status;
        accountId = builder.accountId;
        password = builder.password;
        senderId = builder.senderId;
        receiverId = builder.receiverId;
        tokenId = builder.tokenId;
        amount = builder.amount;

    }

    public String getStatus() {
        return status;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getPassword() {
        return password;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {

        return new StringBuilder()
            .append(accountId == null ? "" : "&accountId=" + accountId)
            .append(password == null ? "" : "&password=" + password)
            .append(senderId == null ? "" : "&senderId=" + senderId)
            .append(receiverId == null ? "" : "&receiverId=" + receiverId)
            .append(tokenId == null ? "" : "&tokenId=" + tokenId)
            .append(amount == null ? "" : "&amount=" + amount)
            .toString();

    }

    public static class Builder {

        private String status;
        private String accountId;
        private String password;
        private String senderId;
        private String receiverId;
        private String tokenId;
        private BigDecimal amount;

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setAccountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setSenderId(String senderId) {
            this.senderId = senderId;
            return this;
        }

        public Builder setReceiverId(String receiverId) {
            this.receiverId = receiverId;
            return this;
        }

        public Builder setTokenId(String tokenId) {
            this.tokenId = tokenId;
            return this;
        }

        public Builder setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Params build() {
            return new Params(this);
        }
    }
}
