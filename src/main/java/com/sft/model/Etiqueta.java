/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.DocumentEmbeddable;

/**
 *
 * @author avbravo
 */
@DocumentEmbeddable
public class Etiqueta {
    @Column
    private String etiqueta;
    @Column
    private Boolean active;

    public Etiqueta() {
    }

    public Etiqueta(String etiqueta, Boolean active) {
        this.etiqueta = etiqueta;
        this.active = active;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
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
        sb.append("Etiqueta{");
        sb.append("etiqueta=").append(etiqueta);
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }

    
    
    
    
}
