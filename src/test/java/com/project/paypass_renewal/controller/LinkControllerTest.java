package com.project.paypass_renewal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.paypass_renewal.domain.ServiceCode;
import com.project.paypass_renewal.domain.User;
import com.project.paypass_renewal.domain.dto.request.LinkRequestDto;
import com.project.paypass_renewal.domain.dto.request.SupporterNumberRequestDto;
import com.project.paypass_renewal.domain.dto.response.LinkListResponseDto;
import com.project.paypass_renewal.service.LinkService;

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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class LinkControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    LinkService linkService;

    @InjectMocks
    LinkController linkController;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(linkController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("링크_저장_테스트")
    void saveNewLinkTest() throws Exception {
        // given
        final String url = "/link/saveNewLink";
        LinkRequestDto linkDto = new LinkRequestDto("01089091234", "01012341234");
        String json = objectMapper.writeValueAsString(linkDto);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isOk())
                .andExpect(content().string("saveSuccess"));
    }

    // 보내야하는 정보: 이름, 주소
    @Test
    @DisplayName("이용자_조회_테스트")
    void findUsersListTest() throws Exception{
        // given
        final String url = "/link/getLinkList";

        SupporterNumberRequestDto supporterNumberRequestDto = new SupporterNumberRequestDto("01012345678");
        String json = objectMapper.writeValueAsString(supporterNumberRequestDto);

        // stub

        when(linkService.findUserNumbersBySupporterNumber(any(SupporterNumberRequestDto.class))).thenReturn(List.of("01011111111", "01022222222"));

        when(linkService.findUserListByNumbers(anyList())).thenReturn(List.of(
                new User("test1", "abc123", LocalDate.of(2000, 01, 01), "01011111111", "12352", null, "AG1DE1", ServiceCode.CARE_SERVICE),
                new User("test2", "abc123", LocalDate.of(2000, 01, 02), "01022222222", "12152", null, "AG1DEV", ServiceCode.CARE_SERVICE)
        ));

        when(linkService.userToLinkResponseDto(anyList()))
                .thenReturn(List.of(
                        new LinkListResponseDto("01089091234", "User1","12641"),
                        new LinkListResponseDto("01012341234", "User2", "12451")
                ));

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].userNumber").value("01089091234"))
                .andExpect(jsonPath("$[0].name").value("User1"))
                .andExpect(jsonPath("$[1].userNumber").value("01012341234"))
                .andExpect(jsonPath("$[1].name").value("User2"));
    }


}