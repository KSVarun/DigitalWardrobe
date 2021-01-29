package com.hci.digitalwardrobe;

public class IndividualClothDetails {
    private String dress_name;
    private String category;

    public IndividualClothDetails(String dress_name, String category) {
        this.dress_name = dress_name;
        this.category = category;
    }

    public String getDress_name() {
        return dress_name;
    }

    public void setDress_name(String dress_name) {
        this.dress_name = dress_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
