package com.omnicart.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Ranjit Soni on 20-10-2026.
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

    @Column(name = "permission_code")
    private String permissionCode;

    @Column(name = "is_active")
    private boolean isActive;
}
