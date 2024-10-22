package hhplus.concert.domain.queue;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueueService {

    private final QueueRepository queueRepository;

    // 특정 사용자 ID에 대한 대기열 토큰을 조회
    public Queue getToken(Long userId) {
        return queueRepository.findQueue(userId);
    }

    // 특정 토큰 값을 이용해 대기열 정보를 조회
    public Queue getToken(String token) {
        return queueRepository.findQueue(token);
    }

    // 새로운 대기열 토큰을 생성
    public Queue createToken(Long userId) {

        // 활성화된 토큰 개수를 검색하여 현재 대기열 상황을 파악
        Long activateCount = queueRepository.findActiveCount();

        // 대기 순번을 조회
        Long rank = queueRepository.findCurrentRank();

        // 토큰을 생성하는 로직을 통해 Queue 객체를 생성
        Queue token = Queue.createToken(userId, activateCount, rank);

        // 저장 후 새로 생성된 토큰을 반환
        return queueRepository.save(token);
    }

    public void expireToken(Queue token) {
        Queue expiredToken = token.expired();
        queueRepository.expireToken(expiredToken);
    }

    public Queue checkQueueStatus(Queue queue) {

        // 대기열 상태를 검증. 이때 만료된 토근 사용시 에러 반환
        boolean activated = queue.checkStatus();

        if (activated) {
            return queue;
        }

        Long rank = queueRepository.findUserRank(queue.id());

        return Queue.builder()
                .createdAt(queue.createdAt())
                .status(queue.status())
                .rank(queue.rank())
                .build();
    }

    public Queue validateToken(String token) {
        Queue queue = queueRepository.findQueue(token);
        queue.validateToken();
        return queue;
    }
}
