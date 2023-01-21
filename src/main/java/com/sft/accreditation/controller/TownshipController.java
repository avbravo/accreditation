/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.accreditation.controller;

import com.jmoordb.core.model.Search;
import com.jmoordb.core.util.DocumentUtil;
import com.jmoordb.core.util.MessagesUtil;
import com.sft.model.Township;
import com.sft.model.Township;
import com.sft.repository.TownshipRepository;
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
@Path("township")
@RolesAllowed({"admin"})
@Tag(name = "Información del township", description = "End-point para entidad Township")
public class TownshipController {

    
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    TownshipRepository townshipRepository;
    
 

  

// </editor-fold>
 

    // <editor-fold defaultstate="collapsed" desc="findAll">
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Timed(name = "townshipesFindAll",
            description = "Monitorea el tiempo en que se obtiene la lista de todos los townshipes",
            unit = MetricUnits.MILLISECONDS, absolute = true)
    @Operation(summary = "Obtiene todos los townshipes", description = "Retorna todos los townshipes disponibles")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los townshipes")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los townshipes", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los townshipes", required = true, name = "townshipes")))
    public List<Township> findAll() {
        
        return townshipRepository.findAll();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Township findByIdtownship">
    @GET
    @RolesAllowed({"admin"})
    @Path("{idtownship}")
    @Operation(summary = "Busca un township por el idtownship", description = "Busqueda de township por idtownship")
    @APIResponse(responseCode = "200", description = "El township")
    @APIResponse(responseCode = "404", description = "Cuando no existe el idtownship")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El township", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Township.class)))
    public Township findByIdtownship(
            @Parameter(description = "El idtownship", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idtownship") Long idtownship) {

      

        return townshipRepository.findByPk(idtownship).orElseThrow(
                () -> new WebApplicationException("No hay township con idtownship " + idtownship, Response.Status.NOT_FOUND));

    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="List<Township> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size)">

    @GET
    @Path("lookup")
    @RolesAllowed({"admin"})
    @Operation(summary = "Busca un appconfiguration", description = "Busqueda de user por search")
    @APIResponse(responseCode = "200", description = "Township")
    @APIResponse(responseCode = "404", description = "Cuando no existe la condicion en el search")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El search", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Township.class)))

    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Township> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size) {
        List<Township> suggestions = new ArrayList<>();
        try {

        Search search = DocumentUtil.convertForLookup(filter, sort, page, size);
        suggestions = townshipRepository.lookup(search);

        } catch (Exception e) {
       
          MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }

        return suggestions;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response save">
    @POST
    @RolesAllowed({"admin"})
    @Metered(name = "townshipSave",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor la rata de eventos ocurridos al insertar township",
            absolute = true)
    @Operation(summary = "Inserta un nuevo township", description = "Inserta un nuevo township")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  township")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response save(
            @RequestBody(description = "Crea un nuevo township.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Township.class))) Township township) {


        return Response.status(Response.Status.CREATED).entity(townshipRepository.save(township)).build();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response update">

    @PUT
    @RolesAllowed({"admin"})
    @Operation(summary = "Inserta un nuevo township", description = "Inserta un nuevo township")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  township")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo township.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Township.class))) Township township) {


        return Response.status(Response.Status.CREATED).entity(townshipRepository.save(township)).build();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response delete">
    @DELETE
    @RolesAllowed({"admin"})
    @Path("{idtownship}")
    @Operation(summary = "Elimina un township por  idtownship", description = "Elimina un township por su idtownship")
    @APIResponse(responseCode = "200", description = "Cuando elimina el township")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response delete(
            @Parameter(description = "El elemento idtownship", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idtownship") Long idtownship) {
        townshipRepository.deleteByPk(idtownship);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    // </editor-fold>
    
}
