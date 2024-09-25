/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shareme.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * @author hokho
 */
@Entity
@Table(name = "orders")
@XmlRootElement
@Builder
@Getter
@Setter
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
        @NamedQuery(name = "Orders.findById", query = "SELECT o FROM Orders o WHERE o.id = :id"),
        @NamedQuery(name = "Orders.findByTotalPrice", query = "SELECT o FROM Orders o WHERE o.totalPrice = :totalPrice"),
        @NamedQuery(name = "Orders.findByDeliveryFee", query = "SELECT o FROM Orders o WHERE o.deliveryFee = :deliveryFee"),
        @NamedQuery(name = "Orders.findByPaymentMethod", query = "SELECT o FROM Orders o WHERE o.paymentMethod = :paymentMethod"),
        @NamedQuery(name = "Orders.findByStatus", query = "SELECT o FROM Orders o WHERE o.status = :status"),
        @NamedQuery(name = "Orders.findByCreatedDate", query = "SELECT o FROM Orders o WHERE o.createdDate = :createdDate"),
        @NamedQuery(name = "Orders.findByUpdatedDate", query = "SELECT o FROM Orders o WHERE o.updatedDate = :updatedDate"),
        @NamedQuery(name = "Orders.findByUserId", query = "SELECT o FROM Orders o WHERE o.users.id = :userId"),
        @NamedQuery(name = "Orders.findByStoreId", query = "SELECT o FROM Orders o WHERE o.stores.id = :storeId"),
        @NamedQuery(name = "Orders.findByActive", query = "SELECT o FROM Orders o WHERE o.active = :active")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalPrice")
    private BigDecimal totalPrice;
    @Column(name = "deliveryFee")
    private BigDecimal deliveryFee;
    @Size(max = 11)
    @Column(name = "paymentMethod")
    private String paymentMethod;
    @Size(max = 9)
    @Column(name = "status")
    private String status;
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "active")
    private Boolean active;
    @OneToMany(mappedBy = "orders")
    private Set<Orderitems> orderitemsSet;
    @JoinColumns(@JoinColumn(name = "storeId", referencedColumnName = "id"))
    @ManyToOne
    private Stores stores;
    @JoinColumns(@JoinColumn(name = "userId", referencedColumnName = "id"))
    @ManyToOne
    private Users users;

    public Orders() {
    }

    public Orders(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    public Set<Orderitems> getOrderitemsSet() {
        return orderitemsSet;
    }

    public void setOrderitemsSet(Set<Orderitems> orderitemsSet) {
        this.orderitemsSet = orderitemsSet;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Orders[ id=" + id + " ]";
    }

}
