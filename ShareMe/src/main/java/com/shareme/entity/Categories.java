/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shareme.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author hokho
 */
@Entity
@Table(name = "categories")
@XmlRootElement
@Builder
@Getter
@Setter
@AllArgsConstructor
@NamedQueries({
    @NamedQuery(name = "Categories.findAll", query = "SELECT c FROM Categories c"),
    @NamedQuery(name = "Categories.findById", query = "SELECT c FROM Categories c WHERE c.id = :id"),
    @NamedQuery(name = "Categories.findByName", query = "SELECT c FROM Categories c WHERE c.name = :name"),
    @NamedQuery(name = "Categories.findByIcon", query = "SELECT c FROM Categories c WHERE c.icon = :icon"),
    @NamedQuery(name = "Categories.findByCreatedDate", query = "SELECT c FROM Categories c WHERE c.createdDate = :createdDate"),
    @NamedQuery(name = "Categories.findByUpdatedDate", query = "SELECT c FROM Categories c WHERE c.updatedDate = :updatedDate"),
    @NamedQuery(name = "Categories.findByActive", query = "SELECT c FROM Categories c WHERE c.active = :active")})
public class Categories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "icon")
    private String icon;
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "active")
    private Boolean active;
    @OneToMany(mappedBy = "categoriesId")
    private Set<Menuitems> menuitemsSet;
    @OneToMany(mappedBy = "parentId", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Categories> categoriesSet;
    @JoinColumn(name = "parentId", referencedColumnName = "id")
    @ManyToOne
    private Categories parentId;

    public Categories() {
    }

    public Categories(Integer id) {
        this.id = id;
    }

    public Categories(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @XmlTransient
    public Set<Menuitems> getMenuitemsSet() {
        return menuitemsSet;
    }

    public void setMenuitemsSet(Set<Menuitems> menuitemsSet) {
        this.menuitemsSet = menuitemsSet;
    }

    @XmlTransient
    public Set<Categories> getCategoriesSet() {
        return categoriesSet;
    }

    public void setCategoriesSet(Set<Categories> categoriesSet) {
        this.categoriesSet = categoriesSet;
    }

    public Categories getParentId() {
        return parentId;
    }

    public void setParentId(Categories parentId) {
        this.parentId = parentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categories)) {
            return false;
        }
        Categories other = (Categories) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Categories[ id=" + id + " ]";
    }
    
}
