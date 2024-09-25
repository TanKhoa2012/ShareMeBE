/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shareme.entity;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author hokho
 */
@Entity
@Table(name = "orderitems")
@XmlRootElement
@Builder
@Getter
@Setter
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Orderitems.findAll", query = "SELECT o FROM Orderitems o"),
        @NamedQuery(name = "Orderitems.findById", query = "SELECT o FROM Orderitems o WHERE o.id = :id"),
        @NamedQuery(name = "Orderitems.findByMenuitemId", query = "SELECT o FROM Orderitems o WHERE o.menuitems.id = :menuitemId"),
        @NamedQuery(name = "Orderitems.findByOrderId", query = "SELECT o FROM Orderitems o WHERE o.orders.id = :orderId"),
        @NamedQuery(name = "Orderitems.findByQuantity", query = "SELECT o FROM Orderitems o WHERE o.quantity = :quantity")})
public class Orderitems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @OneToMany(mappedBy = "orderItemsId")
    private Set<Reviewmenuitems> reviewmenuitemsSet;
    @JoinColumns(@JoinColumn(name = "menuItemId", referencedColumnName = "id"))
    @ManyToOne
    private Menuitems menuitems;
    @JoinColumns(@JoinColumn(name = "orderId", referencedColumnName = "id"))
    @ManyToOne
    private Orders orders;

    public Orderitems() {
    }

    public Orderitems(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @XmlTransient
    public Set<Reviewmenuitems> getReviewmenuitemsSet() {
        return reviewmenuitemsSet;
    }

    public void setReviewmenuitemsSet(Set<Reviewmenuitems> reviewmenuitemsSet) {
        this.reviewmenuitemsSet = reviewmenuitemsSet;
    }

    public Menuitems getMenuitems() {
        return menuitems;
    }

    public void setMenuitems(Menuitems menuitems) {
        this.menuitems = menuitems;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
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
        if (!(object instanceof Orderitems)) {
            return false;
        }
        Orderitems other = (Orderitems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Orderitems[ id=" + id + " ]";
    }

}
