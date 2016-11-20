package com.lightningstudio.qrcodescanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRScannerActivity extends ActionBarActivity {

    public static final String DEBUGTAG = "TR";

    private Button scan_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        scan_button = (Button) findViewById(R.id.scan_button);
        final Activity activity = this;
        scan_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("scan in progress");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }

        });
        }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //String amount = "";

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null){
            if(result.getContents() == null){
                Toast.makeText(this,"Scan is no longer in process", Toast.LENGTH_LONG).show();

            }
            else{

                Toast.makeText(this,result.getContents(), Toast.LENGTH_LONG).show();

                String tokenDataString = result.getContents();
                String[] tokenDataArray = tokenDataString.split(",");

                String tokenDataName = tokenDataArray[0];
                String tokenDataAmount = tokenDataArray[1];

                Log.d(DEBUGTAG, tokenDataName);
                Log.d(DEBUGTAG, tokenDataAmount);

            }


        }
        else{

            super.onActivityResult(requestCode, resultCode, data);

        }




    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_qrscanner, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
