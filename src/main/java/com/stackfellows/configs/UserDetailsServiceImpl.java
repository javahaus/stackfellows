package com.stackfellows.stackfellows.configs;

import com.stackfellows.stackfellows.StackfellowsApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


    @Service
    public class UserDetailsServiceImpl implements UserDetailsService {

        @Autowired
        StackfellowsApplication stackFellowshipRepo;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//            return stackFellowshipRepo.findByUsername(username);
        return loadUserByUsername("empty");
        }
}
