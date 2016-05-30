package com.sally.fanguubao.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 分期 － iphone专区 实体
 *
 * Created by sally on 16/5/29.
 */
public class FenQiIphoneProduct implements Serializable {

    private int id;
    private String tp;
    private String name;
    private Logo logo;
    private String model1;
    private String model;
    private double price;
    private String detail;
    private String remark;
    private List<OneProduct> product_logos;

    public int getId() {
        return id;
    }

    public String getTp() {
        return tp;
    }

    public String getName() {
        return name;
    }

    public Logo getLogo() {
        return logo;
    }

    public String getModel1() {
        return model1;
    }

    public String getModel() {
        return model;
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

    public List<OneProduct> getProduct_logos() {
        return product_logos;
    }

    @Override
    public String toString() {
        return "FenQiIphoneProduct{" +
                "id=" + id +
                ", tp='" + tp + '\'' +
                ", name='" + name + '\'' +
                ", logo=" + logo +
                ", model1='" + model1 + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", detail='" + detail + '\'' +
                ", remark='" + remark + '\'' +
                ", product_logos=" + product_logos +
                '}';
    }

    /**
     * logo
     */
    public static class Logo implements Serializable {
        private String url;

        public String getUrl() {
            return url;
        }

        @Override
        public String toString() {
            return "Logo{" +
                    "url='" + url + '\'' +
                    '}';
        }
    }

    /**
     * product_logos
     */
    public static class OneProduct implements Serializable {
        private int id;
        private int product_id;
        private Logo logo;

        public int getId() {
            return id;
        }

        public int getProduct_id() {
            return product_id;
        }

        public Logo getLogo() {
            return logo;
        }

        @Override
        public String toString() {
            return "OneProduct{" +
                    "id=" + id +
                    ", product_id=" + product_id +
                    ", logo=" + logo +
                    '}';
        }
    }

}
