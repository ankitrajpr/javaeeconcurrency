package com.app.beans;
/* =================================

author ankitrajprasad created on 03/04/20 
inside the package - com.app.beans 


--Store data in beans so, for OOP, we store data in beans
=====================================*/


public class BankAccount {

    private int accNumber;
    private String name;
    private String email;
    private String accType;

    public int getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(int accNumber) {
        this.accNumber = accNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }




}
