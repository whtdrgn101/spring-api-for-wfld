package com.wfld.claims.demo.claim.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimRequest {
    private String name;
    private String description;
    private Double amount;
    private Long statusId;
    private String createdBy;
}
