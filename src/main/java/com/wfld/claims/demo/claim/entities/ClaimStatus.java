package com.wfld.claims.demo.claim.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("claim_statuses")
public class ClaimStatus {
    @Id
    private Long id;
    private String code;
    private String description;
    private boolean active;
} 