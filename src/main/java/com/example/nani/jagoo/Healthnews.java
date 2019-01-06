package com.example.nani.jagoo;

public class Healthnews {
    String Title;
    String Imgurl;
    String Desc;
    String PubAt;
    String PubBy;
    String Newsurl;



    public Healthnews(String Title, String Imgurl, String Desc, String PubAt, String PubBy, String Newsurl){
        this.Title=Title;
        this.Imgurl=Imgurl;
        this.Desc=Desc;
        this.PubAt=PubAt;
        this.PubBy=PubBy;
        this.Newsurl=Newsurl;

    }

    public String getNewsurl() {
        return Newsurl;
    }

    public String getTitle() {
        return Title;
    }

    public String getImgurl() {
        return Imgurl;
    }

    public String getDesc() {
        return Desc;
    }

    public String getPubAt() {
        return PubAt;
    }

    public String getPubBy() {
        return PubBy;
    }
}
