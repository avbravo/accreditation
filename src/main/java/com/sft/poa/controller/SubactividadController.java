/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.poa.controller;

import com.sft.model.Subactividad;
import com.sft.repository.SubactividadRepository;
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
import java.util.Date;
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
@Path("subactividad")
@Tag(name = "Información del subactividad", description = "End-point para entidad Subactividad")
public class SubactividadController {

    
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    SubactividadRepository subactividadRepository;
    
 

    @Inject
    @Metric(name = "counter")
    private Counter counter;

    @Inject
    @Metric(name = "idsubactividadhistrograma", description = "Ejemplo de histograma.",
            displayName = "Histogra de idsubactividad con paginación")
    private Histogram histogram;

    @Inject
    private MetricRegistry registry;

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="  @Path("insert")">
    @Path("insert")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Subactividad> insert(@QueryParam("inicial") final Integer inicial) {
//
//        Integer limiteFactor = 13545;
//
//        Integer maximo = inicial + limiteFactor;
//        for (int i = inicial; i <= maximo; i++) {
//
//            Subactividad subactividad = new Subactividad();
//            subactividad.setIdsubactividad(JmoordbCoreUtil.integerToLong(i));
//            subactividad.setSubactividad("Subactividad - " + subactividad.getIdsubactividad());
//            subactividad.setFecha(new Date());
//            subactividadRepository.save(subactividad);
//        }
        return new ArrayList<>();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="findAll">
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Timed(name = "subactividadesFindAll",
            description = "Monitorea el tiempo en que se obtiene la lista de todos los subactividades",
            unit = MetricUnits.MILLISECONDS, absolute = true)
    @Operation(summary = "Obtiene todos los subactividades", description = "Retorna todos los subactividades disponibles")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los subactividades")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los subactividades", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los subactividades", required = true, name = "subactividades")))
    public List<Subactividad> findAll() {
       
        return subactividadRepository.findAll();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Subactividad findByIdsubactividad">
    @GET
    @Path("{idsubactividad}")
    @Operation(summary = "Busca un subactividad por el idsubactividad", description = "Busqueda de subactividad por idsubactividad")
    @APIResponse(responseCode = "200", description = "El subactividad")
    @APIResponse(responseCode = "404", description = "Cuando no existe el idsubactividad")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El subactividad", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Subactividad.class)))
    public Subactividad findByIdsubactividad(
            @Parameter(description = "El idsubactividad", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idsubactividad") Long idsubactividad) {

        counter.inc();

        return subactividadRepository.findByPk(idsubactividad).orElseThrow(
                () -> new WebApplicationException("No hay subactividad con idsubactividad " + idsubactividad, Response.Status.NOT_FOUND));

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response save">
    @POST
    @Metered(name = "subactividadSave",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor la rata de eventos ocurridos al insertar subactividad",
            absolute = true)
    @Operation(summary = "Inserta un nuevo subactividad", description = "Inserta un nuevo subactividad")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  subactividad")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response save(
            @RequestBody(description = "Crea un nuevo subactividad.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Subactividad.class))) Subactividad subactividad) {


        return Response.status(Response.Status.CREATED).entity(subactividadRepository.save(subactividad)).build();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response update">

    @PUT
    @Operation(summary = "Inserta un nuevo subactividad", description = "Inserta un nuevo subactividad")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  subactividad")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo subactividad.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Subactividad.class))) Subactividad subactividad) {


        return Response.status(Response.Status.CREATED).entity(subactividadRepository.save(subactividad)).build();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response delete">
    @DELETE
    @Path("{idsubactividad}")
    @Operation(summary = "Elimina un subactividad por  idsubactividad", description = "Elimina un subactividad por su idsubactividad")
    @APIResponse(responseCode = "200", description = "Cuando elimina el subactividad")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response delete(
            @Parameter(description = "El elemento idsubactividad", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idsubactividad") Long idsubactividad) {
        subactividadRepository.deleteByPk(idsubactividad);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    // </editor-fold>
    
}
