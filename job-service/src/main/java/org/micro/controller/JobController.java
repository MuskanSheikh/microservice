package org.micro.controller;

import org.micro.dto.JobDTO;
import org.micro.service.JobService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("job-api/")
public class JobController {
    public final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @PostMapping("{userId}/add-job")
    public ResponseEntity<?> addJob (@RequestBody JobDTO jobDTO, @PathVariable Long userId){
        JobDTO response = jobService.createJob(jobDTO,userId);
        if(response != null){
            return ResponseEntity.ok(response);
        }
        return  ResponseEntity.badRequest().body("fail to post job");
    }

    @GetMapping("get-all")
    public ResponseEntity<?> getAllJobs(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(jobService.getAllJobs(pageable));
    }
}
