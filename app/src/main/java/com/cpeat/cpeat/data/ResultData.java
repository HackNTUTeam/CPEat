package com.cpeat.cpeat.data;

import android.util.Log;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CodeDiablos on 2017/7/23.
 */

public class ResultData implements Serializable {
    public class FoodDetail implements Serializable {
        public float percent;
        public float count;
    }
    public float orgPrice;
    public float eatPrice;
    public float level;

    public FoodDetail meat = new FoodDetail();
    public FoodDetail vege = new FoodDetail();
    public FoodDetail seafood = new FoodDetail();

    public static void getTotalPrice(List<FoodEntry> foods, float price, float level, FoodDetail detail) {
        float total = 0.0f;
        int count = 0;
        for (FoodEntry food : foods) {
            if (food.checked) {
                count++;
                total += food.food.price;
            }
        }

        if (count == 0) {
            return;
        }
        float coefficient = price / (count * 100);
        detail.percent  = total * coefficient;
        detail.count = detail.percent / 10;

        Log.e("TEST", detail.percent + "   " + detail.count);

    }
}
