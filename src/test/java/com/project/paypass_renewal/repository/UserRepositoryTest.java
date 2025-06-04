package com.project.paypass_renewal.repository;

import com.project.paypass_renewal.domain.ServiceCode;
import com.project.paypass_renewal.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("신규유저_저장_테스트")
    void saveNewUser(){
        // given
        User user = new User("test@gmail.com", "정종인",
                LocalDate.of(2000, 5, 13), "01012345678",
                "01675", "01747", "245863", ServiceCode.PROTECT_SERVICE);

        // when
        userRepository.save(user);
        Long id = userRepository.findById(user.getId()).get().getId();

        // then
        Assertions.assertThat(id).isEqualTo(user.getId());

    }
}
