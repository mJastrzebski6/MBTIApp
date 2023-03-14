package com.example.mbtifriends;

import android.content.Context;
import android.graphics.Color;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;

public class Helpers {
    static int getColorByType(String type){
        if (Constants.Analysts.contains(type)) return Color.parseColor(Constants.AnalystsColor);
        else if (Constants.Diplomats.contains(type)) return Color.parseColor(Constants.DiplomatsColor);
        else if (Constants.Sentinels.contains(type)) return Color.parseColor(Constants.SentinelsColor);
        else if (Constants.Explorers.contains(type)) return Color.parseColor(Constants.ExplorersColor);
        else return Color.parseColor("#ffffff");
    }

    static TextView getTitleTV(Context context, String title, Integer color){
        TextView tv = new TextView(context);
        tv.setText(title);
        tv.setTextSize(30);
        tv.setTextColor(color);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,60,0,30);

        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }

    //@RequiresApi(api = Build.VERSION_CODES.O)
    static TextView getTextTV(Context context, String text){
        TextView tv = new TextView(context);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            tv.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
//        }
        tv.setText(text);
        tv.setTextSize(20);
        return tv;
    }

    static TextView getListTV(Context context, ArrayList<String> object, String bulletChar){
        TextView tv = new TextView(context);
        //tv.setLines(object.size());
        String strings = "";
        for(String str : object){
            strings += bulletChar + " " + str + "\n";
        }
        tv.setText(strings);
        tv.setTextSize(20);
        return tv;
    }
    static TextView getPopulationTV(Context context, ArrayList<Integer> object){
        TextView tv = new TextView(context);
        tv.setLines(3);
        String strings = "Kobiety: "+ object.get(1) + "%\n";
        strings +="Mężczyźni: "+ object.get(0) + "%\n";
        strings +="Wszyscy: "+ object.get(2) + "%\n";




        tv.setText(strings);
        tv.setTextSize(20);
        return tv;
    }
}
