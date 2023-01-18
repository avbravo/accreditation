/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.poa.controller;

import com.sft.model.Area;
import com.sft.repository.AreaRepository;
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
@Path("area")
@Tag(name = "Información del area", description = "End-point para entidad Area")
public class AreaController {

    
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    AreaRepository areaRepository;
    
 

    @Inject
    @Metric(name = "counter")
    private Counter counter;

    @Inject
    @Metric(name = "idareahistrograma", description = "Ejemplo de histograma.",
            displayName = "Histogra de idarea con paginación")
    private Histogram histogram;

    @Inject
    private MetricRegistry registry;

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="  @Path("insert")">
    @Path("insert")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Area> insert(@QueryParam("inicial") final Integer inicial) {
//
//        Integer limiteFactor = 13545;
//
//        Integer maximo = inicial + limiteFactor;
//        for (int i = inicial; i <= maximo; i++) {
//
//            Area area = new Area();
//            area.setIdarea(JmoordbCoreUtil.integerToLong(i));
//            area.setArea("Area - " + area.getIdarea());
//            area.setFecha(new Date());
//            areaRepository.save(area);
//        }
        return new ArrayList<>();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="findAll">
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Timed(name = "areaesFindAll",
            description = "Monitorea el tiempo en que se obtiene la lista de todos los areaes",
            unit = MetricUnits.MILLISECONDS, absolute = true)
    @Operation(summary = "Obtiene todos los areaes", description = "Retorna todos los areaes disponibles")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los areaes")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los areaes", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los areaes", required = true, name = "areaes")))
    public List<Area> findAll() {
        
        return areaRepository.findAll();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Area findByIdarea">
    @GET
    @Path("{idarea}")
    @Operation(summary = "Busca un area por el idarea", description = "Busqueda de area por idarea")
    @APIResponse(responseCode = "200", description = "El area")
    @APIResponse(responseCode = "404", description = "Cuando no existe el idarea")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El area", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Area.class)))
    public Area findByIdarea(
            @Parameter(description = "El idarea", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idarea") Long idarea) {

        counter.inc();

        return areaRepository.findByPk(idarea).orElseThrow(
                () -> new WebApplicationException("No hay area con idarea " + idarea, Response.Status.NOT_FOUND));

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response save">
    @POST
    @Metered(name = "areaSave",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor la rata de eventos ocurridos al insertar area",
            absolute = true)
    @Operation(summary = "Inserta un nuevo area", description = "Inserta un nuevo area")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  area")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response save(
            @RequestBody(description = "Crea un nuevo area.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Area.class))) Area area) {


        return Response.status(Response.Status.CREATED).entity(areaRepository.save(area)).build();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response update">

    @PUT
    @Operation(summary = "Inserta un nuevo area", description = "Inserta un nuevo area")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  area")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo area.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Area.class))) Area area) {


        return Response.status(Response.Status.CREATED).entity(areaRepository.save(area)).build();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response delete">
    @DELETE
    @Path("{idarea}")
    @Operation(summary = "Elimina un area por  idarea", description = "Elimina un area por su idarea")
    @APIResponse(responseCode = "200", description = "Cuando elimina el area")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response delete(
            @Parameter(description = "El elemento idarea", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idarea") Long idarea) {
        areaRepository.deleteByPk(idarea);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    // </editor-fold>
    
}
