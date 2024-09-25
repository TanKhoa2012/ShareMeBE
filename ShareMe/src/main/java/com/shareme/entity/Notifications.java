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
@Table(name = "notifications")
@XmlRootElement
@Builder
@Getter
@Setter
@AllArgsConstructor
@NamedQueries({
    @NamedQuery(name = "Notifications.findAll", query = "SELECT n FROM Notifications n"),
    @NamedQuery(name = "Notifications.findById", query = "SELECT n FROM Notifications n WHERE n.id = :id"),
        @NamedQuery(name = "Notifications.findByUserId", query = "SELECT n FROM Notifications n WHERE n.users.id = :userId"),
        @NamedQuery(name = "Notifications.findByStoreId", query = "SELECT n FROM Notifications n WHERE n.stores.id = :storeId"),
    @NamedQuery(name = "Notifications.findBySentDate", query = "SELECT n FROM Notifications n WHERE n.sentDate = :sentDate")})
public class Notifications implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "messages")
    private String messages;
    @Column(name = "sentDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sentDate;
    @JoinColumns(@JoinColumn(name = "userId", referencedColumnName = "id"))
    @ManyToOne
    private Users users;
    @JoinColumns(@JoinColumn(name = "storeId", referencedColumnName = "id"))
    @ManyToOne
    private Stores stores;

    public Notifications() {
    }

    public Notifications(Integer id) {
        this.id = id;
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
        if (!(object instanceof Notifications)) {
            return false;
        }
        Notifications other = (Notifications) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Notifications[ id=" + id + " ]";
    }
    
}
