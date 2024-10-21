package hhplus.concert.domain.concert;

import hhplus.concert.support.exception.CustomException;
import hhplus.concert.support.exception.ErrorCode;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ConcertSchedule(
        Long id,
        Long concertId,
        LocalDateTime reservationAt,
        LocalDateTime deadLine,
        LocalDateTime concertAt
) {
    public void checkStatus() {
        if(reservationAt.isAfter(LocalDateTime.now())) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        if(deadLine().isBefore(LocalDateTime.now())) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
