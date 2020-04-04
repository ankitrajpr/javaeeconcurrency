package com.app.runnables;
/* =================================

author ankitrajprasad created on 03/04/20 
inside the package - com.app.runnables 

=====================================*/


import com.app.beans.BankAccount;
import com.app.beans.BankAccountTransaction;
import com.app.dao.BankAccountDao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.concurrent.Callable;

public class ReportsProcessor implements Callable<Boolean> {

    private BankAccount account;
    private BankAccountDao dao;

    public ReportsProcessor(BankAccount account, BankAccountDao dao) {
        this.account = account;
        this.dao = dao;
    }

    @Override
    public Boolean call() throws Exception {
        boolean reportGenerated = false;
        List<BankAccountTransaction> transactions = dao.getTransactionsForAccount(account);
        File file = new File("/Users/ankitrajprasad/Downloads/Reports/" + account.getAccNumber() + "_tx_report.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        for (BankAccountTransaction transaction : transactions) {


                writer.write("AccountNumber : " + transaction.getAccNumber());
                writer.write("Transcation type : " + transaction.getTxType());
                writer.write("Tx id : " + transaction.getTxId());
                writer.write("Amount : " + transaction.getAmount());
                writer.write("Transaction Date : " + transaction.getTxDate());
                writer.newLine();

            }
            writer.flush();//Once Iterations is completed, do flush to see Data is definitely written out to the file.
        }
        reportGenerated = true; //True basically when WriterObject job is completed
        return reportGenerated;
    }
}
