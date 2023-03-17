package org.micro.service.implementation;

import org.micro.common.JobType;
import org.micro.common.JobsQueryUtils;
import org.micro.common.Utils;
import org.micro.dto.CompanyPersonDetailsDTO;
import org.micro.dto.JobDTO;
import org.micro.dto.JobLocationDTO;
import org.micro.dto.PageResponse;
import org.micro.entity.CompanyPersonDetails;
import org.micro.entity.JobLocation;
import org.micro.entity.Jobs;
import org.micro.repository.CompanyPersonDetailsRepository;
import org.micro.repository.JobLocationRepository;
import org.micro.repository.JobsRepository;
import org.micro.service.JobService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobsRepository jobsRepository;
    private final JobLocationRepository jobLocationRepository;
    private final CompanyPersonDetailsRepository companyPersonDetailsRepository;

    protected Session getCurrentSession() {
        return entitymanager.unwrap(Session.class);
    }
    @PersistenceContext
    private final EntityManager entitymanager;

    @Override
    public JobDTO createJob(JobDTO jobDTO,Long userId) {
        Jobs jobs = Jobs.builder()
                .jobTitle(jobDTO.getJobTitle())
                .userId(userId).description(jobDTO.getDescription())
                .eligibilityCriteria(jobDTO.getEligibilityCriteria())
                .minExperience(jobDTO.getMinExperience())
                .maxExperience(jobDTO.getMaxExperience())
                .noOfOpening(jobDTO.getNoOfOpening())
                .monthlySalary(jobDTO.getMonthlySalary())
                .annualPackage(jobDTO.getAnnualPackage())
                .companyAddress(jobDTO.getCompanyAddress()).build();
        if (jobDTO.getType().equalsIgnoreCase("remote")) {
            jobs.setType(String.valueOf(JobType.REMOTE));
        } else if (jobDTO.getType().equalsIgnoreCase("onsite")) {
            jobs.setType(String.valueOf(JobType.ON_SITE));
        }
        jobs = jobsRepository.save(jobs);
        JobLocation locationEntity = JobLocation.builder().build();
        if (jobDTO.getJobLocation() != null) {
            JobLocationDTO location = jobDTO.getJobLocation();
            locationEntity.setJobId(jobs.getId());
            locationEntity.setState(location.getState());
            locationEntity.setCity(location.getCity()) ;
            locationEntity = jobLocationRepository.save(locationEntity);
        }
        CompanyPersonDetails details =  CompanyPersonDetails.builder().build();
        if(jobDTO.getCompanyPersonDetails() != null){
            CompanyPersonDetailsDTO companyPersonDetails = jobDTO.getCompanyPersonDetails();
            details.setJobId(jobs.getId());
            details.setName(companyPersonDetails.getName());
            details.setEmail(companyPersonDetails.getEmail());
            details.setPhone(companyPersonDetails.getPhone());
            details = companyPersonDetailsRepository.save(details);
        }
        JobDTO dto = new JobDTO(jobs);
        dto.setJobLocation(new JobLocationDTO(locationEntity));
        dto.setCompanyPersonDetails(new CompanyPersonDetailsDTO(details));
        return dto;
    }

    @Override
    public PageResponse<JobDTO> getAllJobs(Pageable pageable) {
        String countQuery = JobsQueryUtils.getJobCountQuery();
        List<Jobs> jobEntity = jobsRepository.findAll();
        List<JobDTO> dtoList = new ArrayList<>();
        JobDTO dto = new JobDTO();
        if(Utils.isValidList(jobEntity)){
            for (Jobs item :jobEntity){
                 dto = new JobDTO(item);
                Optional<JobLocation> locationEntity = jobLocationRepository.findByJobId(item.getId());
                if(locationEntity!= null){
                    dto.setJobLocation(new JobLocationDTO(locationEntity.get()));
                }
                List<CompanyPersonDetails> personDetailslist = companyPersonDetailsRepository.findAllByJobId(item.getId());
                if(Utils.isValidList(personDetailslist)){
                    JobDTO finalDto = dto;
                    personDetailslist.stream().forEach(person -> {
                        finalDto.setCompanyPersonDetails(new CompanyPersonDetailsDTO(person));
                    });
                }
                dtoList.add(dto);
            }
        }
        NativeQuery countResult = getCurrentSession().createNativeQuery(countQuery);
        Long count = (Long) countResult.getSingleResult();
        return new PageResponse<>(new PageImpl<>(dtoList, pageable, count.intValue()));
    }
}
