
package hhplus.concert.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class UserPoint {

    private Long id;
    private String userId;
    private int pint;
    public LocalDateTime createAt;
    public LocalDateTime updateAt;

    public Long version;
}
