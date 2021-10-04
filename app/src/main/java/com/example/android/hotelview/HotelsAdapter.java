package com.example.android.hotelview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.ViewHolder>  {

    private ArrayList<Hotel> mHotelsData;
    private Context mContext;

    HotelsAdapter(Context context, ArrayList<Hotel> hotelsData) {
        this.mHotelsData = hotelsData;
        this.mContext = context;
    }

    @Override
    public HotelsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(HotelsAdapter.ViewHolder holder,
                                 int position) {
        Hotel currentHotel = mHotelsData.get(position);

        holder.bindTo(currentHotel);
    }

    @Override
    public int getItemCount() {
        return mHotelsData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mHotelsImage;

        ViewHolder(View itemView) {
            super(itemView);

            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mHotelsImage = itemView.findViewById(R.id.hotelsImage);

            itemView.setOnClickListener(this);
        }

        void bindTo(Hotel currentHotel){
            mTitleText.setText(currentHotel.getTitle());
            mInfoText.setText(currentHotel.getInfo());
            Glide.with(mContext).load(currentHotel.getImageResource()).into(mHotelsImage);

        }

        @Override
        public void onClick(View view) {
            Hotel currentHotel = mHotelsData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, DetailActivity.class);
            detailIntent.putExtra("title", currentHotel.getTitle());
            detailIntent.putExtra("image_resource",
                    currentHotel.getImageResource());
            mContext.startActivity(detailIntent);
        }
    }
}
