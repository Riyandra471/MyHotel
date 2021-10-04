package com.example.android.hotelview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView hotelsTitle = findViewById(R.id.titleDetail);
        ImageView hotelsImage = findViewById(R.id.hotelsImageDetail);
        hotelsTitle.setText(getIntent().getStringExtra("title"));
        Glide.with(this).load(getIntent().getIntExtra("image_resource",0)).into(hotelsImage);
    }
}