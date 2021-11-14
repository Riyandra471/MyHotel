package com.example.android.hotelview;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.about){
            startActivity(new Intent(this, AboutActivity.class));
        } else if (item.getItemId() == R.id.setting) {
            startActivity(new Intent(this, SettingActivity.class));
        } else if (item.getItemId() == R.id.action_favorites) {
            displayToast(getString(R.string.action_favorites_message));
            return true;
        } else if (item.getItemId() == R.id.action_contact) {
            displayToast(getString(R.string.action_search_message));
            return true;
        } //finish

        return true;
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
}
