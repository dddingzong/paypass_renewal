package com.project.paypass_renewal.repository;

import com.project.paypass_renewal.domain.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

    UserAddress findByNumber(String number);

}
