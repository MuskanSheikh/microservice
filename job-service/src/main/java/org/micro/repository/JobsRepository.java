package org.micro.repository;

import org.micro.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobsRepository extends JpaRepository<Jobs, Long> {

    @Query("SELECT J FROM Jobs J WHERE J.userId = :userId")
    List<Jobs> getJobsByUserId(@Param("userId") Long userId);
}
