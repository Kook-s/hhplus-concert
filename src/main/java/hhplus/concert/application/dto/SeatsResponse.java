package hhplus.concert.application.dto;

import hhplus.concert.domain.concert.Seat;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record SeatsResponse(
        Long scheduleId,
        Long concertId,
        LocalDateTime concertAt,
        List<Seat> seats
) {
}
