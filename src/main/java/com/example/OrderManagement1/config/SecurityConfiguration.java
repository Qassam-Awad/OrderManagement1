package com.example.OrderManagement1.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.example.OrderManagement1.user.Permission.ADMIN_CREATE;
import static com.example.OrderManagement1.user.Permission.ADMIN_DELETE;
import static com.example.OrderManagement1.user.Permission.ADMIN_READ;
import static com.example.OrderManagement1.user.Permission.ADMIN_UPDATE;
import static com.example.OrderManagement1.user.Permission.MANAGER_CREATE;
import static com.example.OrderManagement1.user.Permission.MANAGER_DELETE;
import static com.example.OrderManagement1.user.Permission.MANAGER_READ;
import static com.example.OrderManagement1.user.Permission.MANAGER_UPDATE;
import static com.example.OrderManagement1.user.Role.ADMIN;
import static com.example.OrderManagement1.user.Role.MANAGER;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**",
                                "/v2/api-docs",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html").permitAll()
                        .anyRequest().authenticated()
                ).authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), ADMIN_UPDATE.name()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), ADMIN_DELETE.name()))
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logoutConfigurer -> logoutConfigurer.logoutUrl("/api/v1/auth/logout").addLogoutHandler(logoutHandler).logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));

        return http.build();
    }
}
