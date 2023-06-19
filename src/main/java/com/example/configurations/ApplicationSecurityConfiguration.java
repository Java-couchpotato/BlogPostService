package com.example.configurations;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true,jsr250Enabled = true)
public class ApplicationSecurityConfiguration {

    @Bean
    @SneakyThrows
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   SecurityFilter securityFilter) {

        http.csrf()//mechanism for protection verificaton html page
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/entry/registration", "/entry/login", "/public","/posts","/swagger-ui/**","/v3/api-docs/**").permitAll()
                .anyRequest()
                .authenticated()
                ;



        http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
}
