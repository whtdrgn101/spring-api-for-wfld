package com.wfld.claims.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Value("${spring.security.oauth2.client.registration.ping.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.ping.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.ping.issuer-uri}")
    private String issuerUri;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/actuator/health", "/heartbeat").permitAll()
                .anyExchange().authenticated()
            )
            .oauth2Client(oauth2 -> oauth2
                .clientRegistrationRepository(clientRegistrationRepository())
            );
        
        return http.build();
    }

    @Bean
    public ReactiveClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration pingRegistration = ClientRegistration.withRegistrationId("ping")
            .clientId(clientId)
            .clientSecret(clientSecret)
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
            .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
            .scope("openid", "profile", "email", "can-request-claims", "can-submit-claims")
            .authorizationUri(issuerUri + "/as/authorization.oauth2")
            .tokenUri(issuerUri + "/as/token.oauth2")
            .userInfoUri(issuerUri + "/idp/userinfo.openid")
            .userNameAttributeName("sub")
            .jwkSetUri(issuerUri + "/pf/JWKS")
            .clientName("PingFederate")
            .build();

        return new InMemoryReactiveClientRegistrationRepository(pingRegistration);
    }
} 