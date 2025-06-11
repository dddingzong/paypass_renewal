package com.project.paypass_renewal.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Link {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String supporterNumber;

    @Column(nullable = false)
    private String userNumber;

    public Link(String supporterNumber, String userNumber) {
        this.supporterNumber = supporterNumber;
        this.userNumber = userNumber;
    }
}
