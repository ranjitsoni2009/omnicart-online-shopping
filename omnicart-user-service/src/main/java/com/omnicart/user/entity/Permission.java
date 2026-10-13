package com.omnicart.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@Getter
@Setter
@Entity
@Table(name = "permissions")
public class Permission extends BaseEntity {

    @Column(name = "permission_code")
    private String permissionCode;

    @Column(name = "description")
    private String description;

}
