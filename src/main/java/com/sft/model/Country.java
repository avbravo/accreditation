/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.Entity;
import com.jmoordb.core.annotation.Id;

/**
 *
 * @author avbravo
 */
@Entity()
public class Country {

    @Id
    private String idcountry;
    @Column
    private String country;

    public Country() {
    }

    public Country(String idcountry, String country) {
        this.idcountry = idcountry;
        this.country = country;
    }

    public String getIdcountry() {
        return idcountry;
    }

    public void setIdcountry(String idcountry) {
        this.idcountry = idcountry;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Country{");
        sb.append("idcountry=").append(idcountry);
        sb.append(", country=").append(country);
        sb.append('}');
        return sb.toString();
    }


}
