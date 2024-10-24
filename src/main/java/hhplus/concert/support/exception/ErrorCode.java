package hhplus.concert.support.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Business
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "토큰 인증에 실패 헸습니다."),
    BEFORE_RESERVATION_AT(HttpStatus.BAD_REQUEST, "예약하기에는 이릅니다."),
    AFTER_DEADLINE(HttpStatus.BAD_REQUEST, "예약 가능 시간이 지났습니다."),
    SEAT_UNAVAILABLE(HttpStatus.BAD_REQUEST, "예약 가능한 좌석이 아닙니다."),
    PAYMENT_TIMEOUT(HttpStatus.BAD_REQUEST, "결제 가능한 시간이 지났습니다."),
    PAYMENT_DIFFERENT_USER(HttpStatus.BAD_REQUEST, "결제자 정보가 불일치합니다."),
    PAYMENT_FAILED_AMOUNT(HttpStatus.BAD_REQUEST, "결제 잔액이 부족합니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러가 발생하였습니다."),

    // JPA
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"올바르지 않은 사용자 입니다."),
    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "올바르지 않는 토큰입니다."),
    CONCERT_NOT_FOUND(HttpStatus.NOT_FOUND, "올바르지 않는 콘서트 정보 입니다."),
    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "올바르지 않는 콘서트 일정 입니다."),
    SEAT_NOT_FOUND(HttpStatus.NOT_FOUND, "올바르지 않는 좌석 정보 입니다."),
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "올바르지 않는예 약 정보 입니다."),
    BALANCE_NOT_FOUND(HttpStatus.NOT_FOUND, "올바르지 않는 잔액 정보 입니다."),

    // HTTP
    HTTP_NOT_READ(HttpStatus.BAD_REQUEST, "올바르지 않은 요청입니다."),
    NOT_READ_TOKEN(HttpStatus.UNAUTHORIZED, "존재하지 않는 토큰 입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
