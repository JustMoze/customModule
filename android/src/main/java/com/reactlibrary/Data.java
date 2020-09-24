package com.reactlibrary;

public class Data {
    private double rating;
    private String imageUrl;
    private String _id;
    private String genre;

    public Data(double rating, String imageUrl, String _id, String genre){
        this.rating = rating;
        this.imageUrl = imageUrl;
        this._id = _id;
        this.genre = genre;
    }
    public double getRating(){
        return rating;
    }
    public String getImageUrl(){
        return imageUrl;
    }
    public String get_id(){
        return _id;
    }
    public String getGenre(){
        return genre;
    }
    public void setRating(double rating){
        this.rating = rating;
    }
    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }
    public void set_id(String _id){
        this._id = _id;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
}
