package org.micro.dto;

import org.micro.entity.CompanyPersonDetails;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class CompanyPersonDetailsDTO {
    private Long id;
    private Long jobId;
    private String name;
    private String email;
    private String phone;

    public CompanyPersonDetailsDTO(CompanyPersonDetails entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.jobId = entity.getJobId();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
    }
}
