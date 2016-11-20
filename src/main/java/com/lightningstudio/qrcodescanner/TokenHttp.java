package com.lightningstudio.qrcodescanner;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/19/16.
 */
public class TokenHttp extends AsyncTask<String, Void, String> {

    public String getAccount(String accountId, String password) {


        return null;
    }

    public String createToken(String accountId, String password, BigDecimal amount) {
        return null;
    }

    public String removeToken(String accountId, String password, String tokenId) {
        return null;
    }

    public String tokenTransaction(String senderId, String receiverId, String password, String tokenId) {
        return null;
    }

    @Override
    protected String doInBackground(String[] params) {

        String server_response = "";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://www.kwanii.com/account");

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("accountId", "00000001"));
        nameValuePair.add(new BasicNameValuePair("password", "tomopw"));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            HttpResponse response = httpClient.execute(httpPost);
            server_response = EntityUtils.toString(response.getEntity());
            System.out.println(server_response);
        }catch(Exception e) {
            e.printStackTrace();
        }

        return server_response;
    }

    @Override
    protected void onPostExecute(String message) {
        //process message
    }

//    public static void makeHttpRequest() {
//
//
//        String jsonResponse = "";
//        HttpURLConnection urlConnection = null;
//        InputStream inputStream = null;
//        try {
//            urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestMethod("GET");
//            urlConnection.connect();
//            inputStream = urlConnection.getInputStream();
//            jsonResponse = readFromStream(inputStream);
//        } catch (IOException e) {
//
//            // comeback to add message
//        } finally {
//
//
//            if (urlConnection != null) {
//
//                urlConnection.disconnect();
//            }
//
//            if (inputStream != null) {
//                inputStream.close();
//            }
//        }
//        return jsonResponse;


//    }

}