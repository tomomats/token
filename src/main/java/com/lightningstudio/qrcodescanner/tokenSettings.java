package com.lightningstudio.qrcodescanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class tokenSettings extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_settings);

        Button accountBtn = (Button)findViewById(R.id.account);

        final Context context = this;
        accountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Account.class);
                startActivity(intent);
            }
        });

        Button balanceBtn = (Button)findViewById(R.id.balance);
        balanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Balance.class);
                startActivity(intent);
            }
        });

        Button createTokenBalance = (Button)findViewById(R.id.create);
        createTokenBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CreateTokenFriend.class);
                startActivity(intent);
            }
        });

        Button deleteToken = (Button)findViewById(R.id.delete);
        deleteToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DeleteToken.class);
                startActivity(intent);
            }
        });

        Button logoutBtn = (Button)findViewById(R.id.logout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}