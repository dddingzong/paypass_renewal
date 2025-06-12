package com.project.paypass_renewal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.paypass_renewal.domain.dto.LinkRequestDto;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    // 보내야하는 정보: 이름,
    @Test
    @DisplayName("이용자_조회_테스트")
    void findUsersListTest() throws Exception{
        // given
        final String url = "link/getUserList";

        String supporterNumber = "01012345678";
        String json = objectMapper.writeValueAsString(supporterNumber);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // then


    }


}