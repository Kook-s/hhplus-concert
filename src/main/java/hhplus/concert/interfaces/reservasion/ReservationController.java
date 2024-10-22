package hhplus.concert.interfaces.reservasion;

import hhplus.concert.application.dto.ReservationResponse;
import hhplus.concert.application.facade.ReservationFacade;
import hhplus.concert.interfaces.concert.dto.SeatDto;
import hhplus.concert.interfaces.reservasion.dto.ReservationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationFacade reservationFacade;


    @PostMapping
    public ResponseEntity<ReservationDto.ReservationResponse> createReservation(
            @RequestHeader("Token") String token,
            @RequestBody ReservationDto.ReservationRequest request
    ){
        ReservationResponse reservation = reservationFacade.reservation(request.toCommand(token));
        return ResponseEntity.ok(
                ReservationDto.ReservationResponse.builder()
                        .reservationId(reservation.reservationId())
                        .concertId(reservation.concertId())
                        .concertAt(reservation.concertAt())
                        .seat(SeatDto.builder()
                                .seatId(reservation.seat().id())
                                .seatNo(reservation.seat().seatNo())
                                .seatPrice(reservation.seat().seatPrice()).build())
                        .reservationStatus(reservation.status())
                        .build()
        );
    }
}
