/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.accreditation.controller;

import com.jmoordb.core.model.Search;
import com.jmoordb.core.util.DocumentUtil;
import com.jmoordb.core.util.MessagesUtil;
import com.sft.model.Province;
import com.sft.repository.ProvinceRepository;
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
@Path("province")
@Tag(name = "Información del province", description = "End-point para entidad Province")
@RolesAllowed({"admin"})
public class ProvinceController {

    
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    ProvinceRepository provinceRepository;
    
 


// </editor-fold>
   

    // <editor-fold defaultstate="collapsed" desc="findAll">
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Timed(name = "provinceesFindAll",
            description = "Monitorea el tiempo en que se obtiene la lista de todos los provincees",
            unit = MetricUnits.MILLISECONDS, absolute = true)
    @Operation(summary = "Obtiene todos los provincees", description = "Retorna todos los provincees disponibles")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los provincees")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los provincees", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los provincees", required = true, name = "provincees")))
    public List<Province> findAll() {
        
        return provinceRepository.findAll();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Province findByIdprovince">
    @GET
    @RolesAllowed({"admin"})
    @Path("{idprovince}")
    @Operation(summary = "Busca un province por el idprovince", description = "Busqueda de province por idprovince")
    @APIResponse(responseCode = "200", description = "El province")
    @APIResponse(responseCode = "404", description = "Cuando no existe el idprovince")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El province", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Province.class)))
    public Province findByIdprovince(
            @Parameter(description = "El idprovince", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idprovince") Long idprovince) {

       

        return provinceRepository.findByPk(idprovince).orElseThrow(
                () -> new WebApplicationException("No hay province con idprovince " + idprovince, Response.Status.NOT_FOUND));

    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="List<Province> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size)">

    @GET
    @Path("lookup")
    @RolesAllowed({"admin"})
    @Operation(summary = "Busca un appconfiguration", description = "Busqueda de user por search")
    @APIResponse(responseCode = "200", description = "Province")
    @APIResponse(responseCode = "404", description = "Cuando no existe la condicion en el search")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El search", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Province.class)))

    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Province> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size) {
        List<Province> suggestions = new ArrayList<>();
        try {

        Search search = DocumentUtil.convertForLookup(filter, sort, page, size);
        suggestions = provinceRepository.lookup(search);

        } catch (Exception e) {
       
          MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }

        return suggestions;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response save">
    @POST
    @RolesAllowed({"admin"})
    @Metered(name = "provinceSave",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor la rata de eventos ocurridos al insertar province",
            absolute = true)
    @Operation(summary = "Inserta un nuevo province", description = "Inserta un nuevo province")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  province")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response save(
            @RequestBody(description = "Crea un nuevo province.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Province.class))) Province province) {


        return Response.status(Response.Status.CREATED).entity(provinceRepository.save(province)).build();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response update">

    @PUT
    @RolesAllowed({"admin"})
    @Operation(summary = "Inserta un nuevo province", description = "Inserta un nuevo province")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  province")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo province.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Province.class))) Province province) {


        return Response.status(Response.Status.CREATED).entity(provinceRepository.update(province)).build();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response delete">
    @DELETE
    @RolesAllowed({"admin"})
    @Path("{idprovince}")
    @Operation(summary = "Elimina un province por  idprovince", description = "Elimina un province por su idprovince")
    @APIResponse(responseCode = "200", description = "Cuando elimina el province")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response delete(
            @Parameter(description = "El elemento idprovince", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idprovince") Long idprovince) {
        provinceRepository.deleteByPk(idprovince);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    // </editor-fold>
    
}
