package com.project.paypass_renewal.util.algorithm.constants;

import com.project.paypass_renewal.domain.PaypassGeofence;

import java.time.LocalDateTime;
import java.util.List;

public class AlgorithmTestConstants {

    public static final List<PaypassGeofence> BASIC_TEST_LIST = List.of (
            new PaypassGeofence("01089099721", 108000382L, "{100100014,1},{100100017,1},{108000002,1}",LocalDateTime.now().minusMinutes(50)),
            new PaypassGeofence("01089099721", 108000014L, "{100100014,2},{100100017,2},{100100023,2}", LocalDateTime.now().minusMinutes(40)),
            new PaypassGeofence("01089099721", 108000015L, "{100100014,3},{100100017,3},{100100023,3}", LocalDateTime.now().minusMinutes(30))
    );

    public static final List<PaypassGeofence> OPPOSITE_TEST_LIST = List.of(
            new PaypassGeofence("01089099721", 108000382L, "{100100014,1},{100100017,1},{108000002,1}", LocalDateTime.now().minusMinutes(50)),
            new PaypassGeofence("01089099721", 108000016L, "{100100014,63},{100100017,88},{100100023,98}", LocalDateTime.now().minusMinutes(41)),
            new PaypassGeofence("01089099721", 108000014L, "{100100014,2},{100100017,2},{100100023,2}", LocalDateTime.now().minusMinutes(40)),
            new PaypassGeofence("01089099721", 108000015L, "{100100014,3},{100100017,3},{100100023,3}", LocalDateTime.now().minusMinutes(30)),
            new PaypassGeofence("01089099721", 108000018L, "{100100014,5},{100100017,5},{100100023,5}", LocalDateTime.now().minusMinutes(20)),
            new PaypassGeofence("01089099721", 108000019L, "{100100014,6},{100100017,6},{100100023,6}", LocalDateTime.now().minusMinutes(10))
    );

    public static final List<PaypassGeofence> MANY_OPPOSITE_TEST_LIST =  List.of(
            new PaypassGeofence("01089099721", 108000382L, "{100100014,1},{100100017,1},{108000002,1}", LocalDateTime.now().minusMinutes(50)),
            new PaypassGeofence("01089099721", 108000047L, "{100100014,55},{100100023,90}", LocalDateTime.now().minusMinutes(49)),
            new PaypassGeofence("01089099721", 108000014L, "{100100014,2},{100100017,2},{100100023,2}", LocalDateTime.now().minusMinutes(44)),
            new PaypassGeofence("01089099721", 108000069L, "{100100014,54},{100100023,89}", LocalDateTime.now().minusMinutes(43)),
            new PaypassGeofence("01089099721", 108000071L, "{100100014,53},{100100023,88}", LocalDateTime.now().minusMinutes(31)),
            new PaypassGeofence("01089099721", 108000015L, "{100100014,3},{100100017,3},{100100023,3}", LocalDateTime.now().minusMinutes(30)),
            new PaypassGeofence("01089099721", 107000081L, "{100100014,18}", LocalDateTime.now().minusMinutes(20))
    );

    public static final List<PaypassGeofence> MIXED_TEST_LIST =  List.of(
            new PaypassGeofence("01089099721", 108000382L, "{100100014,1},{100100017,1},{108000002,1}", LocalDateTime.now().minusMinutes(60)),
            new PaypassGeofence("01089099721", 108000015L, "{100100014,3},{100100017,3},{100100023,3}", LocalDateTime.now().minusMinutes(55)),
            new PaypassGeofence("01089099721", 107000081L, "{100100014,18}", LocalDateTime.now().minusMinutes(50)),
            new PaypassGeofence("01089099721", 108000017L, "{100100014,4},{100100017,4},{100100023,4}", LocalDateTime.now().minusMinutes(50)),
            new PaypassGeofence("01089099721", 108000018L, "{100100014,5},{100100017,5},{100100023,5}", LocalDateTime.now().minusMinutes(45)),
            new PaypassGeofence("01089099721", 108000046L, "{100100014,10},{100100023,10}", LocalDateTime.now().minusMinutes(40)),
            new PaypassGeofence("01089099721", 108000068L, "{100100014,11},{100100023,11}", LocalDateTime.now().minusMinutes(35)),
            new PaypassGeofence("01089099721", 108000072L, "{100100014,13},{100100023,13}", LocalDateTime.now().minusMinutes(30)),
            new PaypassGeofence("01089099721", 108000074L, "{100100014,14},{100100023,14}", LocalDateTime.now().minusMinutes(25))
    );

    public static final List<PaypassGeofence> DOUBLE_TEST_LIST = List.of(
            new PaypassGeofence("01089099721", 110000183L, "{100100148,10},{100100148,57},{100100150,10},{100100150,51},{100100151,10},{100100151,31},{100100165,38},{100100192,30},{100100193,24},{100100193,57}", LocalDateTime.now().minusMinutes(60)),
            new PaypassGeofence("01089099721", 107000056L, "{100100148,11},{100100148,58},{100100150,11},{100100150,52},{100100151,11},{100100151,32},{100100165,39},{100100179,49}", LocalDateTime.now().minusMinutes(55)),
            new PaypassGeofence("01089099721", 107000057L, "{100100148,12},{100100148,59},{100100150,12},{100100150,53},{100100151,12},{100100151,33},{100100165,40},{100100179,50},{100100192,31},{100100193,25},{100100193,58}", LocalDateTime.now().minusMinutes(50)),
            new PaypassGeofence("01089099721", 110000006L, "{100100148,13},{100100165,41},{100100192,32},{100100193,26},{100100193,59}", LocalDateTime.now().minusMinutes(50)),
            new PaypassGeofence("01089099721", 110000017L, "{100100148,14},{100100165,42}", LocalDateTime.now().minusMinutes(45)),
            new PaypassGeofence("01089099721", 110000058L, "{100100148,17},{100100425,29}", LocalDateTime.now().minusMinutes(40)),
            new PaypassGeofence("01089099721", 110000037L, "{100100148,18},{100100425,30}", LocalDateTime.now().minusMinutes(35)),
            new PaypassGeofence("01089099721", 110000035L, "{100100148,19},{100100425,31}", LocalDateTime.now().minusMinutes(30)),
            new PaypassGeofence("01089099721", 110000033L, "{100100148,20}", LocalDateTime.now().minusMinutes(25))
    );

    public static final List<PaypassGeofence> REVERSE_TEST_LIST = List.of(
             new PaypassGeofence("01089099721", 108000015L, "{100100014,3},{100100017,3},{100100023,3}", LocalDateTime.now().minusMinutes(50)),
             new PaypassGeofence("01089099721", 108000014L, "{100100014,2},{100100017,2},{100100023,2}", LocalDateTime.now().minusMinutes(40)),
             new PaypassGeofence("01089099721", 108000382L, "{100100014,1},{100100017,1},{108000002,1}", LocalDateTime.now().minusMinutes(30))
    );

    public static final List<PaypassGeofence> TIME_NOT_SATISFY_TEST_LIST = List.of (
            new PaypassGeofence("01089099721", 108000382L, "{100100014,1},{100100017,1},{108000002,1}",LocalDateTime.now().minusMinutes(50)),
            new PaypassGeofence("01089099721", 108000014L, "{100100014,2},{100100017,2},{100100023,2}", LocalDateTime.now().minusMinutes(45)),
            new PaypassGeofence("01089099721", 108000015L, "{100100014,3},{100100017,3},{100100023,3}", LocalDateTime.now().minusMinutes(5))
    );

}
