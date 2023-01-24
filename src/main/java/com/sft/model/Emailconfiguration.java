/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.DocumentEmbeddable;
import com.jmoordb.core.annotation.Ignore;
import com.jmoordb.core.annotation.Referenced;
import com.jmoordb.core.annotation.enumerations.TypeReferenced;

/**
 *
 * @author avbravo
 */
@DocumentEmbeddable
public class Emailconfiguration {
    @Ignore
    private Long id;
     @Column
      private String email;
     @Column
    private String password;
     @Column
    private String mailSmtpHost;
     @Column
    private String mailSmtpAuth;
     @Column
    private String mailSmtpPort;
     @Column
    private String mailSmtpStarttlsEnable;
 @Column
    private Boolean active;
    public Emailconfiguration() {
    }

    public Emailconfiguration(Long id, String email, String password, String mailSmtpHost, String mailSmtpAuth, String mailSmtpPort, String mailSmtpStarttlsEnable, Boolean active) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.mailSmtpHost = mailSmtpHost;
        this.mailSmtpAuth = mailSmtpAuth;
        this.mailSmtpPort = mailSmtpPort;
        this.mailSmtpStarttlsEnable = mailSmtpStarttlsEnable;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailSmtpHost() {
        return mailSmtpHost;
    }

    public void setMailSmtpHost(String mailSmtpHost) {
        this.mailSmtpHost = mailSmtpHost;
    }

    public String getMailSmtpAuth() {
        return mailSmtpAuth;
    }

    public void setMailSmtpAuth(String mailSmtpAuth) {
        this.mailSmtpAuth = mailSmtpAuth;
    }

    public String getMailSmtpPort() {
        return mailSmtpPort;
    }

    public void setMailSmtpPort(String mailSmtpPort) {
        this.mailSmtpPort = mailSmtpPort;
    }

    public String getMailSmtpStarttlsEnable() {
        return mailSmtpStarttlsEnable;
    }

    public void setMailSmtpStarttlsEnable(String mailSmtpStarttlsEnable) {
        this.mailSmtpStarttlsEnable = mailSmtpStarttlsEnable;
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
        sb.append("Emailconfiguration{");
        sb.append("id=").append(id);
        sb.append(", email=").append(email);
        sb.append(", password=").append(password);
        sb.append(", mailSmtpHost=").append(mailSmtpHost);
        sb.append(", mailSmtpAuth=").append(mailSmtpAuth);
        sb.append(", mailSmtpPort=").append(mailSmtpPort);
        sb.append(", mailSmtpStarttlsEnable=").append(mailSmtpStarttlsEnable);
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }

    

    

      
    
   
}
