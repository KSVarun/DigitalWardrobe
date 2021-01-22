package com.hci.digitalwardrobe.calls;

import com.hci.digitalwardrobe.models.CreateUserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    @POST("createUser/")
    Call<CreateUserModel> createUser(@Body CreateUserModel userModel);

    @POST("authenticateUser/")
    Call<CreateUserModel> authenticateUser(@Body CreateUserModel userModel);

}
