/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.Entity;
import com.jmoordb.core.annotation.Id;
import com.jmoordb.core.annotation.ViewReferenced;
import java.util.Date;

/**
 *
 * @author avbravo
 */
@Entity
public class Timeline {
    @Id
    private Long idtimeline;
    @Column
    private String titulo;
    @Column
    private String subtitulo;
    
    @Column
    private Boolean active;
    
    @Column
    private Date fecha;
    
    
    @ViewReferenced(from = "proyecto", localField = "idproyecto")
    private ProyectoView proyectoView;

    public Timeline() {
    }

    public Timeline(Long idtimeline, String titulo, String subtitulo, Boolean active, Date fecha, ProyectoView proyectoView) {
        this.idtimeline = idtimeline;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.active = active;
        this.fecha = fecha;
        this.proyectoView = proyectoView;
    }

    public Long getIdtimeline() {
        return idtimeline;
    }

    public void setIdtimeline(Long idtimeline) {
        this.idtimeline = idtimeline;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ProyectoView getProyectoView() {
        return proyectoView;
    }

    public void setProyectoView(ProyectoView proyectoView) {
        this.proyectoView = proyectoView;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Timeline{");
        sb.append("idtimeline=").append(idtimeline);
        sb.append(", titulo=").append(titulo);
        sb.append(", subtitulo=").append(subtitulo);
        sb.append(", active=").append(active);
        sb.append(", fecha=").append(fecha);
        sb.append(", proyectoView=").append(proyectoView);
        sb.append('}');
        return sb.toString();
    }

   

    
    
}
