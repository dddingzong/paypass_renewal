package com.project.paypass_renewal.service;

import com.project.paypass_renewal.domain.ServiceCode;
import com.project.paypass_renewal.domain.User;
import com.project.paypass_renewal.domain.dto.UserDto;
import com.project.paypass_renewal.generator.LinkCodeGenerator;
import com.project.paypass_renewal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final LinkCodeGenerator linkCodeGenerator;

    public boolean checkDuplicateMainId (String mainId) {
        return userRepository.existsByMainId(mainId);
    }

    public User saveNewUser(UserDto userDto) {
        // linkCode 생성
        String linkCode = linkCodeGenerator.generate();

        // linkCode 중복 검사
        String uniqueLinkCode = checkLinkCodeDuplicate(linkCode);

        // User 생성 및 저장
        User user = toEntity(userDto, uniqueLinkCode);

        userRepository.save(user);
        return user;
    }

    private String checkLinkCodeDuplicate(String firstLinkCode){
        String linkCode = firstLinkCode;
        while (userRepository.existsByLinkCode(linkCode)){
            linkCode = linkCodeGenerator.generate();
        }
        return linkCode;
    }

    private User toEntity(UserDto userDto, String linkCode){
        String mainId = userDto.getMainId();
        String name = userDto.getName();
        LocalDate birth = userDto.getBirth();
        String number = userDto.getNumber();
        String homeAddress = userDto.getHomeAddress();
        String centerAddress = userDto.getCenterAddress();
        ServiceCode serviceCode = userDto.getServiceCode();

        User user = new User(mainId, name, birth, number, homeAddress, centerAddress, linkCode, serviceCode);

        return user;
    }

}
