/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shareme.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
@Entity
@Table(name = "employee")
@XmlRootElement
@Builder
@Getter
@Setter
@AllArgsConstructor
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByUserId", query = "SELECT e FROM Employee e WHERE e.employeePK.userId = :userId"),
        @NamedQuery(name = "Employee.findByUsername", query = "SELECT e FROM Employee e WHERE e.users.username = :username"),
    @NamedQuery(name = "Employee.findByStoreId", query = "SELECT e FROM Employee e WHERE e.employeePK.storeId = :storeId"),
    @NamedQuery(name = "Employee.findByEmploymentType", query = "SELECT e FROM Employee e WHERE e.employmentType = :employmentType")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmployeePK employeePK;
    @Size(max = 14)
    @Column(name = "employmentType")
    private String employmentType;
    @JoinColumn(name = "storeId", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Stores stores;
    @JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Employee() {
    }

    public Employee(EmployeePK employeePK) {
        this.employeePK = employeePK;
    }

    public Employee(int userId, int storeId) {
        this.employeePK = new EmployeePK(userId, storeId);
    }

    public EmployeePK getEmployeePK() {
        return employeePK;
    }

    public void setEmployeePK(EmployeePK employeePK) {
        this.employeePK = employeePK;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public Stores getStores() {
        return stores;
    }

    public void setStores(Stores stores) {
        this.stores = stores;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeePK != null ? employeePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.employeePK == null && other.employeePK != null) || (this.employeePK != null && !this.employeePK.equals(other.employeePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee[ employeePK=" + employeePK + " ]";
    }
    
}
