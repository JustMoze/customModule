package com.reactlibrary;

import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.security.AccessController.getContext;


public class SealsNativeModuleManager extends ViewGroupManager<RecyclerView> {

    public static final String REACT_CLASS = "SealsNativeModule";
    private ArrayList<Data> mData = new ArrayList<Data>();
    private ThemedReactContext reactContext;
    private RecyclerView recyclerView;
    private DataCardRecyclerAdapter dataAdapter;

    @Override
    public String getName() {
        return REACT_CLASS;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public RecyclerView createViewInstance(ThemedReactContext c) {
        reactContext = c;
        recyclerView = new RecyclerView(c);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);
        LinearLayoutManager layout = new LinearLayoutManager(c, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layout);

        dataAdapter = new DataCardRecyclerAdapter(mData, c);
        recyclerView.setAdapter(dataAdapter);
        setListeners();

        return recyclerView;
    }
    @ReactProp(name = "data")
    public void setData(RecyclerView view, ReadableArray data){
        for (int i = 0; i < data.size(); i++){
            String imageUrl = data.getMap(i).getString("coverImage");
            String genre = data.getMap(i).getString("genre");
            String _id = data.getMap(i).getString("_id");
            double rating = data.getMap(i).getDouble("rating");

            Data movieData = new Data(rating, imageUrl, _id, genre);
            mData.add(i, movieData);
        }
        mData.subList(data.size(), mData.size()).clear();

    }
    public Map getExportedCustomBubblingEventTypeConstants(){
        return MapBuilder.builder()
                .put("onPress", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onClick"))).build();
    }
    public void onReceiveNativeEvent(int position, ThemedReactContext context){
        WritableMap event = Arguments.createMap();
        event.putString("id", mData.get(position).get_id());
        context.getJSModule(RCTEventEmitter.class).receiveEvent(
                recyclerView.getId(),
                "onPress",
                event);
    }
    private void setListeners(){
        dataAdapter.setOnItemClickListener(new DataCardRecyclerAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                onReceiveNativeEvent(position, reactContext);
            }
        });
    }
}
