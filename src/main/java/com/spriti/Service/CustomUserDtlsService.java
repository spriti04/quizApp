package com.spriti.Service;

import com.spriti.Entity.User;
import com.spriti.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDtlsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = repository.findByEmail(username);

        if (optUser.isPresent()) {
            User userObj = optUser.get();

            List<GrantedAuthority> authority = new ArrayList<>();

            SimpleGrantedAuthority sga = new SimpleGrantedAuthority(userObj.getRole());
            authority.add(sga);

            return new org.springframework.security.core.userdetails
                    .User(userObj.getEmail(), userObj.getPassword(), authority);
        }else{
            throw new BadCredentialsException("Invalid Credentials");
        }

    }
}
