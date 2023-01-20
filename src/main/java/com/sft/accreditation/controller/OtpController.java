/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.accreditation.controller;

import com.jmoordb.core.model.Search;
import com.jmoordb.core.util.DocumentUtil;
import com.jmoordb.core.util.MessagesUtil;
import com.sft.model.Otp;
import com.sft.repository.OtpRepository;
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
@Path("otp")
@Tag(name = "Información del otp", description = "End-point para entidad Otp")
@RolesAllowed({"admin"})
public class OtpController {

    
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    OtpRepository otpRepository;
    
 

    @Inject
    @Metric(name = "counter")
    private Counter counter;

    @Inject
    @Metric(name = "idotphistrograma", description = "Ejemplo de histograma.",
            displayName = "Histogra de idotp con paginación")
    private Histogram histogram;

    @Inject
    private MetricRegistry registry;

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="  @Path("insert")">
    @Path("insert")
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Otp> insert(@QueryParam("inicial") final Integer inicial) {
//
//        Integer limiteFactor = 13545;
//
//        Integer maximo = inicial + limiteFactor;
//        for (int i = inicial; i <= maximo; i++) {
//
//            Otp otp = new Otp();
//            otp.setIdotp(JmoordbCoreUtil.integerToLong(i));
//            otp.setOtp("Otp - " + otp.getIdotp());
//            otp.setFecha(new Date());
//            otpRepository.save(otp);
//        }
        return new ArrayList<>();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="findAll">
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Timed(name = "otpesFindAll",
            description = "Monitorea el tiempo en que se obtiene la lista de todos los otpes",
            unit = MetricUnits.MILLISECONDS, absolute = true)
    @Operation(summary = "Obtiene todos los otpes", description = "Retorna todos los otpes disponibles")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los otpes")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los otpes", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los otpes", required = true, name = "otpes")))
    public List<Otp> findAll() {
        
        return otpRepository.findAll();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Otp findByIdotp">
    @GET
    @RolesAllowed({"admin"})
    @Path("{idotp}")
    @Operation(summary = "Busca un otp por el idotp", description = "Busqueda de otp por idotp")
    @APIResponse(responseCode = "200", description = "El otp")
    @APIResponse(responseCode = "404", description = "Cuando no existe el idotp")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El otp", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Otp.class)))
    public Otp findByIdotp(
            @Parameter(description = "El idotp", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idotp") Long idotp) {

        counter.inc();

        return otpRepository.findByPk(idotp).orElseThrow(
                () -> new WebApplicationException("No hay otp con idotp " + idotp, Response.Status.NOT_FOUND));

    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="List<Otp> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size)">

    @GET
    @Path("lookup")
    @RolesAllowed({"admin"})
    @Operation(summary = "Busca un appconfiguration", description = "Busqueda de user por search")
    @APIResponse(responseCode = "200", description = "Otp")
    @APIResponse(responseCode = "404", description = "Cuando no existe la condicion en el search")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El search", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Otp.class)))

    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Otp> lookup(@QueryParam("filter") String filter, @QueryParam("sort") String sort, @QueryParam("page") Integer page, @QueryParam("size") Integer size) {
        List<Otp> suggestions = new ArrayList<>();
        try {

        Search search = DocumentUtil.convertForLookup(filter, sort, page, size);
        suggestions = otpRepository.lookup(search);

        } catch (Exception e) {
       
          MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }

        return suggestions;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response save">
    @POST
    @RolesAllowed({"admin"})
    @Metered(name = "otpSave",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor la rata de eventos ocurridos al insertar otp",
            absolute = true)
    @Operation(summary = "Inserta un nuevo otp", description = "Inserta un nuevo otp")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  otp")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response save(
            @RequestBody(description = "Crea un nuevo otp.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Otp.class))) Otp otp) {


        return Response.status(Response.Status.CREATED).entity(otpRepository.save(otp)).build();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response update">

    @PUT
    @RolesAllowed({"admin"})
    @Operation(summary = "Inserta un nuevo otp", description = "Inserta un nuevo otp")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  otp")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo otp.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Otp.class))) Otp otp) {


        return Response.status(Response.Status.CREATED).entity(otpRepository.save(otp)).build();
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Response delete">
    @DELETE
    @RolesAllowed({"admin"})
    @Path("{idotp}")
    @Operation(summary = "Elimina un otp por  idotp", description = "Elimina un otp por su idotp")
    @APIResponse(responseCode = "200", description = "Cuando elimina el otp")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response delete(
            @Parameter(description = "El elemento idotp", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idotp") Long idotp) {
        otpRepository.deleteByPk(idotp);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    // </editor-fold>
    
}
