/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.epmrpsd.controller;


import com.avbravo.jmoordb.util.encripter.JmoordbBlake3;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


/**
 *
 * @author avbravo
 */
@Path("/brake3")
public class Brake3Controller {
    
 // <editor-fold defaultstate="collapsed" desc="@Path("/search/{idcountry }")">
    @GET
    @Path("/key/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String key(@PathParam("id") String id) {
     String encripter = ""; 
        try {
       encripter=    JmoordbBlake3.encripter(id, 256);
           
        } catch (Exception e) {
            System.out.println("key() " + e.getLocalizedMessage());

        }

        return encripter;
    }
//// </editor-fold>

}
