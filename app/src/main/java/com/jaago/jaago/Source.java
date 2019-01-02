package com.jaago.jaago;

public class Source {
    String Name;
    String Country;
    String Category;
    String Language;
    String Srcurl;



public  Source(){

}
    public Source(String Name, String Country, String Category, String Language, String Srcurl){
        this.Name=Name;
        this.Country=Country;
        this.Category=Category;
        this.Language=Language;
        this.Srcurl=Srcurl;
    }

    public String getName() {
        return Name;
    }

    public String getCountry() {
        return Country;
    }

    public String getCategory() {
        return Category;
    }

    public String getLanguage() {
        return Language;
    }

    public String getSrcurl() {
        return Srcurl;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public void setSrcurl(String srcurl) {
        Srcurl = srcurl;
    }
}
