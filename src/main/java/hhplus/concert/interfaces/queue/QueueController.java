package hhplus.concert.interfaces.queue;

import hhplus.concert.application.facade.QueueFacade;
import hhplus.concert.domain.queue.Queue;
import hhplus.concert.interfaces.queue.dto.QueueDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/queue")
@RequiredArgsConstructor
public class QueueController {

    private final QueueFacade queueFacade;

    /**
     * Queue 에 등록하고, Token 을 발급한다.
     * @param request
     * @return
     */
    @PostMapping("/tokens")
    public ResponseEntity<QueueDto.QueueResponse> createToken(@RequestBody QueueDto.QueueRequest request) {
        Queue token = queueFacade.createToken(request.userid());

        return ResponseEntity.status(HttpStatus.CREATED).body(
                QueueDto.QueueResponse.builder()
                        .token(token.token())
                        .createdAt(token.createdAt())
                        .rank(token.rank())
                        .build()
        );
    }

    /**
     * Queue(대기열) 상태를 조회한다.
     * @param token 발급받은 토큰
     * @param userId 사용자 ID
     * @return 대기열 상태 dto
     */
    @GetMapping("/status")
    public ResponseEntity<QueueDto.QueueResponse> getStatus(
            @RequestHeader("Token") String token,
            @RequestHeader("User-Id") Long userId
    ) {
        Queue queue = queueFacade.getStatus(token, userId);
        return ResponseEntity.ok(
                QueueDto.QueueResponse.builder()
                        .status(queue.status())
                        .rank(queue.rank())
                        .enteredAt(queue.enteredAt())
                        .expiredAt(queue.expiredAt())
                        .build()
        );
    }
}
