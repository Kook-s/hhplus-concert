package hhplus.concert.infra.concert.entity;

import hhplus.concert.domain.concert.Seat;
import hhplus.concert.support.type.SeatStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name="SEAT")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONCERT_SCHEDULE_ID", nullable = false)
    private ConcertScheduleEntity concertSchedule;

    @Column(name="SEAT_NO")
    private int seatNo;

    @Column(name="STATUS", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private SeatStatus status;

    @Column(name = "RESERVATION_AT")
    private LocalDateTime reservationAt;

    @Column(name = "SEAT_PRICE")
    private int seatPrice;

    public static Seat of(SeatEntity entity) {
        return Seat.builder()
                .id(entity.getId())
                .concertScheduleId(entity.getConcertSchedule().getId())
                .seatNo(entity.getSeatNo())
                .reservationAt(entity.getReservationAt())
                .seatPrice(entity.getSeatPrice())
                .build();
    }

    public static SeatEntity from(Seat seat) {
        return SeatEntity.builder()
                .id(seat.id())
                .concertSchedule(ConcertScheduleEntity.builder().id(seat.id()).build())
                .seatNo(seat.seatNo())
                .reservationAt(seat.reservationAt())
                .seatPrice(seat.seatPrice())
                .build();
    }

}
