
package hhplus.concert.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PointHistory {

    @Getter
    private Long id;

    @Getter
    private Long userId;

    @Getter
    private int amount;

    @Getter
    private String type;

    @Getter
    private LocalDateTime createAt;
}
