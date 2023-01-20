/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.accreditation.controller;

import com.jmoordb.core.model.Search;
import com.jmoordb.core.util.DocumentUtil;
import com.jmoordb.core.util.MessagesUtil;
import com.sft.model.Building;
import com.sft.repository.BuildingRepository;
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
@Path("building")
@Tag(name = "Información del building", description = "End-point para entidad Building")
@RolesAllowed({"admin"})
public class BuildingController {
    
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    BuildingRepository buildingRepository;
    
     @Inject
    @Metric(name = "counter")
    private Counter counter;
    @Inject
    @Metric(name = "idbuildinghistrograma", description = "Ejemplo de histograma.",
            displayName = "Histogra de idbuilding con paginación")
    private Histogram histogram;
    @Inject
    private MetricRegistry registry;

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="  @Path("insert")">
    @Path("insert")
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Building> insert(@QueryParam("inicial") final Integer inicial) {
//
//        Integer limiteFactor = 13545;
//
//        Integer maximo = inicial + limiteFactor;
//        for (int i = inicial; i <= maximo; i++) {
//
//            Building building = new Building();
//            building.setIdbuilding(JmoordbCoreUtil.integerToLong(i));
//            building.setBuilding("Building - " + building.getIdbuilding());
//            building.setFecha(new Date());
//            buildingRepository.save(building);
//        }
        return new ArrayList<>();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="findAll">
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Timed(name = "buildingesFindAll",
            description = "Monitorea el tiempo en que se obtiene la lista de todos los buildinges",
            unit = MetricUnits.MILLISECONDS, absolute = true)
    @Operation(summary = "Obtiene todos los buildinges", description = "Retorna todos los buildinges disponibles")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los buildinges")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los buildinges", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los buildinges", required = true, name = "buildinges")))
    public List<Building> findAll() {
        
        return buildingRepository.findAll();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Building findByIdbuilding">
    @GET
    @RolesAllowed({"admin"})
    @Path("{idbuilding}")
    @Operation(summary = "Busca un building por el idbuilding", description = "Busqueda de building por idbuilding")
    @APIResponse(responseCode = "200", description = "El building")
    @APIResponse(responseCode = "404", description = "Cuando no existe el idbuilding")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El building", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Building.class)))
    public Building findByIdbuilding(
            @Parameter(description = "El idbuilding", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idbuilding") Long idbuilding) {

        counter.inc();

        return buildingRepository.findByPk(idbuilding).orElseThrow(
                () -> new WebApplicationException("No hay building con idbuilding " + idbuilding, Response.Status.NOT_FOUND));

    }
// </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="List<Building> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size)">

    @GET
    @Path("lookup")
    @RolesAllowed({"admin"})
    @Operation(summary = "Busca un appconfiguration", description = "Busqueda de user por search")
    @APIResponse(responseCode = "200", description = "Building")
    @APIResponse(responseCode = "404", description = "Cuando no existe la condicion en el search")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El search", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Building.class)))

    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Building> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size) {
        List<Building> suggestions = new ArrayList<>();
        try {

        Search search = DocumentUtil.convertForLookup(filter, sort, page, size);
        suggestions = buildingRepository.lookup(search);

        } catch (Exception e) {
       
          MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }

        return suggestions;
    }

    // </editor-fold>
    

    // <editor-fold defaultstate="collapsed" desc="Response save">
    @POST
    @RolesAllowed({"admin"})
    @Metered(name = "buildingSave",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor la rata de eventos ocurridos al insertar building",
            absolute = true)
    @Operation(summary = "Inserta un nuevo building", description = "Inserta un nuevo building")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  building")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response save(
            @RequestBody(description = "Crea un nuevo building.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Building.class))) Building building) {


        return Response.status(Response.Status.CREATED).entity(buildingRepository.save(building)).build();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response update">

    @PUT
    @RolesAllowed({"admin"})
    @Operation(summary = "Inserta un nuevo building", description = "Inserta un nuevo building")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  building")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo building.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Building.class))) Building building) {


        return Response.status(Response.Status.CREATED).entity(buildingRepository.save(building)).build();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response delete">
    @DELETE
    @RolesAllowed({"admin"})
    @Path("{idbuilding}")
    @Operation(summary = "Elimina un building por  idbuilding", description = "Elimina un building por su idbuilding")
    @APIResponse(responseCode = "200", description = "Cuando elimina el building")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response delete(
            @Parameter(description = "El elemento idbuilding", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idbuilding") Long idbuilding) {
        buildingRepository.deleteByPk(idbuilding);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    // </editor-fold>
    
}
