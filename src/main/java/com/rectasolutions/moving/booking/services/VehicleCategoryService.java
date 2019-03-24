package com.rectasolutions.moving.booking.services;

import com.rectasolutions.moving.booking.entities.BookingDetail;
import com.rectasolutions.moving.booking.entities.Services;
import com.rectasolutions.moving.booking.entities.VehicleCategory;
import com.rectasolutions.moving.booking.repositories.VehicleCategoryRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public class VehicleCategoryService {
    @Autowired
    private VehicleCategoryRepository vehicleCategoryRepository;
    @Autowired
    private RedisService redisService;

    public ResponseEntity<VehicleCategory> getVehicleCategoryByDistance(BookingDetail bookingDetail,String username){

        Optional<VehicleCategory> optional = vehicleCategoryRepository.findFirstByDistance(bookingDetail.getDistance());
        if(optional.isPresent()){
            ResponseEntity<VehicleCategory> response = new ResponseEntity<>(optional.get(),HttpStatus.OK);
            bookingDetail.setVehicleCategory(response.getBody());
            redisService.save(username, bookingDetail, Services.Booking);
            return response;
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<VehicleCategory> getVehicleCategoryByPayload(String username, double payload){

        BookingDetail book = redisService.get(username,Services.Booking);
        Optional<VehicleCategory> optional = vehicleCategoryRepository.findFirstByPayload(book.getDistance(),payload);
        if(optional.isPresent()){
            ResponseEntity<VehicleCategory> response = new ResponseEntity<>(optional.get(),HttpStatus.OK);
            book.setVehicleCategory(response.getBody());
            book.setPayload(payload);
            redisService.save(username,book,Services.Booking);
            return response;
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
