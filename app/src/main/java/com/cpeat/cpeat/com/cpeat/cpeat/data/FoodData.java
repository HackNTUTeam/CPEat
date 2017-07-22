package com.cpeat.cpeat.com.cpeat.cpeat.data;

import java.io.Serializable;

/**
 * Created by cynthia on 2017/7/22.
 */

public class FoodData implements Serializable {


    /**
     * date : 106.07.22
     * uid : 11
     * name : 椰子
     * marketId : 104
     * marketName : 台北二
     * highPrice : 32.7
     * middlePrice : 22.8
     * lowPrice : 7.0
     * averagePrice : 21.6
     * count : 2467.0
     */

    private String date;
    private String uid;
    private String name;
    private String marketId;
    private String marketName;
    private double highPrice;
    private double middlePrice;
    private double lowPrice;
    private double averagePrice;
    private double count;

    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getMiddlePrice() {
        return middlePrice;
    }

    public void setMiddlePrice(double middlePrice) {
        this.middlePrice = middlePrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }
}
