package hhplus.concert.application.dto;

import hhplus.concert.domain.concert.Seat;
import hhplus.concert.support.type.ReservationStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReservationResponse(

        Long reservationId,
        Long concertId,
        LocalDateTime concertAt,
        Seat seat,
        ReservationStatus status
) {
}
