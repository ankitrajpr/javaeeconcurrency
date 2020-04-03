package com.app.rest;
/* =================================

author ankitrajprasad created on 03/04/20 
inside the package - com.app.rest 

=====================================*/

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("greetUser")
public class GreetResource {

    @GET
    public String greetUser(){
        return "JAVA EE Concurrency Starts! ";
    }
}
