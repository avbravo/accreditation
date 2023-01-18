/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.accreditation.controller;

import com.sft.model.Zone;
import com.sft.repository.ZoneRepository;
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
@Path("zone")
@Tag(name = "Información del zone", description = "End-point para entidad Zone")
@RolesAllowed({"admin"})
public class ZoneController {

    
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    ZoneRepository zoneRepository;
    
 

    @Inject
    @Metric(name = "counter")
    private Counter counter;

    @Inject
    @Metric(name = "idzonehistrograma", description = "Ejemplo de histograma.",
            displayName = "Histogra de idzone con paginación")
    private Histogram histogram;

    @Inject
    private MetricRegistry registry;

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="  @Path("insert")">
    @Path("insert")
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Zone> insert(@QueryParam("inicial") final Integer inicial) {
//
//        Integer limiteFactor = 13545;
//
//        Integer maximo = inicial + limiteFactor;
//        for (int i = inicial; i <= maximo; i++) {
//
//            Zone zone = new Zone();
//            zone.setIdzone(JmoordbCoreUtil.integerToLong(i));
//            zone.setZone("Zone - " + zone.getIdzone());
//            zone.setFecha(new Date());
//            zoneRepository.save(zone);
//        }
        return new ArrayList<>();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="findAll">
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Timed(name = "zoneesFindAll",
            description = "Monitorea el tiempo en que se obtiene la lista de todos los zonees",
            unit = MetricUnits.MILLISECONDS, absolute = true)
    @Operation(summary = "Obtiene todos los zonees", description = "Retorna todos los zonees disponibles")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los zonees")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los zonees", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los zonees", required = true, name = "zonees")))
    public List<Zone> findAll() {
        
        return zoneRepository.findAll();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Zone findByIdzone">
    @GET
    @RolesAllowed({"admin"})
    @Path("{idzone}")
    @Operation(summary = "Busca un zone por el idzone", description = "Busqueda de zone por idzone")
    @APIResponse(responseCode = "200", description = "El zone")
    @APIResponse(responseCode = "404", description = "Cuando no existe el idzone")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El zone", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Zone.class)))
    public Zone findByIdzone(
            @Parameter(description = "El idzone", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idzone") Long idzone) {

        counter.inc();

        return zoneRepository.findByPk(idzone).orElseThrow(
                () -> new WebApplicationException("No hay zone con idzone " + idzone, Response.Status.NOT_FOUND));

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response save">
    @POST
    @RolesAllowed({"admin"})
    @Metered(name = "zoneSave",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor la rata de eventos ocurridos al insertar zone",
            absolute = true)
    @Operation(summary = "Inserta un nuevo zone", description = "Inserta un nuevo zone")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  zone")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response save(
            @RequestBody(description = "Crea un nuevo zone.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Zone.class))) Zone zone) {


        return Response.status(Response.Status.CREATED).entity(zoneRepository.save(zone)).build();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response update">

    @PUT
    @RolesAllowed({"admin"})
    @Operation(summary = "Inserta un nuevo zone", description = "Inserta un nuevo zone")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  zone")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo zone.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Zone.class))) Zone zone) {


        return Response.status(Response.Status.CREATED).entity(zoneRepository.save(zone)).build();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response delete">
    @DELETE
    @RolesAllowed({"admin"})
    @Path("{idzone}")
    @Operation(summary = "Elimina un zone por  idzone", description = "Elimina un zone por su idzone")
    @APIResponse(responseCode = "200", description = "Cuando elimina el zone")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response delete(
            @Parameter(description = "El elemento idzone", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idzone") Long idzone) {
        zoneRepository.deleteByPk(idzone);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    // </editor-fold>
    
}
