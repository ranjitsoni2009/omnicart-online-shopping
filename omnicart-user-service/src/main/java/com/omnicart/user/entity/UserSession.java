package com.omnicart.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "user_sessions")
public class UserSession extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "refresh_token_Hash")
    private  String refreshTokenHash;

    @Column(name = "login_identifier")
    private String loginIdentifier;

    @Column(name = "logged_in_at")
    private Instant loggedInAt;

    @Column(name = "last_accessed_at")
    private Instant lastAccessedAt;

    @Column(name = "expired_at")
    private Instant expiredAt;

    @Column(name = "logged_out_at")
    private Instant loggedOutAt;

    @Column(name = "revoked_at")
    private Instant revokedAt;

    @Column(name = "device_info")
    private String deviceInfo;

    @Column(name = "ip_address")
    private String ipAddress;
}
