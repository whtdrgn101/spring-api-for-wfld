package com.wfld.claims.demo.claim.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import io.micrometer.observation.annotation.Observed;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.wfld.claims.demo.claim.entities.ClaimRequest;
import com.wfld.claims.demo.claim.entities.ClaimResponse;
import com.wfld.claims.demo.claim.services.ClaimService;

@RestController
@RequestMapping("/api/claims")
@Observed(name = "claims.controller")
public class ClaimController {
    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_can-request-claims')")
    public Flux<ClaimResponse> getAllClaims(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        return claimService.getAllClaims(page, size);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_can-submit-claims')")
    public Mono<ClaimResponse> createClaim(@RequestBody ClaimRequest claimRequest) {
        return claimService.createClaim(claimRequest);
    }   
}
