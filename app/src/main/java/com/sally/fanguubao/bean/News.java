package com.sally.fanguubao.bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 首页广告和推荐
 * Created by sally on 8/26/16.
 */
public class News implements Serializable {


    private List<Advertise> advs;
    private List<Recommand> recommands;

    public List<Advertise> getAdvs() {
        return advs;
    }

    public List<Recommand> getRecommands() {
        return recommands;
    }

    @Override
    public String toString() {
        return "News{" +
                "advs=" + advs +
                ", recommands=" + recommands +
                '}';
    }

    // advs : []
    class Advertise implements Serializable {
        private int id;
        private String name;
        private Logo logo;
        private int tp;
        private String url;
        private String code;
        private String status;
        private int position;
        private String only;
        private String fenlei;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Logo getLogo() {
            return logo;
        }

        public int getTp() {
            return tp;
        }

        public String getUrl() {
            return url;
        }

        public String getCode() {
            return code;
        }

        public String getStatus() {
            return status;
        }

        public int getPosition() {
            return position;
        }

        public String getOnly() {
            return only;
        }

        public String getFenlei() {
            return fenlei;
        }

        @Override
        public String toString() {
            return "Advertise{" +
                    "fenlei='" + fenlei + '\'' +
                    ", only='" + only + '\'' +
                    ", position=" + position +
                    ", status='" + status + '\'' +
                    ", code='" + code + '\'' +
                    ", url='" + url + '\'' +
                    ", tp=" + tp +
                    ", logo=" + logo +
                    ", name='" + name + '\'' +
                    ", id=" + id +
                    '}';
        }
    }

    // logo/recommand : url
    class Logo implements Serializable {
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

    class Recommand implements Serializable {
        private int id;
        private String name;
        private Logo logo;
        private String category;
        private String tp;
        private String code;
        private String weixin_url;
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

        public String getCategory() {
            return category;
        }

        public String getTp() {
            return tp;
        }

        public String getCode() {
            return code;
        }

        public String getWeixin_url() {
            return weixin_url;
        }

        public boolean isHide() {
            return hide;
        }

        public int getPosition() {
            return position;
        }

        @Override
        public String toString() {
            return "Recommand{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", logo=" + logo +
                    ", category='" + category + '\'' +
                    ", tp='" + tp + '\'' +
                    ", code='" + code + '\'' +
                    ", weixin_url='" + weixin_url + '\'' +
                    ", hide=" + hide +
                    ", position=" + position +
                    '}';
        }
    }
}
