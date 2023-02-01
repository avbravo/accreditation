/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.Entity;
import com.jmoordb.core.annotation.Id;
import com.jmoordb.core.annotation.Referenced;
import com.jmoordb.core.annotation.ViewEntity;
import com.jmoordb.core.annotation.ViewReferenced;
import com.jmoordb.core.annotation.enumerations.AutogeneratedActive;
import java.util.Date;
import java.util.List;

/**
 *
 * @author avbravo
 */
@ViewEntity(collection = "proyecto")
public class ProyectoView {
     
    @Id(autogeneratedActive = AutogeneratedActive.ON)
    private Long idproyecto;
    @Column
    private String proyecto;
    @Column
    private String descripcion;
 
    @Column
    private String prefijo;

    @Column
    private Date fechafinal;
    @Column
    private Date fechainicial;

   
   
    @Column
    private Double avance;

    @Column
    private String estado;
    @Column
    private Boolean active;

    public ProyectoView() {
    }

    public ProyectoView(Long idproyecto, String proyecto, String descripcion, String prefijo, Date fechafinal, Date fechainicial, Double avance, String estado, Boolean active) {
        this.idproyecto = idproyecto;
        this.proyecto = proyecto;
        this.descripcion = descripcion;
        this.prefijo = prefijo;
        this.fechafinal = fechafinal;
        this.fechainicial = fechainicial;
        this.avance = avance;
        this.estado = estado;
        this.active = active;
    }

    
    

    public Long getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Long idproyecto) {
        this.idproyecto = idproyecto;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public Date getFechafinal() {
        return fechafinal;
    }

    public void setFechafinal(Date fechafinal) {
        this.fechafinal = fechafinal;
    }

    public Date getFechainicial() {
        return fechainicial;
    }

    public void setFechainicial(Date fechainicial) {
        this.fechainicial = fechainicial;
    }

    public Double getAvance() {
        return avance;
    }

    public void setAvance(Double avance) {
        this.avance = avance;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProyectoView{");
        sb.append("idproyecto=").append(idproyecto);
        sb.append(", proyecto=").append(proyecto);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", prefijo=").append(prefijo);
        sb.append(", fechafinal=").append(fechafinal);
        sb.append(", fechainicial=").append(fechainicial);
        sb.append(", avance=").append(avance);
        sb.append(", estado=").append(estado);
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }

  
   
   

  

}
