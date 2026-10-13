package com.omnicart.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(name = "role")
    private String role;


    @OneToMany(mappedBy = "role")
    private List<RolePermission> rolePermissions;
}
