package hhplus.concert.application.dto;

public class BalanceDto {

    public record Request(Long userId, Long amount){}
    public record Response(Long userId, Long balance){}
}
