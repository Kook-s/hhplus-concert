## [항해99 백엔드 플러스 6기] 콘서트 예약 서비스 

[e-커머스 서비스]

[맛집 검색 서비스]

✅ [콘서트 예약 서비스]


### **`STEP 05`**

- 시나리오 선정 및 프로젝트 Milestone 제출
- 시나리오 요구사항 별 분석 자료 제출

  > 시퀀스 다이어그램, 플로우 차트 등

- 자료들을 리드미에 작성 후 PR 링크 제출

### **`STEP 06`**

- ERD 설계 자료 제출
- API 명세 및 Mock API 작성
- 자료들을 리드미에 작성 후 PR링크 제출 ( 기본 패키지 구조, 서버 Configuration 등 )
---

### **`STEP 05`**

### **`[콘서트 예약 서비스]`** : [Milestone](https://github.com/Kook-s/hhplus-concert/milestones)

### **`시퀀스다이어그램`**

## 대기열 API
```mermaid
sequenceDiagram
  participant User as 사용자
  participant Queue as 대기열
  participant Token as 토큰

  User->>Queue: 대기열 등록 요청
  Queue->>Queue: 대기열 순번 할당 및 잔여 시간 계산
  Queue-->>Token: 유저 UUID 및 대기열 정보 전달
  Token-->>User: 대기열 토큰 발급

```

## 예약 가능 날짜 / 좌석 API 시퀀스 다이어그램

```mermaid
sequenceDiagram
  participant User as 사용자
  participant Token as 토큰
  participant Date as 예약 날짜
  participant Seat as 좌석

  User->>Token: 토큰 검증 요청
  alt 토큰 유효
    Token-->>User: 토큰 유효 확인
    User->>Date: 예약 가능 날짜 조회 요청
    Date-->>User: 예약 가능한 날짜 목록 반환

    User->>Seat: 특정 날짜의 예약 가능한 좌석 조회
    Seat->>Date: 해당 날짜의 좌석 정보 조회
    Date-->>Seat: 예약 가능한 좌석 목록 반환
    Seat-->>User: 좌석 목록 응답
  else 토큰 유효하지 않음
    Token-->>User: 토큰 유효하지 않음 응답
  end
```

# 좌석 예약 요청 API 시퀀스 다이어그램

```mermaid
sequenceDiagram
  participant User as 사용자
  participant Token as 토큰
  participant Seat as 좌석
  participant Reservation as 예약

  User->>Token: 토큰 검증 요청
  alt 토큰 유효
    Token-->>User: 토큰 유효 확인
    User->>Seat: 좌석 예약 요청 (날짜 및 좌석 번호)
    Seat->>Reservation: 좌석 예약 가능 여부 확인
    alt 좌석 예약 가능
      Reservation-->>Seat: 좌석 예약 가능
      Seat-->>User: 좌석 임시 예약 완료 (5분 유효)
      Note over Seat: 5분 후 임시 예약 해제
    else 좌석 예약 불가
      Reservation-->>Seat: 좌석 예약 불가
      Seat-->>User: 좌석 예약 불가 알림
    end
  else 토큰 유효하지 않음
    Token-->>User: 토큰 유효하지 않음 응답
  end

```

## 잔액 충전 / 조회 API 시퀀스 다이어그램
```mermaid
sequenceDiagram
    participant User as 사용자
    participant Balance as 잔액
    participant Payment as 결제

    User->>Balance: 잔액 조회 요청
    Balance-->>User: 잔액 정보 반환

    User->>Balance: 잔액 충전 요청
    Balance->>Payment: 잔액 충전
    Payment-->>Balance: 충전 완료
    Balance-->>User: 잔액 충전 완료 응답

```

