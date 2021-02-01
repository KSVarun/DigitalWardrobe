package com.hci.digitalwardrobe.models;

import java.io.Serializable;

public class PredictClothesModel implements Serializable {

    public String Name;
    public String User;
    public String Weather;
    public String Min_temp;
    public String Max_temp;
    public String Image;
    public String Category;
    public String Sleevelength;
    public String Neckline;
    public String Pattern;
    public String Skin_exposure;
    public String Collar;
    public String Gender;
    public String Scarf;
    public String Necktie;
    public String Placket;
    public String Colour;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getWeather() {
        return Weather;
    }

    public void setWeather(String weather) {
        Weather = weather;
    }

    public String getMin_temp() {
        return Min_temp;
    }

    public void setMin_temp(String min_temp) {
        Min_temp = min_temp;
    }

    public String getMax_temp() {
        return Max_temp;
    }

    public void setMax_temp(String max_temp) {
        Max_temp = max_temp;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = Image;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getSleevelength() {
        return Sleevelength;
    }

    public void setSleevelength(String sleevelength) {
        Sleevelength = sleevelength;
    }

    public String getNeckline() {
        return Neckline;
    }

    public void setNeckline(String neckline) {
        Neckline = neckline;
    }

    public String getPattern() {
        return Pattern;
    }

    public void setPattern(String pattern) {
        Pattern = pattern;
    }

    public String getSkin_exposure() {
        return Skin_exposure;
    }

    public void setSkin_exposure(String skin_exposure) {
        Skin_exposure = skin_exposure;
    }

    public String getCollar() {
        return Collar;
    }

    public void setCollar(String collar) {
        Collar = collar;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getScarf() {
        return Scarf;
    }

    public void setScarf(String scarf) {
        Scarf = scarf;
    }

    public String getNecktie() {
        return Necktie;
    }

    public void setNecktie(String necktie) {
        Necktie = necktie;
    }

    public String getPlacket() {
        return Placket;
    }

    public void setPlacket(String placket) {
        Placket = placket;
    }

    public String getColour() {
        return Colour;
    }

    public void setColour(String colour) {
        Colour = colour;
    }
}
