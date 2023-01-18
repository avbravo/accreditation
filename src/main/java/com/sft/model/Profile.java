/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.DocumentEmbeddable;
import com.jmoordb.core.annotation.Referenced;
import com.jmoordb.core.annotation.enumerations.TypeReferenced;

/**
 *
 * @author avbravo
 */
@DocumentEmbeddable
public class Profile {
    
     
    @Referenced(from = "applicative", localField = "idapplicative")
    private Applicative applicative;
    @Referenced(from = "role", localField = "idrole")    
    private Role role;
    @Referenced(from = "departament", localField = "iddepartament")
    private Departament departament;
   
 @Column
    private Boolean active;
    public Profile() {
    }

    public Profile(Applicative applicative, Role role, Departament departament, Boolean active) {
        this.applicative = applicative;
        this.role = role;
        this.departament = departament;
        this.active = active;
    }

    public Applicative getApplicative() {
        return applicative;
    }

    public void setApplicative(Applicative applicative) {
        this.applicative = applicative;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Departament getDepartament() {
        return departament;
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
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
        sb.append("Profile{");
        sb.append("applicative=").append(applicative);
        sb.append(", role=").append(role);
        sb.append(", departament=").append(departament);
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }

      
    
   
}
