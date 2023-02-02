/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.poa.controller.domain;

import com.jmoordb.core.model.Search;
import com.jmoordb.core.util.ConsoleUtil;
import com.jmoordb.core.util.DocumentUtil;
import com.jmoordb.core.util.MessagesUtil;
import com.sft.model.Proyecto;
import com.sft.model.Sprint;
import com.sft.model.domain.ProyectoEstadistica;
import com.sft.repository.ProyectoRepository;
import com.sft.repository.SprintRepository;
import com.sft.repository.TarjetaViewRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 *
 * @author avbravo
 */
@Path("proyectoestadistica")
@Tag(name = "Información del proyectoestadistica", description = "End-point para entidad Proyecto")
@RolesAllowed({"admin"})
public class ProyectoEstadisticaController {
// <editor-fold defaultstate="collapsed" desc="fields">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    ProyectoRepository proyectoestadisticaRepository;
    
    

@Inject
SprintRepository sprintRepository;
@Inject
TarjetaViewRepository tarjetaViewRepository;

// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Proyecto findByIdproyecto">
    @GET
    @RolesAllowed({"admin"})
    @Path("{idproyecto}")
    @Operation(summary = "Busca un proyecto por el idproyecto", description = "Busqueda de proyecto por idproyecto")
    @APIResponse(responseCode = "200", description = "El proyecto")
    @APIResponse(responseCode = "404", description = "Cuando no existe el idproyecto")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El proyecto", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Proyecto.class)))
    public ProyectoEstadistica findByIdproyecto(
            @Parameter(description = "El idproyecto", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idproyecto") Long idproyecto) {
ProyectoEstadistica  proyectoEstadistica = new ProyectoEstadistica(0,0,0, 0, 0, idproyecto);
        try {
            
              Integer page = 0;
            Integer size = 1;
            Bson filter = new Document("proyecto.idproyecto", idproyecto).append("open", Boolean.TRUE).append("active",Boolean.TRUE);
            Document sort = new Document("idsprint", 1);
               
              Search search = DocumentUtil.convertForLookup(DocumentUtil.bsonToJson(filter), DocumentUtil.bsonToJson(sort), page, size);
            List<Sprint> sprintList = sprintRepository.lookup(search);
             if(!sprintList.isEmpty()){
                 ConsoleUtil.info("\t\tEncontre sprint para el proyecto.....{ "+idproyecto+"[");
             for(Sprint s:sprintList)   {
                 ConsoleUtil.info("\t\t\t incrementando el contador de sprint");
                 proyectoEstadistica.setTotalSprint(proyectoEstadistica.getTotalSprint()+1);
             }
            }else{
                ConsoleUtil.info("\t\tNo se encontro sprint para el proyecto.....{ "+idproyecto+"[");
            }
            
         
   } catch (Exception e) {
       
          MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }
      
 return proyectoEstadistica;
        
    }
// </editor-fold>
     
    
   
    
}
