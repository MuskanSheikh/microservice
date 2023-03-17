package org.micro.repository;

import org.micro.entity.JobLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobLocationRepository extends JpaRepository<JobLocation,Long> {
    Optional<JobLocation> findByJobId(Long id);
}
