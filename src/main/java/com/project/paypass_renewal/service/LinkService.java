package com.project.paypass_renewal.service;

import com.project.paypass_renewal.domain.Link;
import com.project.paypass_renewal.domain.dto.LinkRequestDto;
import com.project.paypass_renewal.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepository linkRepository;

    public Link saveNewLink(LinkRequestDto linkRequestDto) {

        Link link = toEntity(linkRequestDto);

        linkRepository.save(link);

        return link;
    }

    private Link toEntity(LinkRequestDto linkRequestDto) {
        String supporterNumber = linkRequestDto.getSupporterNumber();
        String userNumber = linkRequestDto.getUserNumber();

        return new Link(supporterNumber, userNumber);
    }

}
