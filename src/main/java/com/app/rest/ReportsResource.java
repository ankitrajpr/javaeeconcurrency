package com.app.rest;
/* =================================

author ankitrajprasad created on 03/04/20 
inside the package - com.app.rest 

=====================================*/

import com.app.beans.BankAccount;
import com.app.dao.BankAccountDao;
import com.app.runnables.ReportsProcessor;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sun.jndi.url.dns.dnsURLContext;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("reports")
public class ReportsResource {

    private BankAccountDao dao;

    /*
    //Via JNDI look up
    InitialContext initialContext;

    {
        try {
            initialContext = new InitialContext();
            ManagedExecutorService service = (ManagedExecutorService)initialContext.lookup("java:comp/DefaultManagedExecutorService");
        } catch (NamingException e) {
            Logger.getLogger(ReportsResource.class.getName()).log(Level.INFO, null, e);
        }
    }
     */

    @Resource(lookup = "java:comp/DefaultManagedExecutorService")
    private ManagedExecutorService service;

    public ReportsResource() {

        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setUser("root");
            dataSource.setPassword("root");
            dao = new BankAccountDao(dataSource);
        } catch ( PropertyVetoException ex) {
            Logger.getLogger(ReportsResource.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @GET
    public String generateReports(){
        System.out.println("service object from JNDI look up: "+service);
        List<BankAccount> accounts = dao.getAllBankAccounts();
        for(BankAccount account: accounts){
            try {
                Future<Boolean> future = service.submit(new ReportsProcessor(account, dao));
                System.out.println("report generated? "+future.get());
            } catch (InterruptedException ex) {
                Logger.getLogger(ReportsResource.class.getName()).log(Level.SEVERE, null, ex);
            } catch ( ExecutionException ex) {
                Logger.getLogger(ReportsResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "Report generation tasks submitted!";
    }
}
