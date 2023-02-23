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
public class Impedimento {
    @Column
    private String impedimento;
  
     @Column
    private Date fecha;
    @Column
    private Boolean active;

  public Impedimento() {
    }

    public Impedimento(String impedimento, Date fecha, Boolean active) {
        this.impedimento = impedimento;
        this.fecha = fecha;
        this.active = active;
    }

    public String getImpedimento() {
        return impedimento;
    }

    public void setImpedimento(String impedimento) {
        this.impedimento = impedimento;
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
        sb.append("Impedimento{");
        sb.append("impedimento=").append(impedimento);
        sb.append(", fecha=").append(fecha);
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }

 
  
   
    
}
