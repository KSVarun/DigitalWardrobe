package com.hci.digitalwardrobe.models;

public class ClothesModel {

    int id;
    String Name;
    String User_id;
    String Created_at;
    String Weather;
    Float Min_temp;
    Float Max_temp;
    String Category;
    String Sleevelength;
    String Neckline;
    String Pattern;
    String Skin_exposure;
    String Collar;
    String Gender;
    String Scarf;
    String Necktie;
    String Placket;
    String Image;
    String Colour;

    public ClothesModel(int Id, String name, String user, String created_at, String weather, Float min_temp, Float max_temp, String category, String sleevelength, String neckline, String pattern, String skin_exposure, String collar, String gender, String scarf, String necktie, String placket, String image) {
        id = Id;
        Name = name;
        User_id = user;
        Created_at = created_at;
        Weather = weather;
        Min_temp = min_temp;
        Max_temp = max_temp;
        Category = category;
        Sleevelength = sleevelength;
        Neckline = neckline;
        Pattern = pattern;
        Skin_exposure = skin_exposure;
        Collar = collar;
        Gender = gender;
        Scarf = scarf;
        Necktie = necktie;
        Placket = placket;
        Image = image;
    }

    public String getColour() {
        return Colour;
    }

    public void setColour(String colour) {
        Colour = colour;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getUser() {
        return User_id;
    }

    public String getCreated_at() {
        return Created_at;
    }

    public String getWeather() {
        return Weather;
    }

    public Float getMin_temp() {
        return Min_temp;
    }

    public Float getMax_temp() {
        return Max_temp;
    }

    public String getCategory() {
        return Category;
    }

    public String getSleevelength() {
        return Sleevelength;
    }

    public String getNeckline() {
        return Neckline;
    }

    public String getPattern() {
        return Pattern;
    }

    public String getSkin_exposure() {
        return Skin_exposure;
    }

    public String getCollar() {
        return Collar;
    }

    public String getGender() {
        return Gender;
    }

    public String getScarf() {
        return Scarf;
    }

    public String getNecktie() {
        return Necktie;
    }

    public String getPlacket() {
        return Placket;
    }

    public String getImage() {
        return Image;
    }
}
