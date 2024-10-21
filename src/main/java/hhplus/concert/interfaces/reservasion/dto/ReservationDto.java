package hhplus.concert.interfaces.reservasion.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hhplus.concert.application.dto.ReservationCommand;
import hhplus.concert.interfaces.concert.dto.SeatDto;
import hhplus.concert.support.type.ReservationStatus;
import lombok.Builder;

import java.time.LocalDateTime;

public class ReservationDto {

    @Builder
    public record ReservationRequest (
            Long userId,
            Long concertId,
            Long scheduleId,
            Long seatId
    ) {
        public ReservationCommand toCommand(String token) {
            return ReservationCommand.builder()
                    .token(token)
                    .userId(this.userId)
                    .concertId(this.concertId)
                    .scheduleId(this.scheduleId)
                    .seatId(this.seatId)
                    .build();
        }
    }

    @Builder
    public record ReservationResponse (
            Long reservationId,
            Long concertId,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
            LocalDateTime concertAt,
            SeatDto seat,
            ReservationStatus reservationStatus
    ) {
    }
}