## [항해99 백엔드 플러스 6기] 콘서트 예약 서비스 

[e-커머스 서비스]

[맛집 검색 서비스]

[콘서트 예약 서비스] ✅


### **`STEP 05`**

- 시나리오 선정 및 프로젝트 Milestone 제출
- 시나리오 요구사항 별 분석 자료 제출

  > 시퀀스 다이어그램, 플로우 차트 등

- 자료들을 리드미에 작성 후 PR 링크 제출

### **`STEP 06`**

- ERD 설계 자료 제출
- API 명세 및 Mock API 작성
- 자료들을 리드미에 작성 후 PR링크 제출 ( 기본 패키지 구조, 서버 Configuration 등 )
-
<br>

---

<br>

## **`STEP 06`**

<br>

## ERD

## ![image](https://github.com/Kook-s/hhplus-concert/blob/week03/document/concert_ERD.png?raw=true)


## API 명세서

### 1. **토큰 발급 API**

- **URL**: `/api/issue`
- **Method**: `POST`
- **Request Body**:

  ```json
  {
    "userId": 123
  }
  ```

- **Response**:

  ```json
  {
    "userId": 123,
    "token": "mock-token-123",
    "waitingId": 10,
    "status": "activate"
  }
  ```

- **Description**: 해당 사용자의 토큰을 발급합니다. `userId`를 입력하면 토큰이 포함된 응답을 반환합니다.

---

### 2. **대기열 상태 확인 API**

- **URL**: `/api/status/{userId}`
- **Method**: `GET`
- **Path Variable**: `userId` (예: `/api/status/123`)
- **Response**:

  ```json
  {
    "userId": 123,
    "token": "mock-token-123",
    "waitingId": 5,
    "status": "waiting"
  }
  ```

- **Description**: 해당 사용자의 대기열 상태를 반환합니다.

---

### 3. **예약 가능한 날짜 목록 조회 API**

- **URL**: `/api/available-dates`
- **Method**: `GET`
- **Response**:

  ```json
  [
    "2024-10-01",
    "2024-10-02"
  ]
  ```

- **Description**: 예약 가능한 날짜의 목록을 반환합니다.

---

### 4. **특정 날짜 좌석 목록 조회 API**

- **URL**: `/api/available-seats/{date}`
- **Method**: `GET`
- **Path Variable**: `date` (예: `/api/available-seats/2024-10-01`)
- **Response**:

  ```json
  [
    {
      "seatNumber": 1,
      "status": "available",
      "price": 10000
    },
    {
      "seatNumber": 2,
      "status": "available",
      "price": 10000
    }
  ]
  ```

- **Description**: 특정 날짜의 예약 가능한 좌석 목록을 반환합니다.

---

### 5. **좌석 예약 요청 API**

- **URL**: `/api/reserve`
- **Method**: `POST`
- **Request Body**:

  ```json
  {
    "userId": 123,
    "seatId": 10
  }
  ```

- **Response**:

  ```json
  {
    "userId": 123,
    "seatId": 10,
    "status": "reserved",
    "amount": 10000
  }
  ```

- **Description**: 좌석을 예약합니다. 좌석이 예약되면 `reserved` 상태로 반환합니다.

---

### 6. **유저 잔액 조회 API**

- **URL**: `/api/{userId}/balance`
- **Method**: `GET`
- **Path Variable**: `userId` (예: `/api/123/balance`)
- **Response**:

  ```json
  {
    "userId": 123,
    "balance": 10000
  }
  ```

- **Description**: 해당 사용자의 잔액을 조회합니다.

---

### 7. **유저 잔액 충전 API**

- **URL**: `/api/{userId}/charge`
- **Method**: `POST`
- **Path Variable**: `userId` (예: `/api/123/charge`)
- **Request Body**:

  ```json
  {
    "amount": 10000
  }
  ```

- **Response**:

  ```json
  {
    "userId": 123,
    "balance": 20000
  }
  ```

- **Description**: 해당 사용자의 잔액을 충전합니다.

---

### 8. **결제 처리 API**

- **URL**: `/api/process`
- **Method**: `POST`
- **Request Body**:

  ```json
  {
    "userId": 123,
    "amount": 10000
  }
  ```

- **Response**:

  ```json
  {
    "userId": 123,
    "amount": 10000,
    "status": "success",
    "paymentTime": "2024-10-06T12:00:00Z"
  }
  ```

- **Description**: 결제를 처리하고, 결제 상태와 결제 시간을 반환합니다.
