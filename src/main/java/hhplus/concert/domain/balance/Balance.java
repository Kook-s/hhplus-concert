
package hhplus.concert.domain.balance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class Balance {

    private Long id;
    private Long userId;
    private int point;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public Long version;

    public Balance(Long id, Long userId, int point) {
        this.id = id;
        this.userId = userId;
        this.point = point;
    }

    public Balance(Long id, Long userId, int point, long version) {
        this.id = id;
        this.userId = userId;
        this.point = point;
        this.version = version;
    }

    public void plusPoint(int addPoint) {
        this.point += addPoint;
    }

    public void minusPoint(int minPoint) {
        if (this.point - minPoint < 0) {

        }
        this.point = this.point - minPoint;
    }
}
