/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.accreditation.controller;

import com.jmoordb.core.model.Search;
import com.jmoordb.core.util.DocumentUtil;
import com.jmoordb.core.util.MessagesUtil;
import com.sft.model.Institution;
import com.sft.repository.InstitutionRepository;
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
@Path("institution")
@Tag(name = "Información del institution", description = "End-point para entidad Institution")
@RolesAllowed({"admin"})
public class InstitutionController {

    
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    InstitutionRepository institutionRepository;
    
 

    @Inject
    @Metric(name = "counter")
    private Counter counter;

    @Inject
    @Metric(name = "idinstitutionhistrograma", description = "Ejemplo de histograma.",
            displayName = "Histogra de idinstitution con paginación")
    private Histogram histogram;

    @Inject
    private MetricRegistry registry;

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="  @Path("insert")">
    @Path("insert")
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Institution> insert(@QueryParam("inicial") final Integer inicial) {
//
//        Integer limiteFactor = 13545;
//
//        Integer maximo = inicial + limiteFactor;
//        for (int i = inicial; i <= maximo; i++) {
//
//            Institution institution = new Institution();
//            institution.setIdinstitution(JmoordbCoreUtil.integerToLong(i));
//            institution.setInstitution("Institution - " + institution.getIdinstitution());
//            institution.setFecha(new Date());
//            institutionRepository.save(institution);
//        }
        return new ArrayList<>();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="findAll">
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Timed(name = "institutionesFindAll",
            description = "Monitorea el tiempo en que se obtiene la lista de todos los institutiones",
            unit = MetricUnits.MILLISECONDS, absolute = true)
    @Operation(summary = "Obtiene todos los institutiones", description = "Retorna todos los institutiones disponibles")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los institutiones")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los institutiones", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los institutiones", required = true, name = "institutiones")))
    public List<Institution> findAll() {
        
        return institutionRepository.findAll();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Institution findByIdinstitution">
    @GET
    @RolesAllowed({"admin"})
    @Path("{idinstitution}")
    @Operation(summary = "Busca un institution por el idinstitution", description = "Busqueda de institution por idinstitution")
    @APIResponse(responseCode = "200", description = "El institution")
    @APIResponse(responseCode = "404", description = "Cuando no existe el idinstitution")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El institution", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Institution.class)))
    public Institution findByIdinstitution(
            @Parameter(description = "El idinstitution", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idinstitution") Long idinstitution) {

        counter.inc();

        return institutionRepository.findByPk(idinstitution).orElseThrow(
                () -> new WebApplicationException("No hay institution con idinstitution " + idinstitution, Response.Status.NOT_FOUND));

    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="List<Institution> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size)">

    @GET
    @Path("lookup")
    @RolesAllowed({"admin"})
    @Operation(summary = "Busca un appconfiguration", description = "Busqueda de user por search")
    @APIResponse(responseCode = "200", description = "Institution")
    @APIResponse(responseCode = "404", description = "Cuando no existe la condicion en el search")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El search", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Institution.class)))

    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Institution> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size) {
        List<Institution> suggestions = new ArrayList<>();
        try {

        Search search = DocumentUtil.convertForLookup(filter, sort, page, size);
        suggestions = institutionRepository.lookup(search);

        } catch (Exception e) {
       
          MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }

        return suggestions;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response save">
    @POST
    @RolesAllowed({"admin"})
    @Metered(name = "institutionSave",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor la rata de eventos ocurridos al insertar institution",
            absolute = true)
    @Operation(summary = "Inserta un nuevo institution", description = "Inserta un nuevo institution")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  institution")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response save(
            @RequestBody(description = "Crea un nuevo institution.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Institution.class))) Institution institution) {


        return Response.status(Response.Status.CREATED).entity(institutionRepository.save(institution)).build();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response update">

    @PUT
    @RolesAllowed({"admin"})
    @Operation(summary = "Inserta un nuevo institution", description = "Inserta un nuevo institution")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  institution")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo institution.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Institution.class))) Institution institution) {


        return Response.status(Response.Status.CREATED).entity(institutionRepository.save(institution)).build();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response delete">
    @DELETE
    @RolesAllowed({"admin"})
    @Path("{idinstitution}")
    @Operation(summary = "Elimina un institution por  idinstitution", description = "Elimina un institution por su idinstitution")
    @APIResponse(responseCode = "200", description = "Cuando elimina el institution")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response delete(
            @Parameter(description = "El elemento idinstitution", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idinstitution") Long idinstitution) {
        institutionRepository.deleteByPk(idinstitution);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    // </editor-fold>
    
}
