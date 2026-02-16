package com.example.testit.service;

import com.example.testit.model.User;

import com.example.testit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> s =
                () -> new UsernameNotFoundException("Problem during authentication!");

        User u = userRepository.findByUsername(username);

        return new org.springframework.security.core.userdetails.User(u.getUsername(),u.getPassword(), Collections.emptyList());
    }


}
