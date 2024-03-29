package uz.fazo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;

import static uz.fazo.user.Role.ADMIN;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL =
            {"/api/auth/**",
                    "v3/api-docs/**",
                    "/api/excel/**",
                    "/api/images/**"
                    ,"/api/users/**",
                    "/api/members/**",
                    "/api/clients/**",
                    "/api/events/**",
                    "/api/problems/**",
                    "/api/tests/**",
                    "/api-docs/**",
                    "/swagger-ui/**",
                    "/api/user-tests/**",
                    "/api/home/**",
                    "/uploadFile/**",
                    "/configuration/security",

            };
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(false);
        configuration.setMaxAge(3600L); // 1 hour
        http.cors(httpSecurityCorsConfigurer ->
                        httpSecurityCorsConfigurer.configurationSource(request ->
                                new CorsConfiguration(configuration)
                        ))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers(GET, "/api/problems/**", "/api/members/**", "/api/materials/**"
                                        , "/api/events/**", "/api/clients/**", "/api/articles/**", "/api/materials/**").permitAll()
                                .requestMatchers(HttpMethod.POST,
                                        "/api/problems/**", "/api/members/**", "/api/materials/**"
                                        , "/api/events/**", "/api/clients/**", "/api/articles/**", "/api/materials/**"
                                ).hasRole(ADMIN.name())
                                .requestMatchers(HttpMethod.PATCH,
                                        "/api/problems/**", "/api/members/**", "/api/materials/**"
                                        , "/api/events/**", "/api/clients/**", "/api/articles/**", "/api/materials/**"
                                ).hasRole(ADMIN.name())
                                .requestMatchers(HttpMethod.DELETE,
                                        "/api/problems/**", "/api/members/**", "/api/materials/**"
                                        , "/api/events/**", "/api/clients/**", "/api/articles/**", "/api/materials/**"
                                ).hasRole(ADMIN.name())
                                .requestMatchers(GET, "/api/users/**").hasRole(ADMIN.name())
                                .anyRequest().authenticated()

                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
        ;
        return http.build();
    }
}
