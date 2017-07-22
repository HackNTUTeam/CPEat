package com.cpeat.cpeat;

import android.content.Context;
import android.util.Log;

import com.cpeat.cpeat.data.FoodEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by CodeDiablos on 2017/7/22.
 */

public class Utility {
    public static String loadJSONFromAsset(Context context) {
        String json;
        try {
            InputStream is = context.getAssets().open("foods.json");
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

    public static void JSON2Data(Context context, List<FoodEntry> foods, String objName) {
        try {
            String jsonString = loadJSONFromAsset(context);
            if (jsonString == null) {
                return;
            }

            JSONObject jsonObj = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObj.getJSONArray(objName);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonInside = jsonArray.getJSONObject(i);
                Log.d("Details-->", jsonInside.getString("name"));
                String name = jsonInside.getString("name");
                String price = jsonInside.getString("price");

                FoodEntry entry = new FoodEntry(name);
                entry.food.price = Double.parseDouble(price);
                foods.add(entry);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
