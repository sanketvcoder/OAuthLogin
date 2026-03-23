package com.sanket.oAuth.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain defatultSecurityFIlterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(req -> req.anyRequest().authenticated())
                .oauth2Login(oauth -> oauth.defaultSuccessUrl("http://localhost:5173/dashboard",true))
                .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("http://localhost:5173") // redirect to home
                .invalidateHttpSession(true) // session delete
                .deleteCookies("JSESSIONID") // cookie delete
                .clearAuthentication(true)
                .permitAll()
                );
//                .formLogin(Customizer.withDefaults())                .

        return http.build();
    }
}
