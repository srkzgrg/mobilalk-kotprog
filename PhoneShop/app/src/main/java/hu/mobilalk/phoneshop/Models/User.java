package hu.mobilalk.phoneshop.Models;

public class User {
    private String id;
    private String email;
    private String nev;

    public User(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public User(String id, String email, String nev) {
        this.id = id;
        this.email = email;
        this.nev = nev;
    }
}
