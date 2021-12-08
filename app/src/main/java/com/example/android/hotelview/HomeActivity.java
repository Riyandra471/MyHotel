package com.example.android.hotelview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity {
    //durasi splash screenn
    private int durasi_load=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //lokasi splash screen berada

        new Handler().postDelayed(new Runable () {
            @Override
            public void run() {
                Intent splash = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(splash);
                finish();
            }
        },durasi_load);
    }
}
