/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sft.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.Entity;
import com.jmoordb.core.annotation.Id;
import com.jmoordb.core.annotation.Referenced;
import java.util.Objects;

/**
 *
 * @author avbravo
 */
@Entity()
public class District {

    @Id
    private String iddistrict;
    @Column
    private Boolean active;
    @Column
    private String district;

    @Referenced(from = "province", localField = "idprovince")
    private Province province;

    public District() {
    }

    public District(Boolean active, String district, String iddistrict, Province province) {
        this.active = active;
        this.district = district;
        this.iddistrict = iddistrict;
        this.province = province;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(String iddistrict) {
        this.iddistrict = iddistrict;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("District{");
        sb.append("active=").append(active);
        sb.append(", district=").append(district);
        sb.append(", iddistrict=").append(iddistrict);
        sb.append(", province=").append(province);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.iddistrict);
        hash = 97 * hash + Objects.hashCode(this.active);
        hash = 97 * hash + Objects.hashCode(this.district);
        hash = 97 * hash + Objects.hashCode(this.province);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final District other = (District) obj;
        if (!Objects.equals(this.iddistrict, other.iddistrict)) {
            return false;
        }
        if (!Objects.equals(this.district, other.district)) {
            return false;
        }
        if (!Objects.equals(this.active, other.active)) {
            return false;
        }
        return Objects.equals(this.province, other.province);
    }

    
    
    
}
