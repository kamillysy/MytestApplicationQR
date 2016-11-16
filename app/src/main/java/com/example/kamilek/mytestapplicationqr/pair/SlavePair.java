package com.example.kamilek.mytestapplicationqr.pair;

import android.view.View;

import com.google.zxing.Result;

/**
 * Created by kamilek on 05.11.16.
 */

public class SlavePair extends PairManager {
    private View view_;
    private String text;
   SlavePair(View view){
       view_=view;
       qr=new QRreader();
   }
    @Override
    public void pair(){
        qr.scan(view_);
        text=qr.getText();
    }
    @Override
    public boolean isPaired(){
        return text==null;
    }

}
