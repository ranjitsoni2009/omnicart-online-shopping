package com.omnicart.user.service;

import com.omnicart.user.entity.CustomUserDetails;
import com.omnicart.user.entity.Role;
import com.omnicart.user.entity.User;
import com.omnicart.user.entity.UserRole;
import com.omnicart.user.repo.IUserRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private IUserRepo userRepo;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may be case-sensitive, or case-insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested.
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmailIdOrPhoneNumber(username, username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(
                user.getId(),
                user.getEmailId(),
                user.getPasswordHash(),
                loadAuthorities(user),
                user.isActive(),
                user.isActive());
    }

    /**
     * This method is used to collection ROLES and PERMISSIONS of user
     */
    private List<SimpleGrantedAuthority> loadAuthorities(User user) {
        List<UserRole> userRoles = user.getUserRoles();
        if (CollectionUtils.isEmpty(userRoles)) {
            return Collections.emptyList();
        }

        return userRoles.stream()
                .flatMap(userRole -> {
                    Role role = userRole.getRole();
                    Stream<SimpleGrantedAuthority> roleAuthority  = Stream.of(
                            new SimpleGrantedAuthority("ROLE_"+role.getRole()));

                    Stream<SimpleGrantedAuthority> permissionAuthorities = role.getRolePermissions().stream()
                            .map(rolePermission ->
                                    new SimpleGrantedAuthority(
                                            rolePermission.getPermission().getPermissionCode()));

                    return Stream.concat(roleAuthority, permissionAuthorities);
                })
                .distinct()
                .toList();
    }
}
