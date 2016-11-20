package com.lightningstudio.qrcodescanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.math.BigDecimal;

public class TokenPage extends ActionBarActivity {

    Button ScanTokenFreind;
    Button menu;
    Button createQrCode;
    Button buttonTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_page);

        ScanTokenFreind = (Button) findViewById(R.id.Scan_Token_Friend);
        menu = (Button) findViewById(R.id.menu_button);
        createQrCode = (Button) findViewById(R.id.createQR_button);


        buttonTest = (Button) findViewById(R.id.button_test);





        final Context context = this;
        ScanTokenFreind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QRScannerActivity.class);
                startActivity(intent);
            }
        });

        createQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Generator.class);
                startActivity(intent);
            }
        });

       menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, tokenSettings.class);
                startActivity(intent);
            }
        });



        // connect to server (make qr with tokens)

        ImageView fiveDollarToken = (ImageView)findViewById(R.id.token_5);
        fiveDollarToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Account account = RequestHandler.account;

                Token token = RequestHandler.createToken(account.getAccountId(), account.getPassword(), new BigDecimal(5));

                if (token != null) {
                    Toast toast = Toast.makeText(context, "Created $5 token", Toast.LENGTH_LONG);
                    toast.show();

                    try {
                        RequestHandler.account = RequestHandler.getAccount(account.getAccountId(), account.getPassword());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    Intent intent = new Intent(context, TokenPage.class);
                    startActivity(intent);

                } else {
                    Toast toast = Toast.makeText(context, "Failed to create a token", Toast.LENGTH_LONG);
                    toast.show();

                }

            }
        });

        ImageView tenDollarToken = (ImageView)findViewById(R.id.token_10);
        tenDollarToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account account = RequestHandler.account;

                Token token = RequestHandler.createToken(account.getAccountId(), account.getPassword(), new BigDecimal(10));

                if (token != null) {
                    Toast toast = Toast.makeText(context, "Created $10 token", Toast.LENGTH_LONG);
                    toast.show();

                    try {
                        RequestHandler.account = RequestHandler.getAccount(account.getAccountId(), account.getPassword());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    Intent intent = new Intent(context, TokenPage.class);
                    startActivity(intent);

                } else {
                    Toast toast = Toast.makeText(context, "Failed to create a token", Toast.LENGTH_LONG);
                    toast.show();

                }

            }
        });


    }



}
