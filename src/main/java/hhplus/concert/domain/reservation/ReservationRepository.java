package hhplus.concert.domain.reservation;

public interface ReservationRepository {
    Reservation save(Reservation reservation);

    Reservation findById(Long aLong);
}
