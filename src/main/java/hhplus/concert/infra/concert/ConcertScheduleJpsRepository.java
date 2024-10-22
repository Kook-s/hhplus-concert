package hhplus.concert.infra.concert;

import hhplus.concert.infra.concert.entity.ConcertScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConcertScheduleJpsRepository extends JpaRepository<ConcertScheduleEntity, Long> {
    List<ConcertScheduleEntity> findByConcertIdAndReservationAtBeforeAndDeadLineAfter(Long concertId, LocalDateTime now, LocalDateTime now1);
}
