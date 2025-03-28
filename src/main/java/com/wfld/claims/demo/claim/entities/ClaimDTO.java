package com.wfld.claims.demo.claim.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("claims")
public class ClaimDTO {
    @Id
    private Long id;
    private String name;
    private String description;
    private Double amount;
    private Long statusId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;

    public ClaimDTO(ClaimRequest claimRequest) {
        this.name = claimRequest.getName();
        this.description = claimRequest.getDescription();
        this.statusId = claimRequest.getStatusId();
        this.createdBy = claimRequest.getCreatedBy();
        this.amount = claimRequest.getAmount();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();  
    }
}   
