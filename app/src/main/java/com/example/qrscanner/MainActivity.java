package com.example.qrscanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    Button scanbtn;
    TextView scantext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }

        scantext = (TextView) findViewById(R.id.scantext);
        scanbtn = (Button) findViewById(R.id.scanbtn);

        scanbtn.setOnClickListener(this);


        scantext.setMovementMethod(LinkMovementMethod.getInstance());

    }



            @Override
            public void onClick(View v) {
                IntentIntegrator integrator=new IntentIntegrator(this);
                integrator.setPrompt("SCANNING...");
               // integrator.setPrompt("for flash light press volume-up");
                integrator.setOrientationLocked(false);
                integrator.initiateScan();
            }






    public void galalry(View view) throws ChecksumException, NotFoundException, FormatException {
        Intent pickIntent = new Intent(Intent.ACTION_PICK);
        pickIntent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

        startActivityForResult(pickIntent, 111);

    }






    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        IntentResult result=IntentIntegrator.parseActivityResult(requestCode, resultCode, data);



        if(result != null) {
            if (result.getContents() == null) {
                Toast.makeText(getBaseContext(), "cancelled", Toast.LENGTH_LONG).show();
            } else {
                scantext.setText(result.getContents());
            }
        }
        else {
            super.onActivityResult(requestCode,resultCode,data);
        }

    }

}


