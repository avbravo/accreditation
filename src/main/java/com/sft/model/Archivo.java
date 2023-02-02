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
    private String extension;

    @Column
    private Boolean active;
    
        @ViewReferenced(from = "user", localField = "iduser")
   private UserView userView;

    public Archivo() {
    }

    public Archivo(String path, Date fecha, String descripcion, String extension, Boolean active, UserView userView) {
        this.path = path;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.extension = extension;
        this.active = active;
        this.userView = userView;
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public UserView getUserView() {
        return userView;
    }

    public void setUserView(UserView userView) {
        this.userView = userView;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Archivo{");
        sb.append("path=").append(path);
        sb.append(", fecha=").append(fecha);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", extension=").append(extension);
        sb.append(", active=").append(active);
        sb.append(", userView=").append(userView);
        sb.append('}');
        return sb.toString();
    }

  

   
}
