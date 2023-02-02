/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.DocumentEmbeddable;
import com.jmoordb.core.annotation.ViewReferenced;
import java.util.List;

/**
 *
 * @author avbravo
 */
@DocumentEmbeddable
public class Tarea {
    @Column
    private String tarea;
    @Column
    private Boolean completado;
    @Column
    private Boolean active;
     
    @ViewReferenced(from = "user", localField = "iduser")
    List<UserView> userView;

    public Tarea() {
    }

    public Tarea(String tarea, Boolean completado, Boolean active, List<UserView> userView) {
        this.tarea = tarea;
        this.completado = completado;
        this.active = active;
        this.userView = userView;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<UserView> getUserView() {
        return userView;
    }

    public void setUserView(List<UserView> userView) {
        this.userView = userView;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tarea{");
        sb.append("tarea=").append(tarea);
        sb.append(", completado=").append(completado);
        sb.append(", active=").append(active);
        sb.append(", userView=").append(userView);
        sb.append('}');
        return sb.toString();
    }

   
   

    
    
    
    
}
