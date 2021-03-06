package com.example.trainingi.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class SplashScrn extends Activity{

    private Thread mSplash;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SplashScrn sPlash = this;
        setContentView(R.layout.splash);

        mSplash =  new Thread(){
            @Override
            public void run(){
                try {
                    synchronized(this){
                        wait(5000);
                    }
                }
                catch(InterruptedException ex){
                }

                finish();

                Intent intent = new Intent();
                intent.setClass(SplashScrn.this, MainActivity.class);
                startActivity(intent);
            }
        };

        mSplash.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent evt)
    {
        if(evt.getAction() == MotionEvent.ACTION_DOWN)
        {
            synchronized(mSplash){
                mSplash.notifyAll();
            }
        }
        return true;
    }
}
