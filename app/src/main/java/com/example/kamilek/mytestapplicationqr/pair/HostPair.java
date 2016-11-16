package com.example.kamilek.mytestapplicationqr.pair;

/**
 * Created by kamilek on 05.11.16.
 */

public class HostPair extends PairManager {
    private QRgenerator qr =new QRgenerator();
    private String text;
    @Override
    public void pair(){
        qr.setTextToQr(text);
        qr.generateQrBitmap();
        if(qr.isBitmapCreated()){
            qr.getBitmap();
        }
    }
    @Override
    public boolean isPaired(){
        return false;
    }
}
