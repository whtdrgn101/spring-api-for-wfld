package com.wfld.claims.demo.claim.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import com.wfld.claims.demo.claim.entities.ClaimStatus;
import reactor.core.publisher.Mono;

@Repository
public interface ClaimStatusRepository extends R2dbcRepository<ClaimStatus, Long> {
    Mono<ClaimStatus> findByCode(String code);
} 