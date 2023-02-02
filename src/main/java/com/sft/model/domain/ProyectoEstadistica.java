/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.model.domain;

import com.jmoordb.core.annotation.Domain;
import com.sft.model.Proyecto;
import lombok.Data;

/**
 *
 * @author avbravo
 */
@Data
@Domain(commentary = "Se usa para calular los totales del proyecto")
public class ProyectoEstadistica {
    public Integer totalSprint;
    public Integer totalTarjetasBacklog;
    public Integer totalTarjetasPendiente;
    public Integer totalTarjetasProgreso;
    public Integer totalTarjetasFinalizado;
    public Long proyecto;

    public ProyectoEstadistica() {
    }

    public ProyectoEstadistica(Integer totalSprint, Integer totalTarjetasBacklog, Integer totalTarjetasPendiente, Integer totalTarjetasProgreso, Integer totalTarjetasFinalizado, Long proyecto) {
        this.totalSprint = totalSprint;
        this.totalTarjetasBacklog = totalTarjetasBacklog;
        this.totalTarjetasPendiente = totalTarjetasPendiente;
        this.totalTarjetasProgreso = totalTarjetasProgreso;
        this.totalTarjetasFinalizado = totalTarjetasFinalizado;
        this.proyecto = proyecto;
    }

   

  
    
    
    
    
    
}
