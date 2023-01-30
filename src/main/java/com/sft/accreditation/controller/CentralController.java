/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.accreditation.controller;

import com.jmoordb.core.model.Search;
import com.jmoordb.core.util.DocumentUtil;
import com.jmoordb.core.util.MessagesUtil;
import com.sft.model.Central;
import com.sft.repository.CentralRepository;
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
@Path("central")
@Tag(name = "Información del central", description = "End-point para entidad Central")
@RolesAllowed({"admin"})
public class CentralController {

    
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    CentralRepository centralRepository;
    
 

   

// </editor-fold>
   

    // <editor-fold defaultstate="collapsed" desc="findAll">
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Timed(name = "centralesFindAll",
            description = "Monitorea el tiempo en que se obtiene la lista de todos los centrales",
            unit = MetricUnits.MILLISECONDS, absolute = true)
    @Operation(summary = "Obtiene todos los centrales", description = "Retorna todos los centrales disponibles")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los centrales")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los centrales", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los centrales", required = true, name = "centrales")))
    public List<Central> findAll() {
        
        return centralRepository.findAll();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Central findByIdcentral">
    @GET
    @RolesAllowed({"admin"})
    @Path("{idcentral}")
    @Operation(summary = "Busca un central por el idcentral", description = "Busqueda de central por idcentral")
    @APIResponse(responseCode = "200", description = "El central")
    @APIResponse(responseCode = "404", description = "Cuando no existe el idcentral")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El central", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Central.class)))
    public Central findByIdcentral(
            @Parameter(description = "El idcentral", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idcentral") Long idcentral) {

      

        return centralRepository.findByPk(idcentral).orElseThrow(
                () -> new WebApplicationException("No hay central con idcentral " + idcentral, Response.Status.NOT_FOUND));

    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="List<Central> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size)">

    @GET
    @Path("lookup")
    @RolesAllowed({"admin"})
    @Operation(summary = "Busca un appconfiguration", description = "Busqueda de user por search")
    @APIResponse(responseCode = "200", description = "Central")
    @APIResponse(responseCode = "404", description = "Cuando no existe la condicion en el search")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El search", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Central.class)))

    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Central> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size) {
        List<Central> suggestions = new ArrayList<>();
        try {

        Search search = DocumentUtil.convertForLookup(filter, sort, page, size);
        suggestions = centralRepository.lookup(search);

        } catch (Exception e) {
       
          MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }

        return suggestions;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response save">
    @POST
    @RolesAllowed({"admin"})
    @Metered(name = "centralSave",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor la rata de eventos ocurridos al insertar central",
            absolute = true)
    @Operation(summary = "Inserta un nuevo central", description = "Inserta un nuevo central")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  central")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response save(
            @RequestBody(description = "Crea un nuevo central.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Central.class))) Central central) {


        return Response.status(Response.Status.CREATED).entity(centralRepository.save(central)).build();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response update">

    @PUT
    @RolesAllowed({"admin"})
    @Operation(summary = "Inserta un nuevo central", description = "Inserta un nuevo central")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  central")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo central.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Central.class))) Central central) {


        return Response.status(Response.Status.CREATED).entity(centralRepository.update(central)).build();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response delete">
    @DELETE
    @RolesAllowed({"admin"})
    @Path("{idcentral}")
    @Operation(summary = "Elimina un central por  idcentral", description = "Elimina un central por su idcentral")
    @APIResponse(responseCode = "200", description = "Cuando elimina el central")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response delete(
            @Parameter(description = "El elemento idcentral", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idcentral") Long idcentral) {
        centralRepository.deleteByPk(idcentral);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    // </editor-fold>
    
}
