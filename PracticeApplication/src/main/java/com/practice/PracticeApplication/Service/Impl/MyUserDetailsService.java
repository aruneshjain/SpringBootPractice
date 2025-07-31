package com.practice.PracticeApplication.Service.Impl;

import com.practice.PracticeApplication.Entity.Login;
import com.practice.PracticeApplication.Entity.UserPrincipal;
import com.practice.PracticeApplication.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    LoginRepository loginRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = loginRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username Invalid"));
        return new UserPrincipal(login);
    }
}
