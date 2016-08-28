package com.sally.fanguubao.util;

/**
 * Created by sally on 16/5/27.
 */
public class Constant {

    public static final int BANNER_MAX_VALUE = 1000;

    public static final String REN_MIN_BI = "¥";

    public static final String KE_FU_DIAN_HUA = "12345678";

    public static final int SELECT_COUNTRY = 0x110;
    public static final int SELECT_CITY = 0x111;
    public static final int GET_CITY_RESPONSE_CODE = 0x112;

    /**
     * 传递数据的常量
     */

    public static final String FENQI_IPHONE_PRODUCT_BUNDLE = "fenqi_iphone_product_bundle";
    public static final String FENQI_IPHONE_PRODUCT_KEY = "fenqi_iphone_product_key";

    public static final String FENQI_MORE_PRODUCT_BUNDLE = "fenqi_more_product_bundle";
    public static final String FENQI_MORE_PRODUCT_KEY = "fenqi_more_product_key";

    public static final String FENQI_CAR_HOT_BUNDLE = "fenqi_car_hot_bundle";
    public static final String FENQI_CAR_HOT_PRODUCT = "fenqi_car_hot_product";

    public static final String FENQI_CAR_BRAND_PRODUCT_ID = "fenqi_car_brand_product_id";

    public static final String FENQI_CAR_BRAND_PRODUCT_DETAIL_BUNDLE = "fenqi_car_brand_product_detail_bundle";
    public static final String FENQI_CAR_BRAND_PRODUCT_DETAIL_DESC = "fenqi_car_brand_product_detail_desc";

    public static final String FENQI_JD_PRODUCT_DETAIL_BUNDLE = "fenqi_jd_product_detail_bundle";
    public static final String FENQI_JD_PRODUCT_DETAIL_DESC = "fenqi_jd_product_detail_desc";

    public static final String FENQI_JD_PRODUCT_CATEGORIES_TITLE = "fenqi_jd_product_categoties_title";
    public static final String FENQI_JD_PRODUCT_CATEGORIES_INDEX = "fenqi_jd_product_categories_index";

    public static final String FENQI_LUX_BUNDLE = "fenqi_lux_bag_bundle";
    public static final String FENQI_LUX_TITLE = "fenqi_lux_bag_title";
    public static final String FENQI_LUX_CATEGORY = "fenqi_lux_category";

    public static final String FENQI_LUX_PRODUCT_CATEGORY_INDEX = "fenqi_lux_product_category_index";
    public static final String FENQI_LUX_PRODUCT_CATEGORY_TITLE = "fenqi_lux_product_category_title";

    public static final String FENQI_LUX_PRODUCT_SHOW_BUNDLE = "fenqi_lux_product_show_bundle";
    public static final String FENQI_LUX_PRODUCT_SHOW_DESC = "fenqi_lux_product_show_desc";

    public static final String COUNTRY_BUNDLE = "country_bundle";
    public static final String COUNTRY = "country";

    public static final String RECOMMAND_IV_FLAG = "recommand_iv_flag";
    public static final String RECOMMAND_IV = "recommand_iv";

    /**
     * activity的title
     */
    public static final String ACTIVITY_TITLE = "activity_title";


    /**
     * url
     */

    public static final String HOST = "http://192.168.1.104:5555/";
//    public static final String HOST = "http://www.uubpay.com/";


    public static final String DEBUG_FULISHE_DETAIL = Constant.HOST + "api/fulishe/list?1=1";

    public static final String DEBUG_FENQI_IPHONE = Constant.HOST + "api/iphone/list?tp=iphone";

    public static final String DEBUG_FENQI_MORE = Constant.HOST + "api/iphone/list?tp=else";

    public static final String DEBUG_FENQI_CAR = Constant.HOST + "api/car/info";
    // http://192.168.56.1:3333/api/car/car_of_brand/31
    public static final String DEBUG_FENQI_CAR_BRAND_PRODUCT = Constant.HOST + "api/car/car_of_brand/";

    public static final String DEBUG_FENQI_JD = Constant.HOST + "api/gome/categories";
    // http://192.168.56.1:3333/api/gome/list?category=电视
    public static final String DEBUG_FENQI_JD_DETAIL = Constant.HOST + "api/gome/list?category=";


    // http://192.168.56.1:3333//api/lux/list?category=BA&key=BALENCIAGA
    public static final String DEBUG_FENQI_LUX_DETAIL = Constant.HOST + "api/lux/list?category=";

    // 首页广告， 分期推荐产品
    public static final String DEBUG_FENQI_TUIJIAN = Constant.HOST + "/api/user/half_screen_advs2?1=1";

    // 旅游地址
    public static final String DEBUG_LY_ADDRESS = HOST + "/api/user/countries";

    /**
     * h5
     */
    public static final String FAQ = "http://d.uubpay.com/weixin/main/faq";

    // 首页推荐 ： http://192.168.0.104:3333/api/decoration/product_of/(53)
    public static final String DEBUG_DECORATING_TAO_CAN = HOST + "/api/decoration/product_of/";


}
