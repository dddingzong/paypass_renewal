package com.project.paypass_renewal.service;

import com.project.paypass_renewal.domain.User;
import com.project.paypass_renewal.repository.UserRepository;
import com.project.paypass_renewal.support.UserUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    @DisplayName("서비스_신규유저_저장_테스트")
    void saveNewUser() {
        // given
        User user = UserUtils.createDummyUser();

        // Mocking the repository save method
        when(userRepository.save(user)).thenReturn(user);

        // when
        User savedUser = userService.save(user);

        // then
        Assertions.assertThat(savedUser.getId()).isEqualTo(user.getId());
    }

}
