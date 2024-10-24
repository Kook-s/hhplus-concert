package hhplus.concert.domain.reservation;


import hhplus.concert.domain.concert.ConcertSchedule;
import hhplus.concert.support.exception.CustomException;
import hhplus.concert.support.exception.ErrorCode;
import hhplus.concert.support.type.ReservationStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
public record Reservation(
        Long id,
        Long concertId,
        Long scheduleId,
        Long seatId,
        Long userId,
        ReservationStatus status,
        LocalDateTime reservationAt
) {
    public static Reservation create(ConcertSchedule schedule, Long seatId, Long userId) {
        return Reservation.builder()
                .concertId(schedule.concertId())
                .scheduleId(schedule.id())
                .seatId(seatId)
                .userId(userId)
                .status(ReservationStatus.PAYMENT_WAITING)
                .reservationAt(LocalDateTime.now())
                .build();
    }

    public void checkValidation(Long userId) {

        if (reservationAt.isBefore(LocalDateTime.now().minusMinutes(5))) {
            throw new CustomException(ErrorCode.PAYMENT_TIMEOUT);
        }

        if (!Objects.equals(userId, userId())) {
            throw new CustomException(ErrorCode.PAYMENT_DIFFERENT_USER);
        }
    }

    public Reservation changeStatus(ReservationStatus status) {
        return Reservation.builder()
                .id(id)
                .concertId(concertId)
                .scheduleId(scheduleId)
                .seatId(seatId)
                .userId(userId)
                .status(status)
                .build();
    }

    public Reservation changeStatus() {
        return Reservation.builder()
                .id(id)
                .concertId(concertId)
                .scheduleId(scheduleId)
                .seatId(seatId)
                .userId(userId)
                .status(ReservationStatus.COMPLETED)
                .build();
    }
}

