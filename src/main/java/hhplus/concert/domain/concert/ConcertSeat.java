package hhplus.concert.domain.concert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ConcertSeat {

    private Long id;
    private Long scheduleId;
    private int seatNum;

}
