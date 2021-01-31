package com.hci.digitalwardrobe.calls;

import com.hci.digitalwardrobe.models.ClothesModel;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UploadClothesAPI {

    @POST("get/")
    Call<List<ClothesModel>> getActivityClothes(@Body Map<String,String> data);

    @POST("calc_travel/")
    Call<List<ClothesModel>> getFilteredClothes(@Body Map<String,String> data);
}
