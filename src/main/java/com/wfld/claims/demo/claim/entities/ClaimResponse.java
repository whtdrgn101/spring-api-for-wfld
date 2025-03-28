package com.wfld.claims.demo.claim.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ClaimResponse {
    private Long id;
    private String name;
    private String description;
    private Double amount;
    private ClaimStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;

    public ClaimResponse(ClaimDTO claimDTO, ClaimStatus status) {
        this.id = claimDTO.getId();
        this.name = claimDTO.getName();
        this.description = claimDTO.getDescription();
        this.amount = claimDTO.getAmount();
        this.status = status;
        this.createdAt = claimDTO.getCreatedAt();
        this.updatedAt = claimDTO.getUpdatedAt();
        this.createdBy = claimDTO.getCreatedBy();
    }
}
