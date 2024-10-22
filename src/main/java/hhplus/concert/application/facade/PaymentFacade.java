package hhplus.concert.application.facade;

import hhplus.concert.domain.balance.Balance;
import hhplus.concert.domain.balance.BalanceService;
import hhplus.concert.domain.concert.ConcertService;
import hhplus.concert.domain.concert.Seat;
import hhplus.concert.domain.payment.Payment;
import hhplus.concert.domain.payment.PaymentService;
import hhplus.concert.domain.queue.QueueService;
import hhplus.concert.domain.reservation.Reservation;
import hhplus.concert.domain.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentFacade {

    private final QueueService queueService;
    private final ReservationService reservationService;
    private final PaymentService paymentService;
    private final BalanceService balanceService;
    private final ConcertService concertService;

    public Payment payment(String token, Long reservationId, Long userId) {

        // 토큰 유효성 검사
        queueService.validateToken(token);

        // 예약 검증 (본인인증, 시간 오버)
        Reservation reservation = reservationService.checkReservation(reservationId, userId);
        Seat seat = concertService.getSeat(reservation.seatId());
        Balance balance = balanceService.getBalance(userId);

        // 잔액 조회
        balanceService.useBalance(balance, seat.seatPrice());

        // 예약 상태 변경
        Reservation reserved = reservationService.changeStatus(reservation);

        // 결재 내역 생성
        return paymentService.createBill(reserved.id(), userId, seat.seatPrice());
    }
}
