/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.poa.controller;

import com.sft.model.Actividad;
import com.sft.repository.ActividadRepository;
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
public class ActividadController {

    
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    ActividadRepository actividadRepository;
    
 

    @Inject
    @Metric(name = "counter")
    private Counter counter;

    @Inject
    @Metric(name = "idactividadhistrograma", description = "Ejemplo de histograma.",
            displayName = "Histogra de idactividad con paginación")
    private Histogram histogram;

    @Inject
    private MetricRegistry registry;

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="  @Path("insert")">
    @Path("insert")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Actividad> insert(@QueryParam("inicial") final Integer inicial) {
//
//        Integer limiteFactor = 13545;
//
//        Integer maximo = inicial + limiteFactor;
//        for (int i = inicial; i <= maximo; i++) {
//
//            Actividad actividad = new Actividad();
//            actividad.setIdactividad(JmoordbCoreUtil.integerToLong(i));
//            actividad.setActividad("Actividad - " + actividad.getIdactividad());
//            actividad.setFecha(new Date());
//            actividadRepository.save(actividad);
//        }
        return new ArrayList<>();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="findAll">
    @GET
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
    @Path("{idactividad}")
    @Operation(summary = "Busca un actividad por el idactividad", description = "Busqueda de actividad por idactividad")
    @APIResponse(responseCode = "200", description = "El actividad")
    @APIResponse(responseCode = "404", description = "Cuando no existe el idactividad")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El actividad", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Actividad.class)))
    public Actividad findByIdactividad(
            @Parameter(description = "El idactividad", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idactividad") Long idactividad) {

        counter.inc();

        return actividadRepository.findByPk(idactividad).orElseThrow(
                () -> new WebApplicationException("No hay actividad con idactividad " + idactividad, Response.Status.NOT_FOUND));

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response save">
    @POST
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
    @Operation(summary = "Inserta un nuevo actividad", description = "Inserta un nuevo actividad")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  actividad")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo actividad.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Actividad.class))) Actividad actividad) {


        return Response.status(Response.Status.CREATED).entity(actividadRepository.save(actividad)).build();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response delete">
    @DELETE
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
    
}
