package org.micro.dto;

import org.micro.entity.Jobs;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class JobDTO {
    private Long id;
    private Long userId;
    private String jobTitle;
    private String type;
    private String description;
    private String eligibilityCriteria;
    private String minExperience;
    private String maxExperience;
    private Long noOfOpening;
    private JobLocationDTO jobLocation;
    private Double monthlySalary;
    private Double annualPackage;
    private CompanyPersonDetailsDTO companyPersonDetails;
    private String companyAddress;

    public JobDTO(Jobs entity){
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.jobTitle = entity.getJobTitle();
        this.type = entity.getType();
        this.description = entity.getDescription();
        this.eligibilityCriteria = entity.getEligibilityCriteria();
        this.minExperience = entity.getMinExperience();
        this.maxExperience = entity.getMaxExperience();
        this.noOfOpening = entity.getNoOfOpening();
        this.monthlySalary = entity.getMonthlySalary();
        this.annualPackage = entity.getAnnualPackage();
        this.companyAddress = entity.getCompanyAddress();
    }
}
