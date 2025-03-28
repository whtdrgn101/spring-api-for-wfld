package com.wfld.claims.demo.claim.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.lang.NonNull;

import com.wfld.claims.demo.claim.entities.ClaimDTO;

@Repository
public interface ClaimRepository extends ReactiveCrudRepository<ClaimDTO, Long> {
    @NonNull
    public Flux<ClaimDTO> findAll();
    @NonNull
    public Mono<ClaimDTO> findById(@NonNull Long id);
    @NonNull
    public Mono<ClaimDTO> save(@NonNull ClaimDTO claimDTO);
    @NonNull
    public Mono<Void> deleteById(@NonNull Long id);

    // If you need pagination, you can add a custom method like this:
    default Flux<ClaimDTO> findAllPaged(int page, int size) {
        return findAll()
            .skip(page * size)
            .take(size);
    }
}
