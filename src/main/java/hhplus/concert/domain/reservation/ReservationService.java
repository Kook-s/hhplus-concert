package hhplus.concert.domain.reservation;

import hhplus.concert.domain.concert.ConcertSchedule;
import hhplus.concert.domain.concert.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public Reservation reservation(ConcertSchedule schedule, Seat seat, Long userId) {
        // 예약 정보 생성
        Reservation reservation = Reservation.create(schedule, seat.id(), userId);

        // 예약 정보 저장
        return reservationRepository.save(reservation);
    }

    public Reservation checkReservation(Long reservationId, Long userId) {

        Reservation reservation = reservationRepository.findById(reservationId);

        // 예약 정보 확인
        reservation.checkValidation(userId);

        return reservation;
    }

    public Reservation changeStatus(Reservation reservation) {
        Reservation changedReservation = reservation.changeStatus();
        return reservationRepository.save(changedReservation);
    }

}
