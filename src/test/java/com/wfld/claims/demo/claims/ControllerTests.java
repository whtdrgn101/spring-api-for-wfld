package com.wfld.claims.demo.claims;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.wfld.claims.demo.claim.entities.ClaimRequest;
import com.wfld.claims.demo.claim.controllers.ClaimController;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.Jwt;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt;

@WebFluxTest(ClaimController.class)
@Import({TestSecurityConfig.class, TestConfig.class})
public class ControllerTests {
    
    @Autowired
    private WebTestClient webClient;
    
    @Test
    void testCreateClaimShouldReturn401WhenNotAuthenticated() {
        ClaimRequest request = new ClaimRequest();
        request.setAmount(1000.00);
        request.setStatusId(1L);
        request.setCreatedBy("test");
    
        webClient.post().uri("/api/claims")
            .bodyValue(request)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isUnauthorized();
    }

    @Test
    void testCreateClaimShouldReturn403WhenNotAuthorized() {
        ClaimRequest request = new ClaimRequest();
        request.setAmount(1000.00);
        request.setStatusId(1L);
        
        Jwt jwt = Jwt.withTokenValue("mock-token")
            .header("alg", "none")
            .subject("sub")
            .build();

        webClient.mutateWith(mockJwt().jwt(jwt))
            .post().uri("/api/claims")
            .bodyValue(request)
            .headers(headers -> headers.setContentType(MediaType.APPLICATION_JSON))
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isForbidden();
    }

    @Test
    void testCreateClaimShouldSucceedWithValidToken() {
        ClaimRequest request = new ClaimRequest();
        request.setAmount(1000.00);
        request.setStatusId(1L);
        request.setCreatedBy("test");

        Jwt jwt = Jwt.withTokenValue("mock-token")
            .header("alg", "none")
            .subject("sub")
            .claim("scope", "can-submit-claims")
            .build();

        webClient.mutateWith(mockJwt().jwt(jwt))
            .post().uri("/api/claims")
            .bodyValue(request)
            .headers(headers -> headers.setContentType(MediaType.APPLICATION_JSON))
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.status.code").isEqualTo("NEW");
    }
}
