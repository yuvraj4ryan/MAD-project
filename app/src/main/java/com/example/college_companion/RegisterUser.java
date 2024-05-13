package com.example.college_companion;

public class RegisterUser {
    public String fname,lname,email,phone,ages,sap,downloadURl;
    public RegisterUser()
    {
        //empty but imp
    }

    public RegisterUser(String fname, String lname, String email, String phone, String ages, String sap, String downloadURl) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.ages = ages;
        this.sap = sap;
        this.downloadURl = downloadURl;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAges() {
        return ages;
    }

    public void setAges(String ages) {
        this.ages = ages;
    }

    public String getSap() {
        return sap;
    }

    public void setSap(String sap) {
        this.sap = sap;
    }

    public String getDownloadURl() {
        return downloadURl;
    }

    public void setDownloadURl(String downloadURl) {
        this.downloadURl = downloadURl;
    }


}
