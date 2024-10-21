package hhplus.concert.infra.reservation;

import hhplus.concert.domain.reservation.Reservation;
import hhplus.concert.domain.reservation.ReservationRepository;
import hhplus.concert.infra.reservation.entity.ReservationEntity;
import hhplus.concert.support.exception.CustomException;
import hhplus.concert.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJpaRepository reservationJpaRepository;


    @Override
    public Reservation save(Reservation reservation) {
        return ReservationEntity.of(reservationJpaRepository.save(ReservationEntity.from(reservation)));
    }

    @Override
    public Reservation findById(Long id) {
        return reservationJpaRepository.findById(id)
                .map(ReservationEntity::of)
                .orElseThrow(() -> new CustomException(ErrorCode.RESERVATION_NOT_FOUND));
    }
}
