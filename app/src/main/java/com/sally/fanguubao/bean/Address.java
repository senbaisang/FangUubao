package com.sally.fanguubao.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 旅游的地址，先随便用用
 * Created by sally on 16/6/2.
 */
public class Address implements Serializable {

    private int id;
    private String name;
    private List<Country> countries;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Country> getCountries() {
        return countries;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countries=" + countries +
                '}';
    }

    public static class Country implements Serializable {
        private int id;
        private int state_id;
        private String state_name;
        private String name;
        private int position;

        public int getId() {
            return id;
        }

        public int getState_id() {
            return state_id;
        }

        public String getState_name() {
            return state_name;
        }

        public String getName() {
            return name;
        }

        public int getPosition() {
            return position;
        }

        @Override
        public String toString() {
            return "Country{" +
                    "id=" + id +
                    ", state_id=" + state_id +
                    ", state_name='" + state_name + '\'' +
                    ", name='" + name + '\'' +
                    ", position=" + position +
                    '}';
        }
    }
}
