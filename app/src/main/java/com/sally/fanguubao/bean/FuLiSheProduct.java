package com.sally.fanguubao.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 福利社bean
 * Created by sally on 16/5/27.
 */
public class FuLiSheProduct implements Serializable {

    private int id;
    private int category_id;
    private String code;
    private String name;
    private String model;

    private Logo logo;

    private int score;
    private int count;
    private int remain;
    private double price;
    private double o_price;
    private String brand;
    private String introduce;
    private String detail;
    private int position;
    private Date end_at;
    private List<OneProduct> a_product_logos;


    public static class Logo implements Serializable {
        public String url;

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

    public int getId() {
        return id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public Logo getLogo() {
        return logo;
    }

    public int getScore() {
        return score;
    }

    public int getCount() {
        return count;
    }

    public int getRemain() {
        return remain;
    }

    public double getPrice() {
        return price;
    }

    public double getO_price() {
        return o_price;
    }

    public String getBrand() {
        return brand;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getDetail() {
        return detail;
    }

    public int getPosition() {
        return position;
    }

    public Date getEnd_at() {
        return end_at;
    }

    public List<OneProduct> getOneProducts() {
        return a_product_logos;
    }

    @Override
    public String toString() {
        return "FuLiSheProduct{" +
                "id=" + id +
                ", category_id=" + category_id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", logo=" + logo +
                ", score=" + score +
                ", count=" + count +
                ", remain=" + remain +
                ", price=" + price +
                ", o_price=" + o_price +
                ", brand='" + brand + '\'' +
                ", introduce='" + introduce + '\'' +
                ", detail='" + detail + '\'' +
                ", position=" + position +
                ", end_at=" + end_at +
                ", oneProducts=" + a_product_logos +
                '}';
    }
}
