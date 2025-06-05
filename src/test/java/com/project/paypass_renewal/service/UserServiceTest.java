package com.project.paypass_renewal.service;

import com.project.paypass_renewal.domain.User;
import com.project.paypass_renewal.domain.dto.UserDto;
import com.project.paypass_renewal.generator.LinkCodeGenerator;
import com.project.paypass_renewal.repository.UserRepository;
import com.project.paypass_renewal.support.UserDtoTestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    LinkCodeGenerator linkCodeGenerator;

    @InjectMocks
    UserService userService;

    @Test
    @DisplayName("서비스_신규유저_저장_테스트")
    void saveNewUserTest() {
        // given
        UserDto userDto = UserDtoTestUtil.createDummyUserDto();
        String linkCode = "ABC123";
        // stub
        when(linkCodeGenerator.generate()).thenReturn(linkCode);
        when(userRepository.existsByLinkCode(linkCode)).thenReturn(false);

        // when
        User user = userService.saveNewUser(userDto);

        // then
        assertThat(user.getMainId()).isNotNull();
        assertThat(user.getMainId()).isEqualTo(userDto.getMainId());
        assertThat(user.getLinkCode()).isNotNull();
        assertThat(user.getLinkCode()).isEqualTo(linkCode);
    }

    @Test
    @DisplayName("서비스_신규유저_링크코드_중복_테스트")
    void linkCodeDuplicateTest(){
        // given
        UserDto userDto = UserDtoTestUtil.createDummyUserDto();
        String firstLinkCode = "123ABC";
        String secondLinkCode = "456DEF";
        // stub
        when(linkCodeGenerator.generate()).thenReturn(firstLinkCode,secondLinkCode);
        when(userRepository.existsByLinkCode(firstLinkCode)).thenReturn(true);
        when(userRepository.existsByLinkCode(secondLinkCode)).thenReturn(false);

        // when
        User user = userService.saveNewUser(userDto);

        // then
        assertThat(user.getMainId()).isNotNull();
        assertThat(user.getMainId()).isEqualTo(userDto.getMainId());
        assertThat(user.getLinkCode()).isNotNull();
        assertThat(user.getLinkCode()).isEqualTo(secondLinkCode);
    }

}
