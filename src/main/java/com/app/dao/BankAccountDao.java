package com.app.dao;
/* =================================

author ankitrajprasad created on 03/04/20 
inside the package - com.app.dao 

--DB transactions
=====================================*/


import com.app.beans.BankAccount;
import com.app.beans.BankAccountTransaction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankAccountDao {

    //DataSource object by c3po -- COnnectionPooling concept
    private DataSource dataSource;

    //Ctor intializing DataSource Object
    public BankAccountDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<BankAccount> getAllBankAccounts() {
        List<BankAccount> accounts = new ArrayList<>();
        BankAccount account = null;
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from bank_account");
            while (resultSet.next()) {
                account = new BankAccount();
                account.setAccNumber(resultSet.getInt("acc_number"));
                account.setName(resultSet.getString("acc_holder_name"));
                account.setEmail(resultSet.getString("acc_email"));
                account.setAccType(resultSet.getString("acc_type"));
                accounts.add(account);
            }
            return accounts;

        }catch (SQLException e)
        {
            Logger.getLogger(BankAccountDao.class.getName()).log(Level.SEVERE,null,e);
        }

        return accounts;
    }

    public List<BankAccountTransaction> getTransactionsForAccount(BankAccount account)
    {
        BankAccountTransaction transaction = null;
        List<BankAccountTransaction> transactions = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from bank_account_transaction where acc_number=? ");
            statement.setInt(1,account.getAccNumber());
            ResultSet set = statement.executeQuery();

            while(set.next())
            {
                transaction = new BankAccountTransaction();
                transaction.setAccNumber(set.getInt("acc_number"));
                transaction.setAmount(set.getDouble("amount"));
                transaction.setTxDate(new Date(
                        set.getDate("transaction_date").getTime()
                ));
                transaction.setTxId(set.getInt("tx_id"));
                transaction.setTxType(set.getString("transcation_type"));
            }


        } catch (SQLException e) {
            Logger.getLogger(BankAccountDao.class.getName()).log(Level.SEVERE,null,e);
        }

        return transactions;


    }

}
