/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.DocumentEmbeddable;
import java.util.Date;

/**
 *
 * @author avbravo
 */
@DocumentEmbeddable
public class Comentario {
    @Column
    private String comentario;
    @Column
    private String nombreusuario;
     @Column
    private Date fecha;
    @Column
    private Boolean active;

    public Comentario() {
    }

    public Comentario(String comentario, String nombreusuario, Date fecha, Boolean active) {
        this.comentario = comentario;
        this.nombreusuario = nombreusuario;
        this.fecha = fecha;
        this.active = active;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        sb.append("Comentario{");
        sb.append("comentario=").append(comentario);
        sb.append(", nombreusuario=").append(nombreusuario);
        sb.append(", fecha=").append(fecha);
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }

   

    
    
    
    
}
