package com.hci.digitalwardrobe.models;

import com.hci.digitalwardrobe.RecommendActivity;
import com.hci.digitalwardrobe.TravelRecommendation;

public class RecommendModel {

    RecommendActivity.Activities activity;
    float temperature;
    TravelRecommendation.Condition condition;

    public void setActivity(RecommendActivity.Activities activity) {
        this.activity = activity;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public void setCondition(TravelRecommendation.Condition condition) {
        this.condition = condition;
    }

    public RecommendActivity.Activities getActivity() {
        return activity;
    }

    public float getTemperature() {
        return temperature;
    }

    public TravelRecommendation.Condition getCondition() {
        return condition;
    }
}
