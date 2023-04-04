package hu.mobilalk.phoneshop.Models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int vegosszeg;
    private ArrayList<Cart> termekek;
    private String user_id;
    private Date datum;
    private String cim;
    private String telefon;

    public Order(int vegosszeg, ArrayList<Cart> termekek, String user_id, Date datum, String cim, String telefon) {
        this.vegosszeg = vegosszeg;
        this.termekek = termekek;
        this.user_id = user_id;
        this.datum = datum;
        this.cim = cim;
        this.telefon = telefon;
    }

    public Order() {
    }




    public int getVegosszeg() {
        return vegosszeg;
    }

    public void setVegosszeg(int vegosszeg) {
        this.vegosszeg = vegosszeg;
    }

    public ArrayList<Cart> getTermekek() {
        return termekek;
    }

    public void setTermekek(ArrayList<Cart> termekek) {
        this.termekek = termekek;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
