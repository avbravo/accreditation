/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.poa.controller;

import com.sft.model.Grupo;
import com.sft.repository.GrupoRepository;
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
import java.util.Collection;
import java.util.List;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Metered;
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
@Path("grupo")
@Tag(name = "Información del grupo", description = "End-point para entidad Grupo")
@RolesAllowed({"admin"})
public class GrupoController {

    
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    GrupoRepository grupoRepository;
    
 

// </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="findAll">
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Timed(name = "gruposFindAll",
            description = "Monitorea el tiempo en que se obtiene la lista de todos los grupoes",
            unit = MetricUnits.MILLISECONDS, absolute = true)
    @Operation(summary = "Obtiene todos los grupoes", description = "Retorna todos los grupoes disponibles")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los grupoes")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los grupo", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los grupoes", required = true, name = "grupoes")))
    public List<Grupo> findAll() {
        
        return grupoRepository.findAll();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Grupo findByIdgrupo">
    @GET
    @RolesAllowed({"admin"})
    @Path("{idgrupo}")
    @Operation(summary = "Busca un grupo por el idgrupo", description = "Busqueda de grupo por idgrupo")
    @APIResponse(responseCode = "200", description = "El grupo")
    @APIResponse(responseCode = "404", description = "Cuando no existe el idgrupo")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El grupo", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Grupo.class)))
    public Grupo findByIdgrupo(
            @Parameter(description = "El idgrupo", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idgrupo") Long idgrupo) {

      

        return grupoRepository.findByPk(idgrupo).orElseThrow(
                () -> new WebApplicationException("No hay grupo con idgrupo " + idgrupo, Response.Status.NOT_FOUND));

    }
// </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Grupo findByGrupo">
    @GET
    @RolesAllowed({"admin"})
    @Path("grupo")
    @Operation(summary = "Busca un grupo por proyecti", description = "Busqueda de grupo por grupo")
    @APIResponse(responseCode = "200", description = "El grupo")
    @APIResponse(responseCode = "404", description = "Cuando no existe el grupo")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El grupo", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Grupo.class)))
    
   
    public List<Grupo> findByGrupo(@Parameter(description = "El grupo", required = true, example = "1", schema = @Schema(type = SchemaType.STRING)) @QueryParam("grupo") final String grupo) {

 return grupoRepository.findByGrupo(grupo);

        

    }
//// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response save">
    @POST
    @RolesAllowed({"admin"})
    @Metered(name = "grupoSave",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor la rata de eventos ocurridos al insertar grupo",
            absolute = true)
    @Operation(summary = "Inserta un nuevo grupo", description = "Inserta un nuevo grupo")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  grupo")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response save(
            @RequestBody(description = "Crea un nuevo grupo.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Grupo.class))) Grupo grupo) {


        return Response.status(Response.Status.CREATED).entity(grupoRepository.save(grupo)).build();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response update">

    @PUT
    @RolesAllowed({"admin"})
    @Operation(summary = "Inserta un nuevo grupo", description = "Inserta un nuevo grupo")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  grupo")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo grupo.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Grupo.class))) Grupo grupo) {


        return Response.status(Response.Status.CREATED).entity(grupoRepository.update(grupo)).build();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response delete">
    @DELETE
    @RolesAllowed({"admin"})
    @Path("{idgrupo}")
    @Operation(summary = "Elimina un grupo por  idgrupo", description = "Elimina un grupo por su idgrupo")
    @APIResponse(responseCode = "200", description = "Cuando elimina el grupo")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response delete(
            @Parameter(description = "El elemento idgrupo", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idgrupo") Long idgrupo) {
        grupoRepository.deleteByPk(idgrupo);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    // </editor-fold>
    
}
