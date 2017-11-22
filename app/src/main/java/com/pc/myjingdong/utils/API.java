package com.pc.myjingdong.utils;

/**
 * Created by pc on 2017/11/2.
 */

public class API {
    //公共协议和域名  http://120.27.23.105/
    public static final String URI = "http://120.27.23.105/";
    //登录接口
    public static final String LOGIN = "https://www.zhaoapi.cn/user/login";
    //注册接口
    public static final String REGISTER = "https://www.zhaoapi.cn/user/reg";
    //上传头像
    public static final String UPLOAD = "https://www.zhaoapi.cn/file/upload";
    //获取用户信息
    public static final String GETUSERINFO = "https://www.zhaoapi.cn/user/getUserInfo";
    //首页
    public static final String FIRSTHOME = "https://www.zhaoapi.cn/ad/getAd";
    //分类左边
    public static final String CLASSLEFT = "https://www.zhaoapi.cn/product/getCatagory";
    //分类右边
    public static final String CLASSRIGHT = "https://www.zhaoapi.cn/product/getProductCatagory";
    //商品列表
    public static final String GOODSLIST = URI+"product/";
    //商品详情
    public static final String GOODSINFO = "https://www.zhaoapi.cn/product/getProductDetail";
    //添加购物车
    public static final String ADDCART = "https://www.zhaoapi.cn/product/addCart";
    //查询购物车
    public static final String FINDCART = "https://www.zhaoapi.cn/product/getCarts";
    //删除购物车
    public static final String DELCART = "https://www.zhaoapi.cn/product/deleteCart?uid=72&pid=1";
    //查找常用地址
    public static final String FINDADDRESS = "https://www.zhaoapi.cn/user/getAddrs?uid=540&token=A4EB40E31913AEE5F1D835808E877B56";
    //添加常用地址
    public static final String ADDADDRESS = "https://www.zhaoapi.cn/user/addAddr?uid=71&addr=北京市昌平区金域国际1-1-1&mobile=18612991023&name=kson";
    //创建订单
    public static final String CREATEORDER = "http://120.27.23.105/product/createOrder?uid=540&price=1124&token=A4EB40E31913AEE5F1D835808E877B56";

}
