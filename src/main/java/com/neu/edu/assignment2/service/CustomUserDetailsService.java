package com.neu.edu.assignment2.service;

import com.neu.edu.assignment2.model.CustomUserDetails;
import com.neu.edu.assignment2.model.User;
import com.neu.edu.assignment2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> users = userRepository.findByUsername(username);
        users.orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return users.map(CustomUserDetails::new).get();
    }
}
