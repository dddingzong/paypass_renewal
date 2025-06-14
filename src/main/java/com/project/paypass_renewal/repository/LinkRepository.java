package com.project.paypass_renewal.repository;

import com.project.paypass_renewal.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long> {

    @Query("SELECT l.userNumber FROM Link l WHERE l.supporterNumber = :supporterNumber")
    List<String> findUserNumbersBySupporterNumber(@Param("supporterNumber") String supporterNumber);

    int deleteBySupporterNumberAndUserNumber(String supporterNumber, String userNumber);

    boolean existsBySupporterNumberAndUserNumber(String supporterNumber, String userNumber);

}

