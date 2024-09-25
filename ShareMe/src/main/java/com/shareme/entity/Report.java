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
 * @author hokho
 */
@Entity
@Table(name = "report")
@XmlRootElement
@Builder
@Getter
@Setter
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r"),
        @NamedQuery(name = "Report.findById", query = "SELECT r FROM Report r WHERE r.id = :id"),
        @NamedQuery(name = "Report.findByCreatedDate", query = "SELECT r FROM Report r WHERE r.createdDate = :createdDate"),
        @NamedQuery(name = "Report.findByUpdatedDate", query = "SELECT r FROM Report r WHERE r.updatedDate = :updatedDate"),
        @NamedQuery(name = "Report.findByUserId", query = "SELECT r FROM Report r WHERE r.userId.id = :userId"),
        @NamedQuery(name = "Report.findByMenuItemId", query = "SELECT r FROM Report r WHERE r.menuItemId.id = :menuItemId"),
        @NamedQuery(name = "Report.findByActive", query = "SELECT r FROM Report r WHERE r.active = :active")})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "content")
    private String content;
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "active")
    private Boolean active;
    @JoinColumn(name = "menuItemId", referencedColumnName = "id")
    @ManyToOne
    private Menuitems menuItemId;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne
    private Users userId;

    public Report() {
    }

    public Report(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Menuitems getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Menuitems menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
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
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report[ id=" + id + " ]";
    }

}
