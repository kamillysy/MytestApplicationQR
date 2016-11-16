package com.example.kamilek.mytestapplicationqr.pair;

import android.app.Activity;
import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import javax.xml.transform.Result;

/**
 * Created by kamilek on 06.11.16.
 */

public abstract class QRcode extends Activity {

    protected String text = null;

    public void setText(String textToCode) {
        text = textToCode;
    }

    public String getText() {
        return text;
    }

}
