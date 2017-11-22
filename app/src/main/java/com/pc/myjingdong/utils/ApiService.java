package com.pc.myjingdong.utils;

import com.pc.myjingdong.bean.BaseBean;
import com.pc.myjingdong.bean.FindAddressBean;
import com.pc.myjingdong.bean.FindCartBean;
import com.pc.myjingdong.bean.ClassLeftBean;
import com.pc.myjingdong.bean.ClassRightBean;
import com.pc.myjingdong.bean.GetUserInfoBean;
import com.pc.myjingdong.bean.GoodsInfoBean;
import com.pc.myjingdong.bean.GoodsListBean;
import com.pc.myjingdong.bean.HomeFirstBean;
import com.pc.myjingdong.bean.LoginBean;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by pc on 2017/11/2.
 */

public interface ApiService {

    /**
     * 登录
     * https://www.zhaoapi.cn/
     * user/login
     *
     * @return
     */
    @POST("user/login")
    @FormUrlEncoded
    Call<LoginBean> login(@Field("mobile") String mobile, @Field("password") String password);

    /**
     * 注册
     * https://www.zhaoapi.cn/
     * user/reg
     *
     */
    @GET("user/reg")
    Observable<BaseBean> register(@Query("mobile")String mobile,@Query("password")String password);

    /**
     * 上传头像等信息
     * https://www.zhaoapi.cn/file/upload
     * file/upload
     */
    @Multipart
    @POST("file/upload")
    Call<LoginBean> upload(@Part("uid") RequestBody uid, @Part MultipartBody.Part file,@Part("token")String token);

    /**
     * 获取用户信息
     * https://www.zhaoapi.cn/user/getUserInfo
     * user/getUserInfo
     */
    @POST("user/getUserInfo")
    @FormUrlEncoded
    Call<GetUserInfoBean> getinfo(@Field("uid") String uid,@Field("token")String token);

    /**
     * 首页数据
     * https://www.zhaoapi.cn/ad/getAd
     * ad/getAd
     */
    @GET("ad/getAd")
    Call<HomeFirstBean> firsthome();

    /**
     * 分类左边
     * https://www.zhaoapi.cn/product/getCatagory
     * product/getCatagory
     */
    @GET("product/getCatagory")
    Observable<ClassLeftBean> classleft();

    /**
     * 分类右边
     * https://www.zhaoapi.cn/product/getProductCatagory
     * product/getProductCatagory
     */
    @POST("product/getProductCatagory")
    @FormUrlEncoded
    Call<ClassRightBean> classright(@Field("cid") int cid);

    /**
     * 商品列表
     * https://www.zhaoapi.cn/
     * product/getProducts
     * ?pscid=1&page=1&sort=2
     */
    @GET("product/getProducts")
    Call<GoodsListBean> goodslist(@Query("pscid") String pscid, @Query("page") int page, @Query("sort") int sort);

    /**
     * 商品详情
     * https://www.zhaoapi.cn/
     * product/getProductDetail
     * ?pid=1
     */
    @GET("product/getProductDetail")
    Observable<GoodsInfoBean> goodsinfo(@Query("pid") String pid);

    /**
     * 添加购物车
     * https://www.zhaoapi.cn/
     * product/addCart
     *
     */
    @POST("product/addCart")
    @FormUrlEncoded
    Observable<BaseBean> addcart(@Field("uid")String uid,@Field("pid")String pid,@Field("token")String token);

    /**
     * 查询购物车
     * https://www.zhaoapi.cn/
     * product/getCarts
     * ?uid=540&token=A4EB40E31913AEE5F1D835808E877B56
     */
    @POST("product/getCarts")
    @FormUrlEncoded
    Observable<FindCartBean> findcart(@Field("uid")String uid, @Field("token")String token);

    /**
     *
     * 删除购物车
     * https://www.zhaoapi.cn/
     * product/deleteCart
     * ?uid=72&pid=1&token
     */
    @GET("product/deleteCart")
    Observable<BaseBean> delcart(@Query("uid")String uid,@Query("pid")String pid,@Query("token")String token);

    /**
     * 查找常用收货地址
     * https://www.zhaoapi.cn/
     * user/getAddrs
     * ?uid=540&token=A4EB40E31913AEE5F1D835808E877B56
     */
    @POST("user/getAddrs")
    @FormUrlEncoded
    Observable<FindAddressBean> findAddress(@Field("uid") String uid,@Field("token")String token);

    /**
     * 添加常用地址
     * https://www.zhaoapi.cn/
     * user/addAddr
     * ?uid=540&addr=%E5%8C%97%E4%BA%AC%E5%B8%82%E6%98%8C%E5%B9%B3%E5%8C%BA%E9%87%91%E5%9F%9F%E5%9B%BD%E9%99%851-1-1&mobile=18811456002&name=%E6%AD%A6%E5%B0%91%E9%9B%84&token=A4EB40E31913AEE5F1D835808E877B56
     * uid,addr,mobile,name,token
     */
    @GET("user/addAddr")
    Observable<BaseBean> addAddress(@Query("uid")String uid,@Query("addr")String addr,
                                    @Query("mobile")String mobile,@Query("name")String name,
                                    @Query("token")String token);

    /**
     * 创建订单
     * https://www.zhaoapi.cn/
     * product/createOrder
     * ?uid=540&price=1124&token=A4EB40E31913AEE5F1D835808E877B56
     */
    @GET("product/createOrder")
    Observable<BaseBean> creataorder(@Query("uid")String uid,@Query("price")String price,@Query("token")String token);

}
