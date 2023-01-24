/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.DocumentEmbeddable;
import com.jmoordb.core.annotation.Ignore;

/**
 *
 * @author avbravo
 */
@DocumentEmbeddable
public class Applicativerole {
    @Ignore
    private Long id;
     
    @Column
    private Long idrole;
   
    @Column 
    private String path;
   
 @Column
    private Boolean active;
    public Applicativerole() {
    }

    public Applicativerole(Long id, Long idrole, String path, Boolean active) {
        this.id = id;
        this.idrole = idrole;
        this.path = path;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdrole() {
        return idrole;
    }

    public void setIdrole(Long idrole) {
        this.idrole = idrole;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
        sb.append("Applicativerole{");
        sb.append("id=").append(id);
        sb.append(", idrole=").append(idrole);
        sb.append(", path=").append(path);
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }

  
    
    
    

      
    
   
}
