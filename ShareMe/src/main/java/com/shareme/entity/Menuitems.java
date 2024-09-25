/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shareme.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 *
 * @author hokho
 */
@Entity
@Table(name = "menuitems")
@XmlRootElement
@Builder
@Getter
@Setter
@AllArgsConstructor
@NamedQueries({
    @NamedQuery(name = "Menuitems.findAll", query = "SELECT m FROM Menuitems m"),
    @NamedQuery(name = "Menuitems.findById", query = "SELECT m FROM Menuitems m WHERE m.id = :id"),
    @NamedQuery(name = "Menuitems.findByName", query = "SELECT m FROM Menuitems m WHERE m.name = :name"),
    @NamedQuery(name = "Menuitems.findByPrice", query = "SELECT m FROM Menuitems m WHERE m.price = :price"),
    @NamedQuery(name = "Menuitems.findByCreatedDate", query = "SELECT m FROM Menuitems m WHERE m.createdDate = :createdDate"),
    @NamedQuery(name = "Menuitems.findByUpdatedDate", query = "SELECT m FROM Menuitems m WHERE m.updatedDate = :updatedDate"),
        @NamedQuery(name = "Menuitems.findByStoreId", query = "SELECT m FROM Menuitems m WHERE m.stores.id = :storeId"),
    @NamedQuery(name = "Menuitems.findByActive", query = "SELECT m FROM Menuitems m WHERE m.active = :active")})
public class Menuitems implements Serializable {

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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "active")
    private Boolean active;
    @JoinTable(name = "menuitemstag", joinColumns = {
        @JoinColumn(name = "menuItemsId", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "tagId", referencedColumnName = "id")})
    @ManyToMany
    private Set<Tags> tagsSet;
    @OneToMany(mappedBy = "menuItems", cascade = CascadeType.REMOVE )
    private Set<Likeitems> likeitemsSet;
    @OneToMany(mappedBy = "menuitems", cascade = CascadeType.REMOVE)
    private Set<Reviewmenuitems> reviewmenuitemsSet;
    @JoinColumn(name = "categoriesId", referencedColumnName = "id")
    @ManyToOne
    private Categories categoriesId;
    @JoinColumns(
        @JoinColumn(name = "storeId", referencedColumnName = "id"))
    @ManyToOne
    private Stores stores;
    @OneToMany(mappedBy = "menuitems", cascade = CascadeType.REMOVE)
    private Set<Orderitems> orderitemsSet;
    @OneToMany(mappedBy = "menuItemsId", cascade = CascadeType.REMOVE)
    private Set<Itemimages> itemimagesSet;
    @OneToMany(mappedBy = "menuItemId", cascade = CascadeType.REMOVE)
    private Set<Report> reportSet;

    public Menuitems() {
    }

    public Menuitems(Integer id) {
        this.id = id;
    }

    public Menuitems(Integer id, String name) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
    public Set<Tags> getTagsSet() {
        return tagsSet;
    }

    public void setTagsSet(Set<Tags> tagsSet) {
        this.tagsSet = tagsSet;
    }

    @XmlTransient
    public Set<Likeitems> getLikeitemsSet() {
        return likeitemsSet;
    }

    public void setLikeitemsSet(Set<Likeitems> likeitemsSet) {
        this.likeitemsSet = likeitemsSet;
    }

    @XmlTransient
    public Set<Reviewmenuitems> getReviewmenuitemsSet() {
        return reviewmenuitemsSet;
    }

    public void setReviewmenuitemsSet(Set<Reviewmenuitems> reviewmenuitemsSet) {
        this.reviewmenuitemsSet = reviewmenuitemsSet;
    }

    public Categories getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(Categories categoriesId) {
        this.categoriesId = categoriesId;
    }

    public Stores getStores() {
        return stores;
    }

    public void setStores(Stores stores) {
        this.stores = stores;
    }

    @XmlTransient
    public Set<Orderitems> getOrderitemsSet() {
        return orderitemsSet;
    }

    public void setOrderitemsSet(Set<Orderitems> orderitemsSet) {
        this.orderitemsSet = orderitemsSet;
    }

    @XmlTransient
    public Set<Itemimages> getItemimagesSet() {
        return itemimagesSet;
    }

    public void setItemimagesSet(Set<Itemimages> itemimagesSet) {
        this.itemimagesSet = itemimagesSet;
    }

    @XmlTransient
    public Set<Report> getReportSet() {
        return reportSet;
    }

    public void setReportSet(Set<Report> reportSet) {
        this.reportSet = reportSet;
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
        if (!(object instanceof Menuitems)) {
            return false;
        }
        Menuitems other = (Menuitems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Menuitems[ id=" + id + " ]";
    }
    
}
