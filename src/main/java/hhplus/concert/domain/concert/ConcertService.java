package hhplus.concert.domain.concert;

import hhplus.concert.support.type.SeatStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcertService {

    private final ConcertRepository concertRepository;

     public List<Concert> getConcerts() {
         return concertRepository.findConcerts();
     }

    public List<ConcertSchedule> getConcertSchedules(Long concertId) {
        Concert concert = concertRepository.findConcert(concertId);

        if (!concert.checkStatus()) {
            return null;
        }

        return concertRepository.findConcertSchedules(concertId);
    }

    public List<Seat> getSeats(Long concertId, Long scheduleId) {
        return concertRepository.findSeats(concertId, scheduleId, SeatStatus.AVAILABLE);
    }

    public ConcertSchedule scheduleInfo(Long scheduleId) {
        return concertRepository.findConcertSchedule(scheduleId);
    }

    public void isAvailableReservation(ConcertSchedule schedule, Seat seat) {
         // 예약 가능 상태인지 확인
         schedule.checkStatus();

         //예약 가능 좌석인지 확인
         seat.checkStatus();
    }

    public Seat getSeat(Long seatId) {
         return concertRepository.findSeat(seatId);
    }

    public void assignmentSeat(Seat seat) {
        Seat assignment = seat.assign();
        concertRepository.saveSeat(assignment);
    }
}
