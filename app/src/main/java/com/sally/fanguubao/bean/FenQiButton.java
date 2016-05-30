package com.sally.fanguubao.bean;

/**
 * Created by sally on 16/5/27.
 */
public class FenQiButton {

    private String title;
    private int image;

    public FenQiButton() {
    }

    public FenQiButton(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "FenQiButton{" +
                "title='" + title + '\'' +
                ", image=" + image +
                '}';
    }
}
