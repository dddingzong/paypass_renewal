package com.project.paypass_renewal.repository;

import com.project.paypass_renewal.domain.Link;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class LinkRepositoryTest {

    @Autowired
    LinkRepository linkRepository;

    @Test
    @DisplayName("링크_저장_테스트")
    void saveNewLinkTest(){
        // given
        Link link = new Link("01012345678", "01011111111");

        // when
        Link savedLink = linkRepository.save(link);

        // then
        assertThat(savedLink.getId()).isEqualTo(link.getId());
        assertThat(savedLink.getSupporterNumber()).isEqualTo(link.getSupporterNumber());
        assertThat(savedLink.getUserNumber()).isEqualTo(link.getUserNumber());
    }

    @Test
    @DisplayName("이용자_번호_조회_테스트")
    void findUserNumberBySupporterNumberTest(){
        // given
        String supporterNumber = "01012345678";
        String supporterNumberDummy = "01012341234";
        String userNumberOne = "01011111111";
        String userNumberTwo = "01022222222";

        Link linkOne = new Link(supporterNumber, userNumberOne);
        Link linkTwo = new Link(supporterNumber, userNumberTwo);
        Link linkDummy = new Link(supporterNumberDummy, userNumberOne);

        linkRepository.save(linkOne);
        linkRepository.save(linkTwo);
        linkRepository.save(linkDummy);

        // when
        List<String> userNumbers = linkRepository.findUserNumbersBySupporterNumber(supporterNumber);

        // then
        assertThat(userNumbers).hasSize(2);
        assertThat(userNumbers).containsExactlyInAnyOrder(userNumberOne, userNumberTwo);
    }

}
