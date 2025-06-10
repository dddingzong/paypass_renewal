package com.project.paypass_renewal.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.paypass_renewal.domain.dto.UserLocationRequestDto;
import com.project.paypass_renewal.service.UserLocationService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserLocationControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private UserLocationService userLocationService;

    @InjectMocks
    private UserLocationController userLocationController;


    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userLocationController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("유저위치_저장_테스트")
    void saveUserLocationTest() throws Exception{
        // given
        final String url = "/user/getUserLocation";

        UserLocationRequestDto userLocationDto = new UserLocationRequestDto("test@gmail.com", "37.6616521", "127.0561246");

        String json = objectMapper.writeValueAsString(userLocationDto);
        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );
        // then
        result.andExpect(status().isOk())
                .andExpect(content().string("success"));
    }

}
