package com.project.paypass_renewal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.paypass_renewal.domain.dto.request.UserPaypassGeofenceRequestDto;
import com.project.paypass_renewal.service.PaypassGeofenceService;
import com.project.paypass_renewal.service.StationService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PayPassGeofenceControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    PaypassGeofenceService paypassGeofenceService;

    @Mock
    StationService stationService;

    @InjectMocks
    PayPassGeofenceController payPassGeofenceController;

    @BeforeEach
    void init() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(payPassGeofenceController).build();
    }

    @Test
    @DisplayName("사용자가 geofence에 진입했을 때, paypassGeofence 생성 테스트")
    void userFenceInTest() throws Exception {
        // given
        final String url = "/geofence/userFenceIn";

        UserPaypassGeofenceRequestDto requestDto = new UserPaypassGeofenceRequestDto("01012345678", 12345L, "testName");
        String json = objectMapper.writeValueAsString(requestDto);

        // when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        // then
        result.andExpect(status().isOk())
              .andExpect(content().string("success save geofence data"));
    }




}
