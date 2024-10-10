package hhplus.concert;
import com.fasterxml.jackson.databind.ObjectMapper;
import hhplus.concert.application.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HhplusConcertApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;  // Jackson ObjectMapper

    // 1. 토큰 발급 테스트
    @Test
    public void issueTokenTest() throws Exception {
        TokenDto.Request request = new TokenDto.Request(123L);
        String requestBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/issue")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(123))
                .andExpect(jsonPath("$.token").value("mock-token-123"))
                .andExpect(jsonPath("$.waitingId").value(10))
                .andExpect(jsonPath("$.status").value("activate"));
    }

    // 2. 대기열 상태 확인 테스트
    @Test
    public void getTokenStatusTest() throws Exception {
        mockMvc.perform(get("/api/status/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(123))
                .andExpect(jsonPath("$.token").value("mock-token-123"))
                .andExpect(jsonPath("$.waitingId").value(5))
                .andExpect(jsonPath("$.status").value("waiting"));
    }

    // 3. 예약 가능한 날짜 목록 조회 테스트
    @Test
    public void getAvailableSeatDatesTest() throws Exception {
        mockMvc.perform(get("/api/available-dates"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("2024-10-01"))
                .andExpect(jsonPath("$[1]").value("2024-10-02"));
    }

    // 4. 특정 날짜 좌석 목록 조회 테스트
    @Test
    public void getAvailableSeatsTest() throws Exception {
        mockMvc.perform(get("/api/available-seats/2024-10-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].seatNumber").value(1))
                .andExpect(jsonPath("$[0].status").value("available"))
                .andExpect(jsonPath("$[0].price").value(10000));
    }

    // 5. 좌석 예약 요청 테스트
    @Test
    public void reserveSeatTest() throws Exception {
        ReservationDto.Request request = new ReservationDto.Request(123L, 10);
        String requestBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/reserve")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(123))
                .andExpect(jsonPath("$.seatId").value(10))
                .andExpect(jsonPath("$.status").value("reserved"))
                .andExpect(jsonPath("$.amount").value(10000));
    }

    // 6. 유저 잔액 조회 테스트
    @Test
    public void getUserBalanceTest() throws Exception {
        mockMvc.perform(get("/api/123/balance"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(123))
                .andExpect(jsonPath("$.balance").value(10000L));
    }

    // 7. 유저 잔액 충전 테스트
    @Test
    public void chargeBalanceTest() throws Exception {
        // DTO 객체 생성
        BalanceDto.Request request = new BalanceDto.Request(123L,10000L);

        // DTO 객체를 JSON으로 변환
        String requestBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/123/charge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(123))
                .andExpect(jsonPath("$.balance").value(20000L));
    }

    // 8. 결제 처리 테스트
    @Test
    public void processPaymentTest() throws Exception {
        // DTO 객체 생성
        PaymentDto.Request request = new PaymentDto.Request(123L, 10000);

        // DTO 객체를 JSON으로 변환
        String requestBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/process")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(123))
                .andExpect(jsonPath("$.amount").value(10000))
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.paymentTime").value("2024-10-06T12:00:00Z"));
    }
}
