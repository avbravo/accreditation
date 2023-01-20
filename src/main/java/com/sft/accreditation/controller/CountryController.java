/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.accreditation.controller;

import com.jmoordb.core.model.Search;
import com.jmoordb.core.util.DocumentUtil;
import com.jmoordb.core.util.MessagesUtil;
import com.sft.model.Country;
import com.sft.repository.CountryRepository;
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
@Path("country")
@Tag(name = "Información del country", description = "End-point para entidad Country")
@RolesAllowed({"admin"})
public class CountryController {

    
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    CountryRepository countryRepository;
    
 

    @Inject
    @Metric(name = "counter")
    private Counter counter;

    @Inject
    @Metric(name = "idcountryhistrograma", description = "Ejemplo de histograma.",
            displayName = "Histogra de idcountry con paginación")
    private Histogram histogram;

    @Inject
    private MetricRegistry registry;

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="  @Path("insert")">
    @Path("insert")
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Country> insert(@QueryParam("inicial") final Integer inicial) {
//
//        Integer limiteFactor = 13545;
//
//        Integer maximo = inicial + limiteFactor;
//        for (int i = inicial; i <= maximo; i++) {
//
//            Country country = new Country();
//            country.setIdcountry(JmoordbCoreUtil.integerToLong(i));
//            country.setCountry("Country - " + country.getIdcountry());
//            country.setFecha(new Date());
//            countryRepository.save(country);
//        }
        return new ArrayList<>();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="findAll">
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Timed(name = "countryesFindAll",
            description = "Monitorea el tiempo en que se obtiene la lista de todos los countryes",
            unit = MetricUnits.MILLISECONDS, absolute = true)
    @Operation(summary = "Obtiene todos los countryes", description = "Retorna todos los countryes disponibles")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los countryes")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los countryes", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los countryes", required = true, name = "countryes")))
    public List<Country> findAll() {
        
        return countryRepository.findAll();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Country findByIdcountry">
    @GET
    @RolesAllowed({"admin"})
    @Path("{idcountry}")
    @Operation(summary = "Busca un country por el idcountry", description = "Busqueda de country por idcountry")
    @APIResponse(responseCode = "200", description = "El country")
    @APIResponse(responseCode = "404", description = "Cuando no existe el idcountry")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El country", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Country.class)))
    public Country findByIdcountry(
            @Parameter(description = "El idcountry", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idcountry") String idcountry) {

        counter.inc();

        return countryRepository.findByPk(idcountry).orElseThrow(
                () -> new WebApplicationException("No hay country con idcountry " + idcountry, Response.Status.NOT_FOUND));

    }
// </editor-fold>

    
    // <editor-fold defaultstate="collapsed" desc="List<Country> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size)">

    @GET
    @Path("lookup")
    @RolesAllowed({"admin"})
    @Operation(summary = "Busca un appconfiguration", description = "Busqueda de user por search")
    @APIResponse(responseCode = "200", description = "Country")
    @APIResponse(responseCode = "404", description = "Cuando no existe la condicion en el search")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El search", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Country.class)))

    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Country> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size) {
        List<Country> suggestions = new ArrayList<>();
        try {

        Search search = DocumentUtil.convertForLookup(filter, sort, page, size);
        suggestions = countryRepository.lookup(search);

        } catch (Exception e) {
       
          MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }

        return suggestions;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Response save">
    @POST
    @RolesAllowed({"admin"})
    @Metered(name = "countrySave",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor la rata de eventos ocurridos al insertar country",
            absolute = true)
    @Operation(summary = "Inserta un nuevo country", description = "Inserta un nuevo country")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  country")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response save(
            @RequestBody(description = "Crea un nuevo country.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class))) Country country) {


        return Response.status(Response.Status.CREATED).entity(countryRepository.save(country)).build();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response update">

    @PUT
    @RolesAllowed({"admin"})
    @Operation(summary = "Inserta un nuevo country", description = "Inserta un nuevo country")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  country")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo country.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class))) Country country) {


        return Response.status(Response.Status.CREATED).entity(countryRepository.save(country)).build();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response delete">
    @DELETE
    @RolesAllowed({"admin"})
    @Path("{idcountry}")
    @Operation(summary = "Elimina un country por  idcountry", description = "Elimina un country por su idcountry")
    @APIResponse(responseCode = "200", description = "Cuando elimina el country")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response delete(
            @Parameter(description = "El elemento idcountry", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idcountry") String idcountry) {
        countryRepository.deleteByPk(idcountry);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    // </editor-fold>
    
}
