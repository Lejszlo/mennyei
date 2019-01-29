package com.hajdu.sp.competition.update;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Collections;

@TestConfiguration
public class SpringSecurityWebAuxTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        UserDetails basicUser = User.withUsername("enduser")
                .password("{noop}password")
                .authorities(new SimpleGrantedAuthority("ROLE_USER"))
                .build();

        return new InMemoryUserDetailsManager(Collections.singletonList(basicUser));
    }
}