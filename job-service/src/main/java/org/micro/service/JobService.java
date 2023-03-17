package org.micro.service;

import org.micro.dto.JobDTO;
import org.micro.dto.PageResponse;
import org.springframework.data.domain.Pageable;

public interface JobService {
    JobDTO createJob(JobDTO jobDTO,Long userId);


    PageResponse<JobDTO> getAllJobs(Pageable pageable);

    PageResponse<JobDTO> getJobsByUserId(Pageable pageable, Long userId);
}
