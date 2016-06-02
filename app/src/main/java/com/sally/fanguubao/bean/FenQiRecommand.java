package com.sally.fanguubao.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sally on 16/6/2.
 */
public class FenQiRecommand implements Serializable {

    private List<Adv> advs;
    private List<Recommand> recommands;

    public List<Adv> getAdvs() {
        return advs;
    }

    public List<Recommand> getRecommands() {
        return recommands;
    }

    @Override
    public String toString() {
        return "FenQiRecommand{" +
                "advs=" + advs +
                ", recommands=" + recommands +
                '}';
    }

    /**
     * advs
     */
    public static class Adv implements Serializable {
        private int id;
        private String name;
        private FuLiSheProduct.Logo logo;
        private int tp;
        private String url;
        private String code;
        private String status;
        private int position;
        private String only;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public FuLiSheProduct.Logo getLogo() {
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

        @Override
        public String toString() {
            return "Adv{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", logo=" + logo +
                    ", tp=" + tp +
                    ", url='" + url + '\'' +
                    ", code='" + code + '\'' +
                    ", status='" + status + '\'' +
                    ", position=" + position +
                    ", only='" + only + '\'' +
                    '}';
        }
    }


    /**
     * recommands
     */
  public static class Recommand implements Serializable {
      private int id;
      private String name;
      private FuLiSheProduct.Logo logo;
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

      public FuLiSheProduct.Logo getLogo() {
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
          return "FenQiRecommand{" +
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
