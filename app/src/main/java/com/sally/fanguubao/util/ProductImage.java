package com.sally.fanguubao.util;

import java.io.Serializable;

/**
 * Created by sally on 16/5/30.
 */
public class ProductImage implements Serializable {

    private String src;

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSrc() {
        return src;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "src='" + src + '\'' +
                '}';
    }
}
