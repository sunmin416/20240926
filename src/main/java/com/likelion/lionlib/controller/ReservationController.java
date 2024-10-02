package com.likelion.lionlib.controller;

import com.likelion.lionlib.dto.CountReservationResponse;
import com.likelion.lionlib.dto.CustomUserDetails;
import com.likelion.lionlib.dto.ReservationRequest;
import com.likelion.lionlib.dto.ReservationResponse;
import com.likelion.lionlib.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReservationController {
    private final ReservationService reservationService;
    // 도서 예약 등록
    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponse> addReservation(Authentication authentication, @RequestBody ReservationRequest reservationRequest) {
        Long memberId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        log.info("Request POST a reservation: {}", reservationRequest);
        ReservationResponse savedReservation = reservationService.addReservation(memberId, reservationRequest);
        log.info("Response POST a reservation: {}", savedReservation);
        return ResponseEntity.ok(savedReservation);
    }

    // 예약 정보 조회
    @GetMapping("/reservations/{reservationId}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable Long reservationId) {
        log.info("Request GET a reservation: {}", reservationId);
        ReservationResponse reservation = reservationService.getReservation(reservationId);
        log.info("Response GET a reservation: {}", reservation);
        return ResponseEntity.ok(reservation);
    }

    // 예약 취소
    @DeleteMapping("/reservations/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) {
        log.info("Request DELETE a reservation: {}", reservationId);
        reservationService.deleteReservation(reservationId);
        log.info("Response DELETE a reservation: {}", reservationId);
        return ResponseEntity.noContent().build();
    }

    // 사용자 예약 목록 조회
    @GetMapping("/members/reservations")
    public ResponseEntity<List<ReservationResponse>> getReservationsByMemberId(Authentication authentication) {
        Long memberId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        log.info("Request GET reservations for member with ID: {}", memberId);
        List<ReservationResponse> reservations = reservationService.getReservationsByMemberId(memberId);
        log.info("Response GET reservations for member: {}", reservations);
        return ResponseEntity.ok(reservations);
    }

    // 도서 예약 수 현황 조회
    @GetMapping("/books/{bookId}/reservations")
    public Map<String, Long> getReservationsByBookId(@PathVariable Long bookId){
        log.info("Request GET reservations for book with ID: {}", bookId);
        Map<String, Long> counts = reservationService.getReservationsByBookId(bookId);
        return counts;
    }
}
