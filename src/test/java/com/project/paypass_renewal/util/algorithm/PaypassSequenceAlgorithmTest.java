package com.project.paypass_renewal.util.algorithm;


import com.project.paypass_renewal.domain.PaypassGeofence;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PaypassSequenceAlgorithmTest {

    @Autowired
    private PaypassSequenceAlgorithm paypassSequenceAlgorithm;

    @Test
    @DisplayName("PaypassSequenceAlgorithm_테스트_간단한_순차")
    void paypassSequenceAlgorithmBasicTest() {
        // given
        PaypassGeofence paypassGeofenceOne = new PaypassGeofence("01089099721", 108000382L, "{100100014,1},{100100017,1},{108000002,1}", LocalDateTime.now().minusMinutes(50));
        PaypassGeofence paypassGeofenceTwo = new PaypassGeofence("01089099721", 108000014L, "{100100014,2},{100100017,2},{100100023,2}", LocalDateTime.now().minusMinutes(40));
        PaypassGeofence paypassGeofenceThree = new PaypassGeofence("01089099721", 108000015L, "{100100014,3},{100100017,3},{100100023,3}", LocalDateTime.now().minusMinutes(30));

        List<PaypassGeofence> paypassGeofenceList = List.of(paypassGeofenceOne, paypassGeofenceTwo, paypassGeofenceThree);
        // when
        Map<String, List<Long>> sequenceGeofenceMap = paypassSequenceAlgorithm.algorithmStart(paypassGeofenceList);

        // then
        assertThat(sequenceGeofenceMap).isNotEmpty();
        assertThat(sequenceGeofenceMap.get("100100014_1")).isEqualTo(List.of(1L,2L,3L));
        assertThat(sequenceGeofenceMap.get("100100017_1")).isEqualTo(List.of(1L,2L,3L));
        assertThat(sequenceGeofenceMap.get("100100023_1")).isEqualTo(List.of(2L,3L));
    }

    @Test
    @DisplayName("PaypassSequenceAlgorithm_테스트_건너편_정류장")
    void paypassSequenceAlgorithmOppositeTest() {
        // given
        PaypassGeofence paypassGeofenceOne = new PaypassGeofence("01089099721", 108000382L, "{100100014,1},{100100017,1},{108000002,1}", LocalDateTime.now().minusMinutes(50));
        PaypassGeofence paypassGeofenceTwo = new PaypassGeofence("01089099721", 108000016L, "{100100014,63},{100100017,88},{100100023,98}", LocalDateTime.now().minusMinutes(41));
        PaypassGeofence paypassGeofenceThree = new PaypassGeofence("01089099721", 108000014L, "{100100014,2},{100100017,2},{100100023,2}", LocalDateTime.now().minusMinutes(40));
        PaypassGeofence paypassGeofenceFour = new PaypassGeofence("01089099721", 108000015L, "{100100014,3},{100100017,3},{100100023,3}", LocalDateTime.now().minusMinutes(30));
        PaypassGeofence paypassGeofenceFive = new PaypassGeofence("01089099721", 108000018L, "{100100014,5},{100100017,5},{100100023,5}", LocalDateTime.now().minusMinutes(20));
        PaypassGeofence paypassGeofenceSix = new PaypassGeofence("01089099721", 108000019L, "{100100014,6},{100100017,6},{100100023,6}", LocalDateTime.now().minusMinutes(10));

        List<PaypassGeofence> paypassGeofenceList = List.of(paypassGeofenceOne, paypassGeofenceTwo, paypassGeofenceThree, paypassGeofenceFour, paypassGeofenceFive, paypassGeofenceSix);
        // when
        Map<String, List<Long>> sequenceGeofenceMap = paypassSequenceAlgorithm.algorithmStart(paypassGeofenceList);

        // then
        assertThat(sequenceGeofenceMap).isNotEmpty();
        assertThat(sequenceGeofenceMap.get("100100014_1")).isEqualTo(List.of(1L,2L,3L));
        assertThat(sequenceGeofenceMap.get("100100014_2")).isEqualTo(List.of(5L,6L));
        assertThat(sequenceGeofenceMap.get("100100017_1")).isEqualTo(List.of(1L,2L,3L));
        assertThat(sequenceGeofenceMap.get("100100017_2")).isEqualTo(List.of(5L, 6L));
        assertThat(sequenceGeofenceMap.get("100100023_1")).isEqualTo(List.of(2L,3L));
        assertThat(sequenceGeofenceMap.get("100100023_2")).isEqualTo(List.of(5L, 6L));
    }

    @Test
    @DisplayName("PaypassSequenceAlgorithm_테스트_역순")
    void paypassSequenceAlgorithmReverseTest() {
        // given
        PaypassGeofence paypassGeofenceThree = new PaypassGeofence("01089099721", 108000015L, "{100100014,3},{100100017,3},{100100023,3}", LocalDateTime.now().minusMinutes(50));
        PaypassGeofence paypassGeofenceTwo = new PaypassGeofence("01089099721", 108000014L, "{100100014,2},{100100017,2},{100100023,2}", LocalDateTime.now().minusMinutes(40));
        PaypassGeofence paypassGeofenceOne = new PaypassGeofence("01089099721", 108000382L, "{100100014,1},{100100017,1},{108000002,1}", LocalDateTime.now().minusMinutes(30));

        List<PaypassGeofence> paypassGeofenceList = List.of(paypassGeofenceOne, paypassGeofenceTwo, paypassGeofenceThree);
        // when
        Map<String, List<Long>> sequenceGeofenceMap = paypassSequenceAlgorithm.algorithmStart(paypassGeofenceList);

        // then
        assertThat(sequenceGeofenceMap).isEmpty();
    }

    @Test
    @DisplayName("PaypassSequenceAlgorithm_테스트_건너편_정류장_다수")
    void paypassSequenceAlgorithmManyOppositeStationTest() {
        // given
        PaypassGeofence paypassGeofenceOne = new PaypassGeofence("01089099721", 108000014L, "{100100014,1}", LocalDateTime.now().minusMinutes(50));
        PaypassGeofence paypassGeofenceTwo = new PaypassGeofence("01089099721", 108000014L, "{100100014,55}", LocalDateTime.now().minusMinutes(49));
        PaypassGeofence paypassGeofenceThree = new PaypassGeofence("01089099721", 108000014L, "{100100014,2}", LocalDateTime.now().minusMinutes(44));
        PaypassGeofence paypassGeofenceFour = new PaypassGeofence("01089099721", 108000014L, "{100100014,54}", LocalDateTime.now().minusMinutes(43));
        PaypassGeofence paypassGeofenceFive = new PaypassGeofence("01089099721", 108000014L, "{100100014,53}", LocalDateTime.now().minusMinutes(31));
        PaypassGeofence paypassGeofenceSix = new PaypassGeofence("01089099721", 108000014L, "{100100014,3}", LocalDateTime.now().minusMinutes(30));
        PaypassGeofence paypassGeofenceSeven = new PaypassGeofence("01089099721", 108000014L, "{100100014,4}", LocalDateTime.now().minusMinutes(20));

        List<PaypassGeofence> paypassGeofenceList = List.of(paypassGeofenceOne, paypassGeofenceTwo, paypassGeofenceThree, paypassGeofenceFour, paypassGeofenceFive, paypassGeofenceSix, paypassGeofenceSeven);
        // when
        Map<String, List<Long>> sequenceGeofenceMap = paypassSequenceAlgorithm.algorithmStart(paypassGeofenceList);

        // then
        assertThat(sequenceGeofenceMap).isNotEmpty();
        assertThat(sequenceGeofenceMap.get("100100014_1")).isEqualTo(List.of(1L, 2L, 3L, 4L));
    }

    @Test
    @DisplayName("PaypassSequenceAlgorithm_테스트_복잡한_혼합")
    void paypassSequenceAlgorithmMixedTest() {
        PaypassGeofence geofenceSeq1 = new PaypassGeofence("010", 0L, "{100100014,1}", LocalDateTime.now().minusMinutes(60));
        PaypassGeofence geofenceSeq2 = new PaypassGeofence("010", 0L, "{100100014,3}", LocalDateTime.now().minusMinutes(55));
        PaypassGeofence geofenceSeq3 = new PaypassGeofence("010", 0L, "{100100014,18}", LocalDateTime.now().minusMinutes(50));
        PaypassGeofence geofenceSeq4 = new PaypassGeofence("010", 0L, "{100100014,4}", LocalDateTime.now().minusMinutes(50));
        PaypassGeofence geofenceSeq5 = new PaypassGeofence("010", 0L, "{100100014,5}", LocalDateTime.now().minusMinutes(45));
        PaypassGeofence geofenceSeq6 = new PaypassGeofence("010", 0L, "{100100014,10}", LocalDateTime.now().minusMinutes(40));
        PaypassGeofence geofenceSeq7 = new PaypassGeofence("010", 0L, "{100100014,11}", LocalDateTime.now().minusMinutes(35));
        PaypassGeofence geofenceSeq8 = new PaypassGeofence("010", 0L, "{100100014,13}", LocalDateTime.now().minusMinutes(30));
        PaypassGeofence geofenceSeq9 = new PaypassGeofence("010", 0L, "{100100014,14}", LocalDateTime.now().minusMinutes(25));

        List<PaypassGeofence> list = List.of(
                geofenceSeq1, geofenceSeq2, geofenceSeq3, geofenceSeq4,
                geofenceSeq5, geofenceSeq6, geofenceSeq7, geofenceSeq8, geofenceSeq9
        );
        Map<String, List<Long>> result = paypassSequenceAlgorithm.algorithmStart(list);

        assertThat(result.get("100100014_1")).isEqualTo(List.of(3L, 4L, 5L));
        assertThat(result.get("100100014_2")).isEqualTo(List.of(10L, 11L));
        assertThat(result.get("100100014_3")).isEqualTo(List.of(13L, 14L));
    }

    @Test
    @DisplayName("PaypassSequenceAlgorithm_테스트_복수정류장_혼합")
    void paypassSequenceAlgorithmDoubleTest() {
        PaypassGeofence geofenceSeq1 = new PaypassGeofence("010", 0L, "{100100148,10},{100100148,57},{100100150,10},{100100150,51},{100100151,10},{100100151,31},{100100165,38},{100100192,30},{100100193,24},{100100193,57}", LocalDateTime.now().minusMinutes(60));
        PaypassGeofence geofenceSeq2 = new PaypassGeofence("010", 0L, "{100100148,11},{100100148,58},{100100150,11},{100100150,52},{100100151,11},{100100151,32},{100100165,39},{100100179,49}", LocalDateTime.now().minusMinutes(55));
        PaypassGeofence geofenceSeq3 = new PaypassGeofence("010", 0L, "{100100148,12},{100100148,59},{100100150,12},{100100150,53},{100100151,12},{100100151,33},{100100165,40},{100100179,50},{100100192,31},{100100193,25},{100100193,58}", LocalDateTime.now().minusMinutes(50));
        PaypassGeofence geofenceSeq4 = new PaypassGeofence("010", 0L, "{100100148,13},{100100165,41},{100100192,32},{100100193,26},{100100193,59}", LocalDateTime.now().minusMinutes(50));
        PaypassGeofence geofenceSeq5 = new PaypassGeofence("010", 0L, "{100100148,14},{100100165,42}", LocalDateTime.now().minusMinutes(45));
        PaypassGeofence geofenceSeq6 = new PaypassGeofence("010", 0L, "{100100148,17},{100100425,29}", LocalDateTime.now().minusMinutes(40));
        PaypassGeofence geofenceSeq7 = new PaypassGeofence("010", 0L, "{100100148,18},{100100425,30}", LocalDateTime.now().minusMinutes(35));
        PaypassGeofence geofenceSeq8 = new PaypassGeofence("010", 0L, "{100100148,19},{100100425,31}", LocalDateTime.now().minusMinutes(30));
        PaypassGeofence geofenceSeq9 = new PaypassGeofence("010", 0L, "{100100148,20}", LocalDateTime.now().minusMinutes(25));

        List<PaypassGeofence> list = List.of(
                geofenceSeq1, geofenceSeq2, geofenceSeq3, geofenceSeq4,
                geofenceSeq5, geofenceSeq6, geofenceSeq7, geofenceSeq8, geofenceSeq9
        );
        Map<String, List<Long>> result = paypassSequenceAlgorithm.algorithmStart(list);

        assertThat(result.get("100100148_1")).isEqualTo(List.of(10L, 11L, 12L, 13L, 14L));
        assertThat(result.get("100100148_2")).isEqualTo(List.of(17L, 18L, 19L, 20L));
        assertThat(result.get("100100150_1")).isEqualTo(List.of(10L, 11L, 12L));
        assertThat(result.get("100100151_1")).isEqualTo(List.of(10L, 11L, 12L));
        assertThat(result.get("100100165_1")).isEqualTo(List.of(38L, 39L, 40L, 41L, 42L));
        assertThat(result.get("100100179_1")).isEqualTo(List.of(49L, 50L));
        assertThat(result.get("100100192_1")).isEqualTo(List.of(30L, 31L, 32L));
        assertThat(result.get("100100193_1")).isEqualTo(List.of(24L, 25L, 26L));
        assertThat(result.get("100100425_1")).isEqualTo(List.of(29L, 30L, 31L));

    }



}
