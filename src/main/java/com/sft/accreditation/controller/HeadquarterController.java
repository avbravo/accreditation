/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.accreditation.controller;

import com.sft.model.Headquarter;
import com.sft.repository.HeadquarterRepository;
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
@Path("headquarter")
@Tag(name = "Información del headquarter", description = "End-point para entidad Headquarter")
public class HeadquarterController {

    
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    HeadquarterRepository headquarterRepository;
    
 

    @Inject
    @Metric(name = "counter")
    private Counter counter;

    @Inject
    @Metric(name = "idheadquarterhistrograma", description = "Ejemplo de histograma.",
            displayName = "Histogra de idheadquarter con paginación")
    private Histogram histogram;

    @Inject
    private MetricRegistry registry;

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="  @Path("insert")">
    @Path("insert")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Headquarter> insert(@QueryParam("inicial") final Integer inicial) {
//
//        Integer limiteFactor = 13545;
//
//        Integer maximo = inicial + limiteFactor;
//        for (int i = inicial; i <= maximo; i++) {
//
//            Headquarter headquarter = new Headquarter();
//            headquarter.setIdheadquarter(JmoordbCoreUtil.integerToLong(i));
//            headquarter.setHeadquarter("Headquarter - " + headquarter.getIdheadquarter());
//            headquarter.setFecha(new Date());
//            headquarterRepository.save(headquarter);
//        }
        return new ArrayList<>();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="findAll">
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Timed(name = "headquarteresFindAll",
            description = "Monitorea el tiempo en que se obtiene la lista de todos los headquarteres",
            unit = MetricUnits.MILLISECONDS, absolute = true)
    @Operation(summary = "Obtiene todos los headquarteres", description = "Retorna todos los headquarteres disponibles")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los headquarteres")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los headquarteres", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los headquarteres", required = true, name = "headquarteres")))
    public List<Headquarter> findAll() {
        
        return headquarterRepository.findAll();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Headquarter findByIdheadquarter">
    @GET
    @Path("{idheadquarter}")
    @Operation(summary = "Busca un headquarter por el idheadquarter", description = "Busqueda de headquarter por idheadquarter")
    @APIResponse(responseCode = "200", description = "El headquarter")
    @APIResponse(responseCode = "404", description = "Cuando no existe el idheadquarter")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El headquarter", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Headquarter.class)))
    public Headquarter findByIdheadquarter(
            @Parameter(description = "El idheadquarter", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idheadquarter") Long idheadquarter) {

        counter.inc();

        return headquarterRepository.findByPk(idheadquarter).orElseThrow(
                () -> new WebApplicationException("No hay headquarter con idheadquarter " + idheadquarter, Response.Status.NOT_FOUND));

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response save">
    @POST
    @Metered(name = "headquarterSave",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor la rata de eventos ocurridos al insertar headquarter",
            absolute = true)
    @Operation(summary = "Inserta un nuevo headquarter", description = "Inserta un nuevo headquarter")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  headquarter")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response save(
            @RequestBody(description = "Crea un nuevo headquarter.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Headquarter.class))) Headquarter headquarter) {


        return Response.status(Response.Status.CREATED).entity(headquarterRepository.save(headquarter)).build();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response update">

    @PUT
    @Operation(summary = "Inserta un nuevo headquarter", description = "Inserta un nuevo headquarter")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  headquarter")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo headquarter.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Headquarter.class))) Headquarter headquarter) {


        return Response.status(Response.Status.CREATED).entity(headquarterRepository.save(headquarter)).build();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response delete">
    @DELETE
    @Path("{idheadquarter}")
    @Operation(summary = "Elimina un headquarter por  idheadquarter", description = "Elimina un headquarter por su idheadquarter")
    @APIResponse(responseCode = "200", description = "Cuando elimina el headquarter")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response delete(
            @Parameter(description = "El elemento idheadquarter", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idheadquarter") Long idheadquarter) {
        headquarterRepository.deleteByPk(idheadquarter);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    // </editor-fold>
    
}
