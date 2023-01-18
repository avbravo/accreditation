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
import com.jmoordb.core.annotation.enumerations.AutogeneratedActive;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author avbravo
 */
@Entity()
public class User {
   
    @Id(autogeneratedActive = AutogeneratedActive.ON)
    private Long iduser;
    @Column
    private String username;
  
    @Column
    private String cellphone;
    @Column
    private String email;
    @Column
    private String identificationcard;
    @Column
    private String sex;
    @Column
    private String socialsecuritynumber;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private String photo;
    
    @Column
    private Boolean active;
      
      @Embedded
      List<Profile> profile;
      
      @Referenced(from = "headquarter", localField = "idheadquarter")
      Headquarter headquarter;
      

    public User() {
    }

    public User(Long iduser, String username, String cellphone, String email, String identificationcard, String sex, String socialsecuritynumber, String name, String password, String photo, Boolean active, List<Profile> profile, Headquarter headquarter) {
        this.iduser = iduser;
        this.username = username;
        this.cellphone = cellphone;
        this.email = email;
        this.identificationcard = identificationcard;
        this.sex = sex;
        this.socialsecuritynumber = socialsecuritynumber;
        this.name = name;
        this.password = password;
        this.photo = photo;
        this.active = active;
        this.profile = profile;
        this.headquarter = headquarter;
    }

    

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentificationcard() {
        return identificationcard;
    }

    public void setIdentificationcard(String identificationcard) {
        this.identificationcard = identificationcard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSocialsecuritynumber() {
        return socialsecuritynumber;
    }

    public void setSocialsecuritynumber(String socialsecuritynumber) {
        this.socialsecuritynumber = socialsecuritynumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Profile> getProfile() {
        return profile;
    }

    public void setProfile(List<Profile> profile) {
        this.profile = profile;
    }

    public Headquarter getHeadquarter() {
        return headquarter;
    }

    public void setHeadquarter(Headquarter headquarter) {
        this.headquarter = headquarter;
    }

    
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{");
        sb.append("iduser=").append(iduser);
        sb.append(", username=").append(username);
        sb.append(", cellphone=").append(cellphone);
        sb.append(", email=").append(email);
        sb.append(", identificationcard=").append(identificationcard);
        sb.append(", sex=").append(sex);
        sb.append(", socialsecuritynumber=").append(socialsecuritynumber);
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append(", photo=").append(photo);
        sb.append(", active=").append(active);
        sb.append(", profile=").append(profile);
        sb.append(", headquarter=").append(headquarter);
        sb.append('}');
        return sb.toString();
    }

   
    
  
  

}
