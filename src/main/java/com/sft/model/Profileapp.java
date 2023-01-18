/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.DocumentEmbeddable;
import com.jmoordb.core.annotation.Referenced;
import java.util.Objects;

/**
 *
 * @author avbravo
 */
@DocumentEmbeddable
public class Profileapp {
    
   
    @Referenced(from = "departament", localField = "iddepartament")
    private Departament departament;
    @Referenced(from = "role", localField = "idrole")
    private Role role;
    
 @Column
    private Boolean active;

    public Profileapp() {
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Departament getDepartament() {
        return departament;
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Profileapp{");
        sb.append("active=").append(active);
        sb.append(", departament=").append(departament);
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }

 
    
    
}
