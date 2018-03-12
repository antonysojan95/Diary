package com.voyager.diary;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by User on 08-Mar-18.
 */

public class SplashScreen extends AppCompatActivity {

    private int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, SignInPage.class);
                startActivity(intent);
                finish();

            }
        },SPLASH_DISPLAY_LENGTH);

    }
}