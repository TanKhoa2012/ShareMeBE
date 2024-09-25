package com.shareme.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "likeitems")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Likeitems.findAll", query = "SELECT l FROM Likeitems l"),
        @NamedQuery(name = "Likeitems.findByUserId", query = "SELECT l FROM Likeitems l WHERE l.likeitemsPK.userId = :userId"),
        @NamedQuery(name = "Likeitems.findByMenuItemsId", query = "SELECT l FROM Likeitems l WHERE l.likeitemsPK.menuItemsId = :menuItemsId"),
        @NamedQuery(name = "Likeitems.findByCreatedDate", query = "SELECT l FROM Likeitems l WHERE l.createdDate = :createdDate"),
        @NamedQuery(name = "Likeitems.findByUpdatedDate", query = "SELECT l FROM Likeitems l WHERE l.updatedDate = :updatedDate"),
        @NamedQuery(name = "Likeitems.findByActive", query = "SELECT l FROM Likeitems l WHERE l.active = :active")
})
public class Likeitems implements Serializable {

    @EmbeddedId
    protected LikeitemsPK likeitemsPK;

    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "updatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "menuItemsId", referencedColumnName = "id", insertable = false, updatable = false)
    private Menuitems menuItems;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false)
    private Users user;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (likeitemsPK != null ? likeitemsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Likeitems)) {
            return false;
        }
        Likeitems other = (Likeitems) object;
        return this.likeitemsPK != null && this.likeitemsPK.equals(other.likeitemsPK);
    }

    @Override
    public String toString() {
        return "Likeitems[ likeitemsPK=" + likeitemsPK + " ]";
    }
}