## 결제 API 시퀀스 다이어그램
```mermaid
sequenceDiagram
    participant User as 사용자
    participant Token as 토큰
    participant Payment as 결제
    participant Balance as 잔액
    participant Reservation as 예약

    User->>Token: 토큰 검증 요청
    alt 토큰 유효
        Token-->>User: 토큰 유효 확인
        User->>Payment: 결제 요청 (좌석 ID, 금액)
        Payment->>Balance: 잔액 차감 요청
        Balance-->>Payment: 잔액 차감 완료
        alt 잔액 충분
            Payment-->>Reservation: 결제 완료 및 좌석 확정
            Reservation-->>User: 결제 완료 및 좌석 예약 확정
            Token-->>Token: 대기열 토큰 만료 처리
        else 잔액 부족
            Payment-->>User: 결제 실패 (잔액 부족)
        end
    else 토큰 유효하지 않음
        Token-->>User: 토큰 유효하지 않음 응답
    end
```

## 콘서트 예약 서비스(전체)
```mermaid
sequenceDiagram
  participant User as 사용자
  participant Queue as 대기열
  participant Token as 토큰
  participant Date as 예약 날짜
  participant Seat as 좌석
  participant Reservation as 예약
  participant Balance as 잔액
  participant Payment as 결제

%% 대기열 등록 및 토큰 발급
  User->>Queue: 대기열 등록 요청
  Queue->>Queue: 대기열 순번 할당 및 잔여 시간 계산
  Queue-->>Token: 유저 UUID 및 대기열 정보 전달
  Token-->>User: 대기열 토큰 발급

%% 예약 가능 날짜 및 좌석 조회
  User->>Token: 토큰 검증 요청
  alt 토큰 유효
    Token-->>User: 토큰 유효 확인
    User->>Date: 예약 가능 날짜 조회 요청
    Date-->>User: 예약 가능한 날짜 목록 반환

    User->>Seat: 특정 날짜의 예약 가능한 좌석 조회
    Seat->>Date: 해당 날짜의 좌석 정보 조회
    Date-->>Seat: 예약 가능한 좌석 목록 반환
    Seat-->>User: 좌석 목록 응답
  else 토큰 유효하지 않음
    Token-->>User: 토큰 유효하지 않음 응답
  end

%% 좌석 예약 요청
  User->>Token: 토큰 재검증 요청
  alt 토큰 유효
    Token-->>User: 토큰 유효 확인
    User->>Seat: 좌석 예약 요청 (날짜 및 좌석 번호)
    Seat->>Reservation: 좌석 예약 가능 여부 확인
    alt 좌석 예약 가능
      Reservation-->>Seat: 좌석 예약 가능
      Seat-->>User: 좌석 임시 예약 완료 (5분 유효)
      Note over Seat: 5분 후 임시 예약 해제
    else 좌석 예약 불가
      Reservation-->>Seat: 좌석 예약 불가
      Seat-->>User: 좌석 예약 불가 알림
    end
  else 토큰 유효하지 않음
    Token-->>User: 토큰 유효하지 않음 응답
  end

%% 잔액 조회 및 충전
  User->>Balance: 잔액 조회 요청
  Balance-->>User: 잔액 정보 반환

  User->>Balance: 잔액 충전 요청
  Balance->>Payment: 잔액 충전
  Payment-->>Balance: 충전 완료
  Balance-->>User: 잔액 충전 완료 응답

%% 결제 요청
  User->>Token: 토큰 검증 요청
  alt 토큰 유효
    Token-->>User: 토큰 유효 확인
    User->>Payment: 결제 요청 (좌석 ID, 금액)
    Payment->>Balance: 잔액 차감 요청
    Balance-->>Payment: 잔액 차감 완료
    alt 잔액 충분
      Payment-->>Reservation: 결제 완료 및 좌석 확정
      Reservation-->>User: 결제 완료 및 좌석 예약 확정
      Token-->>Token: 대기열 토큰 만료 처리
    else 잔액 부족
      Payment-->>User: 결제 실패 (잔액 부족)
    end
  else 토큰 유효하지 않음
    Token-->>User: 토큰 유효하지 않음 응답
  end
```
