/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.accreditation.controller;

import com.sft.model.Appconfiguration;
import com.sft.repository.AppconfigurationRepository;
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
@Path("appconfiguration")
@Tag(name = "Información del appconfiguration", description = "End-point para entidad Appconfiguration")
public class AppconfigurationController {

    
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    AppconfigurationRepository appconfigurationRepository;
    
    
 

    @Inject
    @Metric(name = "counter")
    private Counter counter;

    @Inject
    @Metric(name = "idappconfigurationhistrograma", description = "Ejemplo de histograma.",
            displayName = "Histogra de idappconfiguration con paginación")
    private Histogram histogram;

    @Inject
    private MetricRegistry registry;

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="  @Path("insert")">
    @Path("insert")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Appconfiguration> insert(@QueryParam("inicial") final Integer inicial) {
//
//        Integer limiteFactor = 13545;
//
//        Integer maximo = inicial + limiteFactor;
//        for (int i = inicial; i <= maximo; i++) {
//
//            Appconfiguration appconfiguration = new Appconfiguration();
//            appconfiguration.setIdappconfiguration(JmoordbCoreUtil.integerToLong(i));
//            appconfiguration.setAppconfiguration("Appconfiguration - " + appconfiguration.getIdappconfiguration());
//            appconfiguration.setFecha(new Date());
//            appconfigurationRepository.save(appconfiguration);
//        }
        return new ArrayList<>();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="findAll">
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Timed(name = "appconfigurationesFindAll",
            description = "Monitorea el tiempo en que se obtiene la lista de todos los appconfigurationes",
            unit = MetricUnits.MILLISECONDS, absolute = true)
    @Operation(summary = "Obtiene todos los appconfigurationes", description = "Retorna todos los appconfigurationes disponibles")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los appconfigurationes")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los appconfigurationes", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los appconfigurationes", required = true, name = "appconfigurationes")))
    public List<Appconfiguration> findAll() {
        
        return appconfigurationRepository.findAll();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Appconfiguration findByIdappconfiguration">
    @GET
    @Path("{idappconfiguration}")
    @Operation(summary = "Busca un appconfiguration por el idappconfiguration", description = "Busqueda de appconfiguration por idappconfiguration")
    @APIResponse(responseCode = "200", description = "El appconfiguration")
    @APIResponse(responseCode = "404", description = "Cuando no existe el idappconfiguration")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El appconfiguration", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Appconfiguration.class)))
    public Appconfiguration findByIdappconfiguration(
            @Parameter(description = "El idappconfiguration", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idappconfiguration") Long idappconfiguration) {

        counter.inc();

        return appconfigurationRepository.findByPk(idappconfiguration).orElseThrow(
                () -> new WebApplicationException("No hay appconfiguration con idappconfiguration " + idappconfiguration, Response.Status.NOT_FOUND));

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response save">
    @POST
    @Metered(name = "appconfigurationSave",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor la rata de eventos ocurridos al insertar appconfiguration",
            absolute = true)
    @Operation(summary = "Inserta un nuevo appconfiguration", description = "Inserta un nuevo appconfiguration")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  appconfiguration")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response save(
            @RequestBody(description = "Crea un nuevo appconfiguration.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Appconfiguration.class))) Appconfiguration appconfiguration) {


        return Response.status(Response.Status.CREATED).entity(appconfigurationRepository.save(appconfiguration)).build();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response update">

    @PUT
    @Operation(summary = "Inserta un nuevo appconfiguration", description = "Inserta un nuevo appconfiguration")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  appconfiguration")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo appconfiguration.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Appconfiguration.class))) Appconfiguration appconfiguration) {


        return Response.status(Response.Status.CREATED).entity(appconfigurationRepository.save(appconfiguration)).build();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response delete">
    @DELETE
    @Path("{idappconfiguration}")
    @Operation(summary = "Elimina un appconfiguration por  idappconfiguration", description = "Elimina un appconfiguration por su idappconfiguration")
    @APIResponse(responseCode = "200", description = "Cuando elimina el appconfiguration")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response delete(
            @Parameter(description = "El elemento idappconfiguration", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idappconfiguration") Long idappconfiguration) {
        appconfigurationRepository.deleteByPk(idappconfiguration);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    // </editor-fold>
    
}
