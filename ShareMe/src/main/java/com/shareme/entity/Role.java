package com.shareme.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Role {
    @Id
    private String name;

    private String description;

    @ManyToMany()
    private Set<Permission> permissions;

}
