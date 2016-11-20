package com.lightningstudio.qrcodescanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class MainPageTokenFriend extends ActionBarActivity {

    Button create_btn;
    Button scanCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_token_friend);


        scanCode = (Button) findViewById(R.id.scan_button);

        final Context context = this;

        scanCode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                startActivity(new Intent(context, QRScannerActivity.class));


            }
        });


    }




}
