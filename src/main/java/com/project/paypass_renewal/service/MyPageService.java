package com.project.paypass_renewal.service;

import com.project.paypass_renewal.domain.User;
import com.project.paypass_renewal.domain.UserAddress;
import com.project.paypass_renewal.domain.dto.request.NumberRequestDto;
import com.project.paypass_renewal.domain.dto.response.MyPageResponseDto;
import com.project.paypass_renewal.repository.UserAddressRepository;
import com.project.paypass_renewal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;

    public MyPageResponseDto getMyPageData(NumberRequestDto numberRequestDto) {

        String number = numberRequestDto.getNumber();

        User user = userRepository.findByNumber(number);
        UserAddress userAddress = userAddressRepository.findByNumber(number);

        MyPageResponseDto myPageResponseDto = userAndAddressToEntity(user, userAddress);

        return myPageResponseDto;
    }

    private MyPageResponseDto userAndAddressToEntity(User user, UserAddress userAddress) {
        return new MyPageResponseDto(
                user.getName(),
                user.getNumber(),
                userAddress.getHomeStreetAddress(),
                userAddress.getHomeStreetAddressDetail(),
                userAddress.getCenterStreetAddress(),
                user.getLinkCode()
        );
    }

}
