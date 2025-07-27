package com.spriti.Config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .cors(cors -> cors.configurationSource(
                        new CorsConfigurationSource() {
                            @Override
                            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                                CorsConfiguration cfg = new CorsConfiguration();

                                cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
                                cfg.setAllowedMethods(Collections.singletonList("*"));
                                cfg.setAllowCredentials(true);
                                cfg.setAllowedHeaders(Collections.singletonList("*"));
                                cfg.setExposedHeaders(Arrays.asList("Authorization"));
                                return cfg;
                            }
                        }
                ))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**","/admin/saveAdmin", "/user/saveUser", "/signIn").permitAll()
                        .requestMatchers(HttpMethod.POST, "/admin/saveQuiz", "/admin/saveQues/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/admin/getAll", "/user/getUser").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user/getQuiz/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/user/quiz/submit/**").hasRole("USER")
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
