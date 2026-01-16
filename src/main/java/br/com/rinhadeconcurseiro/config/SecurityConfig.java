package br.com.rinhadeconcurseiro.config;

import br.com.rinhadeconcurseiro.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/error").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .oidcUserService(customOAuth2UserService)
                        )
                        .successHandler(oAuth2SuccessHandler)
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}