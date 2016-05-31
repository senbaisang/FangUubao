package com.sally.fanguubao.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sally on 16/5/31.
 */
public class FenQiCarBrandsProduct implements Serializable {

    private int id;
    private int category_id;
    private String name;
    private FenQiIphoneProduct.Logo logo;
    private double o_price;
    private double o_price2;
    private double price;
    private double downpayment;
    private String color;
    private String model;
    private boolean recommand;
    private boolean hide;
    private String autohomeurl;
    private String sinaurl;
    private String sohuurl;
    private String neteaseurl;
    private String detail;
    private List<FenQiCarProduct.CarLogo> car_logos;

    public int getId() {
        return id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getName() {
        return name;
    }

    public FenQiIphoneProduct.Logo getLogo() {
        return logo;
    }

    public double getO_price() {
        return o_price;
    }

    public double getO_price2() {
        return o_price2;
    }

    public double getPrice() {
        return price;
    }

    public double getDownpayment() {
        return downpayment;
    }

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }

    public boolean isRecommand() {
        return recommand;
    }

    public boolean isHide() {
        return hide;
    }

    public String getAutohomeurl() {
        return autohomeurl;
    }

    public String getSinaurl() {
        return sinaurl;
    }

    public String getSohuurl() {
        return sohuurl;
    }

    public String getNeteaseurl() {
        return neteaseurl;
    }

    public String getDetail() {
        return detail;
    }

    public List<FenQiCarProduct.CarLogo> getCar_logos() {
        return car_logos;
    }

    @Override
    public String toString() {
        return "FenQiCarBrandsProduct{" +
                "id=" + id +
                ", category_id=" + category_id +
                ", name='" + name + '\'' +
                ", logo=" + logo +
                ", o_price=" + o_price +
                ", o_price2=" + o_price2 +
                ", price=" + price +
                ", downpayment=" + downpayment +
                ", color='" + color + '\'' +
                ", model='" + model + '\'' +
                ", recommand=" + recommand +
                ", hide=" + hide +
                ", autohomeurl='" + autohomeurl + '\'' +
                ", sinaurl='" + sinaurl + '\'' +
                ", sohuurl='" + sohuurl + '\'' +
                ", neteaseurl='" + neteaseurl + '\'' +
                ", detail='" + detail + '\'' +
                ", car_logos=" + car_logos +
                '}';
    }
}
