package com.rectasolutions.moving.booking.services;

import com.rectasolutions.moving.booking.api.VehicleApi;
import com.rectasolutions.moving.booking.entities.BookingDetail;
import com.rectasolutions.moving.booking.entities.Services;
import com.rectasolutions.moving.booking.entities.VehicleCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VehicleCategoryService {
    @Autowired
    private RedisService redisService;

    @Autowired
    private VehicleApi vehicleApi;

    public ResponseEntity<VehicleCategory> getVehicleCategoryByDistance(BookingDetail bookingDetail, String username) {

        VehicleCategory vehicleCategory = vehicleApi.getVehicleCategoryByDistance(bookingDetail.getDistance());
        return getVehicleResponse(vehicleCategory, bookingDetail, username);
    }

    public ResponseEntity<VehicleCategory> getVehicleCategoryByPayload(String username, double payload) {

        BookingDetail bookingDetail = redisService.get(username, Services.BOOKING);
        VehicleCategory vehicleCategory = vehicleApi.getVehicleCategoryByPayload(bookingDetail.getDistance(), payload);
        return getVehicleResponse(vehicleCategory, bookingDetail, username);

    }

    private ResponseEntity<VehicleCategory> getVehicleResponse(VehicleCategory vehicleCategory, BookingDetail bookingDetail, String username) {

        if (vehicleCategory == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            ResponseEntity<VehicleCategory> response = new ResponseEntity<>(vehicleCategory, HttpStatus.OK);
            bookingDetail.setVehicleCategory(response.getBody());
            redisService.save(username, bookingDetail, Services.BOOKING);
            return response;
        }
    }
}
