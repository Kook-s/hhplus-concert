package hhplus.concert.api.web;

import hhplus.concert.application.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MockController {

    // 토큰 발급
    @PostMapping("/issue")
    public TokenDto.Response issueToken(@RequestBody TokenDto.Request request) {
        return new TokenDto.Response(request.userId(),"mock-token-123", 10, "activate");
    }
    // 대기열 상태 확인
    @GetMapping("/status/{userId}")
    public TokenDto.Response getTokenStatus(@PathVariable Long userId) {
        return new TokenDto.Response(userId,"mock-token-123", 5, "waiting");
    }

    //예약 가능한 날짜 목록 조회
    @GetMapping("/available-dates")
    public List<String> getAvailableSeatDates() {
        List<String> dates = new ArrayList<>();
        dates.add("2024-10-01");
        dates.add("2024-10-02");
        return dates;
    }

    //특정 날짜 좌석 목록 조회
    @GetMapping("/available-seats/{date}")
    public List<SeatDto.Response> getAvailableSeats(@PathVariable String date) {
        List<SeatDto.Response> seats = new ArrayList<>();
        for(int i=1; i<= 5; i++){
            seats.add(new SeatDto.Response(i, "available", 10000));
        }
        return seats;
    }

    //좌석 예약 요청
    @PostMapping("/reserve")
    public ReservationDto.Response reserveSeat(@RequestBody ReservationDto.Request request) {
        return new ReservationDto.Response(request.userId(), request.seatId(), "reserved", 10000);
    }

    // 유저 잔액 조회
    @GetMapping("/{userId}/balance")
    public BalanceDto.Response getUserBalance(@PathVariable Long userId) {
        return new BalanceDto.Response(userId, 10000L);
    }

    //유저 잔액 충전
    @PostMapping("/{userId}/charge")
    public BalanceDto.Response chargeBalance(@PathVariable Long userId, @RequestBody BalanceDto.Request request) {
        return new BalanceDto.Response(userId, 10000L+ request.amount());
    }

    //결제
    @PostMapping("/process")
    public PaymentDto.Response processPayment(@RequestBody PaymentDto.Request request) {
        return new PaymentDto.Response(request.userId(), request.amount(), "success", "2024-10-06T12:00:00Z");
    }

}




