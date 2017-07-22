package com.cpeat.cpeat.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cynthia on 2017/7/22.
 */

public class FoodEntry implements Serializable {

    public Boolean checked;
    public Food food;

    public FoodEntry(String name) {
        food = new Food();
        food.name = name;
        this.checked = false;
    }
}
