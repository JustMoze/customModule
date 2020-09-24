package com.reactlibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadImageDrawable extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;
    public LoadImageDrawable(ImageView imageView){
        this.imageView = imageView;
    }
    @Override
    protected Bitmap doInBackground(String... urls){
        Bitmap x;
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(urls[0]).openConnection();
        } catch (IOException e){
            e.printStackTrace();
        }
        InputStream inputStream = null;
        try {
            if (connection == null) throw new AssertionError();
            connection.connect();
            inputStream = connection.getInputStream();
        } catch (IOException e){
            e.printStackTrace();
        }
        x = BitmapFactory.decodeStream(inputStream);
        return x;
    }
    protected void onPostExecute(Bitmap res){
        imageView.setImageBitmap(res);
    }
}
