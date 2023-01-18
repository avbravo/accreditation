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
public class Requisito {
    @Column
    private String requisito;
    @Column
    private Boolean completado;

    public Requisito() {
    }

    public Requisito(String requisito, Boolean completado) {
        this.requisito = requisito;
        this.completado = completado;
    }

    public String getRequisito() {
        return requisito;
    }

    public void setRequisito(String requisito) {
        this.requisito = requisito;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Requisito{");
        sb.append("requisito=").append(requisito);
        sb.append(", completado=").append(completado);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
