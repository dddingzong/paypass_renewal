package com.project.paypass_renewal.service;

import com.project.paypass_renewal.domain.Link;
import com.project.paypass_renewal.domain.dto.LinkRequestDto;
import com.project.paypass_renewal.repository.LinkRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LinkServiceTest {

    @Mock
    LinkRepository linkRepository;


    @InjectMocks
    LinkService linkService;

    @Test
    @DisplayName("링크_저장_테스트")
    void saveNewLinkTest() {
        // given
        LinkRequestDto linkDto = new LinkRequestDto("01089091234","01012341234");

        // when
        Link link = linkService.saveNewLink(linkDto);

        // then
        assertThat(link.getSupporterNumber()).isEqualTo("01089091234");
        assertThat(link.getUserNumber()).isEqualTo("01012341234");
    }



}
