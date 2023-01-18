/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.Entity;
import com.jmoordb.core.annotation.Id;
import com.jmoordb.core.annotation.Referenced;
import com.jmoordb.core.annotation.enumerations.TypeReferenced;

/**
 *
 * @author avbravo
 */
@Entity
public class Headquarter {
    @Id
    private Long idheadquarter;
    @Column
    private String headquarter;
    @Column
    private Boolean active;
    
    @Referenced(from = "institution",localField = "idinstitution",typeReferenced = TypeReferenced.REFERENCED)
    private Institution institution;

    public Headquarter() {
    }

    public Headquarter(Long idheadquarter, String headquarter, Boolean active, Institution institution) {
        this.idheadquarter = idheadquarter;
        this.headquarter = headquarter;
        this.active = active;
        this.institution = institution;
    }

    public Long getIdheadquarter() {
        return idheadquarter;
    }

    public void setIdheadquarter(Long idheadquarter) {
        this.idheadquarter = idheadquarter;
    }

    public String getHeadquarter() {
        return headquarter;
    }

    public void setHeadquarter(String headquarter) {
        this.headquarter = headquarter;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Headquarter{");
        sb.append("idheadquarter=").append(idheadquarter);
        sb.append(", headquarter=").append(headquarter);
        sb.append(", active=").append(active);
        sb.append(", institution=").append(institution);
        sb.append('}');
        return sb.toString();
    }

   
  
}
