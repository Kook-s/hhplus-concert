package hhplus.concert.infra.reservation;

import hhplus.concert.infra.reservation.entity.ReservationEntity;
import hhplus.concert.support.type.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface ReservationJpaRepository extends JpaRepository<ReservationEntity, Long> {
    List<ReservationEntity> findByStatusAndReservationAtBefore(ReservationStatus reservationStatus, LocalDateTime localDateTime);
}
