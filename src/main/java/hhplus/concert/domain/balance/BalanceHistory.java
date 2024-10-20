
package hhplus.concert.domain.balance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BalanceHistory {

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
