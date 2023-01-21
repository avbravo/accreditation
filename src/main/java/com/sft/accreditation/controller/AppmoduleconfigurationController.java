/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.accreditation.controller;

import com.jmoordb.core.model.Search;
import com.jmoordb.core.util.DocumentUtil;
import com.jmoordb.core.util.MessagesUtil;
import com.sft.model.Appmoduleconfiguration;
import com.sft.repository.AppmoduleconfigurationRepository;
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
@Path("appmoduleconfiguration")
@Tag(name = "Información del appmoduleconfiguration", description = "End-point para entidad Appmoduleconfiguration")
@RolesAllowed({"admin"})
public class AppmoduleconfigurationController {

    
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    AppmoduleconfigurationRepository appmoduleconfigurationRepository;
    
    
 

    @Inject
    @Metric(name = "counter")
    private Counter counter;

    @Inject
    @Metric(name = "idappmoduleconfigurationhistrograma", description = "Ejemplo de histograma.",
            displayName = "Histogra de idappmoduleconfiguration con paginación")
    private Histogram histogram;

    @Inject
    private MetricRegistry registry;

// </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="findAll">
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Timed(name = "appmoduleconfigurationesFindAll",
            description = "Monitorea el tiempo en que se obtiene la lista de todos los appmoduleconfigurationes",
            unit = MetricUnits.MILLISECONDS, absolute = true)
    @Operation(summary = "Obtiene todos los appmoduleconfigurationes", description = "Retorna todos los appmoduleconfigurationes disponibles")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los appmoduleconfigurationes")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los appmoduleconfigurationes", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los appmoduleconfigurationes", required = true, name = "appmoduleconfigurationes")))
    public List<Appmoduleconfiguration> findAll() {
        
        return appmoduleconfigurationRepository.findAll();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Appmoduleconfiguration findByIdappmoduleconfiguration">
    @GET
    @RolesAllowed({"admin"})
    @Path("{idappmoduleconfiguration}")
    @Operation(summary = "Busca un appmoduleconfiguration por el idappmoduleconfiguration", description = "Busqueda de appmoduleconfiguration por idappmoduleconfiguration")
    @APIResponse(responseCode = "200", description = "El appmoduleconfiguration")
    @APIResponse(responseCode = "404", description = "Cuando no existe el idappmoduleconfiguration")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El appmoduleconfiguration", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Appmoduleconfiguration.class)))
    public Appmoduleconfiguration findByIdappmoduleconfiguration(
            @Parameter(description = "El idappmoduleconfiguration", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idappmoduleconfiguration") Long idappmoduleconfiguration) {

        counter.inc();

        return appmoduleconfigurationRepository.findByPk(idappmoduleconfiguration).orElseThrow(
                () -> new WebApplicationException("No hay appmoduleconfiguration con idappmoduleconfiguration " + idappmoduleconfiguration, Response.Status.NOT_FOUND));

    }
// </editor-fold>

    
    // <editor-fold defaultstate="collapsed" desc="List<Appmoduleconfiguration> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size)">

    @GET
    @Path("lookup")
    @RolesAllowed({"admin"})
    @Operation(summary = "Busca un appmoduleconfiguration", description = "Busqueda de user por search")
    @APIResponse(responseCode = "200", description = "Appmoduleconfiguration")
    @APIResponse(responseCode = "404", description = "Cuando no existe la condicion en el search")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El search", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Appmoduleconfiguration.class)))

    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Appmoduleconfiguration> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size) {
        List<Appmoduleconfiguration> suggestions = new ArrayList<>();
        try {

        Search search = DocumentUtil.convertForLookup(filter, sort, page, size);
        suggestions = appmoduleconfigurationRepository.lookup(search);

        } catch (Exception e) {
       
          MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }

        return suggestions;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response save">
    @POST
    @RolesAllowed({"admin"})
    @Metered(name = "appmoduleconfigurationSave",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor la rata de eventos ocurridos al insertar appmoduleconfiguration",
            absolute = true)
    @Operation(summary = "Inserta un nuevo appmoduleconfiguration", description = "Inserta un nuevo appmoduleconfiguration")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  appmoduleconfiguration")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response save(
            @RequestBody(description = "Crea un nuevo appmoduleconfiguration.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Appmoduleconfiguration.class))) Appmoduleconfiguration appmoduleconfiguration) {


        return Response.status(Response.Status.CREATED).entity(appmoduleconfigurationRepository.save(appmoduleconfiguration)).build();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response update">

    @PUT
    @RolesAllowed({"admin"})
    @Operation(summary = "Inserta un nuevo appmoduleconfiguration", description = "Inserta un nuevo appmoduleconfiguration")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  appmoduleconfiguration")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo appmoduleconfiguration.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Appmoduleconfiguration.class))) Appmoduleconfiguration appmoduleconfiguration) {


        return Response.status(Response.Status.CREATED).entity(appmoduleconfigurationRepository.save(appmoduleconfiguration)).build();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response delete">
    @DELETE
    @RolesAllowed({"admin"})
    @Path("{idappmoduleconfiguration}")
    @Operation(summary = "Elimina un appmoduleconfiguration por  idappmoduleconfiguration", description = "Elimina un appmoduleconfiguration por su idappmoduleconfiguration")
    @APIResponse(responseCode = "200", description = "Cuando elimina el appmoduleconfiguration")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response delete(
            @Parameter(description = "El elemento idappmoduleconfiguration", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idappmoduleconfiguration") Long idappmoduleconfiguration) {
        appmoduleconfigurationRepository.deleteByPk(idappmoduleconfiguration);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    // </editor-fold>
    
}
