package hhplus.concert.application.dto;

public class PaymentDto {
    public record Request(Long userId, int amount){}
    public record Response(Long userId, int amount, String status, String paymentTime){}
}
