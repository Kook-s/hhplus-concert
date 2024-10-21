package hhplus.concert.infra.concert.entity;

import hhplus.concert.domain.concert.ConcertSchedule;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name="CONCERT_SCHEDULE")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConcertScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CONCERT_ID", nullable = false)
    private ConcertEntity concert;

    @Column(name="RESERVATION_AT")
    private LocalDateTime reservationAt;

    @Column(name="DEAD_LINE")
    private LocalDateTime deadLine;

    @Column(name="CONCERT_AT")
    private LocalDateTime concertAt;

    public static ConcertSchedule of(ConcertScheduleEntity entity) {
        return ConcertSchedule.builder()
                .id(entity.id)
                .concertId(entity.getConcert().getId())
                .reservationAt(entity.reservationAt)
                .deadLine(entity.deadLine)
                .concertAt(entity.concertAt)
                .build();
    }

    public static ConcertScheduleEntity from(ConcertSchedule schedule) {
        return ConcertScheduleEntity.builder()
                .concert(ConcertEntity.builder().id(schedule.concertId()).build())
                .reservationAt(schedule.reservationAt())
                .deadLine(schedule.deadLine())
                .concertAt(schedule.concertAt())
                .build();
    }
}
