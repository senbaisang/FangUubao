package com.sally.fanguubao.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sally.fanguubao.bean.FenQiIphoneProduct;
import com.sally.fanguubao.bean.FuLiSheProduct;

import java.util.List;

/**
 * Created by sally on 16/5/27.
 */
public class GsonUtil {

    /**
     * 福利社数据
     * @param str
     * @return
     */
    public static List<FuLiSheProduct> fuLiSheJson(String str) {
        List<FuLiSheProduct> lists = new Gson().fromJson(str, new TypeToken<List<FuLiSheProduct>>() {}.getType());
        return lists;
    }

    /**
     * 分期 － iphone专区 数据
     * @param str
     * @return
     */
    public static List<FenQiIphoneProduct> fenQiIphoneJson(String str) {
        List<FenQiIphoneProduct> lists = new Gson().fromJson(str, new TypeToken<List<FenQiIphoneProduct>>() {}.getType());
        return lists;
    }
}
