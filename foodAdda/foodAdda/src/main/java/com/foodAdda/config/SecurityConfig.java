package com.foodAdda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

        @Bean
        public UserDetailsService userDetailsService() {

                PasswordEncoder encoder = passwordEncoder();

                UserDetails admin = User
                                .withUsername("admin")
                                .password(encoder.encode("admin123"))
                                .roles("ADMIN")
                                .build();

                UserDetails vendor = User
                                .withUsername("vendor")
                                .password(encoder.encode("vendor123"))
                                .roles("VENDOR")
                                .build();

                UserDetails customer = User
                                .withUsername("customer")
                                .password(encoder.encode("customer123"))
                                .roles("CUSTOMER")
                                .build();

                return new InMemoryUserDetailsManager(admin, vendor, customer);
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                http
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(auth -> auth
                                                // Public APIs (no login required)
                                                .requestMatchers("/public/**").permitAll()

                                                // Vendor APIs
                                                .requestMatchers("/vendor/**").hasAnyRole("VENDOR", "ADMIN")

                                                // Customer APIs
                                                .requestMatchers("/customer/**").hasAnyRole("CUSTOMER", "ADMIN")

                                                // ADMIN CAN ACCESS EVERYTHING
                                                .requestMatchers("/**").hasRole("ADMIN")

                                                .anyRequest().authenticated())
                                .httpBasic(withDefaults());

                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
