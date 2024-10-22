package hhplus.concert.application.facade;

import hhplus.concert.application.dto.ReservationCommand;
import hhplus.concert.application.dto.ReservationResponse;
import hhplus.concert.domain.concert.Concert;
import hhplus.concert.domain.concert.ConcertSchedule;
import hhplus.concert.domain.concert.ConcertService;
import hhplus.concert.domain.concert.Seat;
import hhplus.concert.domain.queue.QueueService;
import hhplus.concert.domain.reservation.Reservation;
import hhplus.concert.domain.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationFacade {

    private final QueueService queueService;
    private final ConcertService concertService;
    private final ReservationService reservationService;

    public ReservationResponse reservation(ReservationCommand command) {

        // 토큰 유효성 검증
        queueService.validateToken(command.token());

        // 콘서트 상태 조회
        ConcertSchedule schedule = concertService.scheduleInfo(command.scheduleId());
        Seat seat = concertService.getSeat(command.seatId());

        // 예약 가능 상태 확인
        concertService.isAvailableReservation(schedule, seat);

        // 에약 정보 저장
        Reservation reservation = reservationService.reservation(schedule, seat, command.userId());

        // 좌서 점유
        concertService.assignmentSeat(seat);

        return ReservationResponse.builder()
                .reservationId(reservation.id())
                .concertId(schedule.concertId())
                .concertAt(schedule.concertAt())
                .seat(Seat.builder().id(seat.id()).seatNo(seat.seatNo()).seatPrice(seat.seatPrice()).build())
                .status(reservation.status())
                .build();
    }
}
