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
import java.util.Date;


/**
 *
 * @author hokho
 */
@Entity
@Table(name = "itemimages")
@XmlRootElement
@Builder
@Getter
@Setter
@AllArgsConstructor
@NamedQueries({
    @NamedQuery(name = "Itemimages.findAll", query = "SELECT i FROM Itemimages i"),
    @NamedQuery(name = "Itemimages.findById", query = "SELECT i FROM Itemimages i WHERE i.id = :id"),
    @NamedQuery(name = "Itemimages.findByUrl", query = "SELECT i FROM Itemimages i WHERE i.url = :url"),
    @NamedQuery(name = "Itemimages.findByPublicUrl", query = "SELECT i FROM Itemimages i WHERE i.publicUrl = :publicUrl"),
    @NamedQuery(name = "Itemimages.findByCreatedDate", query = "SELECT i FROM Itemimages i WHERE i.createdDate = :createdDate"),
    @NamedQuery(name = "Itemimages.findByUpdatedDate", query = "SELECT i FROM Itemimages i WHERE i.updatedDate = :updatedDate"),
    @NamedQuery(name = "Itemimages.findByActive", query = "SELECT i FROM Itemimages i WHERE i.active = :active"),
        @NamedQuery(name = "Itemimages.findByMenuItemsId", query = "SELECT i FROM Itemimages i WHERE i.menuItemsId.id = :menuItemsId")})
public class Itemimages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "url")
    private String url;
    @Size(max = 255)
    @Column(name = "publicUrl")
    private String publicUrl;
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "active")
    private Boolean active;
    @JoinColumn(name = "menuItemsId", referencedColumnName = "id")
    @ManyToOne
    private Menuitems menuItemsId;

    public Itemimages() {
    }

    public Itemimages(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublicUrl() {
        return publicUrl;
    }

    public void setPublicUrl(String publicUrl) {
        this.publicUrl = publicUrl;
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

    public Menuitems getMenuItemsId() {
        return menuItemsId;
    }

    public void setMenuItemsId(Menuitems menuItemsId) {
        this.menuItemsId = menuItemsId;
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
        if (!(object instanceof Itemimages)) {
            return false;
        }
        Itemimages other = (Itemimages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Itemimages[ id=" + id + " ]";
    }
    
}
