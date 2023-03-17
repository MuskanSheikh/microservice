package org.micro.dto;

import org.micro.entity.JobLocation;
import jakarta.persistence.Entity;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class JobLocationDTO {
    private Long id;
    private Long jobId;
    private String state;
    private String city;

    public JobLocationDTO(JobLocation entity) {
        this.id = entity.getId();
        this.jobId = entity.getJobId();
        this.state = entity.getState();
        this.city = entity.getCity();
    }
}
