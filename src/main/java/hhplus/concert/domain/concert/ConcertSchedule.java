package hhplus.concert.domain.concert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class ConcertSchedule {
    private Long id;
    private Long concertId;
    private LocalDateTime openDate;
}
