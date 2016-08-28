package com.sally.fanguubao.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sally.fanguubao.bean.Address;
import com.sally.fanguubao.bean.FenQiCarBrandsProduct;
import com.sally.fanguubao.bean.FenQiCarProduct;
import com.sally.fanguubao.bean.FenQiIphoneProduct;
import com.sally.fanguubao.bean.FenQiJdCategories;
import com.sally.fanguubao.bean.FenQiJdProduct;
import com.sally.fanguubao.bean.FenQiLuxProductDetail;
import com.sally.fanguubao.bean.FenQiRecommand;
import com.sally.fanguubao.bean.FenQiRecommandTaoCan;
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

    /**
     * 分期 － car 数据 ； 热销 ＋ 各种品牌信息
     * @param str
     * @return
     */
    public static FenQiCarProduct fenQiCarJson(String str) {
        FenQiCarProduct product = new Gson().fromJson(str, new TypeToken<FenQiCarProduct>() {}.getType());
        return product;
    }

    /**
     * 分期 － car 数据 ：各种品牌的详细信息
     *
     * @param str
     * @return
     */
    public static List<FenQiCarBrandsProduct> fenQiCarBrandsProducts(String str) {
        List<FenQiCarBrandsProduct> lists = new Gson().fromJson(str, new TypeToken<List<FenQiCarBrandsProduct>>() {}.getType());
        return lists;
    }

    /**
     * 分期 － jd 数据
     * @param str
     * @return
     */
    public static List<FenQiJdCategories> fenQiJdJson(String str) {
        List<FenQiJdCategories> lists = new Gson().fromJson(str, new TypeToken<List<FenQiJdCategories>>() {}.getType());
        return lists;
    }

    /**
     * 分期 － jd 每一个分类的产品信息
     * @param str
     * @return
     */
    public static List<FenQiJdProduct> fenQiJdProductJson(String str) {
        List<FenQiJdProduct> products = new Gson().fromJson(str, new TypeToken<List<FenQiJdProduct>>() {}.getType());
        return products;
    }

    /**
     * 分期 － lux 每一个分类下的产品信息
     * @param str
     * @return
     */
    public static List<FenQiLuxProductDetail> fenqiLuxProductJson(String str) {
        List<FenQiLuxProductDetail> products = new Gson().fromJson(str, new TypeToken<List<FenQiLuxProductDetail>>() {}.getType());
        return products;
    }

    /**
     * 这里返回的时 分期 － 精品推荐的信息 是一个json对象，里面包含 启动广告轮播图 ＋ 推荐产品两个json对象
     * @param str
     * @return
     */
    public static FenQiRecommand fenQiRecommandJson(String str) {
        FenQiRecommand products = new Gson().fromJson(str, new TypeToken<FenQiRecommand>() {}.getType());
        return products;
    }

    /**
     * 分期 － 旅游 的地址
     * @param str
     * @return
     */
    public static List<Address> fenQiLyAddress(String str) {
        List<Address> addresses = new Gson().fromJson(str, new TypeToken<List<Address>>() {}.getType());
        return addresses;
    }

    /**
     * 分期 － 精品推荐 － 产品套餐
     * @param str
     * @return
     */
    public static FenQiRecommandTaoCan fenQiRecommandTaoCan(String str) {
        FenQiRecommandTaoCan taoCan = new Gson().fromJson(str, new TypeToken<FenQiRecommandTaoCan>(){}.getType());
        return taoCan;
    }

}
