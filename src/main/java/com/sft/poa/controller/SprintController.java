/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.poa.controller;

import com.sft.model.Sprint;
import com.sft.repository.SprintRepository;
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
@Path("sprint")
@Tag(name = "Información del sprint", description = "End-point para entidad Sprint")
  @RolesAllowed({"admin"})
public class SprintController {

    
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    SprintRepository sprintRepository;
    
 

    @Inject
    @Metric(name = "counter")
    private Counter counter;

    @Inject
    @Metric(name = "idsprinthistrograma", description = "Ejemplo de histograma.",
            displayName = "Histogra de idsprint con paginación")
    private Histogram histogram;

    @Inject
    private MetricRegistry registry;

// </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="findAll">
    @GET
      @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Timed(name = "sprintesFindAll",
            description = "Monitorea el tiempo en que se obtiene la lista de todos los sprintes",
            unit = MetricUnits.MILLISECONDS, absolute = true)
    @Operation(summary = "Obtiene todos los sprintes", description = "Retorna todos los sprintes disponibles")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los sprintes")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los sprintes", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los sprintes", required = true, name = "sprintes")))
    public List<Sprint> findAll() {
        
        return sprintRepository.findAll();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Sprint findByIdsprint">
    @GET
      @RolesAllowed({"admin"})
    @Path("{idsprint}")
    @Operation(summary = "Busca un sprint por el idsprint", description = "Busqueda de sprint por idsprint")
    @APIResponse(responseCode = "200", description = "El sprint")
    @APIResponse(responseCode = "404", description = "Cuando no existe el idsprint")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El sprint", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Sprint.class)))
    public Sprint findByIdsprint(
            @Parameter(description = "El idsprint", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idsprint") Long idsprint) {

        counter.inc();

        return sprintRepository.findByPk(idsprint).orElseThrow(
                () -> new WebApplicationException("No hay sprint con idsprint " + idsprint, Response.Status.NOT_FOUND));

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response save">
    @POST
      @RolesAllowed({"admin"})
    @Metered(name = "sprintSave",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor la rata de eventos ocurridos al insertar sprint",
            absolute = true)
    @Operation(summary = "Inserta un nuevo sprint", description = "Inserta un nuevo sprint")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  sprint")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response save(
            @RequestBody(description = "Crea un nuevo sprint.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sprint.class))) Sprint sprint) {


        return Response.status(Response.Status.CREATED).entity(sprintRepository.save(sprint)).build();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response update">

    @PUT
      @RolesAllowed({"admin"})
    @Operation(summary = "Inserta un nuevo sprint", description = "Inserta un nuevo sprint")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  sprint")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo sprint.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sprint.class))) Sprint sprint) {


        return Response.status(Response.Status.CREATED).entity(sprintRepository.update(sprint)).build();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response delete">
    @DELETE
    @RolesAllowed({"admin"})
    @Path("{idsprint}")
    @Operation(summary = "Elimina un sprint por  idsprint", description = "Elimina un sprint por su idsprint")
    @APIResponse(responseCode = "200", description = "Cuando elimina el sprint")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response delete(
            @Parameter(description = "El elemento idsprint", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idsprint") Long idsprint) {
        sprintRepository.deleteByPk(idsprint);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    // </editor-fold>
    
}
