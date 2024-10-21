package hhplus.concert.domain.payment;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Getter
    private Long id;

    @Getter
    private Long userId;

    @Getter
    private Long concertId;

    @Getter
    private String concertTitle;

    @Getter
    private Long scheduleId;

    @Getter
    private Long seatId;

    @Getter
    private int price;

    @Getter
    private String status;

    @Getter
    public LocalDateTime createdAt;

    @Getter
    public LocalDateTime updatedAt;

    @Getter
    private SeatReservation seatReservation;
}
