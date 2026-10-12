package com.flashreserve.controller;

import com.flashreserve.entity.Reservation;
import com.flashreserve.service.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation reserve(
            @RequestParam Long productId,
            @RequestParam int quantity) {

        return reservationService.reserve(productId, quantity);
    }
}