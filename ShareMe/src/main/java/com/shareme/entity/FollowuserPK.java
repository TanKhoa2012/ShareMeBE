/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shareme.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 *
 * @author hokho
 */
@Builder
@Getter
@Setter
@Embeddable
public class FollowuserPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "followerId")
    private int followerId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "followedId")
    private int followedId;

    public FollowuserPK() {
    }

    public FollowuserPK(int followerId, int followedId) {
        this.followerId = followerId;
        this.followedId = followedId;
    }

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    public int getFollowedId() {
        return followedId;
    }

    public void setFollowedId(int followedId) {
        this.followedId = followedId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) followerId;
        hash += (int) followedId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FollowuserPK)) {
            return false;
        }
        FollowuserPK other = (FollowuserPK) object;
        if (this.followerId != other.followerId) {
            return false;
        }
        if (this.followedId != other.followedId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FollowuserPK[ followerId=" + followerId + ", followedId=" + followedId + " ]";
    }
    
}
