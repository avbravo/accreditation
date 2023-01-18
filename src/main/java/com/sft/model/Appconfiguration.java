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

/**
 *
 * @author avbravo
 */
@Entity()
public class Appconfiguration {

    @Id(autogeneratedActive = AutogeneratedActive.ON)
    private Long idappconfiguration;
    @Column
    private Boolean active;

    @Referenced(from = "applicative", localField = "idapplicative")
    private Applicative applicative;

    @Column
    private String event;

    @Column
    private String module;

    @Embedded
    List<Profileapp> profileapp;

    public Appconfiguration() {
    }

    public Appconfiguration(Long idappconfiguration, Boolean active, Applicative applicative, String event, String module, List<Profileapp> profileapp) {
        this.idappconfiguration = idappconfiguration;
        this.active = active;
        this.applicative = applicative;
        this.event = event;
        this.module = module;
        this.profileapp = profileapp;
    }

    public Long getIdappconfiguration() {
        return idappconfiguration;
    }

    public void setIdappconfiguration(Long idappconfiguration) {
        this.idappconfiguration = idappconfiguration;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Applicative getApplicative() {
        return applicative;
    }

    public void setApplicative(Applicative applicative) {
        this.applicative = applicative;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public List<Profileapp> getProfileapp() {
        return profileapp;
    }

    public void setProfileapp(List<Profileapp> profileapp) {
        this.profileapp = profileapp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Appconfiguration{");
        sb.append("idappconfiguration=").append(idappconfiguration);
        sb.append(", active=").append(active);
        sb.append(", applicative=").append(applicative);
        sb.append(", event=").append(event);
        sb.append(", module=").append(module);
        sb.append(", profileapp=").append(profileapp);
        sb.append('}');
        return sb.toString();
    }

}
