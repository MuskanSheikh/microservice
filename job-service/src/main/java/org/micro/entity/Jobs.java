package org.micro.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.internal.build.AllowPrintStacktrace;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class Jobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String jobTitle;
    private String type;
    private String description;
    private String eligibilityCriteria;
    private String minExperience;
    private String maxExperience;
    private Long noOfOpening;
    private Double monthlySalary;
    private Double annualPackage;
    private String companyAddress;
}
