package hhplus.concert.domain.concert;

import hhplus.concert.support.type.ConcertStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
public record Concert (
    Long id,
    String title,
    String description,
    ConcertStatus status
){
    public boolean checkStatus() {
        return status.equals(ConcertStatus.AVAILABLE);
    }

}
