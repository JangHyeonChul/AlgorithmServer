package com.algorithm.algoprojectserver.security;

/**
 * File Name : MemberDetails
 * Description : Spring Security에서 로그인한 사용자의 데이터가 담길 DTO
 * Update : 2023-08-21
 */


import com.algorithm.algoprojectserver.dto.MemberDTO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data

public class MemberDetails implements UserDetails {


    private MemberDTO memberDTO;


    public MemberDetails(MemberDTO memberDTO) {
        this.memberDTO = memberDTO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String role = memberDTO.getUser_role();
        authorities.add(new SimpleGrantedAuthority(role));

        return authorities;
    }

    @Override
    public String getPassword() {
        return memberDTO.getUser_password();
    }

    @Override
    public String getUsername() {
        return memberDTO.getUser_id();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
