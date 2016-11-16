package com.example.kamilek.mytestapplicationqr.pair;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.kamilek.mytestapplicationqr.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by kamilek on 06.11.16.
 */

public class QRreader extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView scannerView = null;
    private String text=null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fullscreen);
//    }

    public void scan(View view) {
//        Context context=view.getContext();

        scannerView = new ZXingScannerView(view.getContext());
        setContentView(scannerView);
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        text = result.toString();
    }

    public String getText() {
        return text;
    }

}
