package com.likelion.lionlib.service;

import com.likelion.lionlib.domain.Book;
import com.likelion.lionlib.domain.Member;
import com.likelion.lionlib.domain.Reservation;
import com.likelion.lionlib.dto.ReservationRequest;
import com.likelion.lionlib.dto.ReservationResponse;
import com.likelion.lionlib.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    private final GlobalService globalService;

    public ReservationResponse addReservation(Long memberId, ReservationRequest reservationRequest) {
        Member member = globalService.findMemberById(memberId);
        Book book = globalService.findBookById(reservationRequest.getBookId());
        Reservation savedReservation = Reservation.builder()
                .member(member)
                .book(book)
                .build();
        reservationRepository.save(savedReservation);
        return ReservationResponse.fromEntity(savedReservation);
    }

    public ReservationResponse getReservation(Long reservationId) {
        Reservation reservation = findReservationById(reservationId);
        return ReservationResponse.fromEntity(reservation);
    }

    public void deleteReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    public List<ReservationResponse> getReservationsByMemberId(Long memberId) {
        List<Reservation> reservations = findReservationsByMemberId(memberId);
        return reservations.stream()
                .map(ReservationResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public Map<String, Long> getReservationsByBookId(Long bookId) {
        Long count = findResrvationsByBookId(bookId);
        return Map.of("count", count);
    }

    private Reservation findReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("reservation not found"));
    }

    private List<Reservation> findReservationsByMemberId(Long memberId) {
        Member member = globalService.findMemberById(memberId);
        return reservationRepository.findAllByMember(member);
    }

    private Long findResrvationsByBookId(Long bookId) {
        Book book = globalService.findBookById(bookId);
        return reservationRepository.countByBook(book);
    }
}
