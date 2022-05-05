package Cyber_Community.serviceBean;

import Cyber_Community.entities.User;
import Cyber_Community.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepositoryUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceBean userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getUser(username);

        GrantedAuthority authority=new SimpleGrantedAuthority(user.getRole().getName());
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(authority);

        return new org.springframework.security.core.userdetails.User(user.getNickname(),
                user.getPassword(), roles);

    }
}
