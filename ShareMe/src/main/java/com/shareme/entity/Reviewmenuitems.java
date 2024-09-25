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
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hokho
 */
@Entity
@Table(name = "reviewmenuitems")
@XmlRootElement
@Builder
@Getter
@Setter
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Reviewmenuitems.findAll", query = "SELECT r FROM Reviewmenuitems r"),
        @NamedQuery(name = "Reviewmenuitems.findById", query = "SELECT r FROM Reviewmenuitems r WHERE r.id = :id"),
        @NamedQuery(name = "Reviewmenuitems.findByMenuItemId", query = "SELECT r FROM Reviewmenuitems r WHERE r.menuitems.id = :menuItemId"),
        @NamedQuery(name = "Reviewmenuitems.findByUserId", query = "SELECT r FROM Reviewmenuitems r WHERE r.users.id = :userId"),
        @NamedQuery(name = "Reviewmenuitems.findByRating", query = "SELECT r FROM Reviewmenuitems r WHERE r.rating = :rating"),
        @NamedQuery(name = "Reviewmenuitems.findByCreatedDate", query = "SELECT r FROM Reviewmenuitems r WHERE r.createdDate = :createdDate"),
        @NamedQuery(name = "Reviewmenuitems.findByUpdatedDate", query = "SELECT r FROM Reviewmenuitems r WHERE r.updatedDate = :updatedDate"),
        @NamedQuery(name = "Reviewmenuitems.findByActive", query = "SELECT r FROM Reviewmenuitems r WHERE r.active = :active")})
public class Reviewmenuitems implements Serializable {

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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rating")
    private BigDecimal rating;
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "active")
    private Boolean active;
    @JoinColumns(@JoinColumn(name = "menuItemId", referencedColumnName = "id"))
    @ManyToOne
    private Menuitems menuitems;
    @JoinColumn(name = "orderItemsId", referencedColumnName = "id")
    @ManyToOne
    private Orderitems orderItemsId;
    @JoinColumns(@JoinColumn(name = "userId", referencedColumnName = "id"))
    @ManyToOne
    private Users users;

    public Reviewmenuitems() {
    }

    public Reviewmenuitems(Integer id) {
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

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
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

    public Menuitems getMenuitems() {
        return menuitems;
    }

    public void setMenuitems(Menuitems menuitems) {
        this.menuitems = menuitems;
    }

    public Orderitems getOrderItemsId() {
        return orderItemsId;
    }

    public void setOrderItemsId(Orderitems orderItemsId) {
        this.orderItemsId = orderItemsId;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reviewmenuitems)) {
            return false;
        }
        Reviewmenuitems other = (Reviewmenuitems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reviewmenuitems[ id=" + id + " ]";
    }

}
