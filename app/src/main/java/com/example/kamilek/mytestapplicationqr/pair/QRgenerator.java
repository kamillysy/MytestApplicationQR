package com.example.kamilek.mytestapplicationqr.pair;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.encoder.QRCode;
import com.journeyapps.barcodescanner.BarcodeEncoder;

/**
 * Created by kamilek on 06.11.16.
 */

public class QRgenerator  {
    private int bitmapX = 0;
    private int bitmapY = 0;
    private Bitmap bitmap = null;
    private BarcodeFormat format = BarcodeFormat.QR_CODE;
    private String text=null;

    public void setBitmapSize(int width, int height) {
        bitmapX = width;
        bitmapY = height;
    }

    public void setTextToQr(String string){
        text=string;
    }

    public Bitmap generateQrBitmap() {
        BitMatrix bitMatrix = createBitMatrix();
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        if (bitMatrix != null)
            bitmap = barcodeEncoder.createBitmap(bitMatrix);
        return bitmap;
    }

    public boolean isBitmapCreated() {
        return bitmap != null;
    }


    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBarcodeFormat(BarcodeFormat barcodeFormat) {
        format = barcodeFormat;
    }

    private BitMatrix createBitMatrix() {
        BitMatrix bitmatrix = null;
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            bitmatrix = multiFormatWriter.encode(text, format, bitmapX, bitmapY);
        } catch (Exception matrixExc) {
            matrixExc.printStackTrace();
        }
        return bitmatrix;
    }
}
