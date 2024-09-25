/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shareme.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 *
 * @author hokho
 */
@XmlRootElement
@Builder
@Getter
@Setter
@Embeddable
public class EmployeePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "userId")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "storeId")
    private int storeId;

    public EmployeePK() {
    }

    public EmployeePK(int userId, int storeId) {
        this.userId = userId;
        this.storeId = storeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) storeId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeePK)) {
            return false;
        }
        EmployeePK other = (EmployeePK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.storeId != other.storeId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EmployeePK[ userId=" + userId + ", storeId=" + storeId + " ]";
    }
    
}
