package com.lightningstudio.qrcodescanner;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Generator extends ActionBarActivity {

    EditText text;
    Button gen_btn;
    ImageView image;
    String retrievedCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);

        text = (EditText) findViewById(R.id.text);
        gen_btn = (Button) findViewById(R.id.gen_btn);
        image = (ImageView) findViewById(R.id.image);


        gen_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                //RequestHandler.createToken();

                retrievedCode = text.getText().toString().trim();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try{
                    BitMatrix bitMatrix = multiFormatWriter.encode(retrievedCode, BarcodeFormat.QR_CODE, 200, 200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitMap = barcodeEncoder.createBitmap(bitMatrix);
                    image.setImageBitmap(bitMap);
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "QR Bar code Generated", Toast.LENGTH_LONG);
                    toast.show();
                }catch(WriterException e){
                    e.printStackTrace();
                }



            }
        });
    }

}
