package hhplus.concert.infra.reservation.entity;

import hhplus.concert.domain.reservation.Reservation;
import hhplus.concert.infra.concert.entity.ConcertEntity;
import hhplus.concert.infra.concert.entity.ConcertScheduleEntity;
import hhplus.concert.infra.concert.entity.SeatEntity;
import hhplus.concert.infra.user.entity.UserEntity;
import hhplus.concert.support.type.ReservationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "RESERVATION")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONCERT_ID")
    @Column(name = "CONCERT")
    private ConcertEntity concert;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONCERT_SCHEDULE_ID")
    @Column(name = "SCHEDULE")
    private ConcertScheduleEntity schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SEAT_ID")
    @Column(name = "SEAT")
    private SeatEntity seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @Column(name = "USER")
    private UserEntity user;

    @Column(name = "STATUS")
    private ReservationStatus status;

    @Column(name = "RESERVATION_AT")
    private LocalDateTime reservationAt;

    public static ReservationEntity from(Reservation reservation) {
        return ReservationEntity.builder()
                .id(reservation.id())
                .concert(ConcertEntity.builder().id(reservation.concertId()).build())
                .schedule(ConcertScheduleEntity.builder().id(reservation.scheduleId()).build())
                .seat(SeatEntity.builder().id(reservation.seatId()).build())
                .user(UserEntity.builder().id(reservation.userId()).build())
                .status(reservation.status())
                .reservationAt(reservation.reservationAt())
                .build();
    }

    public static Reservation of(ReservationEntity entity) {
        return Reservation.builder()
                .id(entity.id)
                .concertId(entity.getConcert().getId())
                .scheduleId(entity.getSchedule().getId())
                .seatId(entity.getSeat().getId())
                .userId(entity.getUser().getId())
                .status(entity.status)
                .reservationAt(entity.getReservationAt())
                .build();
    }
}
