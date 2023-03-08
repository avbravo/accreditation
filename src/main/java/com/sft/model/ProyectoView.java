/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.Embedded;
import com.jmoordb.core.annotation.Id;
import com.jmoordb.core.annotation.Referenced;
import com.jmoordb.core.annotation.ViewEntity;
import com.jmoordb.core.annotation.enumerations.AutogeneratedActive;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author avbravo
 */
@ViewEntity(collection = "proyecto")
public class ProyectoView {
     
    @Id(autogeneratedActive = AutogeneratedActive.ON)
    private Long idproyecto;
    @Column
    private String proyecto;
    @Column
    private String descripcion;
 
    @Column
    private String prefijo;

    @Column
    private Date fechafinal;
    @Column
    private Date fechainicial;

    @Referenced(from = "Icono", localField = "idicono")
    private Icono icono;
   
    @Column
    private Double avance;

    @Column
    private String estado;
    
    
     
    @Column(commentary="true cierra los sprint y genera nuevos de manera automatica ")
   private Boolean generarsprintautomaticamente;
    @Column(commentary="semanal,quincenal, mensual, trimestral, semestral,anual")
    private String perocidiadsprint;
    
    @Embedded
    DiasLaborable diasLaborable;
    
    @Column
    private Boolean active;

    public ProyectoView() {
    }

    public ProyectoView(Long idproyecto, String proyecto, String descripcion, String prefijo, Date fechafinal, Date fechainicial, Icono icono, Double avance, String estado, Boolean generarsprintautomaticamente, String perocidiadsprint, DiasLaborable diasLaborable, Boolean active) {
        this.idproyecto = idproyecto;
        this.proyecto = proyecto;
        this.descripcion = descripcion;
        this.prefijo = prefijo;
        this.fechafinal = fechafinal;
        this.fechainicial = fechainicial;
        this.icono = icono;
        this.avance = avance;
        this.estado = estado;
        this.generarsprintautomaticamente = generarsprintautomaticamente;
        this.perocidiadsprint = perocidiadsprint;
        this.diasLaborable = diasLaborable;
        this.active = active;
    }

    public Long getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Long idproyecto) {
        this.idproyecto = idproyecto;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public Date getFechafinal() {
        return fechafinal;
    }

    public void setFechafinal(Date fechafinal) {
        this.fechafinal = fechafinal;
    }

    public Date getFechainicial() {
        return fechainicial;
    }

    public void setFechainicial(Date fechainicial) {
        this.fechainicial = fechainicial;
    }

    public Icono getIcono() {
        return icono;
    }

    public void setIcono(Icono icono) {
        this.icono = icono;
    }

    public Double getAvance() {
        return avance;
    }

    public void setAvance(Double avance) {
        this.avance = avance;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getGenerarsprintautomaticamente() {
        return generarsprintautomaticamente;
    }

    public void setGenerarsprintautomaticamente(Boolean generarsprintautomaticamente) {
        this.generarsprintautomaticamente = generarsprintautomaticamente;
    }

    public String getPerocidiadsprint() {
        return perocidiadsprint;
    }

    public void setPerocidiadsprint(String perocidiadsprint) {
        this.perocidiadsprint = perocidiadsprint;
    }

    public DiasLaborable getDiasLaborable() {
        return diasLaborable;
    }

    public void setDiasLaborable(DiasLaborable diasLaborable) {
        this.diasLaborable = diasLaborable;
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
        sb.append("ProyectoView{");
        sb.append("idproyecto=").append(idproyecto);
        sb.append(", proyecto=").append(proyecto);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", prefijo=").append(prefijo);
        sb.append(", fechafinal=").append(fechafinal);
        sb.append(", fechainicial=").append(fechainicial);
        sb.append(", icono=").append(icono);
        sb.append(", avance=").append(avance);
        sb.append(", estado=").append(estado);
        sb.append(", generarsprintautomaticamente=").append(generarsprintautomaticamente);
        sb.append(", perocidiadsprint=").append(perocidiadsprint);
        sb.append(", diasLaborable=").append(diasLaborable);
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.idproyecto);
        hash = 79 * hash + Objects.hashCode(this.proyecto);
        hash = 79 * hash + Objects.hashCode(this.descripcion);
        hash = 79 * hash + Objects.hashCode(this.prefijo);
        hash = 79 * hash + Objects.hashCode(this.fechafinal);
        hash = 79 * hash + Objects.hashCode(this.fechainicial);
        hash = 79 * hash + Objects.hashCode(this.icono);
        hash = 79 * hash + Objects.hashCode(this.avance);
        hash = 79 * hash + Objects.hashCode(this.estado);
        hash = 79 * hash + Objects.hashCode(this.generarsprintautomaticamente);
        hash = 79 * hash + Objects.hashCode(this.perocidiadsprint);
        hash = 79 * hash + Objects.hashCode(this.diasLaborable);
        hash = 79 * hash + Objects.hashCode(this.active);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProyectoView other = (ProyectoView) obj;
        if (!Objects.equals(this.proyecto, other.proyecto)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.prefijo, other.prefijo)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.perocidiadsprint, other.perocidiadsprint)) {
            return false;
        }
        if (!Objects.equals(this.idproyecto, other.idproyecto)) {
            return false;
        }
        if (!Objects.equals(this.fechafinal, other.fechafinal)) {
            return false;
        }
        if (!Objects.equals(this.fechainicial, other.fechainicial)) {
            return false;
        }
        if (!Objects.equals(this.icono, other.icono)) {
            return false;
        }
        if (!Objects.equals(this.avance, other.avance)) {
            return false;
        }
        if (!Objects.equals(this.generarsprintautomaticamente, other.generarsprintautomaticamente)) {
            return false;
        }
        if (!Objects.equals(this.diasLaborable, other.diasLaborable)) {
            return false;
        }
        return Objects.equals(this.active, other.active);
    }

    
   
   
   

  

}
