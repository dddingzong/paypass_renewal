package com.project.paypass_renewal.repository;

import com.project.paypass_renewal.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByLinkCode(String linkCode);

    boolean existsByNumber(String number);

}
