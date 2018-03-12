package com.voyager.diary.Helper;

/**
 * Created by User on 08-Mar-18.
 */

public class UserDetails {

    String FName;
    String pswd;
    String retypePswd;
    String email;
    String phno;
    String city;
    String country;
    public int userID;



    public UserDetails() {
    }

    public UserDetails(int userID, String FName, String pswd, String email, String phno, String city, String country) {
        this.userID = userID;
        this.FName = FName;
        this.pswd = pswd;
        this.email = email;
        this.phno = phno;
        this.city = city;
        this.country = country;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getRetypePswd() {
        return retypePswd;
    }

    public void setRetypePswd(String retypePswd) {
        this.retypePswd = retypePswd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
