package com.example.day14proj1.config;

import com.example.day14proj1.service.JwtAuthFilter;
import com.example.day14proj1.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtService jwtService;

        @Bean
        public UserDetailsService userDetailsService() {
                UserDetails admin = User.withUsername("admin").password("{noop}admin123").roles("ADMIN").build();
                UserDetails user = User.withUsername("user").password("{noop}user123").roles("USER").build();
                UserDetails developer = User.withUsername("developer").password("{noop}developer123").roles("DEVELOPER")
                                .build();
                return new InMemoryUserDetailsManager(admin, user, developer);
        }

        @Bean
        public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
                DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
                provider.setUserDetailsService(userDetailsService);
                return provider;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
                return config.getAuthenticationManager();
        }

        @Bean
        public JwtAuthFilter jwtAuthFilter(UserDetailsService userDetailsService) {
                return new JwtAuthFilter(jwtService, userDetailsService);
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter)
                        throws Exception {
                http.csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(
                                                                "/swagger-ui/**",
                                                                "/v3/api-docs/**",
                                                                "/v3/api-docs.yaml")
                                                .permitAll()
                                                .requestMatchers("/api/auth/**").permitAll()
                                                .requestMatchers("/api/bugs/admin/update/**")
                                                .hasAnyRole("DEVELOPER", "ADMIN")
                                                .requestMatchers("/api/bugs/admin/**").hasRole("ADMIN")
                                                .requestMatchers("/api/bugs/user/**").hasRole("USER")
                                                .requestMatchers("/api/bugs/**")
                                                .hasAnyRole("ADMIN", "USER", "DEVELOPER")
                                                .anyRequest().authenticated())
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}
