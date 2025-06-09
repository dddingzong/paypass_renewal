package com.project.paypass_renewal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.project.paypass_renewal.domain.User;
import com.project.paypass_renewal.domain.dto.UserDto;
import com.project.paypass_renewal.service.UserService;
import com.project.paypass_renewal.support.UserDtoTestUtil;
import com.project.paypass_renewal.support.UserTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void init() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @DisplayName("컨트롤러_신규유저_DTO_활용_저장_테스트")
    void createNewUserTest() throws Exception {
        // given
        final String url = "/login/newUser";
        UserDto userDto = UserDtoTestUtil.createDummyUserDto();

        User user = UserTestUtils.createDummyUser();

        // stub
        when(userService.checkDuplicateMainId(any(String.class))).thenReturn(false);
        // userService.saveNewUser 호출 시 dummyUser 반환하도록 설정
        when(userService.saveNewUser(any(UserDto.class))).thenReturn(user);

        String json = objectMapper.writeValueAsString(userDto);
        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.mainId").value("dummy@gmail.com"));

    }

    @Test
    @DisplayName("컨트롤러_신규유저_JSON_활용_저장_테스트")
    void createNewUserUsingJsonTest() throws Exception {
        // given
        final String url = "/login/newUser";
        String json = """
                {
                    "homeAddress": "32521",
                    "birth": "2025-06-02",
                    "centerAddress": null,
                    "name": "정종인",
                    "number": "01089099721",
                    "serviceCode": "PROTECT_SERVICE",
                    "mainId": "chungjongin@gmail.com"
                }
                """;

        User user = UserTestUtils.createDummyUser();

        // stub
        when(userService.checkDuplicateMainId(any(String.class))).thenReturn(false);
        // userService.saveNewUser 호출 시 dummyUser 반환하도록 설정
        when(userService.saveNewUser(any(UserDto.class))).thenReturn(user);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.mainId").value("dummy@gmail.com"));

    }

    @Test
    @DisplayName("컨트롤러_신규유저_이메일중복_테스트")
    void duplicateMainIdTest() throws Exception {
        // given
        final String url = "/login/newUser";
        UserDto userDto = UserDtoTestUtil.createDummyUserDto();

        // stub
        when(userService.checkDuplicateMainId(any(String.class))).thenReturn(true);

        String json = objectMapper.writeValueAsString(userDto);
        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());

    }
}
