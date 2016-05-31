package com.sally.fanguubao.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sally on 16/5/31.
 */
public class FenQiJdCategories implements Serializable {

    private int id;
    private String tp;
    private String title;
    private String sub_categories;
    private String brands;
    private Date created_at;
    private Date updated_at;

    public int getId() {
        return id;
    }

    public String getTp() {
        return tp;
    }

    public String getTitle() {
        return title;
    }

    public String getSub_categories() {
        return sub_categories;
    }

    public String getBrands() {
        return brands;
    }

    @Override
    public String toString() {
        return "FenQiJdCategories{" +
                "id=" + id +
                ", tp='" + tp + '\'' +
                ", title='" + title + '\'' +
                ", sub_categories='" + sub_categories + '\'' +
                ", brands='" + brands + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
