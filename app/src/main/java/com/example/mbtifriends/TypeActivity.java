package com.example.mbtifriends;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeActivity extends AppCompatActivity {

    ActionBar actionBar;
    LinearLayout ll;
    int color;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        String type = getIntent().getStringExtra("type");

        ll = this.findViewById(R.id.ll);
        actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.abs_layout);

        read(type);

    }

    public void read(String type) {
        String jsonFileString = "[" + loadJSONFromAsset(type) + "]";
        Gson gson = new Gson();

        Type listUserType = new TypeToken<List<TypeClass>>() { }.getType();
        List<TypeClass> users = gson.fromJson(jsonFileString, listUserType);
        TypeClass tc = users.get(0);

        Class<?> c = tc.getClass();
        Field[] fields = c.getDeclaredFields();
        Map<String, Object> temp = new HashMap<String, Object>();

        for( Field field : fields ){
            try {
                temp.put(field.getName().toString(), field.get(tc));
            } catch (IllegalArgumentException e1) {
            } catch (IllegalAccessException e1) {
            }
        }



        color = Helpers.getColorByType(String.valueOf(temp.get("type")));

        ll.addView(Helpers.getTitleTV(this, "Główny opis \uD83D\uDCCB", color));
        ll.addView(Helpers.getTextTV(this, String.valueOf(temp.get("description"))));

        ll.addView(Helpers.getTitleTV(this, "Przyjaźnie \uD83E\uDDD1\u200D\uD83E\uDD1D\u200D\uD83E\uDDD1", color));
        ll.addView(Helpers.getTextTV(this, String.valueOf(temp.get("friendships"))));

        ll.addView(Helpers.getTitleTV(this, "Relacje romantyczne ❤", color));
        ll.addView(Helpers.getTextTV(this, String.valueOf(temp.get("dating"))));

        ll.addView(Helpers.getTitleTV(this, "Mocne strony \uD83D\uDCAA", color));
        ll.addView(Helpers.getListTV(this, (ArrayList<String>) temp.get("strengths"), "+"));

        ll.addView(Helpers.getTitleTV(this, "Słabe strony \uD83D\uDE13", color));
        ll.addView(Helpers.getListTV(this, (ArrayList<String>) temp.get("weaknesses"), "-"));

        ll.addView(Helpers.getTitleTV(this, "W populacji \uD83D\uDCCA", color));
        ll.addView(Helpers.getPopulationTV(this, (ArrayList<Integer>) temp.get("population")));

        ll.addView(Helpers.getTitleTV(this, "Znani " + temp.get("type") + " \uD83D\uDC68\u200D\uD83D\uDCBC", color));
        ll.addView(Helpers.getListTV(this, (ArrayList<String>) temp.get("famous"), "•"));

        ll.addView(Helpers.getTitleTV(this, "Enneagram 9️⃣ ", color));
        ll.addView(Helpers.getListTV(this, (ArrayList<String>) temp.get("enneagram"), "•"));

        AppCompatTextView aptv = findViewById(R.id.tvTitle);
        aptv.setText(String.valueOf(temp.get("type") + " - " + temp.get("name")));
        aptv.setTextColor(color);

        loadCareer(type);
    }

    public String loadJSONFromAsset(String type) {
        String json = null;
        try {
            InputStream is = this.getAssets().open("json/" + type.toLowerCase() +".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public String loadCareer(String type){
        String jsonFileString = loadJSONFromAsset("career");
        try {
            JSONObject jsonObject = new JSONObject(jsonFileString);
            String desc = jsonObject.getString(type.toLowerCase() + "Description");
            JSONArray ar = jsonObject.getJSONArray(type.toLowerCase());

            ArrayList<String> listdata = new ArrayList<String>();

            if (ar != null) {
                for (int i=0;i<ar.length();i++){
                    listdata.add(ar.getString(i));
                }
            }

            ll.addView(Helpers.getTitleTV(this, "W pracy", color));
            ll.addView(Helpers.getTextTV(this, desc ));

            ll.addView(Helpers.getTitleTV(this, "Zawody", color));
            ll.addView(Helpers.getListTV(this, (ArrayList<String>) listdata, "•"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

}