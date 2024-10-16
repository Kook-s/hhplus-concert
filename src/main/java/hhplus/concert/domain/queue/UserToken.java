package hhplus.concert.domain.queue;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserToken {

    @Getter
    private Long id;

    @Getter
    private Long userId;

    @Getter
    private String accessToken;

    @Getter
    private String status;

    @Getter
    private LocalDateTime createAt;

    @Getter
    private LocalDateTime updateAt;


}
