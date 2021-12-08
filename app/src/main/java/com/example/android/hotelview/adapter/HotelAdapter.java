package com.example.android.hotelview.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.hotelview.R;
import com.example.android.hotelview.model.HotelModel;

import java.util.ArrayList;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {

    private HotelAdapter.ItemClickListener itemClickListener;
    Context context;
    private ArrayList<HotelModel> hotelModel = new ArrayList<>();

    public interface ItemClickListener {
        void onClick(HotelModel HotelModel);
    }

    public void onClick(HotelAdapter.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public HotelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HotelAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bindItem(viewHolder.itemView.getContext(), hotelModel.get(i), itemClickListener);


    }

    @Override
    public int getItemCount() {
        return hotelModel.size();
    }

    public void setData(ArrayList<HotelModel> hotelModel){
        this.hotelModel = hotelModel;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            view = itemView;

        }

        public void bindItem(Context ctx, HotelModel hotelModel, ItemClickListener itemClickListener) {

           TextView nama_hotel =view.findViewById(R.id.title);
            TextView alamat_hotel =view.findViewById(R.id.subTitle);
            TextView no_telp =view.findViewById(R.id.newsTitle);
            ImageView hotelsImage =view.findViewById(R.id.hotelsImage);


            nama_hotel.setText(hotelModel.getNama());
            alamat_hotel.setText(hotelModel.getAlamat());
            no_telp.setText(hotelModel.getNo_telp());

            if (hotelModel != null){
                Glide.with(ctx).load(hotelModel.getGambar_url()).into(hotelsImage);
            }

            if (itemClickListener != null){
                itemView.setOnClickListener(view -> {
                    itemClickListener.onClick(hotelModel);
                });
            }
        }

    }
}
