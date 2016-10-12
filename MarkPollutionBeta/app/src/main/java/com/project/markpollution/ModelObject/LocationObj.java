package com.project.markpollution.ModelObject;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.project.markpollution.ModelObject
 * Name project: MarkPollution
 * Date: 10/8/2016
 * Time: 2:37 AM
 */
public class LocationObj {
    public int id_Po;
    public int id_Cate;
    public int id_User;
    public String image;
    public String titlePo;
    public String descPo;
    public String timePo;
    public double Latitude;
    public double Longitude;


    public LocationObj(double Latitude, double Longitude) {
        this.Latitude = Latitude;
        this.Longitude = Longitude;
    }

    public LocationObj(String image, String titlePo, String descPo) {
        this.image = image;
        this.titlePo = titlePo;
        this.descPo = descPo;
    }

    public LocationObj(int id_Po, int id_Cate, int id_User, String image, String titlePo, String descPo, String timePo, double latitude, double longitude) {
        this.id_Po = id_Po;
        this.id_Cate = id_Cate;
        this.id_User = id_User;
        this.image = image;
        this.titlePo = titlePo;
        this.descPo = descPo;
        this.timePo = timePo;
        Latitude = latitude;
        Longitude = longitude;
    }

    public int getId_Po() {
        return id_Po;
    }

    public void setId_Po(int id_Po) {
        this.id_Po = id_Po;
    }

    public int getId_Cate() {
        return id_Cate;
    }

    public void setId_Cate(int id_Cate) {
        this.id_Cate = id_Cate;
    }

    public int getId_User() {
        return id_User;
    }

    public void setId_User(int id_User) {
        this.id_User = id_User;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitlePo() {
        return titlePo;
    }

    public void setTitlePo(String titlePo) {
        this.titlePo = titlePo;
    }

    public String getDescPo() {
        return descPo;
    }

    public void setDescPo(String descPo) {
        this.descPo = descPo;
    }

    public String getTimePo() {
        return timePo;
    }

    public void setTimePo(String timePo) {
        this.timePo = timePo;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }
}
