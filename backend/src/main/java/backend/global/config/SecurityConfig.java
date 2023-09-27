package backend.global.config;

import backend.global.config.jwt.JwtAuthenticationFilter;
import backend.global.config.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtProvider jwtProvider;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf().disable() // TODO : 개발 중에만 유지
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                antMatcher("/swagger-ui**"),
                                antMatcher("/swagger-ui/**"),
                                antMatcher("/v3/api-docs/**"),
                                antMatcher("/v3/api-docs**")).permitAll()
                        .requestMatchers(antMatcher("/usr/login")).anonymous()
                        .anyRequest().permitAll()) // TODO : 개발 중에만 유지
                .formLogin(formLogin -> formLogin.loginPage("/usr/login"))
                .oauth2Login(oauth2Login -> oauth2Login.loginPage("/usr/login"))
                .logout(logout -> logout.logoutUrl("/logout"))
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setStatus(403);
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("text/html; charset=UTF-8");
                    response.getWriter().write("권한이 없는 사용자입니다.");
                })
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(401);
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("text/html; charset=UTF-8");
                    response.getWriter().write("인증되지 않은 사용자입니다.");
                });
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
