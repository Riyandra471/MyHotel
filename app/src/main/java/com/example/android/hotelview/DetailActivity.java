package com.example.android.hotelview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.hotelview.model.HotelModel;

public class DetailActivity extends AppCompatActivity {

    public static final String STRING_HOTEL = "string_hotel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView hotelsTitle = findViewById(R.id.titleDetail);
        TextView hotelSubTittle = findViewById(R.id.subTitleDetail);
        TextView noTelp = findViewById(R.id.newsTitleDetail);
        ImageView hotelsImage = findViewById(R.id.hotelsImageDetail);
//        Glide.with(this).load(getIntent().getIntExtra("image_resource",0)).into(hotelsImage);

        HotelModel HotelModel = null;
        if (getIntent() != null){
            HotelModel = getIntent().getParcelableExtra(STRING_HOTEL);
        }

        if (HotelModel != null){
            hotelsTitle.setText(HotelModel.getNama());
            hotelSubTittle.setText(HotelModel.getAlamat());
            noTelp.setText(HotelModel.getNo_telp());
            Glide.with(this).load(HotelModel.getGambar_url()).into(hotelsImage);

        }
    }
}