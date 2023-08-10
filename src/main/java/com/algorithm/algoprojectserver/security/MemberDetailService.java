package com.algorithm.algoprojectserver.security;

import com.algorithm.algoprojectserver.dto.MemberDTO;
import com.algorithm.algoprojectserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberDetailService implements UserDetailsService {

    @Autowired
    UserService userService;
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDTO user = userService.findByUserIdOrEmail(username);

        if(user != null) {
            return new MemberDetails(user);
        }

        return null;
    }
}
