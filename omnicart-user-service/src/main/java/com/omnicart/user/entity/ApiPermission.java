package com.omnicart.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@Getter
@Setter
@Entity
@Table(name = "api_permissions")
public class ApiPermission extends BaseEntity {

    @Column(name = "http_method")
    private String httpMethod;

    @Column(name = "url_pattern")
    private String urlPattern;

    @OneToOne
    @JoinColumn(name = "permission_id", referencedColumnName = "id")
    private Permission permission;

    @Column(name = "is_active")
    private boolean isActive;
}
