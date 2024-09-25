/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shareme.entity;

import jakarta.persistence.*;
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
@Table(name = "followstore")
@XmlRootElement
@Builder
@Getter
@Setter
@AllArgsConstructor
@NamedQueries({
    @NamedQuery(name = "Followstore.findAll", query = "SELECT f FROM Followstore f"),
    @NamedQuery(name = "Followstore.findByUserId", query = "SELECT f FROM Followstore f WHERE f.followstorePK.userId = :userId"),
    @NamedQuery(name = "Followstore.findByStoreId", query = "SELECT f FROM Followstore f WHERE f.followstorePK.storeId = :storeId"),
    @NamedQuery(name = "Followstore.findByCreatedDate", query = "SELECT f FROM Followstore f WHERE f.createdDate = :createdDate"),
    @NamedQuery(name = "Followstore.findByUpdatedDate", query = "SELECT f FROM Followstore f WHERE f.updatedDate = :updatedDate"),
    @NamedQuery(name = "Followstore.findByActive", query = "SELECT f FROM Followstore f WHERE f.active = :active")})
public class Followstore implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FollowstorePK followstorePK;
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "active")
    private Boolean active;
    @JoinColumn(name = "storeId", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Stores stores;
    @JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Followstore() {
    }

    public Followstore(FollowstorePK followstorePK) {
        this.followstorePK = followstorePK;
    }

    public Followstore(int userId, int storeId) {
        this.followstorePK = new FollowstorePK(userId, storeId);
    }

    public FollowstorePK getFollowstorePK() {
        return followstorePK;
    }

    public void setFollowstorePK(FollowstorePK followstorePK) {
        this.followstorePK = followstorePK;
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
        hash += (followstorePK != null ? followstorePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Followstore)) {
            return false;
        }
        Followstore other = (Followstore) object;
        if ((this.followstorePK == null && other.followstorePK != null) || (this.followstorePK != null && !this.followstorePK.equals(other.followstorePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Followstore[ followstorePK=" + followstorePK + " ]";
    }
    
}
