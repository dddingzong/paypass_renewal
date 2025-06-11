package com.project.paypass_renewal.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.project.paypass_renewal.controller.UserController;
import com.project.paypass_renewal.domain.dto.UserDto;
import com.project.paypass_renewal.exception.handler.user.UserControllerExceptionHandler;
import com.project.paypass_renewal.service.UserService;
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

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserDtoValidTest {
    // UserDto에서 받는 값은 name, password, birth, number, homeAddress, centerAddress, serviceCode

    private final String url = "/login/newUser";

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

        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new UserControllerExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("UserDto_name_이름형식_오류")
    void userDtoNameNotValidTest() throws Exception {
        // given
        UserDto userDto = createUserDtoDummy("정종정종인", "abc123" , LocalDate.of(2000, 5, 1), "01089099721", "01213", "12211", ServiceCode.PAYPASS_SERVICE);
        String json = objectMapper.writeValueAsString(userDto);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    @DisplayName("UserDto_name_이름형식_빈칸")
    void userDtoNameBlankTest() throws Exception {
        // given
        UserDto userDto = createUserDtoDummy("", "abc123", LocalDate.of(2000, 5, 1), "01089099721", "01213", "12211", ServiceCode.PAYPASS_SERVICE);
        String json = objectMapper.writeValueAsString(userDto);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    @DisplayName("UserDto_password_비밀번호형식_한국어_오류")
    void userDtoPasswordNotValidKoreanTest() throws Exception {
        // given
        UserDto userDto = createUserDtoDummy("정종인", "abㄱ123", LocalDate.of(2000, 5, 1), "01089099721", "01213", "12211", ServiceCode.PAYPASS_SERVICE);
        String json = objectMapper.writeValueAsString(userDto);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    @DisplayName("UserDto_password_비밀번호형식_특수기호_오류")
    void userDtoPasswordNotValidKSpecialTest() throws Exception {
        // given
        UserDto userDto = createUserDtoDummy("정종인", "ab!123", LocalDate.of(2000, 5, 1), "01089099721", "01213", "12211", ServiceCode.PAYPASS_SERVICE);
        String json = objectMapper.writeValueAsString(userDto);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    @DisplayName("UserDto_password_비밀번호형식_여섯자리미만_오류")
    void userDtoPasswordNotValidUnderTest() throws Exception {
        // given
        UserDto userDto = createUserDtoDummy("정종인", "ab123", LocalDate.of(2000, 5, 1), "01089099721", "01213", "12211", ServiceCode.PAYPASS_SERVICE);
        String json = objectMapper.writeValueAsString(userDto);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    @DisplayName("UserDto_password_비밀번호형식_빈칸")
    void userDtoPasswordBlankTest() throws Exception {
        // given
        UserDto userDto = createUserDtoDummy("정종인", "", LocalDate.of(2000, 5, 1), "01089099721", "01213", "12211", ServiceCode.PAYPASS_SERVICE);
        String json = objectMapper.writeValueAsString(userDto);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    @DisplayName("UserDto_password_비밀번호형식_null")
    void userDtoPasswordNullTest() throws Exception {
        // given
        UserDto userDto = createUserDtoDummy("정종인", null, LocalDate.of(2000, 5, 1), "01089099721", "01213", "12211", ServiceCode.PAYPASS_SERVICE);
        String json = objectMapper.writeValueAsString(userDto);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }



    @Test
    @DisplayName("UserDto_birth_생년월일형식_오류")
    void userDtoBirthNotValidTest() throws Exception {
        // given
        String json = """
                {
                    "homeAddress": "34521",
                    "birth": "2025-06",
                    "centerAddress": null,
                    "name": "정종인",
                    "number": "01089099721",
                    "serviceCode": "CARE_SERVICE",
                    "password": "abc123"
                }
                """;

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    @DisplayName("UserDto_birth_생년월일형식_빈칸")
    void userDtoBirthBlankTest() throws Exception {
        // given
        String json = """
                {
                    "homeAddress": "32451",
                    "birth": "",
                    "centerAddress": null,
                    "name": "정종인",
                    "number": "01089099721",
                    "serviceCode": "CARE_SERVICE",
                    "password": "abs123"
                }
                """;

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }


    @Test
    @DisplayName("UserDto_birth_생년월일형식_미래")
    void userDtoBirthFutureTest() throws Exception {
        // given
        UserDto userDto = createUserDtoDummy("정종인", "abc123", LocalDate.of(2050, 5, 1), "01089099721", "01313", "13211", ServiceCode.PAYPASS_SERVICE);
        String json = objectMapper.writeValueAsString(userDto);
        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    @DisplayName("UserDto_number_번호형식_하이픈_오류")
    void userDtoNumberNotValidHyphenTest() throws Exception {
        // given
        UserDto userDto = createUserDtoDummy("정종인", "abc123", LocalDate.of(2000, 5, 1), "010-8909-9721", "01233", "12311", ServiceCode.PAYPASS_SERVICE);
        String json = objectMapper.writeValueAsString(userDto);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    @DisplayName("UserDto_number_번호형식_자릿수초과_오류")
    void userDtoNumberNotValidCountOverTest() throws Exception {
        // given
        UserDto userDto = createUserDtoDummy("정종인", "abc123", LocalDate.of(2000, 5, 1), "010890997211", "01213", "12321", ServiceCode.PAYPASS_SERVICE);
        String json = objectMapper.writeValueAsString(userDto);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    @DisplayName("UserDto_number_번호형식_자릿수미만_오류")
    void userDtoNumberNotValidCountUnderTest() throws Exception {
        // given
        UserDto userDto = createUserDtoDummy("정종인", "abc123", LocalDate.of(2000, 5, 1), "0108909972", "01213", "12321", ServiceCode.PAYPASS_SERVICE);
        String json = objectMapper.writeValueAsString(userDto);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    @DisplayName("UserDto_number_번호형식_빈칸")
    void userDtoNumberBlankTest() throws Exception {
        // given
        UserDto userDto = createUserDtoDummy("정종인", "abc123", LocalDate.of(2000, 5, 1), "", "01313", "12321", ServiceCode.PAYPASS_SERVICE);
        String json = objectMapper.writeValueAsString(userDto);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    @DisplayName("UserDto_homeAddress_집주소형식_다섯자리미만_오류")
    void userDtoHomeAddressNotValidShortTest() throws Exception {
        // given
        UserDto userDto = createUserDtoDummy("정종인", "abs123", LocalDate.of(2000, 5, 1), "01089099721", "0131", "12311", ServiceCode.PAYPASS_SERVICE);
        String json = objectMapper.writeValueAsString(userDto);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    @DisplayName("UserDto_homeAddress_집주소형식_다섯자리초과_오류")
    void userDtoHomeAddressNotValidLongTest() throws Exception {
        // given
        UserDto userDto = createUserDtoDummy("정종인", "abs123", LocalDate.of(2000, 5, 1), "01089099721", "012331", "12321", ServiceCode.PAYPASS_SERVICE);
        String json = objectMapper.writeValueAsString(userDto);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    @DisplayName("UserDto_homeAddress_집주소형식_빈칸")
    void userDtoHomeAddressBlankTest() throws Exception {
        // given
        UserDto userDto = createUserDtoDummy("정종인", "abs123", LocalDate.of(2000, 5, 1), "01089099721", "", "12321", ServiceCode.PAYPASS_SERVICE);
        String json = objectMapper.writeValueAsString(userDto);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    @DisplayName("UserDto_centerAddress_센터주소형식_다섯자리초과_오류")
    void userDtoCenterAddressNotValidLongTest() throws Exception {
        // given
        UserDto userDto = createUserDtoDummy("정종인", "abs123", LocalDate.of(2000, 5, 1), "01089099721", "15311", "123211", ServiceCode.PAYPASS_SERVICE);
        String json = objectMapper.writeValueAsString(userDto);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    @DisplayName("UserDto_centerAddress_센터주소형식_다섯자리미만_오류")
    void userDtoCenterAddressNotValidShortTest() throws Exception {
        // given
        UserDto userDto = createUserDtoDummy("정종인", "abs123", LocalDate.of(2000, 5, 1), "01089099721", "15311", "1211", ServiceCode.PAYPASS_SERVICE);
        String json = objectMapper.writeValueAsString(userDto);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    @DisplayName("UserDto_centerAddress_센터주소형식_빈칸")
    void userDtoCenterAddressBlankTest() throws Exception {
        // given
        UserDto userDto = createUserDtoDummy("정종인", "abs123", LocalDate.of(2000, 5, 1), "01089099721", "15311", "", ServiceCode.PAYPASS_SERVICE);
        String json = objectMapper.writeValueAsString(userDto);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    @DisplayName("UserDto_centerAddress_센터주소형식_Null")
    void userDtoCenterAddressNullTest() throws Exception {
        // given
        UserDto userDto = createUserDtoDummy("정종인", "abs123", LocalDate.of(2000, 5, 1), "01089099721", "15311", null, ServiceCode.PAYPASS_SERVICE);
        String json = objectMapper.writeValueAsString(userDto);
        User user = toEntity(userDto, "ABC123");

        // stub
        when(userService.saveNewUser(any(UserDto.class))).thenReturn(user);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value("01089099721"));
    }

    @Test
    @DisplayName("UserDto_serviceCode_코드형식_null")
    void userDtoServiceCodeNullTest() throws Exception {
        // given
        UserDto userDto = createUserDtoDummy("정종인", "abs123", LocalDate.of(2000, 5, 1), "01089099721", "15311", "123121", null);
        String json = objectMapper.writeValueAsString(userDto);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400));
    }


    private UserDto createUserDtoDummy(String name, String password, LocalDate birth, String number, String homeAddress, String centerAddress, ServiceCode serviceCode){
        return new UserDto(name, password, birth, number, homeAddress, centerAddress, serviceCode);
    }

    private User toEntity(UserDto userDto, String linkCode){
        String name = userDto.getName();
        String password = userDto.getPassword();
        LocalDate birth = userDto.getBirth();
        String number = userDto.getNumber();
        String homeAddress = userDto.getHomeAddress();
        String centerAddress = userDto.getCenterAddress();
        ServiceCode serviceCode = userDto.getServiceCode();

        User user = new User(name, password, birth, number, homeAddress, centerAddress, linkCode, serviceCode);

        return user;
    }
}
