package com.project.paypass_renewal.service;

import com.project.paypass_renewal.domain.Link;
import com.project.paypass_renewal.domain.User;
import com.project.paypass_renewal.domain.dto.request.LinkDeleteRequestDto;
import com.project.paypass_renewal.domain.dto.request.SupporterNumberRequestDto;
import com.project.paypass_renewal.domain.dto.response.LinkListResponseDto;
import com.project.paypass_renewal.domain.dto.request.LinkRequestDto;
import com.project.paypass_renewal.repository.LinkRepository;
import com.project.paypass_renewal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepository linkRepository;
    private final UserRepository userRepository;


    public Link saveNewLink(LinkRequestDto linkRequestDto) {

        Link link = toEntity(linkRequestDto);

        linkRepository.save(link);

        return link;
    }

    public boolean checkLinkCodeExist(LinkRequestDto linkRequestDto) {
        String linkCode = linkRequestDto.getUserLinkCode();
        return userRepository.existsByLinkCode(linkCode);
    }

    public boolean checkDuplicateLink(LinkRequestDto linkRequestDto) {
        String linkCode = linkRequestDto.getUserLinkCode();
        User user = userRepository.findByLinkCode(linkCode);

        String supporterNumber = linkRequestDto.getSupporterNumber();
        String userNumber = user.getNumber();

        return linkRepository.existsBySupporterNumberAndUserNumber(supporterNumber, userNumber);
    }

    public List<String> findUserNumbersBySupporterNumber(SupporterNumberRequestDto supporterNumberRequestDto) {
        String supporterNumber = supporterNumberRequestDtoToString(supporterNumberRequestDto);
        return linkRepository.findUserNumbersBySupporterNumber(supporterNumber);
    }

    public List<User> findUserListByNumbers (List<String> userNumbers) {
        return userRepository.findByNumberIn(userNumbers);
    }

    public List<LinkListResponseDto> userToLinkResponseDto(List<User> userList) {
        return userList.stream()
                .map(user -> new LinkListResponseDto(
                        user.getNumber(),
                        user.getName(),
                        user.getHomeAddress()))
                .toList();
    }

    public int deleteLink(LinkDeleteRequestDto linkDeleteRequestDto){
        String supporterNumber = linkDeleteRequestDto.getSupporterNumber();
        String userNumber = linkDeleteRequestDto.getUserNumber();

        return linkRepository.deleteBySupporterNumberAndUserNumber(supporterNumber,userNumber);
    }

    private String supporterNumberRequestDtoToString(SupporterNumberRequestDto supporterNumberRequestDto) {
        return supporterNumberRequestDto.getSupporterNumber();
    }

    private Link toEntity(LinkRequestDto linkRequestDto) {
        String supporterNumber = linkRequestDto.getSupporterNumber();
        String linkCode = linkRequestDto.getUserLinkCode();
        User user = userRepository.findByLinkCode(linkCode);
        String userNumber = user.getNumber();

        return new Link(supporterNumber, userNumber);
    }


}
