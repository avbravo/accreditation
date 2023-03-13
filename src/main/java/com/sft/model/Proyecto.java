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
import com.jmoordb.core.annotation.ViewReferenced;
import com.jmoordb.core.annotation.enumerations.AutogeneratedActive;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author avbravo
 */
@Entity
public class Proyecto {
     
    @Id(autogeneratedActive = AutogeneratedActive.ON)
    private Long idproyecto;
    @Column
    private String proyecto;
    @Column
    private String descripcion;
 @Referenced(from = "Icono", localField = "idicono")
    private Icono icono;
    @Column
    private String prefijo;

    @Column
    private Date fechafinal;
    @Column
    private Date fechainicial;

   
    @ViewReferenced(from = "departament", localField = "iddepartament")
    List<DepartamentView> departamentView;
      
    @Referenced(from = "grupo", localField = "idgrupo")
    private Grupo grupo;

//    @ViewReferenced(from = "user", localField = "iduser")
//    private List<UserView> userView;
   @Embedded
   List<ProyectoMiembro> proyectoMiembro;
  
   
    @ViewReferenced(from = "central", localField = "idcentral")
    private CentralView centralView;
   
    @Column
    private Double avance;

     @Column(commentary = "iniciado,detenido, finalizado")
    private String estado;
    @Column(commentary = "true indioa privado y false indica publico")
    private Boolean privado;
    @Column
    private Boolean active;
    
    
    
    @Column(commentary="true cierra los sprint y genera nuevos de manera automatica ")
   private Boolean generarsprintautomaticamente;
    @Column(commentary="semanal,quincenal, mensual, trimestral, semestral,anual")
    private String perocidiadsprint;
    
    @Embedded
    DiasLaborable diasLaborable;
   @Embedded
List<ActionHistory> actionHistory;

    public Proyecto() {
    }

    public Proyecto(Long idproyecto, String proyecto, String descripcion, Icono icono, String prefijo, Date fechafinal, Date fechainicial, List<DepartamentView> departamentView, Grupo grupo, List<ProyectoMiembro> proyectoMiembro, CentralView centralView, Double avance, String estado, Boolean privado, Boolean active, Boolean generarsprintautomaticamente, String perocidiadsprint, DiasLaborable diasLaborable, List<ActionHistory> actionHistory) {
        this.idproyecto = idproyecto;
        this.proyecto = proyecto;
        this.descripcion = descripcion;
        this.icono = icono;
        this.prefijo = prefijo;
        this.fechafinal = fechafinal;
        this.fechainicial = fechainicial;
        this.departamentView = departamentView;
        this.grupo = grupo;
        this.proyectoMiembro = proyectoMiembro;
        this.centralView = centralView;
        this.avance = avance;
        this.estado = estado;
        this.privado = privado;
        this.active = active;
        this.generarsprintautomaticamente = generarsprintautomaticamente;
        this.perocidiadsprint = perocidiadsprint;
        this.diasLaborable = diasLaborable;
        this.actionHistory = actionHistory;
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

    public Icono getIcono() {
        return icono;
    }

    public void setIcono(Icono icono) {
        this.icono = icono;
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

    public List<DepartamentView> getDepartamentView() {
        return departamentView;
    }

    public void setDepartamentView(List<DepartamentView> departamentView) {
        this.departamentView = departamentView;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<ProyectoMiembro> getProyectoMiembro() {
        return proyectoMiembro;
    }

    public void setProyectoMiembro(List<ProyectoMiembro> proyectoMiembro) {
        this.proyectoMiembro = proyectoMiembro;
    }

    public CentralView getCentralView() {
        return centralView;
    }

    public void setCentralView(CentralView centralView) {
        this.centralView = centralView;
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

    public Boolean getPrivado() {
        return privado;
    }

    public void setPrivado(Boolean privado) {
        this.privado = privado;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public List<ActionHistory> getActionHistory() {
        return actionHistory;
    }

    public void setActionHistory(List<ActionHistory> actionHistory) {
        this.actionHistory = actionHistory;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Proyecto{");
        sb.append("idproyecto=").append(idproyecto);
        sb.append(", proyecto=").append(proyecto);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", icono=").append(icono);
        sb.append(", prefijo=").append(prefijo);
        sb.append(", fechafinal=").append(fechafinal);
        sb.append(", fechainicial=").append(fechainicial);
        sb.append(", departamentView=").append(departamentView);
        sb.append(", grupo=").append(grupo);
        sb.append(", proyectoMiembro=").append(proyectoMiembro);
        sb.append(", centralView=").append(centralView);
        sb.append(", avance=").append(avance);
        sb.append(", estado=").append(estado);
        sb.append(", privado=").append(privado);
        sb.append(", active=").append(active);
        sb.append(", generarsprintautomaticamente=").append(generarsprintautomaticamente);
        sb.append(", perocidiadsprint=").append(perocidiadsprint);
        sb.append(", diasLaborable=").append(diasLaborable);
        sb.append(", actionHistory=").append(actionHistory);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.idproyecto);
        hash = 59 * hash + Objects.hashCode(this.proyecto);
        hash = 59 * hash + Objects.hashCode(this.descripcion);
        hash = 59 * hash + Objects.hashCode(this.icono);
        hash = 59 * hash + Objects.hashCode(this.prefijo);
        hash = 59 * hash + Objects.hashCode(this.fechafinal);
        hash = 59 * hash + Objects.hashCode(this.fechainicial);
        hash = 59 * hash + Objects.hashCode(this.departamentView);
        hash = 59 * hash + Objects.hashCode(this.grupo);
        hash = 59 * hash + Objects.hashCode(this.proyectoMiembro);
        hash = 59 * hash + Objects.hashCode(this.centralView);
        hash = 59 * hash + Objects.hashCode(this.avance);
        hash = 59 * hash + Objects.hashCode(this.estado);
        hash = 59 * hash + Objects.hashCode(this.privado);
        hash = 59 * hash + Objects.hashCode(this.active);
        hash = 59 * hash + Objects.hashCode(this.generarsprintautomaticamente);
        hash = 59 * hash + Objects.hashCode(this.perocidiadsprint);
        hash = 59 * hash + Objects.hashCode(this.diasLaborable);
        hash = 59 * hash + Objects.hashCode(this.actionHistory);
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
        final Proyecto other = (Proyecto) obj;
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
        if (!Objects.equals(this.icono, other.icono)) {
            return false;
        }
        if (!Objects.equals(this.fechafinal, other.fechafinal)) {
            return false;
        }
        if (!Objects.equals(this.fechainicial, other.fechainicial)) {
            return false;
        }
        if (!Objects.equals(this.departamentView, other.departamentView)) {
            return false;
        }
        if (!Objects.equals(this.grupo, other.grupo)) {
            return false;
        }
        if (!Objects.equals(this.proyectoMiembro, other.proyectoMiembro)) {
            return false;
        }
        if (!Objects.equals(this.centralView, other.centralView)) {
            return false;
        }
        if (!Objects.equals(this.avance, other.avance)) {
            return false;
        }
        if (!Objects.equals(this.privado, other.privado)) {
            return false;
        }
        if (!Objects.equals(this.active, other.active)) {
            return false;
        }
        if (!Objects.equals(this.generarsprintautomaticamente, other.generarsprintautomaticamente)) {
            return false;
        }
        if (!Objects.equals(this.diasLaborable, other.diasLaborable)) {
            return false;
        }
        return Objects.equals(this.actionHistory, other.actionHistory);
    }

   
   

  

}
