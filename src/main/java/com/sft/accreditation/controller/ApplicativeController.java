/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.accreditation.controller;

import com.jmoordb.core.model.Search;
import com.jmoordb.core.util.DocumentUtil;
import com.jmoordb.core.util.MessagesUtil;
import com.sft.model.Applicative;
import com.sft.repository.ApplicativeRepository;
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
@Path("applicative")
@Tag(name = "Información del applicative", description = "End-point para entidad Applicative")
@RolesAllowed({"admin"})
public class ApplicativeController {

    
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    ApplicativeRepository applicativeRepository;
    
 

    @Inject
    @Metric(name = "counter")
    private Counter counter;

    @Inject
    @Metric(name = "idapplicativehistrograma", description = "Ejemplo de histograma.",
            displayName = "Histogra de idapplicative con paginación")
    private Histogram histogram;

    @Inject
    private MetricRegistry registry;

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="  @Path("insert")">
    @Path("insert")
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Applicative> insert(@QueryParam("inicial") final Integer inicial) {
//
//        Integer limiteFactor = 13545;
//
//        Integer maximo = inicial + limiteFactor;
//        for (int i = inicial; i <= maximo; i++) {
//
//            Applicative applicative = new Applicative();
//            applicative.setIdapplicative(JmoordbCoreUtil.integerToLong(i));
//            applicative.setApplicative("Applicative - " + applicative.getIdapplicative());
//            applicative.setFecha(new Date());
//            applicativeRepository.save(applicative);
//        }
        return new ArrayList<>();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="findAll">
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Timed(name = "applicativeesFindAll",
            description = "Monitorea el tiempo en que se obtiene la lista de todos los applicativees",
            unit = MetricUnits.MILLISECONDS, absolute = true)
    @Operation(summary = "Obtiene todos los applicativees", description = "Retorna todos los applicativees disponibles")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los applicativees")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los applicativees", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los applicativees", required = true, name = "applicativees")))
    public List<Applicative> findAll() {
        
        return applicativeRepository.findAll();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Applicative findByIdapplicative">
    @GET
    @RolesAllowed({"admin"})
    @Path("{idapplicative}")
    @Operation(summary = "Busca un applicative por el idapplicative", description = "Busqueda de applicative por idapplicative")
    @APIResponse(responseCode = "200", description = "El applicative")
    @APIResponse(responseCode = "404", description = "Cuando no existe el idapplicative")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El applicative", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Applicative.class)))
    public Applicative findByIdapplicative(
            @Parameter(description = "El idapplicative", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idapplicative") Long idapplicative) {

        counter.inc();

        return applicativeRepository.findByPk(idapplicative).orElseThrow(
                () -> new WebApplicationException("No hay applicative con idapplicative " + idapplicative, Response.Status.NOT_FOUND));

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response save">
    @POST
    @RolesAllowed({"admin"})
    @Metered(name = "applicativeSave",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor la rata de eventos ocurridos al insertar applicative",
            absolute = true)
    @Operation(summary = "Inserta un nuevo applicative", description = "Inserta un nuevo applicative")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  applicative")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response save(
            @RequestBody(description = "Crea un nuevo applicative.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Applicative.class))) Applicative applicative) {


        return Response.status(Response.Status.CREATED).entity(applicativeRepository.save(applicative)).build();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response update">

    @PUT
    @RolesAllowed({"admin"})
    @Operation(summary = "Inserta un nuevo applicative", description = "Inserta un nuevo applicative")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  applicative")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo applicative.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Applicative.class))) Applicative applicative) {


        return Response.status(Response.Status.CREATED).entity(applicativeRepository.save(applicative)).build();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response delete">
    @DELETE
    @RolesAllowed({"admin"})
    @Path("{idapplicative}")
    @Operation(summary = "Elimina un applicative por  idapplicative", description = "Elimina un applicative por su idapplicative")
    @APIResponse(responseCode = "200", description = "Cuando elimina el applicative")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response delete(
            @Parameter(description = "El elemento idapplicative", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idapplicative") Long idapplicative) {
        applicativeRepository.deleteByPk(idapplicative);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="List<Applicative> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size)">

    @GET
    @Path("lookup")
    @RolesAllowed({"admin"})
    @Operation(summary = "Busca un appconfiguration", description = "Busqueda de user por search")
    @APIResponse(responseCode = "200", description = "Applicative")
    @APIResponse(responseCode = "404", description = "Cuando no existe la condicion en el search")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El search", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Applicative.class)))

    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Applicative> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size) {
        List<Applicative> suggestions = new ArrayList<>();
        try {

        Search search = DocumentUtil.convertForLookup(filter, sort, page, size);
        suggestions = applicativeRepository.lookup(search);

        } catch (Exception e) {
       
          MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }

        return suggestions;
    }

    // </editor-fold>
    
}
