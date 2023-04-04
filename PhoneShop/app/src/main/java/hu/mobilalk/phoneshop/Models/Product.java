package hu.mobilalk.phoneshop.Models;

public class Product {
    private String id;
    private String model;
    private String marka;
    private int tarhely;
    private String szin;
    private int ar;
    private String image_url;

    public Product() {
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Product(String id, String model, String marka, int tarhely, String szin, int ar, String image) {
        this.id = id;
        this.model = model;
        this.marka = marka;
        this.tarhely = tarhely;
        this.ar = ar;
        this.szin = szin;
        this.image_url = image;
    }

    public Product(String id, String model, String marka, int ar, String image, String szin, int tarhely) {
        this.id = id;
        this.model = model;
        this.marka = marka;
        this.ar = ar;
        this.image_url = image;
        this.szin = szin;
        this.tarhely = tarhely;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public int getTarhely() {
        return tarhely;
    }

    public void setTarhely(int tarhely) {
        this.tarhely = tarhely;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public String getSzin() {
        return szin;
    }

    public void setSzin(String szin) {
        this.szin = szin;
    }
}
