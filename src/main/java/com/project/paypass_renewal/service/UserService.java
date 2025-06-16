package com.project.paypass_renewal.service;

import com.project.paypass_renewal.domain.ServiceCode;
import com.project.paypass_renewal.domain.User;
import com.project.paypass_renewal.domain.dto.request.UserRequestDto;
import com.project.paypass_renewal.generator.LinkCodeGenerator;
import com.project.paypass_renewal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserAddressService userAddressService;
    private final LinkCodeGenerator linkCodeGenerator;

    public boolean checkDuplicateNumber (String number) {
        return userRepository.existsByNumber(number);
    }

    public User saveNewUser(UserRequestDto userRequestDto) {
        // linkCode 생성
        String linkCode = linkCodeGenerator.generate();

        // linkCode 중복 검사
        String uniqueLinkCode = checkLinkCodeDuplicate(linkCode);

        // User 생성 및 저장
        User user = toEntity(userRequestDto, uniqueLinkCode);

        // UserAddress 생성 및 저장
        userAddressService.saveNewUserAddress(userRequestDto);

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

    private User toEntity(UserRequestDto userRequestDto, String linkCode){
        String name = userRequestDto.getName();
        String password = userRequestDto.getPassword();
        LocalDate birth = userRequestDto.getBirth();
        String number = userRequestDto.getNumber();
        String homeAddress = userRequestDto.getHomeAddress();
        String centerAddress = userRequestDto.getCenterAddress();
        ServiceCode serviceCode = userRequestDto.getServiceCode();

        User user = new User(name, password, birth, number, homeAddress, centerAddress, linkCode, serviceCode);

        return user;
    }

}
