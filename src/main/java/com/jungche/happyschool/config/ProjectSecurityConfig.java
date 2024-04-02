package com.jungche.happyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf)->csrf.ignoringRequestMatchers("/saveMsg"))
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/", "/home").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers("/courses").permitAll()
                        .requestMatchers("/contact").permitAll()
                        .requestMatchers("/holidays/**").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/saveMsg").permitAll())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }
}
