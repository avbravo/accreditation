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
public class Archivo {

    @Column
    private String path;

    @Column
    private Date fecha;
    @Column
    private String descripcion;
    
  

    @Column
    private Boolean active;
    
    
    public Archivo() {
    }

    public Archivo(String path, Date fecha, String descripcion, Boolean active) {
        this.path = path;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.active = active;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        sb.append("Archivo{");
        sb.append("path=").append(path);
        sb.append(", fecha=").append(fecha);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }

   
  

   
}
