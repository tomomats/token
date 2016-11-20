package com.lightningstudio.qrcodescanner;

import android.util.JsonReader;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class RequestHandler {

    public static Account account;

    private static Account readAccount(JsonReader reader) throws  IOException {

        Account account = new Account();

        reader.beginObject();

        while (reader.hasNext()) {
            switch (reader.nextName()) {
                case "accountId":
                    account.setAccountId(reader.nextString());
                    break;
                case "password":
                    account.setPassword(reader.nextString());
                    break;
                case "name":
                    account.setName(reader.nextString());
                    break;
                case "balance":
                    account.setBalance(new BigDecimal(reader.nextString()));
                    break;
                case "tokens":
                    account.setTokens(readTokens(reader));
                    break;
                default:
                    reader.skipValue();
            }
        }
        reader.endObject();
        return account;
    }

    private static List<Token> readTokens(JsonReader reader) throws IOException {
        List<Token> messages = new ArrayList<>();

        reader.beginObject();
        while (reader.hasNext()) {
            messages.add(readToken(reader));
        }
        reader.endObject();
        return messages;
    }

    private static Token readToken(JsonReader reader) throws IOException {

        Token token = new Token();

        reader.beginObject();
        while (reader.hasNext()) {

            switch (reader.nextName()) {
                case "tokenId":
                    token.setTokenId(reader.nextString());
                    break;
                case "amount":
                    token.setAmount(new BigDecimal(reader.nextString()));
                    break;
                default:
                    reader.skipValue();
            }
        }
        reader.endObject();
        return token;
    }

    private static BufferedReader requestPost(Params params, String path) throws Exception {

        String url = "http://www.kwanii.com/" + path;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(params.toString());
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Params: " + params.toString());
        System.out.println("Response Code : " + responseCode);


        return new BufferedReader(new InputStreamReader(con.getInputStream()));

    }

    public static Account getAccount(String accountId, String password) throws Exception {

        Params params = new Params.Builder()
            .setAccountId(accountId).setPassword(password).build();

        JsonReader reader = new JsonReader(requestPost(params, "account"));

        try {
            Account account = null;
            reader.beginObject();

            while (reader.hasNext()) {
                if (reader.nextName().equals("account")) {
                    account = readAccount(reader);
                }
                else
                    reader.skipValue();
            }

            reader.endObject();
            return account;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        } finally {

            reader.close();
        }
    }

    public static Token createToken(String accountId, String password, BigDecimal amount) {
        try {

            Params params = new Params.Builder()
                .setAccountId(accountId).setPassword(password)
                .setAmount(amount).build();

            JsonReader reader = new JsonReader(requestPost(params, "createToken"));

            reader.beginObject();

            while (reader.hasNext()) {
                if (reader.nextName().equals("token"))
                    return readToken(reader);
                else
                    reader.skipValue();

            }
            reader.endObject();
            return null;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean deleteToken(String accountId, String password, String tokenId) {
        try {
            Params params = new Params.Builder()
                .setAccountId(accountId).setPassword(password)
                .setTokenId(tokenId).build();

            JsonReader reader = new JsonReader(requestPost(params, "deleteToken"));

            reader.beginObject();

            while (reader.hasNext()) {
                if (reader.nextName().equals("status"))
                    return reader.nextString().equals("ACCEPT");
                else
                    reader.skipValue();

            }
            reader.endObject();
            return false;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean tokenTransaction(String senderId, String receiverId, String password, String tokenId) {
        try {

            Params params = new Params.Builder()
                .setSenderId(senderId).setReceiverId(receiverId)
                .setPassword(password).setTokenId(tokenId).build();

            JsonReader reader = new JsonReader(requestPost(params, "tokenTransaction"));

            reader.beginObject();
            while (reader.hasNext()) {
                if (reader.nextName().equals("status"))
                    return reader.nextString().equals("ACCEPT");
                else
                    reader.skipValue();

            }
            reader.endObject();
            return false;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


}