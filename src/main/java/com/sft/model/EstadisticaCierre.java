/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.DocumentEmbeddable;
import com.jmoordb.core.annotation.ViewReferenced;
import java.util.Date;
import java.util.List;

/**
 *
 * @author avbravo
 */
@DocumentEmbeddable
public class EstadisticaCierre {
    @Column
    private Integer pendiente;
       @Column
    private Integer progreso;
          @Column
    private Integer finalizado;
  
     @Column
    private Date fecha;


  public EstadisticaCierre() {
    }

    public EstadisticaCierre(Integer pendiente, Integer progreso, Integer finalizado, Date fecha) {
        this.pendiente = pendiente;
        this.progreso = progreso;
        this.finalizado = finalizado;
        this.fecha = fecha;
    }

    public Integer getPendiente() {
        return pendiente;
    }

    public void setPendiente(Integer pendiente) {
        this.pendiente = pendiente;
    }

    public Integer getProgreso() {
        return progreso;
    }

    public void setProgreso(Integer progreso) {
        this.progreso = progreso;
    }

    public Integer getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Integer finalizado) {
        this.finalizado = finalizado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EstadisticaCierre{");
        sb.append("pendiente=").append(pendiente);
        sb.append(", progreso=").append(progreso);
        sb.append(", finalizado=").append(finalizado);
        sb.append(", fecha=").append(fecha);
        sb.append('}');
        return sb.toString();
    }

    
  
   
    
}
