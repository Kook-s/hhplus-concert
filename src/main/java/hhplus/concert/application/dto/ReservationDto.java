package hhplus.concert.application.dto;

public class ReservationDto {
    public record Request(Long userId, int seatId){}
    public record Response(Long userId, int seatId, String status, int amount){}
}
