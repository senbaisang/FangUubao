package com.sally.fanguubao.bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 * fenqi_car_product_bean
 *
 * Created by sally on 16/5/30.
 */
public class FenQiCarProduct implements Serializable {

    private List<Recommands> recommands;
    private List<Brands> brands;

    public List<Recommands> getRecommands() {
        return recommands;
    }

    public List<Brands> getBrands() {
        return brands;
    }

    @Override
    public String toString() {
        return "FenQiCarProduct{" +
                "recommands=" + recommands +
                ", brands=" + brands +
                '}';
    }

    /**
     * recommands
     */
    public static class Recommands implements Serializable {
        private int id;
        private int categoties_id;
        private String name;
        private Logo logo;
        private double o_price;
        private double o_price2;
        private double price;
        private int downpayment;
        private String color;
        private String model;
        private boolean recommand;
        private boolean hide;
        private String autohomeurl;
        private String sinaurl;
        private String sohuurl;
        private String neteaseurl;
        private String detail;
        private List<CarLogo> car_logos;

        public int getId() {
            return id;
        }

        public int getCategoties_id() {
            return categoties_id;
        }

        public String getName() {
            return name;
        }

        public Logo getLogo() {
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

        public int getDownpayment() {
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

        public List<CarLogo> getCarLogos() {
            return car_logos;
        }

        @Override
        public String toString() {
            return "Recommands{" +
                    "id=" + id +
                    ", categoties_id=" + categoties_id +
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
                    ", carLogos=" + car_logos +
                    '}';
        }
    }

    /**
     * brands
     */
    public static class Brands implements Serializable {
        private int id;
        private String name;
        private Logo logo;
        private boolean hide;
        private int position;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Logo getLogo() {
            return logo;
        }

        public boolean isHide() {
            return hide;
        }

        public int getPosition() {
            return position;
        }

        @Override
        public String toString() {
            return "Brands{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", logo=" + logo +
                    ", hide=" + hide +
                    ", position=" + position +
                    '}';
        }
    }

    /**
     * logo { url: ...}
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
     * car_logos : [ {id, car_id, logo{url}}, {}]
     */
    public static class CarLogo implements Serializable {
        private int id;
        private int car_id;
        private Logo logo;

        public int getId() {
            return id;
        }

        public int getCar_id() {
            return car_id;
        }

        public Logo getLogo() {
            return logo;
        }

        @Override
        public String toString() {
            return "CarLogo{" +
                    "id=" + id +
                    ", car_id=" + car_id +
                    ", logo=" + logo +
                    '}';
        }
    }
}
