/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.epmrpsd.controller;

//import com.avbravo.jmoordbutils.JsfUtil;
import com.avbravo.jmoordb.util.JmoordbUtil;
import ec.gob.epmrpsd.model.Province;
import ec.gob.epmrpsd.repository.ProvinceRepository;
import ec.gob.epmrpsd.services.CollectionincrementableServices;
import ec.gob.epmrpsd.services.LoggerServices;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;

/**
 *
 * @author avbravo
 */
@Path("/province")
public class ProvinceController {
    
     // <editor-fold defaultstate="collapsed" desc="@Inject">
    @Inject
           LoggerServices loggerServices;
    @Inject
    ProvinceRepository provinceRepository;
    @Inject
    CollectionincrementableServices collectionincrementableServices;
    // </editor-fold>
     // <editor-fold defaultstate="collapsed" desc="@GET">
    @GET
     //@RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Province> get() {

        return provinceRepository.findAll();
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="@Path("/findall")">
    @GET
    @Path("/findall")
     //@RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Province> findAll() {

        return provinceRepository.findAll();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="@Path("/add")">
    @POST
    @Path("/add")
     //@RolesAllowed({"admin"})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Province province) {
        try {
            
             while (true) {
                
                province.setIdprovince(collectionincrementableServices.generate("province").getCount());
                if (provinceRepository.save(province)) {
                    return Response.status(201).entity(province.getIdprovince()).build();
                } else {
                    if (provinceRepository.getException().getLocalizedMessage().toString().equals("A document with the primary key already exists.")) {                
                    // aqui contar el ultimo y a partir de alli reinicar el conteo.
                    } else {                 
                        return Response.status(400).entity("Error " + provinceRepository.getException().getLocalizedMessage()).build();
                    }
                }
            }
          
        } catch (Exception e) {
  loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, true); 
             
     
            return Response.status(400).entity("Error!!" + e.getLocalizedMessage()).build();

        }

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="@Path("/update")">
    @PUT
    @Path("/update")
     //@RolesAllowed({"admin"})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Province province) {
        try {

            if (provinceRepository.update(province)) {
                return Response.status(201).entity("Ok").build();
            }
//Response.ok().build();
            return Response.status(400).entity("Error"+provinceRepository.getException().getLocalizedMessage()).build();
        } catch (Exception e) {
  loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, true); 
             
     
            return Response.status(400).entity("Error!!" + e.getLocalizedMessage()).build();

        }

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="@Path("/delete")">
    @DELETE
    @Path("/delete")
    //@RolesAllowed({"admin"})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(Province province) {
        try {

            if (provinceRepository.delete(province)) {
                return Response.status(201).entity("Ok").build();
            }
//Response.ok().build();
            return Response.status(400).entity("Error "+provinceRepository.getException().getLocalizedMessage()).build();
        } catch (Exception e) {
          loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, true); 
             
     
            return Response.status(400).entity("Ocurrio un error!!" + e.getLocalizedMessage()).build();
        }

    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="@Path("/search/{idprovince}")">

    @GET
    @Path("/search/{idprovince}")
     //@RolesAllowed({"admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public Province findByIdProvince(@PathParam("idprovince") Integer idprovince) {
        Province province = new Province();
        try {

            province.setIdprovince(idprovince);

            Optional<Province> optional = provinceRepository.findById(province);
            if (optional.isPresent()) {
                province = optional.get();

            }
        } catch (Exception e) {
            loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, true); 
             
     
        }

        return province;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" @Path("/autocomplete/{query}")">
    @GET
    @Path("/autocomplete/{query}")
     //@RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Province> complete(@PathParam("query") String query) {
        List<Province> suggestions = new ArrayList<>();
        try {
         
            query = query.trim();
            suggestions = provinceRepository.findRegex("province", query, true, new Document("province", 1));

        } catch (Exception e) {
            loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, true); 
             
     
        }
        return suggestions;
    }

    // </editor-fold>
    
  
  // <editor-fold defaultstate="collapsed" desc=" @Path("/jsonquery")">
    /**
     * Recive json para consulta y ordenaciones y paginacion y realiza la
     * busqueda
     *
     * @param query
     * @param sort
     * @param pageNumber
     * @param rowForPage
     * @return
     */
    @GET
    @Path("/jsonquery")
     //@RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Province> jsonQuery(@QueryParam("query") String query, @QueryParam("sort") String sort,
            @QueryParam("pagenumber") Integer pageNumber, @QueryParam("rowforpage") Integer rowForPage) {
        List<Province> suggestions = new ArrayList<>();
        try {

            Document docQuery = provinceRepository.jsonToDocument(query);
            Document docSort = provinceRepository.jsonToDocument(sort);

            suggestions = provinceRepository.findPagination(docQuery, pageNumber, rowForPage, docSort);

        } catch (Exception e) {
             loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, true); 
             
     

        }

        return suggestions;
    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="     @Path("/jsonquerywithoutpagination")">
    /**
     * Hace cosultas sin paginacion
     *
     * @param query
     * @param sort
     * @return
     */
    @GET
    @Path("/jsonquerywithoutpagination")
     //@RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Province> jsonQueryWithoutPagination(@QueryParam("query") String query, @QueryParam("sort") String sort) {
        List<Province> suggestions = new ArrayList<>();
        try {

            Document docQuery = provinceRepository.jsonToDocument(query);
            Document docSort = provinceRepository.jsonToDocument(sort);

            suggestions = provinceRepository.findBy(docQuery, docSort);

        } catch (Exception e) {
              loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, true); 
             
     

        }

        return suggestions;
    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Response countJsonQuery((@QueryParam("query") String query)">
    /**
     * Cuenta el numero de registros 
     * @param query
     * @return 
     */
    @GET
    @Path("/countjsonquery")
     //@RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public Response countJsonQuery(@QueryParam("query") String query) {
        List<Province> suggestions = new ArrayList<>();
        try {

            Document docQuery = provinceRepository.jsonToDocument(query);
            Integer total = 0;

            total = provinceRepository.count(docQuery);
            return Response.status(201).entity(total).build();

        } catch (Exception e) {

           loggerServices.processException(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e, true); 
             
     
            return Response.status(400).entity("error: " + provinceRepository.getException().getLocalizedMessage()).build();
        }
    }

// </editor-fold>

}
