package com.jaago.jaago;

public class News {
    String Title;
    String Imgurl;
    String Desc;
    String PubAt;
    String PubBy;
    String Newsurl;
    String Id;


    public News() {

    }

    public News(String Title, String Imgurl, String Desc, String PubAt, String PubBy, String Newsurl) {
        this.Title = Title;
        this.Imgurl = Imgurl;
        this.Desc = Desc;
        this.PubAt = PubAt;
        this.PubBy = PubBy;
        this.Newsurl = Newsurl;

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

    public void setTitle(String title) {
        Title = title;
    }

    public void setImgurl(String imgurl) {
        Imgurl = imgurl;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public void setPubAt(String pubAt) {
        PubAt = pubAt;
    }

    public void setPubBy(String pubBy) {
        PubBy = pubBy;
    }

    public void setNewsurl(String newsurl) {
        Newsurl = newsurl;
    }

    public void setNewsid(String id) {
        Id = id;
    }

    public String getId() {
        return Id;
    }
}
