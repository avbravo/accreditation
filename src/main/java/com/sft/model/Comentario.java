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
public class Comentario {
    @Column
    private String comentario;

     @Column
    private Date fecha;
    @Column
    private Boolean active;
        @ViewReferenced(from = "user", localField = "iduser")
private UserView userView;

    public Comentario() {
    }

    public Comentario(String comentario, Date fecha, Boolean active, UserView userView) {
        this.comentario = comentario;
        this.fecha = fecha;
        this.active = active;
        this.userView = userView;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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

    public UserView getUserView() {
        return userView;
    }

    public void setUserView(UserView userView) {
        this.userView = userView;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Comentario{");
        sb.append("comentario=").append(comentario);
        sb.append(", fecha=").append(fecha);
        sb.append(", active=").append(active);
        sb.append(", userView=").append(userView);
        sb.append('}');
        return sb.toString();
    }

    
    
    
    
}
