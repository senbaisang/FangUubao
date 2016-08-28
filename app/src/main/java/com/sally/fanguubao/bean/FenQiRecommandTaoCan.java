package com.sally.fanguubao.bean;

import java.io.Serializable;

/**
 *
 * 分期 － 精品推荐 － 装修套餐bean
 *
 * Created by sally on 8/27/16.
 */
public class FenQiRecommandTaoCan implements Serializable {

    private int id;
    private String tp;
    private String name;
    private FuLiSheProduct.Logo logo;
    private double price;
    private String detail;
    private String remark;

    public int getId() {
        return id;
    }

    public String getTp() {
        return tp;
    }

    public String getName() {
        return name;
    }

    public FuLiSheProduct.Logo getLogo() {
        return logo;
    }

    public double getPrice() {
        return price;
    }

    public String getDetail() {
        return detail;
    }

    public String getRemark() {
        return remark;
    }

    @Override
    public String toString() {
        return "FenQiRecommandTaoCan{" +
                "id=" + id +
                ", tp='" + tp + '\'' +
                ", name='" + name + '\'' +
                ", logo=" + logo +
                ", price=" + price +
                ", detail='" + detail + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
