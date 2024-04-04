package com.jungche.happyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf)->csrf.ignoringRequestMatchers("/saveMsg"))
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/", "/home").authenticated()
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers("/courses").permitAll()
                        .requestMatchers("/contact").permitAll()
                        .requestMatchers("/holidays/**").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/saveMsg").permitAll())
                .formLogin(login -> login.loginPage("/login")
                        .defaultSuccessUrl("/dashboard")
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .permitAll());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users
                .username("user")
                .password("12345")
                .roles("USER")
                .build();

        UserDetails admin = users
                .username("admin")
                .password("54321")
                .roles("Admin")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
