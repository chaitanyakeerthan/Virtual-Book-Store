package com.example.VirtualBookStore.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(csrf -> csrf.disable())


                .cors(cors -> {})


                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(

                                "/api/users/register",
                                "/api/users/login",
                                "/api/users/getUsers",
                                "/api/users/getUsers/{id}",


                                "/api/books/**",


                                "/api/cart/**",


                                "/api/cart-items/**",


                                "/api/orders/**"
                        ).permitAll()
                        .anyRequest().permitAll()
                )


                .formLogin(form -> form.disable())
                .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
