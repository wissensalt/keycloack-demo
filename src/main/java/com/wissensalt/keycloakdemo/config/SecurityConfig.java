package com.wissensalt.keycloakdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorizeRequests -> {
          authorizeRequests.anyRequest().permitAll();
          authorizeRequests.requestMatchers("/home/**").hasRole("ADMIN");
        });

    http.oauth2Login(oauth2 -> {
      oauth2.loginPage("/oauth2/authorization/keycloak");
      oauth2.defaultSuccessUrl("/home");
    });

    http.logout(logout -> {
      logout.logoutSuccessUrl("/");
      logout.invalidateHttpSession(true);
      logout.clearAuthentication(true);
      logout.deleteCookies("JSESSIONID");
    });

    http.sessionManagement(s -> s
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
  }
}
