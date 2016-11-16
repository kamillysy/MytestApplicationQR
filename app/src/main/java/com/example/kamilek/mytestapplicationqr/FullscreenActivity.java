package com.example.kamilek.mytestapplicationqr;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kamilek.mytestapplicationqr.pair.QRgenerator;
import com.example.kamilek.mytestapplicationqr.pair.QRreader;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        editText= (EditText) findViewById(R.id.editText);

    }

    public void read(View view) {

        QRreader qr=new QRreader();
        qr.scan(view);
        String text=qr.getText();
        editText.setText(text, TextView.BufferType.EDITABLE);

    }

    public void generate(View view) {
        QRgenerator qr=new QRgenerator();
        ImageView img= (ImageView) findViewById(R.id.imageView);
        String text= String.valueOf(editText.getText());
        qr.setTextToQr(text);
        qr.setBitmapSize(300,300);
        qr.generateQrBitmap();
        img.setImageBitmap(qr.getBitmap());

    }
}
