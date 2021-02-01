package com.hci.digitalwardrobe.calls;

import com.hci.digitalwardrobe.models.CreateUserModel;
import com.hci.digitalwardrobe.models.PredictClothesModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GetAllClothes {

    @POST("get_all_clothes/")
    Call<PredictClothesModel> getAllClothes(@Body String user);

}
