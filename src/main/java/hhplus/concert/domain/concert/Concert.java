package hhplus.concert.domain.concert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Concert {
    private Long id;
    private String title;
    private String content;
    private int price;
}
