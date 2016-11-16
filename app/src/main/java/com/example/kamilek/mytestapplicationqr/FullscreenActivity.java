package com.example.kamilek.mytestapplicationqr;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private QRreader qRreader;
    private QRgenerator qRgenerator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        editText= (EditText) findViewById(R.id.editText);
        qRreader =new QRreader();
        qRgenerator=new QRgenerator();
    }

    public void read(View view) {

        qRreader.scan(view);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        String text=qRreader.getText();
        builder.setTitle("Readed");
        builder.setMessage(text);
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
        editText.setText(text, TextView.BufferType.EDITABLE);
    }

    public void generate(View view) {

        ImageView img= (ImageView) findViewById(R.id.imageView);
        String text= String.valueOf(editText.getText());
        qRgenerator.setTextToQr(text);
        qRgenerator.setBitmapSize(300,300);
        qRgenerator.generateQrBitmap();
        img.setImageBitmap(qRgenerator.getBitmap());

    }
}
