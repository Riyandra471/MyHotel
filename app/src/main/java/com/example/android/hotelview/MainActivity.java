package com.example.android.hotelview;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Hotel> mHotelsData;
    private HotelsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        mHotelsData = new ArrayList<>();

        mAdapter = new HotelsAdapter(this, mHotelsData);
        mRecyclerView.setAdapter(mAdapter);

        initializeData();

    }

    private void initializeData() {
        String[] hotelsList = getResources()
                .getStringArray(R.array.hotels_titles);
        String[] hotelsInfo = getResources()
                .getStringArray(R.array.hotels_info);
        TypedArray hotelsImageResources =
                getResources().obtainTypedArray(R.array.hotels_images);

        mHotelsData.clear();

        for (int i = 0; i < hotelsList.length; i++) {
            mHotelsData.add(new Hotel(hotelsList[i], hotelsInfo[i], hotelsImageResources.getResourceId(i, 0)));
        }
        hotelsImageResources.recycle();

        mAdapter.notifyDataSetChanged();
    }

    public void resetSports(View view) {
        initializeData();
    }
}
