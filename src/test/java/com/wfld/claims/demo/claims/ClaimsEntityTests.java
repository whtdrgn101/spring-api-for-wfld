package com.wfld.claims.demo.claims;
import com.wfld.claims.demo.claim.entities.ClaimRequest;
import com.wfld.claims.demo.claim.entities.ClaimResponse;
import com.wfld.claims.demo.claim.entities.ClaimDTO;
import com.wfld.claims.demo.claim.entities.ClaimStatus;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ClaimsEntityTests {
    
    @Test
    void testRequestShouldMapToDto() {
        ClaimRequest request = new ClaimRequest();
        request.setAmount(1200.02);
        request.setStatusId(2L);
        request.setCreatedBy("DemoUser");
        request.setDescription("Claim Description for testing");
        ClaimDTO claimDTO = new ClaimDTO(request);
        assertThat(claimDTO.getAmount()).isEqualTo(request.getAmount());
        assertThat(claimDTO.getStatusId()).isEqualTo(request.getStatusId());
        assertThat(claimDTO.getCreatedBy()).isEqualTo(request.getCreatedBy());
        assertThat(claimDTO.getDescription()).isEqualTo(request.getDescription());
    }

    @Test
    void testDTOShouldMapToResponse() {
        ClaimStatus status = new ClaimStatus(1L, "PENDING", "PENDING", true);
        ClaimDTO claimDTO = new ClaimDTO();
        claimDTO.setAmount(1200.02);
        claimDTO.setStatusId(2L);
        claimDTO.setCreatedBy("DemoUser");
        claimDTO.setDescription("Claim Description for testing");
        ClaimResponse response = new ClaimResponse(claimDTO, status );
        assertThat(response.getAmount()).isEqualTo(claimDTO.getAmount());
        assertThat(response.getStatus().getId()).isEqualTo(status.getId());
        assertThat(response.getCreatedBy()).isEqualTo(claimDTO.getCreatedBy());
        assertThat(response.getDescription()).isEqualTo(claimDTO.getDescription()); 
    }

}
