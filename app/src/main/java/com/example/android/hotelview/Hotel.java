package com.example.android.hotelview;

class Hotel {

    private String title;
    private String info;
    private final int imageResource;

    Hotel(String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
    }

    String getTitle() {
        return title;
    }

    String getInfo() {
        return info;
    }

    public int getImageResource() {
        return imageResource;
    }
}
