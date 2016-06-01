package com.sally.fanguubao.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 分期 － 奢侈品
 *
 * Created by sally on 16/6/1.
 */
public class FenQiLuxProductDetail implements Serializable {

    private int id;
    private String name;
    private String price;
    private String details;
    private String category;
    private String source_url;
    private List<ProductLogo> attos_product_logos;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDetails() {
        return details;
    }

    public String getCategory() {
        return category;
    }

    public String getSource_url() {
        return source_url;
    }

    public List<ProductLogo> getAttos_product_logos() {
        return attos_product_logos;
    }

    @Override
    public String toString() {
        return "FenQiLuxProductDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", details='" + details + '\'' +
                ", category='" + category + '\'' +
                ", source_url='" + source_url + '\'' +
                ", attos_product_logos=" + attos_product_logos +
                '}';
    }

    /**
     * attos_product_logos
     */
    public static class ProductLogo implements Serializable {
        private int id;
        private int attos_product_id;
        private Logo logo;

        public int getId() {
            return id;
        }

        public int getAttos_product_id() {
            return attos_product_id;
        }

        public Logo getLogo() {
            return logo;
        }

        @Override
        public String toString() {
            return "ProductLogo{" +
                    "id=" + id +
                    ", attos_product_id=" + attos_product_id +
                    ", logo=" + logo +
                    '}';
        }
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
}
