package com.algorithm.algoprojectserver.security;

/**
 * File Name : SecurityConfig
 * Description : Spring Security 설정 정보들
 * Update : 2023-08-21
 */


import com.algorithm.algoprojectserver.config.AuthorityConstains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class SecurityConfig{

    @Autowired
    LoginSuccessHadler loginSuccessHadler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception  {

        // cors, csrf 비활성화
        return http.cors().and().csrf().disable()


                .authorizeHttpRequests()

                // admin으로 들어오는 사용자의 권한이 ROLE_ADMIN일경우에만 허용
                .requestMatchers("/admin/**").hasAnyAuthority(AuthorityConstains.ROLE_ADMIN)

                // history, challenge, board/write에 미인증 회원일 경우 비허용
                .requestMatchers("/history/**", "/challenge/**", "/board/write").
                hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")

                // 제외한 나머지는 모두 허용
                .requestMatchers("/**").permitAll()
                .and().csrf().disable()

                // loginform 사용 허용
                .formLogin()

                // 로그인 페이지 url
                .loginPage("/login")

                // 로그인 성공 url
                .loginProcessingUrl("/login")


                .defaultSuccessUrl("/problem")

                // 로그인성공시 실행할 로직
                .successHandler(loginSuccessHadler)
                .and()
                .build();

    }

    // BCryptPasswordEncoder 암호화 허용
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }




}
