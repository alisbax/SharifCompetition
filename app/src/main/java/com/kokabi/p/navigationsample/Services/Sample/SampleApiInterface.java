package com.kokabi.p.navigationsample.Services.Sample;

import com.kokabi.p.navigationsample.Objects.ServerObj;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SampleApiInterface {

    /*Get Methods*/
    @GET("user")
    Call<ServerObj> getMobile(@Query("checkmobileexits") String mobile);

    @GET("user")
    Call<ServerObj> getEmail(@Query("checkemailexits") String email);

    @GET("user")
    Call<ServerObj> getProfileInfo(@Query("self") String anything);

    @GET("recovery")
    Call<ServerObj> getRecoveryMobile(@Query("mobile") String mobile);

    @GET("recovery/{mobile}")
    Call<ServerObj> getRecovery(@Path("mobile") String mobile, @Query("confirm") String confirmCode);
    /*============================================================================================*/

    /*PutMethods*/
    @PUT("User")
    Call<ServerObj> profileInfoEdit(@Body HashMap<String, String> params);

    @PUT("Authenticate")
    Call<ServerObj> changePassword(@Body HashMap<String, String> params);

    @PUT("Mobile")
    Call<ServerObj> verifyCode(@Body HashMap<String, String> params);

    @PUT("Recovery")
    Call<ServerObj> forgotPasswordChange(@Body HashMap<String, String> params);
    /*============================================================================================*/

    /*Post Methods*/
    @POST("User")
    Call<ServerObj> signUp(@Body HashMap<String, String> params);

    @POST("Mobile")
    Call<ServerObj> retryVerifyMobile(@Body HashMap<String, String> params);

    @POST("Authenticate")
    Call<ServerObj> signIn(@Body HashMap<String, String> params);
    /*============================================================================================*/

    /*Delete Methods*/
    @DELETE("Parking/{id}")
    Call<ServerObj> deleteFromParking(@Path("id") String id);
    /*============================================================================================*/

}