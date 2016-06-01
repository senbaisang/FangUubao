package com.sally.fanguubao.bean;

import java.io.Serializable;

/**
 * Created by sally on 16/6/1.
 */
public class FenQiJdProduct implements Serializable {

    private int id;
    private String category;
    private String name;
    private String logo;
    private String brand;
    private double price;
    private String url;
    private String third_party;
    private int remain;
    private String discount;

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public String getThird_party() {
        return third_party;
    }

    public int getRemain() {
        return remain;
    }

    public String getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "FenQiJdProduct{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", url='" + url + '\'' +
                ", third_party='" + third_party + '\'' +
                ", remain=" + remain +
                ", discount='" + discount + '\'' +
                '}';
    }
}
