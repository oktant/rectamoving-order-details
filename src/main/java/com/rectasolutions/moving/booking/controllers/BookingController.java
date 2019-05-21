package com.rectasolutions.moving.booking.controllers;

import com.rectasolutions.moving.booking.entities.BookingDetail;
import com.rectasolutions.moving.booking.entities.CustomerDetail;
import com.rectasolutions.moving.booking.entities.VehicleCategory;
import com.rectasolutions.moving.booking.services.BookingService;
import com.rectasolutions.moving.booking.services.VehicleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Autowired
    private VehicleCategoryService vehicleCategoryService;
    @Autowired
    private BookingService bookingService;

    //FOR SIGN IN KEYCLOAK
    @GetMapping
    public String helloWorld(Principal p) {
        return "Hello " + p.getName();
    }

    @PostMapping("/vehicles/categories/distances")
    public ResponseEntity<VehicleCategory> getVehicleCategoryByDistance(@RequestBody BookingDetail bookingDetail, Principal principal) {
        return vehicleCategoryService.getVehicleCategoryByDistance(bookingDetail, principal.getName());

    }

    @GetMapping("/vehicle/categories/payload/{payload}")
    public ResponseEntity<VehicleCategory> getVehicleCategoryByPayload(@PathVariable double payload, Principal principal) {
        return vehicleCategoryService.getVehicleCategoryByPayload(principal.getName(), payload);
    }

    @PostMapping("/price")
    public ResponseEntity<BookingDetail> calculatePrice(@RequestBody BookingDetail bookingDetail, Principal principal) {
        BookingDetail book = bookingService.setMainInfo(bookingDetail, principal.getName());
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/customers/details")
    public ResponseEntity setCustomerDetails(@RequestBody CustomerDetail customerDetail, Principal principal) {
        return bookingService.setCustomerDetails(customerDetail, principal.getName());
    }

    @PostMapping("/details")
    public ResponseEntity setBookingDetail(Principal principal) {

        return bookingService.booking(principal.getName());
    }

    @GetMapping("/details")
    public ResponseEntity<BookingDetail> getBookingDetail(Principal principal) {
        return bookingService.getBookingDetail(principal.getName());
    }
}
