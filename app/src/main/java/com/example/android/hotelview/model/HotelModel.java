package com.example.android.hotelview.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.android.hotelview.adapter.HotelAdapter;

public class HotelModel implements Parcelable {
    private String nama;
    private String alamat;
    private String no_telp;
    private String gambar_url;

    public HotelModel(String nama, String alamat, String no_telp, String gambar_url) {
        this.nama = nama;
        this.alamat = alamat;
        this.no_telp = no_telp;
        this.gambar_url = gambar_url;
    }

    public HotelModel() {
    }

    protected HotelModel(Parcel in) {
        nama = in.readString();
        alamat = in.readString();
        no_telp = in.readString();
        gambar_url = in.readString();
    }

    public static final Creator<HotelModel> CREATOR = new Creator<HotelModel>() {
        @Override
        public HotelModel createFromParcel(Parcel in) {
            return new HotelModel(in);
        }

        @Override
        public HotelModel[] newArray(int size) {
            return new HotelModel[size];
        }
    };

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getGambar_url() {
        return gambar_url;
    }

    public void setGambar_url(String gambar_url) {
        this.gambar_url = gambar_url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nama);
        parcel.writeString(alamat);
        parcel.writeString(no_telp);
        parcel.writeString(gambar_url);
    }
}
