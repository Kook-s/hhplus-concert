package hhplus.concert.infra.queue.entity;

import hhplus.concert.domain.queue.Queue;
import hhplus.concert.infra.user.entity.UserEntity;
import hhplus.concert.support.type.QueueStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "QUEUE")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QueueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "STATUS")
    @Enumerated(value = EnumType.STRING)
    private QueueStatus status;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "ENTERED_AT")
    private LocalDateTime enteredAt;

    @Column(name = "EXPIRED_AT")
    private LocalDateTime expiredAt;

    public static Queue of(QueueEntity entity) {
        return Queue.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .token(entity.getToken())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .enteredAt(entity.getEnteredAt())
                .expiredAt(entity.getExpiredAt())
                .build();
    }

    public QueueEntity from(Queue queue) {
        return QueueEntity.builder()
                .user(UserEntity.builder().id(queue.userId()).build())
                .token(queue.token())
                .status(queue.status())
                .createdAt(queue.createdAt())
                .enteredAt(queue.enteredAt())
                .expiredAt(queue.expiredAt())
                .build();
    }

}
