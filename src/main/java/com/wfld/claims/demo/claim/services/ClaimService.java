package com.wfld.claims.demo.claim.services;

import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.micrometer.observation.annotation.Observed;
import com.wfld.claims.demo.claim.entities.ClaimDTO;
import com.wfld.claims.demo.claim.entities.ClaimRequest;
import com.wfld.claims.demo.claim.entities.ClaimResponse;
import com.wfld.claims.demo.claim.repositories.ClaimRepository;
import com.wfld.claims.demo.claim.repositories.ClaimStatusRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Observed(name = "claims.service")
public class ClaimService {
    private final ClaimRepository claimRepository;
    private final ClaimStatusRepository claimStatusRepository;

    public ClaimService(ClaimRepository claimRepository, ClaimStatusRepository claimStatusRepository) {
        this.claimRepository = claimRepository;
        this.claimStatusRepository = claimStatusRepository;
    }

    @CircuitBreaker(name = "claimService", fallbackMethod = "getAllClaimsFallback")
    @Retry(name = "claimService")
    @Observed(name = "getAllClaims", contextualName = "Getting all claims")
    public Flux<ClaimResponse> getAllClaims(int page, int size) {
        return claimRepository.findAllPaged(page, size)
            .flatMap(claim -> claimStatusRepository.findById(claim.getStatusId())
                .map(status -> new ClaimResponse(claim, status)));
    }

    @Observed(name = "getAllClaimsFallback", contextualName = "Getting all claims fallback")
    public Flux<ClaimResponse> getAllClaimsFallback(int page, int size, Exception ex) {
        return Flux.empty();
    }

    @CircuitBreaker(name = "claimService", fallbackMethod = "createClaimFallback")
    @Retry(name = "claimService")
    @Observed(name = "createClaim", contextualName = "Creating new claim")
    public Mono<ClaimResponse> createClaim(ClaimRequest claimRequest) {
        return claimStatusRepository.findById(claimRequest.getStatusId())
            .flatMap(status -> claimRepository.save(new ClaimDTO(claimRequest))
                .map(claim -> new ClaimResponse(claim, status)));
    }

    @Observed(name = "createClaimFallback", contextualName = "Creating new claim fallback")
    public Mono<ClaimResponse> createClaimFallback(ClaimRequest claimRequest, Exception ex) {
        return Mono.empty();
    }
}
