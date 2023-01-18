/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.Entity;
import com.jmoordb.core.annotation.Id;
import com.jmoordb.core.annotation.Referenced;
import com.jmoordb.core.annotation.enumerations.AutogeneratedActive;
import com.jmoordb.core.annotation.enumerations.TypeReferenced;

/**
 *
 * @author avbravo
 */
@Entity
public class Actividad {
@Id(autogeneratedActive = AutogeneratedActive.ON)
    private Long idactividad;
    @Column
 private String codigo;
    @Column
 private String actividad;
    @Column
 private Integer anio;
    @Column
    private Boolean active;
   @Referenced(from = "objetivo", localField = "idobjetivo") 
   Objetivo objetivo;

    public Actividad() {
    }

    public Actividad(Long idactividad, String codigo, String actividad, Integer anio, Boolean active, Objetivo objetivo) {
        this.idactividad = idactividad;
        this.codigo = codigo;
        this.actividad = actividad;
        this.anio = anio;
        this.active = active;
        this.objetivo = objetivo;
    }

    public Long getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(Long idactividad) {
        this.idactividad = idactividad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Actividad{");
        sb.append("idactividad=").append(idactividad);
        sb.append(", codigo=").append(codigo);
        sb.append(", actividad=").append(actividad);
        sb.append(", anio=").append(anio);
        sb.append(", active=").append(active);
        sb.append(", objetivo=").append(objetivo);
        sb.append('}');
        return sb.toString();
    }
   
   
   

}
