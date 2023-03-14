package com.example.mbtifriends;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> logos;
    LayoutInflater inflter;
    public CustomAdapter(Context applicationContext, ArrayList<String> logos) {
        this.context = applicationContext;
        this.logos = logos;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return logos.size();
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_gridview, null); // inflate the layout
        TextView type_tv = view.findViewById(R.id.type_tv);
        type_tv.setText(logos.get(i));

        type_tv.setBackgroundColor(Helpers.getColorByType(logos.get(i)));

        int width = context.getResources().getDisplayMetrics().widthPixels;

        int chuj = (width-30)/4;

        type_tv.setHeight(chuj);

        return view;
    }
}