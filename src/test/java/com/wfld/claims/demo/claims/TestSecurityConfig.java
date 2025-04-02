package com.wfld.claims.demo.claims;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class TestSecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        ServerAuthenticationEntryPoint entryPoint = new HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED);
        
        http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .exceptionHandling(handling -> handling
                .authenticationEntryPoint(entryPoint)
                .accessDeniedHandler((exchange, denied) -> {
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return Mono.empty();
                })
            )
            .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/actuator/health", "/heartbeat").permitAll()
                .anyExchange().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtDecoder(jwtDecoder()))
            );
        
        return http.build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return token -> {
            return Mono.just(Jwt.withTokenValue(token)
                .header("alg", "none")
                .subject("sub")
                .claim("scope", token)
                .build());
        };
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            String scope = jwt.getClaimAsString("scope");
            return List.of(new SimpleGrantedAuthority("SCOPE_" + scope));
        });
        return jwtConverter;
    }
} 