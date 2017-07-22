package com.cpeat.cpeat.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CodeDiablos on 2017/7/23.
 */

public class ResultData implements Serializable {
    public float orgPrice;
    public float eatPrice;
    public float level;
    public float meatPercent = 30.0f;
    public float vegePercent = 30.0f;
    public float seafoodPercent = 30.0f;
    public int meatCount;
    public int vegeCount;
    public int seafoodCount;

    public static float getTotalPrice(List<FoodEntry> foods, float price, float level) {
        float total = 0.0f;
        int count = 0;
        for (FoodEntry food : foods) {
            if (food.checked) {
                count++;
                total += food.food.price;
            }
        }

        float foodCoefficient = price / (count * 100);
        return total;
    }
}
