package org.micro.repository;

import org.micro.entity.CompanyPersonDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyPersonDetailsRepository extends JpaRepository<CompanyPersonDetails, Long> {
    @Query("SELECT C FROM CompanyPersonDetails C WHERE C.jobId = :jobId")
    List<CompanyPersonDetails> findAllByJobId(@Param("jobId") Long id);
}
