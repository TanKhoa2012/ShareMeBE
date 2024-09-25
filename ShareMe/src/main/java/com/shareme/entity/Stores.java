/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shareme.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
 *
 * @author hokho
 */
@Entity
@Table(name = "stores")
@XmlRootElement
@Builder
@Getter
@Setter
@AllArgsConstructor
@NamedQueries({
    @NamedQuery(name = "Stores.findAll", query = "SELECT s FROM Stores s"),
    @NamedQuery(name = "Stores.findById", query = "SELECT s FROM Stores s WHERE s.id = :id"),
    @NamedQuery(name = "Stores.findByName", query = "SELECT s FROM Stores s WHERE s.name = :name"),
    @NamedQuery(name = "Stores.findByBusinessType", query = "SELECT s FROM Stores s WHERE s.businessType = :businessType"),
    @NamedQuery(name = "Stores.findByNumberPhone", query = "SELECT s FROM Stores s WHERE s.numberPhone = :numberPhone"),
    @NamedQuery(name = "Stores.findByEmail", query = "SELECT s FROM Stores s WHERE s.email = :email"),
    @NamedQuery(name = "Stores.findByCode", query = "SELECT s FROM Stores s WHERE s.code = :code"),
    @NamedQuery(name = "Stores.findByRating", query = "SELECT s FROM Stores s WHERE s.rating = :rating"),
    @NamedQuery(name = "Stores.findByTotalRating", query = "SELECT s FROM Stores s WHERE s.totalRating = :totalRating"),
    @NamedQuery(name = "Stores.findByAvatar", query = "SELECT s FROM Stores s WHERE s.avatar = :avatar"),
    @NamedQuery(name = "Stores.findByBackground", query = "SELECT s FROM Stores s WHERE s.background = :background"),
    @NamedQuery(name = "Stores.findByLocation", query = "SELECT s FROM Stores s WHERE s.location = :location"),
    @NamedQuery(name = "Stores.findByCreatedDate", query = "SELECT s FROM Stores s WHERE s.createdDate = :createdDate"),
    @NamedQuery(name = "Stores.findByUpdateDate", query = "SELECT s FROM Stores s WHERE s.updateDate = :updateDate"),
    @NamedQuery(name = "Stores.findByActive", query = "SELECT s FROM Stores s WHERE s.active = :active"),
        @NamedQuery(name = "Stores.findByUserId", query = "SELECT s FROM Stores s WHERE s.users.id = :userId"),
        @NamedQuery(name = "Stores.findByUsername", query = "SELECT s FROM Stores s WHERE s.users.username = :username")
})
public class Stores implements Serializable {

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
    @Column(name = "businessType")
    private String businessType;
    @Size(max = 20)
    @Column(name = "numberPhone")
    private String numberPhone;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 50)
    @Column(name = "code")
    private String code;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Max(value=5)  @Min(value=0)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rating")
    private Double rating = 5.0;
    @Column(name = "totalRating")
    private Integer totalRating = 0;
    @Size(max = 255)
    @Column(name = "avatar")
    private String avatar;
    @Size(max = 255)
    @Column(name = "background")
    private String background;
    @Size(max = 255)
    @Column(name = "location")
    private String location;
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "active")
    private Boolean active;
    @JoinColumns(@JoinColumn(name = "userId", referencedColumnName = "id"))
    @OneToOne(fetch = FetchType.EAGER)
    private Users users;
    @OneToMany(mappedBy = "stores", cascade = CascadeType.REMOVE)
    private Set<Notifications> notificationsSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stores")
    private Set<Employee> employeeSet;
    @OneToMany(mappedBy = "stores", cascade = CascadeType.REMOVE)
    private Set<Menuitems> menuitemsSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stores")
    private Set<Followstore> followstoreSet;
    @OneToMany(mappedBy = "stores")
    private Set<Orders> ordersSet;

    public Stores() {
    }

    public Stores(Integer id) {
        this.id = id;
    }

    public Stores(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
