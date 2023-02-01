/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.Embedded;
import com.jmoordb.core.annotation.Entity;
import com.jmoordb.core.annotation.Id;
import com.jmoordb.core.annotation.ViewReferenced;
import com.jmoordb.core.annotation.enumerations.AutogeneratedActive;
import java.util.Date;
import java.util.List;

/**
 *
 * @author avbravo
 */
@Entity
public class Tarjeta {

    @Id(autogeneratedActive = AutogeneratedActive.ON)
    private Long idtarjeta;
    @Column
    private String tarjeta;
    @Column
    private String descripcion;

    @ViewReferenced(from = "user", localField = "iduser")
    List<UserView> userView;

    @Column
    private Date fechainicial;
    @Column
    private Date fechafinal;

 @ViewReferenced(from = "sprint",localField = "idsprint")
 private SprintView sprintView;

    @Column
    private String prioridad;

    @Column
    private String estimacion;

   

    @Embedded
    List<Etiqueta> etiqueta;
    
    @Embedded
    List<Tarea> tarea;
    
    @Embedded
    List<Impedimento> impedimento;
      @Column
    private Boolean active;
    
     @Embedded
    List<Comentario> comentario;
     @Embedded
    List<Archivo> archivo;
    

    @Column
    private Long idrequisito;
  

    @Column
    private String columna;
    
    
  

    public Tarjeta() {
    }

    public Tarjeta(Long idtarjeta, String tarjeta, String descripcion, List<UserView> userView, Date fechainicial, Date fechafinal, SprintView sprintView, String prioridad, String estimacion, List<Etiqueta> etiqueta, List<Tarea> tarea, List<Impedimento> impedimento, Boolean active, List<Comentario> comentario, List<Archivo> archivo, Long idrequisito, String columna) {
        this.idtarjeta = idtarjeta;
        this.tarjeta = tarjeta;
        this.descripcion = descripcion;
        this.userView = userView;
        this.fechainicial = fechainicial;
        this.fechafinal = fechafinal;
        this.sprintView = sprintView;
        this.prioridad = prioridad;
        this.estimacion = estimacion;
        this.etiqueta = etiqueta;
        this.tarea = tarea;
        this.impedimento = impedimento;
        this.active = active;
        this.comentario = comentario;
        this.archivo = archivo;
        this.idrequisito = idrequisito;
        this.columna = columna;
    }

    public Long getIdtarjeta() {
        return idtarjeta;
    }

    public void setIdtarjeta(Long idtarjeta) {
        this.idtarjeta = idtarjeta;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<UserView> getUserView() {
        return userView;
    }

    public void setUserView(List<UserView> userView) {
        this.userView = userView;
    }

    public Date getFechainicial() {
        return fechainicial;
    }

    public void setFechainicial(Date fechainicial) {
        this.fechainicial = fechainicial;
    }

    public Date getFechafinal() {
        return fechafinal;
    }

    public void setFechafinal(Date fechafinal) {
        this.fechafinal = fechafinal;
    }

    public SprintView getSprintView() {
        return sprintView;
    }

    public void setSprintView(SprintView sprintView) {
        this.sprintView = sprintView;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getEstimacion() {
        return estimacion;
    }

    public void setEstimacion(String estimacion) {
        this.estimacion = estimacion;
    }

    public List<Etiqueta> getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(List<Etiqueta> etiqueta) {
        this.etiqueta = etiqueta;
    }

    public List<Tarea> getTarea() {
        return tarea;
    }

    public void setTarea(List<Tarea> tarea) {
        this.tarea = tarea;
    }

    public List<Impedimento> getImpedimento() {
        return impedimento;
    }

    public void setImpedimento(List<Impedimento> impedimento) {
        this.impedimento = impedimento;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Comentario> getComentario() {
        return comentario;
    }

    public void setComentario(List<Comentario> comentario) {
        this.comentario = comentario;
    }

    public List<Archivo> getArchivo() {
        return archivo;
    }

    public void setArchivo(List<Archivo> archivo) {
        this.archivo = archivo;
    }

    public Long getIdrequisito() {
        return idrequisito;
    }

    public void setIdrequisito(Long idrequisito) {
        this.idrequisito = idrequisito;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tarjeta{");
        sb.append("idtarjeta=").append(idtarjeta);
        sb.append(", tarjeta=").append(tarjeta);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", userView=").append(userView);
        sb.append(", fechainicial=").append(fechainicial);
        sb.append(", fechafinal=").append(fechafinal);
        sb.append(", sprintView=").append(sprintView);
        sb.append(", prioridad=").append(prioridad);
        sb.append(", estimacion=").append(estimacion);
        sb.append(", etiqueta=").append(etiqueta);
        sb.append(", tarea=").append(tarea);
        sb.append(", impedimento=").append(impedimento);
        sb.append(", active=").append(active);
        sb.append(", comentario=").append(comentario);
        sb.append(", archivo=").append(archivo);
        sb.append(", idrequisito=").append(idrequisito);
        sb.append(", columna=").append(columna);
        sb.append('}');
        return sb.toString();
    }

    

}