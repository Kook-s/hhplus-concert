package hhplus.concert.domain.concert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatReservation {

    private Long id;
    private Long userId;
    private Long concertId;
    private Long scheduleId;
    private Long seatId;
    private String status;
    private ConcertSeat seat;
    private LocalDateTime createAt;
    private Long version;

}
