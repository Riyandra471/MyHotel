package com.example.android.hotelview;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import cz.msebera.android.httpclient.Header;


import com.example.android.hotelview.adapter.HotelAdapter;
import com.example.android.hotelview.model.HotelModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Hotel> mHotelsData;
    ArrayList<HotelModel> hotelModels = new ArrayList<>();
    private HotelAdapter hotelAdapter;
    private HotelModel hotelModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hotelAdapter = new HotelAdapter();
        mRecyclerView = findViewById(R.id.recyclerView);

        getUser();



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager); //vertikal

        //menggabungkan recycleview dgn adapter
        mRecyclerView.setAdapter(hotelAdapter);
        //onClick
        hotelAdapter.onClick(hotel -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(DetailActivity.STRING_HOTEL, hotel);
            startActivity(intent);
        });



    }

    private void getUser() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://run.mocky.io/v3/22eb3e90-1afd-492a-b55c-fc5adcee59ee";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String respone = new String(responseBody);
                try {
                    parseJson(respone);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void parseJson(String respone) throws JSONException {
        JSONObject jsonObject = new JSONObject(respone);
        JSONArray dataArray =jsonObject.getJSONArray("hotel");

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject dataObject =dataArray.getJSONObject(i);
            String nama_hotel =dataObject.getString("nama");
            String alamat = dataObject.getString("alamat");
            String no_telp =dataObject.getString("no_telp");
            String gambar_url = dataObject.getString("gambar_url");

            hotelModel = new HotelModel(nama_hotel,alamat,no_telp,gambar_url);
            hotelModels.add(hotelModel);
        }

        hotelAdapter.setData(hotelModels);


    }


//    private void initializeData() {
//        String[] hotelsList = getResources()
//                .getStringArray(R.array.hotels_titles);
//        String[] hotelsInfo = getResources()
//                .getStringArray(R.array.hotels_info);
//        TypedArray hotelsImageResources =
//                getResources().obtainTypedArray(R.array.hotels_images);
//
//        mHotelsData.clear();
//
//        for (int i = 0; i < hotelsList.length; i++) {
//            mHotelsData.add(new Hotel(hotelsList[i], hotelsInfo[i], hotelsImageResources.getResourceId(i, 0)));
//        }
//        hotelsImageResources.recycle();
//
//        mAdapter.notifyDataSetChanged();
//    }

//    public void resetSports(View view) {
//        initializeData();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

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
        } else if (item.getItemId() == R.id.action_search) {
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
