/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.poa.controller;

import com.jmoordb.core.model.Search;
import com.jmoordb.core.util.DocumentUtil;
import com.jmoordb.core.util.MessagesUtil;
import com.sft.model.Actividad;
import com.sft.model.Actividad;
import com.sft.repository.ActividadRepository;
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
@Path("actividad")
@Tag(name = "Información del actividad", description = "End-point para entidad Actividad")
@RolesAllowed({"admin"})
public class ActividadController {

    
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    ActividadRepository actividadRepository;
    
 

 

// </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="findAll">
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Timed(name = "actividadesFindAll",
            description = "Monitorea el tiempo en que se obtiene la lista de todos los actividades",
            unit = MetricUnits.MILLISECONDS, absolute = true)
    @Operation(summary = "Obtiene todos los actividades", description = "Retorna todos los actividades disponibles")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los actividades")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los actividades", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los actividades", required = true, name = "actividades")))
    public List<Actividad> findAll() {
        
        return actividadRepository.findAll();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Actividad findByIdactividad">
    @GET
    @RolesAllowed({"admin"})
    @Path("{idactividad}")
    @Operation(summary = "Busca un actividad por el idactividad", description = "Busqueda de actividad por idactividad")
    @APIResponse(responseCode = "200", description = "El actividad")
    @APIResponse(responseCode = "404", description = "Cuando no existe el idactividad")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El actividad", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Actividad.class)))
    public Actividad findByIdactividad(
            @Parameter(description = "El idactividad", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idactividad") Long idactividad) {

      

        return actividadRepository.findByPk(idactividad).orElseThrow(
                () -> new WebApplicationException("No hay actividad con idactividad " + idactividad, Response.Status.NOT_FOUND));

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response save">
    @POST
    @RolesAllowed({"admin"})
    @Metered(name = "actividadSave",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor la rata de eventos ocurridos al insertar actividad",
            absolute = true)
    @Operation(summary = "Inserta un nuevo actividad", description = "Inserta un nuevo actividad")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  actividad")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response save(
            @RequestBody(description = "Crea un nuevo actividad.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Actividad.class))) Actividad actividad) {


        return Response.status(Response.Status.CREATED).entity(actividadRepository.save(actividad)).build();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response update">

    @PUT
    @RolesAllowed({"admin"})
    @Operation(summary = "Inserta un nuevo actividad", description = "Inserta un nuevo actividad")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  actividad")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo actividad.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Actividad.class))) Actividad actividad) {


        return Response.status(Response.Status.CREATED).entity(actividadRepository.update(actividad)).build();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response delete">
    @DELETE
    @RolesAllowed({"admin"})
    @Path("{idactividad}")
    @Operation(summary = "Elimina un actividad por  idactividad", description = "Elimina un actividad por su idactividad")
    @APIResponse(responseCode = "200", description = "Cuando elimina el actividad")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response delete(
            @Parameter(description = "El elemento idactividad", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idactividad") Long idactividad) {
        actividadRepository.deleteByPk(idactividad);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="List<Actividad> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size)">

    @GET
    @Path("lookup")
    @RolesAllowed({"admin"})
    @Operation(summary = "Busca un actividad", description = "Busqueda de actividad por search")
    @APIResponse(responseCode = "200", description = "Actividad")
    @APIResponse(responseCode = "404", description = "Cuando no existe la condicion en el search")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El search", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Actividad.class)))

    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Actividad> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size) {
        List<Actividad> suggestions = new ArrayList<>();
        try {

        Search search = DocumentUtil.convertForLookup(filter, sort, page, size);
        suggestions = actividadRepository.lookup(search);

        } catch (Exception e) {
       
          MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }

        return suggestions;
    }

    // </editor-fold>
    
}
