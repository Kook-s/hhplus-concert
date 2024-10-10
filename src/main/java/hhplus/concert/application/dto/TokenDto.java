package hhplus.concert.application.dto;

public class TokenDto {
    public record Request(Long userId) {}
    public record Response(Long userId, String token, int waitingId, String status) {}
}
