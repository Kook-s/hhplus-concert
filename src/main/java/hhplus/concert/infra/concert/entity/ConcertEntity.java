package hhplus.concert.infra.concert.entity;

import hhplus.concert.domain.concert.Concert;
import hhplus.concert.support.type.ConcertStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity(name = "CONCERT")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConcertEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="TITLE")
    private String title;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="STATUS")
    @Enumerated(EnumType.STRING)
    private ConcertStatus status;

    public static Concert of(ConcertEntity entity) {

        return Concert.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .build();
    }

    public static ConcertEntity from(Concert concert) {

        return ConcertEntity.builder()
                .title(concert.title())
                .description(concert.description())
                .status(concert.status())
                .build();
    }

}
