package com.hci.digitalwardrobe.calls;

import com.hci.digitalwardrobe.models.ClothesModel;
import com.hci.digitalwardrobe.models.CreateUserModel;
import com.hci.digitalwardrobe.models.PredictClothesModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GetAllClothes {

    @POST("get_all_clothes/")
    Call<List<ClothesModel>> getAllClothes(@Body Map<String,String> user);

}
