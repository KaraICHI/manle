package com.manle.saitamall.utils;

public class Constants {
    //家里wifi
 //  public static final String BASE = "http://192.168.1.106";
    //公司wifi
   public static final String BASE = "http://192.168.137.1";

   public static final String BASE_MANLE = "http://192.168.137.1:8080";

   public static final String CLIENT_USER = BASE_MANLE + "/api/client-users";

   public static final String USER_FIGURE = CLIENT_USER + "/upload";

   // 请求图片基本URL
   public static final String BASE_SERVER_IMAGE = BASE_MANLE + "/templates/images/";
   public static final String AVATAR_IMAGE = BASE_SERVER_IMAGE + "avatar/";




 // 请求Json数据基本URL
    public static final String BASE_URL_JSON = BASE + "/atguigu/json/";

    // 请求图片基本URL
    public static final String BASE_URl_IMAGE = BASE + "/atguigu/img";

    public static final String UPLOAD_IMG = BASE_URl_IMAGE + "/upload_img";


    public static final String SKIRT_URL = BASE_URL_JSON + "SKIRT_URL.json";
/*    public static final String JACKET_URL = BASE_URL_JSON + "JACKET_URL.json";
    public static final String PANTS_URL = BASE_URL_JSON + "PANTS_URL.json";
    public static final String OVERCOAT_URL = BASE_URL_JSON + "OVERCOAT_URL.json";
    public static final String ACCESSORY_URL = BASE_URL_JSON + "ACCESSORY_URL.json";
    public static final String BAG_URL = BASE_URL_JSON + "BAG_URL.json";
    public static final String DRESS_UP_URL = BASE_URL_JSON + "DRESS_UP_URL.json";
    public static final String HOME_PRODUCTS_URL = BASE_URL_JSON + "HOME_PRODUCTS_URL.json";
    public static final String STATIONERY_URL = BASE_URL_JSON + "STATIONERY_URL.json";
    public static final String DIGIT_URL = BASE_URL_JSON + "DIGIT_URL.json";
    public static final String GAME_URL = BASE_URL_JSON + "GAME_URL.json";*/

    //主页Fragment路径
    public static final String HOME_URL = BASE_URL_JSON + "HOME_URL.json";
    //分类Fragment里面的标签Fragment页面数据
    public static final String Theme_URL = BASE_URL_JSON + "THEME_URL.json";
    public static final String Category_URL = BASE_URL_JSON + "CATEGORY_URL.json";

    public static final String NEW_POST_URL = BASE_URL_JSON + "NEW_POST_URL.json";
    public static final String HOT_POST_URL = BASE_URL_JSON + "HOT_POST_URL.json";

    //页面的具体数据的id
    public static final String GOODSINFO_URL = BASE_URL_JSON + "GOODSINFO_URL.json";


    //果冻
    public static final String GUODONG_STORE = BASE_URL_JSON + "GUODONG_STORE.json";
    public static final String COLLECT_URL = BASE_URL_JSON + "COLLECT_URL.json";

    public static Boolean isBackHome = false;

    //客服数据
 //   public static final String CALL_CENTER = "http://www6.53kf.com/webCompany.php?arg=10007377&style=1&kflist=off&kf=info@atguigu.com,video@atguigu.com,public@atguigu.com,3069368606@qq.com,215648937@qq.com,sudan@atguigu.com,sszhang@atguigu.com&zdkf_type=1&language=zh-cn&charset=gbk&referer=http%3A%2F%2Fwww.atguigu.com%2Fcontant.shtml&keyword=&tfrom=1&tpl=crystal_blue&timeStamp=1479001706368&ucust_id=";
    public static final String CALL_CENTER = "https://chat.kuaishangkf.com/#/?script=xx4yrZrj37tm5HrfH67KJNhH";
}


