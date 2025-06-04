package com.project.paypass_renewal.repository;

import com.project.paypass_renewal.domain.User;
import com.project.paypass_renewal.support.UserUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("리포지토리_신규유저_저장_테스트")
    void saveNewUserTest(){
        // given
        User user = UserUtils.createDummyUser();

        // when
        userRepository.save(user);

        // then
        Long id = userRepository.findById(user.getId()).get().getId();

        Assertions.assertThat(id).isEqualTo(user.getId());

    }
}
