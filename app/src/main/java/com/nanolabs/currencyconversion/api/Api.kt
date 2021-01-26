package com.nanolabs.currencyconversion.api


import com.nanolabs.currencyconversion.model.ApiCurrency
import com.nanolabs.currencyconversion.model.ApiRate
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Api {


    @GET("list")
    suspend fun getSupportedCurrency(@Query("access_key") accessKey: String): Response<ApiCurrency>

    @GET("live")
    suspend fun getCurrencyRate(@Query("access_key") accessKey: String): Response<ApiRate>


//    @GET("categories")
//    fun getAllCategory(): Call<List<Category>>
//
//    @POST("category_product_version2")
//    @FormUrlEncoded
//    fun getCategoryHeaderList(@Field("user_id") userId: Int): Call<List<Category>>
//
//    @POST("categories/products/version2")
//    @FormUrlEncoded
//    fun getProductListByCategory(@Field("category_id") categoryId: Int, @Field("user_id") userId: Int,
//                                 @Field("start") start: Int): Call<List<Product>>
//
//    @POST("products_type_version2")
//    @FormUrlEncoded
//    fun getProductListByType(@Field("type_id") typeId: Int, @Field("user_id") userId: Int,
//                             @Field("start") start: Int): Call<TypeProduct>
//
//    @POST("products/type")
//    @FormUrlEncoded
//    fun getTypeProductList(@Field("user_id") userId: Int): Call<List<TypeProduct>>
//
//    @POST("products/user/version2")
//    @FormUrlEncoded
//    fun getAllProduct(@Field("user_id") userId: Int, @Field("start") start: Int): Call<List<Product>>
//
//    @POST("wishlist/user")
//    @FormUrlEncoded
//    fun getWishList(@Field("user_id") userId: Int): Call<List<Product>>
//
//    @POST("wishlist/store")
//    @FormUrlEncoded
//    fun storeWishList(@Field("user_id") userId: Int, @Field("product_id") productId: Int): Call<Message>
//
//    @POST("wishlist/destroy")
//    @FormUrlEncoded
//    fun deleteWishList(@Field("user_id") userId: Int, @Field("product_id") productId: Int): Call<Message>
//
//    @GET("brands")
//    fun getAllBrand(): Call<List<Brand>>
//
//    @POST("orders/create")
//    @FormUrlEncoded
//    fun storeOrder(@Field("list") list: String, @Field("user_id") userId: Int, @Field("total") total: Int, @Field("address_id") addressId: Int, @Field("time_slot_id") timeslotId: Int, @Field("deliveryfee_id") deliveryId: Int, @Field("total_tax") tax: Int): Call<Message>
//
//    @POST("orders/user/cancel")
//    @FormUrlEncoded
//    fun cancelOrderById(@Field("order_id") orderId: Int): Call<Message>
//
//    @POST("orders/user")
//    @FormUrlEncoded
//    fun getOrderByUser(@Field("user_id") userId: Int): Call<List<Order>>
//
//    @POST("orders/product")
//    @FormUrlEncoded
//    fun getOrderProducts(@Field("order_id") orderId: Int): Call<Order>
//
//    @GET("deliveryfee")
//    fun getDeliveryFeeList(): Call<List<DeliveryFee>>
//
//    @POST("address/user")
//    @FormUrlEncoded
//    fun getAddressByUser(@Field("user_id") userId: Int): Call<List<Address>>
//
//    @POST("address/destroy")
//    @FormUrlEncoded
//    fun deleteUserAddress(@Field("address_id") addressId: Int): Call<Message>
//
//    @POST("address/store")
//    @FormUrlEncoded
//    fun storeAddress(@Field("user_id") userId: Int, @Field("name") name: String,
//                     @Field("address") address: String, @Field("phone") phone: String, @Field("comment") comment: String): Call<Address>
//
//    @POST("address/update")
//    @FormUrlEncoded
//    fun updateAddress(@Field("address_id") addressId: Int, @Field("name") name: String,
//                      @Field("address") address: String, @Field("phone") phone: String, @Field("comment") comment: String): Call<StatusCustomer>
//
//    @POST("customers")
//    @FormUrlEncoded
//    fun getCustomer(@Field("user_id") userId: Int): Call<StatusCustomer>
//
//    @POST("customers/store")
//    @FormUrlEncoded
//    fun registerCustomer(@Field("phone") phone: String): Call<StatusCustomer>
//
//    @POST("customers/update")
//    @FormUrlEncoded
//    fun updateCustomer(@Field("user_id") userId: Int, @Field("name") name: String,
//                       @Field("phone") phone: String, @Field("profile") profilePhoto: String): Call<StatusCustomer>
//
//    @GET("notifications")
//    fun getAllNotification(): Call<List<Notification>>
//
//    @GET("tax")
//    fun getTaxList(): Call<Tax>
//
//    @GET("tutorial")
//    fun getTutorialList(): Call<List<Tutorial>>
//
//    @GET("support_center")
//    fun getCallList(): Call<List<SupportCenter>>
//
//    @POST("feedback/store")
//    @FormUrlEncoded
//    fun storeFeedback(@Field("star") starCount: Int, @Field("about") feedback: String): Call<Message>
//
//    @POST("save_phone2")
//    @FormUrlEncoded
//    fun getSavePhone(@Field("version") version: String): Call<SavePhone>
//
//    @POST("products/search/version2")
//    @FormUrlEncoded
//    fun searchProduct(@Field("product_name") productName: String, @Field("user_id") userId: Int,
//                      @Field("start") start: Int): Call<List<Product>>
//
//    @POST("products/filter")
//    @FormUrlEncoded
//    fun filterProduct(@Field("category_list") categoryList: String, @Field("brand_list") brandList: String,
//                      @Field("min") min: String, @Field("max") max: String, @Field("user_id") userId: Int): Call<List<Product>>
//
//    @GET("about")
//    fun getAboutText(): Call<List<About>>
//
//    @GET("notibar")
//    fun getNotibar(): Call<List<Notibar>>


}

