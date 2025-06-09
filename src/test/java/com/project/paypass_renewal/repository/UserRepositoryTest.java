package com.project.paypass_renewal.repository;

import com.project.paypass_renewal.domain.User;
import com.project.paypass_renewal.support.UserTestUtils;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("리포지토리_신규유저_저장_테스트")
    void saveNewUserTest(){
        // given
        User user = UserTestUtils.createDummyUser();

        // when
        userRepository.save(user);

        // then
        Long id = userRepository.findById(user.getId()).get().getId();

        assertThat(id).isEqualTo(user.getId());
    }

    @Test
    @DisplayName("유저링크_존재유무_테스트")
    void checkExistsLinkCode(){
        // given
        User user = UserTestUtils.createDummyUser();

        // when
        boolean firstCheck = userRepository.existsByLinkCode(user.getLinkCode());

        userRepository.save(user);
        boolean secondCheck = userRepository.existsByLinkCode(user.getLinkCode());

        // then
        assertThat(firstCheck).isFalse();
        assertThat(secondCheck).isTrue();
    }

    @Test
    @DisplayName("유저_이메일_존재유무_테스트")
    void checkExistsMainIdTest() {
        // given
        User user = UserTestUtils.createDummyUser();

        // when
        boolean firstCheck = userRepository.existsByMainId(user.getMainId());

        userRepository.save(user);
        boolean secondCheck = userRepository.existsByMainId(user.getMainId());

        // then
        assertThat(firstCheck).isFalse();
        assertThat(secondCheck).isTrue();
    }

}
