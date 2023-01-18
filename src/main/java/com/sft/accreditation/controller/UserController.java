/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.accreditation.controller;

import com.sft.model.User;
import com.sft.repository.UserRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.Histogram;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Metric;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 *
 * @author avbravo
 */
@Path("user")
@Tag(name = "Información del user", description = "End-point para entidad User")
@RolesAllowed({"admin"})
public class UserController {

    
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    UserRepository userRepository;
    
 

    @Inject
    @Metric(name = "counter")
    private Counter counter;

    @Inject
    @Metric(name = "iduserhistrograma", description = "Ejemplo de histograma.",
            displayName = "Histogra de iduser con paginación")
    private Histogram histogram;

    @Inject
    private MetricRegistry registry;

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="  @Path("insert")">
    @Path("insert")
    @RolesAllowed({"admin"})
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<User> insert(@QueryParam("inicial") final Integer inicial) {
//
//        Integer limiteFactor = 13545;
//
//        Integer maximo = inicial + limiteFactor;
//        for (int i = inicial; i <= maximo; i++) {
//
//            User user = new User();
//            user.setIduser(JmoordbCoreUtil.integerToLong(i));
//            user.setUser("User - " + user.getIduser());
//            user.setFecha(new Date());
//            userRepository.save(user);
//        }
        return new ArrayList<>();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="findAll">
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
        
    @Timed(name = "useresFindAll",
            description = "Monitorea el tiempo en que se obtiene la lista de todos los useres",
            unit = MetricUnits.MILLISECONDS, absolute = true)
    @Operation(summary = "Obtiene todos los useres", description = "Retorna todos los useres disponibles")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los useres")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los useres", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los useres", required = true, name = "useres")))
    public List<User> findAll() {
       
        return userRepository.findAll();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="User findByIduser">
    @GET
    @RolesAllowed({"admin"})
    @Path("{iduser}")
    @Operation(summary = "Busca un user por el iduser", description = "Busqueda de user por iduser")
    @APIResponse(responseCode = "200", description = "El user")
    @APIResponse(responseCode = "404", description = "Cuando no existe el iduser")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El user", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = User.class)))
    public User findByIduser(
            @Parameter(description = "El iduser", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("iduser") Long iduser) {

        counter.inc();

        return userRepository.findByPk(iduser).orElseThrow(
                () -> new WebApplicationException("No hay user con iduser " + iduser, Response.Status.NOT_FOUND));

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response save">
    @POST
    @RolesAllowed({"admin"})
    @Metered(name = "userSave",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor la rata de eventos ocurridos al insertar user",
            absolute = true)
    @Operation(summary = "Inserta un nuevo user", description = "Inserta un nuevo user")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  user")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response save(
            @RequestBody(description = "Crea un nuevo user.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))) User user) {


        return Response.status(Response.Status.CREATED).entity(userRepository.save(user)).build();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response update">

    @PUT
    @RolesAllowed({"admin"})
    @Operation(summary = "Inserta un nuevo user", description = "Inserta un nuevo user")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  user")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo user.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))) User user) {


        return Response.status(Response.Status.CREATED).entity(userRepository.save(user)).build();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response delete">
    @DELETE
    @RolesAllowed({"admin"})
    @Path("{iduser}")
    @Operation(summary = "Elimina un user por  iduser", description = "Elimina un user por su iduser")
    @APIResponse(responseCode = "200", description = "Cuando elimina el user")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response delete(
            @Parameter(description = "El elemento iduser", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("iduser") Long iduser) {
        userRepository.deleteByPk(iduser);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    // </editor-fold>
    
}
