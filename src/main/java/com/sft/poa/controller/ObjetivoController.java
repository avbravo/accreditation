/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.poa.controller;

import com.sft.model.Area;
import com.sft.model.Objetivo;
import com.sft.repository.ObjetivoRepository;
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
@Path("objetivo")
@Tag(name = "Información del objetivo", description = "End-point para entidad Objetivo")
@RolesAllowed({"admin"})
public class ObjetivoController {

    
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    ObjetivoRepository objetivoRepository;
    
 

    @Inject
    @Metric(name = "counter")
    private Counter counter;

    @Inject
    @Metric(name = "idobjetivohistrograma", description = "Ejemplo de histograma.",
            displayName = "Histogra de idobjetivo con paginación")
    private Histogram histogram;

    @Inject
    private MetricRegistry registry;

// </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="findAll">
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Timed(name = "objetivoesFindAll",
            description = "Monitorea el tiempo en que se obtiene la lista de todos los objetivoes",
            unit = MetricUnits.MILLISECONDS, absolute = true)
    @Operation(summary = "Obtiene todos los objetivoes", description = "Retorna todos los objetivoes disponibles")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los objetivoes")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los objetivoes", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los objetivoes", required = true, name = "objetivoes")))
    public List<Objetivo> findAll() {
        
       
        return objetivoRepository.findAll();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Objetivo findByIdobjetivo">
    @GET
    @RolesAllowed({"admin"})
    @Path("{idobjetivo}")
    @Operation(summary = "Busca un objetivo por el idobjetivo", description = "Busqueda de objetivo por idobjetivo")
    @APIResponse(responseCode = "200", description = "El objetivo")
    @APIResponse(responseCode = "404", description = "Cuando no existe el idobjetivo")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El objetivo", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Objetivo.class)))
    public Objetivo findByIdobjetivo(
            @Parameter(description = "El idobjetivo", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idobjetivo") Long idobjetivo) {

        counter.inc();

        return objetivoRepository.findByPk(idobjetivo).orElseThrow(
                () -> new WebApplicationException("No hay objetivo con idobjetivo " + idobjetivo, Response.Status.NOT_FOUND));

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response save">
    @POST
    @RolesAllowed({"admin"})
    @Metered(name = "objetivoSave",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor la rata de eventos ocurridos al insertar objetivo",
            absolute = true)
    @Operation(summary = "Inserta un nuevo objetivo", description = "Inserta un nuevo objetivo")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  objetivo")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response save(
            @RequestBody(description = "Crea un nuevo objetivo.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Objetivo.class))) Objetivo objetivo) {


        return Response.status(Response.Status.CREATED).entity(objetivoRepository.save(objetivo)).build();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response update">

    @PUT
    @RolesAllowed({"admin"})
    @Operation(summary = "Inserta un nuevo objetivo", description = "Inserta un nuevo objetivo")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  objetivo")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo objetivo.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Objetivo.class))) Objetivo objetivo) {


        return Response.status(Response.Status.CREATED).entity(objetivoRepository.update(objetivo)).build();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response delete">
    @DELETE
    @RolesAllowed({"admin"})
    @Path("{idobjetivo}")
    @Operation(summary = "Elimina un objetivo por  idobjetivo", description = "Elimina un objetivo por su idobjetivo")
    @APIResponse(responseCode = "200", description = "Cuando elimina el objetivo")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response delete(
            @Parameter(description = "El elemento idobjetivo", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idobjetivo") Long idobjetivo) {
        objetivoRepository.deleteByPk(idobjetivo);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    // </editor-fold>
    
}
