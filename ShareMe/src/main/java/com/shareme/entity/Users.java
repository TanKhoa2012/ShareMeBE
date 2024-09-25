/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shareme.entity;

import jakarta.enterprise.inject.Default;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
/**
 *
 * @author hokho
 */
@Entity
@Table(name = "users")
@XmlRootElement
@Builder
@Getter
@Setter
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(
                name = "Users.findAllEmployeeUsersByStoreId",
                query = "SELECT u FROM Users u JOIN Employee e ON e.users.id = u.id WHERE e.stores.id = :storeId"
        ),
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByAvatar", query = "SELECT u FROM Users u WHERE u.avatar = :avatar"),
    @NamedQuery(name = "Users.findByCreatedDate", query = "SELECT u FROM Users u WHERE u.createdDate = :createdDate"),
    @NamedQuery(name = "Users.findByUpdatedDate", query = "SELECT u FROM Users u WHERE u.updatedDate = :updatedDate"),
    @NamedQuery(name = "Users.findByNumberPhone", query = "SELECT u FROM Users u WHERE u.numberPhone = :numberPhone"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByLocation", query = "SELECT u FROM Users u WHERE u.location = :location")})
    @NamedQuery(name = "Users.findByRole", query = "SELECT u FROM Users u   WHERE u.role = :role")

public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 3, max = 255, message = "USERNAME_INVALID")
    @Column(name = "username")
    private String username;
    @Size(min = 3, max = 255, message = "USERNAME_INVALID")
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Size(max = 255)
    @Column(name = "avatar")
    private String avatar;
    @Size(max = 255)
    @Column(name = "background")
    private String background;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 13)
//    @Column(name = "role")
//    private String role;
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Size(max = 20)
    @Column(name = "numberPhone")
    private String numberPhone;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "location")
    private String location;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Set<Followuser> followuserSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users1")
    private Set<Followuser> followuserSet1;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Likeitems> likeitemsSet;
    @OneToOne(mappedBy = "users", cascade = CascadeType.REMOVE)
    private Stores store;
    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE)
    private Set<Reviewmenuitems> reviewmenuitemsSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Set<Employee> employeeSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Set<Followstore> followstoreSet;
    @OneToMany(mappedBy = "userId", cascade = CascadeType.REMOVE)
    private Set<Report> reportSet;
    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE)
    private Set<Orders> ordersSet;
    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE)
    private Set<Notifications> notificationsSet;
    @OneToMany(mappedBy = "users", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private Set<InvalidatedToken> invalidatedTokens;

    public Users() {
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdDate == null) {
            this.createdDate = new Date();
            this.updatedDate = new Date();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = new Date();
    }

}
