package com.hci.digitalwardrobe.calls;

import com.hci.digitalwardrobe.models.PredictClothesModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UploadClothesPrediction {

    @POST("predict/")
    Call<PredictClothesModel> predictClothes(@Body PredictClothesModel predictClothesModel);
}
