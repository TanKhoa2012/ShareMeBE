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
@Table(name = "followuser")
@XmlRootElement
@Builder
@Getter
@Setter
@AllArgsConstructor
@NamedQueries({
    @NamedQuery(name = "Followuser.findAll", query = "SELECT f FROM Followuser f"),
    @NamedQuery(name = "Followuser.findByFollowerId", query = "SELECT f FROM Followuser f WHERE f.followuserPK.followerId = :followerId"),
    @NamedQuery(name = "Followuser.findByFollowedId", query = "SELECT f FROM Followuser f WHERE f.followuserPK.followedId = :followedId"),
    @NamedQuery(name = "Followuser.findByCreatedDate", query = "SELECT f FROM Followuser f WHERE f.createdDate = :createdDate"),
    @NamedQuery(name = "Followuser.findByUpdatedDate", query = "SELECT f FROM Followuser f WHERE f.updatedDate = :updatedDate"),
    @NamedQuery(name = "Followuser.findByActive", query = "SELECT f FROM Followuser f WHERE f.active = :active")})
public class Followuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FollowuserPK followuserPK;
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "active")
    private Boolean active;
    @JoinColumn(name = "followerId", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;
    @JoinColumn(name = "followedId", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users1;

    public Followuser() {
    }

    public Followuser(FollowuserPK followuserPK) {
        this.followuserPK = followuserPK;
    }

    public Followuser(int followerId, int followedId) {
        this.followuserPK = new FollowuserPK(followerId, followedId);
    }

    public FollowuserPK getFollowuserPK() {
        return followuserPK;
    }

    public void setFollowuserPK(FollowuserPK followuserPK) {
        this.followuserPK = followuserPK;
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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Users getUsers1() {
        return users1;
    }

    public void setUsers1(Users users1) {
        this.users1 = users1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (followuserPK != null ? followuserPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Followuser)) {
            return false;
        }
        Followuser other = (Followuser) object;
        if ((this.followuserPK == null && other.followuserPK != null) || (this.followuserPK != null && !this.followuserPK.equals(other.followuserPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Followuser[ followuserPK=" + followuserPK + " ]";
    }
    
}
