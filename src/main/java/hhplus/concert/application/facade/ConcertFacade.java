package hhplus.concert.application.facade;

import hhplus.concert.application.dto.SeatsResponse;
import hhplus.concert.domain.concert.*;
import hhplus.concert.domain.queue.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConcertFacade {

    private final ConcertService concertService;
    private final QueueService queueService;

    public List<Concert> getConcert(String token) {
        queueService.validateToken(token);
        return concertService.getConcerts();
    }

    public List<ConcertSchedule> getConcertSchedule(String token, Long concertId) {
        queueService.validateToken(token);
        return concertService.getConcertSchedules(concertId);
    }

    public SeatsResponse getSeats(String token, Long concertId, Long scheduleId) {
        queueService.validateToken(token);
        ConcertSchedule schedule = concertService.scheduleInfo(scheduleId);
        List<Seat> seats = concertService.getSeats(concertId, scheduleId);

        return SeatsResponse.builder()
                .scheduleId(schedule.id())
                .concertId(schedule.concertId())
                .concertAt(schedule.concertAt())
                .seats(seats)
                .build();
    }

}
