package com.wfld.claims.demo.claims;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.wfld.claims.demo.claim.services.ClaimService;
import com.wfld.claims.demo.claim.entities.ClaimResponse;
import com.wfld.claims.demo.claim.entities.ClaimStatus;
import com.wfld.claims.demo.claim.entities.ClaimRequest;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Configuration
public class TestConfig {
    
    @Bean
    @Primary
    public ClaimService mockClaimService() {
        return new ClaimService(null, null) {
            @Override
            public Mono<ClaimResponse> createClaim(ClaimRequest claimRequest) {
                return Mono.just(new ClaimResponse(null, new ClaimStatus(1L, "NEW", "New Claim", true)));
            }
            
            @Override
            public Flux<ClaimResponse> getAllClaims(int page, int size) {
                return Flux.empty();
            }
        };
    }
} 