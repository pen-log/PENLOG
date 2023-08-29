package backend.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable() // TODO : 개발 중에만 유지
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll())
                .formLogin(formLogin -> formLogin.loginPage("/usr/login")
                        .defaultSuccessUrl("/", true))
                .oauth2Login(oauth2Login -> oauth2Login.loginPage("/usr/login")
                        .defaultSuccessUrl("/", true))
                .logout(logout -> logout.logoutUrl("/logout"))
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
