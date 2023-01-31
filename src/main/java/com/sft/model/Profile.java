/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.DocumentEmbeddable;
import com.jmoordb.core.annotation.Ignore;
import com.jmoordb.core.annotation.Referenced;
import com.jmoordb.core.annotation.ViewReferenced;

/**
 *
 * @author avbravo
 */
@DocumentEmbeddable
public class Profile {
    @Ignore
    private Long id;
     
//    @Referenced(from = "applicative", localField = "idapplicative")
//    private Applicative applicative;
    @ViewReferenced(from = "applicative", localField = "idapplicative")
    private ApplicativeView applicativeView;
    @Referenced(from = "role", localField = "idrole")    
    private Role role;
//    @Referenced(from = "departament", localField = "iddepartament")
//    private Departament departament;
   
    @ViewReferenced(from = "departament", localField = "iddepartament")
    private DepartamentView departamentView;
   
 @Column
    private Boolean active;
    public Profile() {
    }

    public Profile(Long id, ApplicativeView applicativeView, Role role, DepartamentView departamentView, Boolean active) {
        this.id = id;
        this.applicativeView = applicativeView;
        this.role = role;
        this.departamentView = departamentView;
        this.active = active;
    }

    public ApplicativeView getApplicativeView() {
        return applicativeView;
    }

    public void setApplicativeView(ApplicativeView applicativeView) {
        this.applicativeView = applicativeView;
    }

  

    public DepartamentView getDepartamentView() {
        return departamentView;
    }

    public void setDepartamentView(DepartamentView departamentView) {
        this.departamentView = departamentView;
    }

  
    
    
 
    

   
 
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
        sb.append("id=").append(id);
        sb.append(", applicativeView=").append(applicativeView);
        sb.append(", role=").append(role);
        sb.append(", departamentView=").append(departamentView);
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }

   
 

    


      
    
   
}
