package edu.whu.demo.security;

import edu.whu.demo.domain.Role;
import edu.whu.demo.domain.UserDto;
import edu.whu.demo.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ryann
 * @create 2023/11/1 - 17:10
 */
@Service
public class DbUserDetailService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userService.getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " is not found");
        }

        return User.builder()
                .username(username)
                .password(user.getPassword())
                .authorities(getAuthorities(user))
                .build();
    }

    private static GrantedAuthority[] getAuthorities(UserDto user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            String[] authorityStrs = role.getAuthorities().split(",");
            for (String auth : authorityStrs) {
                authorities.add(new SimpleGrantedAuthority(auth));
            }
        }
        return authorities.toArray(new GrantedAuthority[authorities.size()]);
    }
}