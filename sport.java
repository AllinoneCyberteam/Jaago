package com.example.dell.sports;

public class sport { String title;
                 String urlToImage;
                 String desc;
                 String pubAt;
                 String pubBy;
                 String sportsurl;
        public sport(String title,String urlToImage,String desc,String pubAt,String pubBy,String Sportsurl){
            this.title=title;
            this.urlToImage=urlToImage;
            this.desc=desc;
            this.pubAt=pubAt;
            this.pubBy=pubBy;
            this.sportsurl=sportsurl;

        }
    public String getsportsurl() {
        return sportsurl;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getDesc() {
        return desc;
    }

    public String getPubAt() {
        return pubAt;
    }

    public String getPubBy() {
        return pubBy;
    }
}


