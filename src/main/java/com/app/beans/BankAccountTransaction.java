package com.app.beans;
/* =================================

author ankitrajprasad created on 03/04/20 
inside the package - com.app.beans 

=====================================*/


import java.util.Date;

public class BankAccountTransaction {

    private int accNumber;
    private double amount;
    private Date txDate;
    private String txType; //Whether it's credit/debit
    private int txId;  //Unique Identifier

    public int getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(int accNumber) {
        this.accNumber = accNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public String getTxType() {
        return txType;
    }

    public void setTxType(String txType) {
        this.txType = txType;
    }

    public int getTxId() {
        return txId;
    }

    public void setTxId(int txId) {
        this.txId = txId;
    }
}
