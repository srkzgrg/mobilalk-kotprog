package hu.mobilalk.phoneshop.Models;

public class Cart {
    private String userid;
    private Product termek;
    private int osszar;
    private int mennyiseg;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Product getTermek() {
        return termek;
    }

    public void setTermek(Product termek) {
        this.termek = termek;
    }

    public int getOsszar() {
        return osszar;
    }

    public void setOsszar(int osszar) {
        this.osszar = osszar;
    }

    public int getMennyiseg() {
        return mennyiseg;
    }

    public void setMennyiseg(int mennyiseg) {
        this.mennyiseg = mennyiseg;
    }

    public Cart() {
    }

    public Cart(String userid, Product termek, int osszar, int mennyiseg) {
        this.userid = userid;
        this.termek = termek;
        this.osszar = osszar;
        this.mennyiseg = mennyiseg;
    }
}
