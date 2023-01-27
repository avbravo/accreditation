/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.Id;
import com.jmoordb.core.annotation.Projection;
import com.jmoordb.core.annotation.enumerations.AutogeneratedActive;

/**
 *
 * @author avbravo
 */
@Projection(collection = "user")
public class UserPersonal {
    @Id(autogeneratedActive = AutogeneratedActive.ON)
    private Long iduser;
    @Column
    private String name;
    @Column
    private String photo;

    public UserPersonal() {
    }

    public UserPersonal(Long iduser, String name, String photo) {
        this.iduser = iduser;
        this.name = name;
        this.photo = photo;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserPersonal{");
        sb.append("iduser=").append(iduser);
        sb.append(", name=").append(name);
        sb.append(", photo=").append(photo);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
