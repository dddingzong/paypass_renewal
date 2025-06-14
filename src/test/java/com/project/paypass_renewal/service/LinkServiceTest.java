package com.project.paypass_renewal.service;

import com.project.paypass_renewal.domain.Link;
import com.project.paypass_renewal.domain.ServiceCode;
import com.project.paypass_renewal.domain.User;
import com.project.paypass_renewal.domain.dto.response.LinkListResponseDto;
import com.project.paypass_renewal.domain.dto.request.LinkRequestDto;
import com.project.paypass_renewal.repository.LinkRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

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

    @Test
    @DisplayName("사용자_링크_리스트_조회_테스트")
    void UserToLinkResponseDto () {
        // List<User> -> List<LinkResponseDto>로 변환
        // given
        List<User> userList = List.of(
                new User("test1", "abc123", LocalDate.of(2000, 01, 01), "01011111111", "12352", null, "AG1DE1", ServiceCode.CARE_SERVICE),
                new User("test2", "abc123", LocalDate.of(2000, 01, 02), "01022222222", "12152", null, "AG1DEV", ServiceCode.CARE_SERVICE)
        );

        // when
        List<LinkListResponseDto> LinkList = linkService.userToLinkResponseDto(userList);

        // then
        assertThat(LinkList).hasSize(2);
        assertThat(LinkList.get(0).getUserNumber()).isEqualTo("01011111111");
        assertThat(LinkList.get(1).getUserNumber()).isEqualTo("01022222222");
    }


}
