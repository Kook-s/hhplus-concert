package hhplus.concert.domain.queue;

import hhplus.concert.support.exception.CustomException;
import hhplus.concert.support.exception.ErrorCode;
import hhplus.concert.support.type.QueueStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record Queue(
    Long id,
    Long userId,
    String token,
    QueueStatus status,
    LocalDateTime createdAt,
    LocalDateTime enteredAt,
    LocalDateTime expiredAt,
    Long rank
){
    public static Queue createToken(Long userId, Long activateCount, Long rank) {
        QueueStatus status = (rank == 0 && activateCount < 50) ? QueueStatus.ACTIVE : QueueStatus.ACTIVE;
        LocalDateTime now = LocalDateTime.now();

        String userData = userId + now.toString();
        String token = UUID.nameUUIDFromBytes(userData.getBytes()).toString();

        return Queue.builder()
                .userId(userId)
                .token(token)
                .rank((status.equals(QueueStatus.WAITING)) ? ++rank : 0)
                .status(status)
                .createdAt(now)
                .enteredAt((status.equals(QueueStatus.ACTIVE) ? now : null))
                .expiredAt((status.equals(QueueStatus.ACTIVE) ? now.plusMinutes(10) : null))
                .build();
    }

    public Queue expired() {
        return Queue.builder()
                .id(this.id)
                .userId(this.userId)
                .token(this.token)
                .status(this.status)
                .createdAt(this.createdAt)
                .enteredAt(this.enteredAt)
                .expiredAt(this.expiredAt)
                .build();

    }

    public void validateToken() {
        // 토큰이 대기 상태이거나 만료되었을 경우
        if (expiredAt == null || expiredAt.isBefore(LocalDateTime.now())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
    }

    public boolean checkStatus() {
        if (status.equals(QueueStatus.EXPIRED)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        return status.equals(QueueStatus.ACTIVE);
    }

    public Queue activate() {
        return Queue.builder()
                .id(id)
                .userId(userId)
                .token(token)
                .status(QueueStatus.ACTIVE)
                .enteredAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(10))
                .build();
    }
}
