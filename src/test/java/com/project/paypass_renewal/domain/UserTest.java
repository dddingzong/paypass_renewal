package com.project.paypass_renewal.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
class UserTest {

    @Test
    @DisplayName("유저_생성자_테스트_모든인자작성")
    void UserCreatorAllVariableTest() {

        // given
        String mainId = "test@gmail.com";
        String name = "정종인";
        LocalDate birth = LocalDate.of(2000, 5, 13);
        String number = "01012345678";
        String homeAddress = "01675";
        String centerAddress = "01747";
        String linkCode = "245863";
        ServiceCode serviceCode = ServiceCode.PROTECT_SERVICE;

        // when
        User user = new User(mainId, name, birth, number, homeAddress, centerAddress, linkCode, serviceCode);

        // then
        assertThat(user.getMainId()).isEqualTo(mainId);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getBirth()).isEqualTo(birth);
        assertThat(user.getNumber()).isEqualTo(number);
        assertThat(user.getHomeAddress()).isEqualTo(homeAddress);
        assertThat(user.getCenterAddress()).isEqualTo(centerAddress);
        assertThat(user.getLinkCode()).isEqualTo(linkCode);
        assertThat(user.getServiceCode()).isEqualTo(serviceCode);
    }

    @Test
    @DisplayName("유저_생성자_테스트_센터주소미작성")
    void UserCreatorWithOutCenterTest() {
        // given
        String mainId = "test@gmail.com";
        String name = "정종인";
        LocalDate birth = LocalDate.of(2000, 5, 13);
        String number = "01012345678";
        String homeAddress = "01675";
        String linkCode = "245863";
        ServiceCode serviceCode = ServiceCode.PROTECT_SERVICE;

        // when
        User user = new User(mainId, name, birth, number, homeAddress, null, linkCode, serviceCode);

        // then
        assertThat(user.getMainId()).isEqualTo(mainId);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getBirth()).isEqualTo(birth);
        assertThat(user.getNumber()).isEqualTo(number);
        assertThat(user.getHomeAddress()).isEqualTo(homeAddress);
        assertThat(user.getCenterAddress()).isNull();
        assertThat(user.getLinkCode()).isEqualTo(linkCode);
        assertThat(user.getServiceCode()).isEqualTo(serviceCode);
    }
}
