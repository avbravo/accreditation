/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.Embedded;
import com.jmoordb.core.annotation.Entity;
import com.jmoordb.core.annotation.Id;
import com.jmoordb.core.annotation.Referenced;
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

    @Referenced(from = "user", localField = "iduser")
    List<User> user;

    @Column
    private Date fechainicial;
    @Column
    private Date fechafinal;

    @Referenced(from = "sprint", localField = "idsprint")
    List<Sprint> sprint;

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
    

    @Embedded
    List<Requisito> requisito;

  

    @Column
    private String estado;
  

    public Tarjeta() {
    }

    public Tarjeta(Long idtarjeta, String tarjeta, String descripcion, List<User> user, Date fechainicial, Date fechafinal, List<Sprint> sprint, String prioridad, String estimacion, List<Etiqueta> etiqueta, List<Tarea> tarea, List<Impedimento> impedimento, Boolean active, List<Comentario> comentario, List<Archivo> archivo, List<Requisito> requisito, String estado) {
        this.idtarjeta = idtarjeta;
        this.tarjeta = tarjeta;
        this.descripcion = descripcion;
        this.user = user;
        this.fechainicial = fechainicial;
        this.fechafinal = fechafinal;
        this.sprint = sprint;
        this.prioridad = prioridad;
        this.estimacion = estimacion;
        this.etiqueta = etiqueta;
        this.tarea = tarea;
        this.impedimento = impedimento;
        this.active = active;
        this.comentario = comentario;
        this.archivo = archivo;
        this.requisito = requisito;
        this.estado = estado;
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

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
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

    public List<Sprint> getSprint() {
        return sprint;
    }

    public void setSprint(List<Sprint> sprint) {
        this.sprint = sprint;
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

    public List<Requisito> getRequisito() {
        return requisito;
    }

    public void setRequisito(List<Requisito> requisito) {
        this.requisito = requisito;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tarjeta{");
        sb.append("idtarjeta=").append(idtarjeta);
        sb.append(", tarjeta=").append(tarjeta);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", user=").append(user);
        sb.append(", fechainicial=").append(fechainicial);
        sb.append(", fechafinal=").append(fechafinal);
        sb.append(", sprint=").append(sprint);
        sb.append(", prioridad=").append(prioridad);
        sb.append(", estimacion=").append(estimacion);
        sb.append(", etiqueta=").append(etiqueta);
        sb.append(", tarea=").append(tarea);
        sb.append(", impedimento=").append(impedimento);
        sb.append(", active=").append(active);
        sb.append(", comentario=").append(comentario);
        sb.append(", archivo=").append(archivo);
        sb.append(", requisito=").append(requisito);
        sb.append(", estado=").append(estado);
        sb.append('}');
        return sb.toString();
    }

    

    
    
}
