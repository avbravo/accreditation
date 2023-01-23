/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.accreditation.controller;

import com.jmoordb.core.model.Search;
import com.jmoordb.core.util.DocumentUtil;
import com.jmoordb.core.util.MessagesUtil;
import com.sft.model.Departament;
import com.sft.repository.DepartamentRepository;
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
@Path("departament")
@Tag(name = "Información del departament", description = "End-point para entidad Departament")
@RolesAllowed({"admin"})
public class DepartamentController {

    
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    DepartamentRepository departamentRepository;
    
 

   
    private MetricRegistry registry;

// </editor-fold>
  

    // <editor-fold defaultstate="collapsed" desc="findAll">
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Timed(name = "departamentesFindAll",
            description = "Monitorea el tiempo en que se obtiene la lista de todos los departamentes",
            unit = MetricUnits.MILLISECONDS, absolute = true)
    @Operation(summary = "Obtiene todos los departamentes", description = "Retorna todos los departamentes disponibles")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los departamentes")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los departamentes", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los departamentes", required = true, name = "departamentes")))
    public List<Departament> findAll() {
        
        return departamentRepository.findAll();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Departament findByIddepartament">
    @GET
    @RolesAllowed({"admin"})
    @Path("{iddepartament}")
    @Operation(summary = "Busca un departament por el iddepartament", description = "Busqueda de departament por iddepartament")
    @APIResponse(responseCode = "200", description = "El departament")
    @APIResponse(responseCode = "404", description = "Cuando no existe el iddepartament")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El departament", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Departament.class)))
    public Departament findByIddepartament(
            @Parameter(description = "El iddepartament", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("iddepartament") Long iddepartament) {

      

        return departamentRepository.findByPk(iddepartament).orElseThrow(
                () -> new WebApplicationException("No hay departament con iddepartament " + iddepartament, Response.Status.NOT_FOUND));

    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="List<Departament> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size)">

    @GET
    @Path("lookup")
    @RolesAllowed({"admin"})
    @Operation(summary = "Busca un appconfiguration", description = "Busqueda de user por search")
    @APIResponse(responseCode = "200", description = "Departament")
    @APIResponse(responseCode = "404", description = "Cuando no existe la condicion en el search")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El search", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Departament.class)))

    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Departament> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size) {
        List<Departament> suggestions = new ArrayList<>();
        try {

        Search search = DocumentUtil.convertForLookup(filter, sort, page, size);
        suggestions = departamentRepository.lookup(search);

        } catch (Exception e) {
       
          MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }

        return suggestions;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response save">
    @POST
    @RolesAllowed({"admin"})
    @Metered(name = "departamentSave",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor la rata de eventos ocurridos al insertar departament",
            absolute = true)
    @Operation(summary = "Inserta un nuevo departament", description = "Inserta un nuevo departament")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  departament")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response save(
            @RequestBody(description = "Crea un nuevo departament.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Departament.class))) Departament departament) {


        return Response.status(Response.Status.CREATED).entity(departamentRepository.save(departament)).build();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response update">

    @PUT
    @RolesAllowed({"admin"})
    @Operation(summary = "Inserta un nuevo departament", description = "Inserta un nuevo departament")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  departament")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo departament.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Departament.class))) Departament departament) {


        return Response.status(Response.Status.CREATED).entity(departamentRepository.update(departament)).build();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response delete">
    @DELETE
    @RolesAllowed({"admin"})
    @Path("{iddepartament}")
    @Operation(summary = "Elimina un departament por  iddepartament", description = "Elimina un departament por su iddepartament")
    @APIResponse(responseCode = "200", description = "Cuando elimina el departament")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response delete(
            @Parameter(description = "El elemento iddepartament", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("iddepartament") Long iddepartament) {
        departamentRepository.deleteByPk(iddepartament);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    // </editor-fold>
    
}
