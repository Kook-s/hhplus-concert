package hhplus.concert.application.facade;

import hhplus.concert.domain.queue.Queue;
import hhplus.concert.domain.queue.QueueService;
import hhplus.concert.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class QueueFacade {

    private  final UserService userService;
    private final QueueService queueService;

    @Transactional
    public Queue createToken(Long userId) {

        // 유저 토큰 유무
        userService.existsUser(userId);

        // 토큰 유무 확인
        Queue token = queueService.getToken(userId);

        if(token != null) {
            // 기존이 있던 토근 만료 처리
            queueService.expireToken(token);
        }
        // 토큰이 없거나 만료되었을 때 새로운 생성
        return queueService.createToken(userId);
    }

    // 대기열 상태 조회
    public Queue getStatus(String token, Long userId) {
        //유저 유무 확인
        userService.existsUser(userId);
        // 토큰 유무 확인
        Queue queue = queueService.getToken(token);
        // 대기열 상태 확인
        return queueService.checkQueueStatus(queue);
    }
}
