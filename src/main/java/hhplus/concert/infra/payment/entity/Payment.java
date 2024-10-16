package hhplus.concert.infra.payment.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "PAYMENT")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "CONERT_ID")
    private Long concertId;

    @Column(name = "CONCERT_TITLE")
    private String concertTitle;

    @Column(name = "SCHEDULE_ID")
    private Long scheduleId;

    @Column(name = "SEAT_ID")
    private Long seatId;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATE_AY")
    private LocalDateTime createAt;

    @Column(name = "UPDATE_AY")
    private LocalDateTime updateAt;

}
