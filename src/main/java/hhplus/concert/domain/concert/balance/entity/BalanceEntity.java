package hhplus.concert.domain.concert.balance.entity;


import hhplus.concert.domain.balance.Balance;
import hhplus.concert.infra.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity(name = "BALANCE")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BalanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @Column(name="AMOUNT")
    private Long amount;

    @CreatedDate
    @Column(name="LAST_UPDATE_AT")
    private LocalDateTime lastUpdateAt;

    public static Balance of(BalanceEntity entity) {

        return Balance.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .amount(entity.getAmount())
                .lastUpdateAt(entity.getLastUpdateAt())
                .build();
    }

    public static BalanceEntity from(Balance balance) {
        return BalanceEntity.builder()
                .id(balance.id())
                .user(UserEntity.builder().id(balance.userId()).build())
                .amount(balance.amount())
                .lastUpdateAt(balance.lastUpdateAt())
                .build();
    }
}
