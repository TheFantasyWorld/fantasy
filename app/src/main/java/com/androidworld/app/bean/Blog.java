package com.androidworld.app.bean;

public class Blog {

    public String uri;
    public String title;
    public int imageUri;
    public String date;

    public Blog(String uri, String title, int imageUri, String date) {
        this.uri = uri;
        this.title = title;
        this.imageUri = imageUri;
        this.date = date;
    }
}
