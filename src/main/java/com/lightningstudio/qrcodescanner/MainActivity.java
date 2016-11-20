package com.lightningstudio.qrcodescanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);

        Button login = (Button)findViewById(R.id.login_btn);

        final Context context = this;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id  = ((EditText)findViewById(R.id.accountid)).getText().toString();
                String pass = ((EditText)findViewById(R.id.password)).getText().toString();

                try {
                    Account account = RequestHandler.getAccount(id, pass);

                    if (account != null) {
                        Toast toast = Toast.makeText(context, "Login succeeded", Toast.LENGTH_LONG);
                        toast.show();

                        RequestHandler.account = account;

                        System.out.println(account.getAccountId());

                        Intent intent = new Intent(context, TokenPage.class);
                        startActivity(intent);

                    } else {
                        Toast toast = Toast.makeText(context, "Login failed", Toast.LENGTH_LONG);
                        toast.show();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

}