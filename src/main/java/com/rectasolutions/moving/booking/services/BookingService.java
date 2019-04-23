package com.rectasolutions.moving.booking.services;

import com.rectasolutions.moving.booking.entities.BookingDetail;
import com.rectasolutions.moving.booking.entities.CustomerDetail;
import com.rectasolutions.moving.booking.entities.Services;
import com.rectasolutions.moving.booking.repositories.BookingDetailRepository;
import com.rectasolutions.moving.booking.repositories.CustomerDetailRepository;
import com.rectasolutions.moving.booking.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    private RedisService redisService;

    @Autowired
    private BookingDetailRepository bookingDetailRepository;

    @Autowired
    private CustomerDetailRepository customerDetailRepository;

    @Autowired
    private LocationRepository locationRepository;

    public BookingDetail setMainInfo(BookingDetail bookingDetail, String username) {
        BookingDetail book = redisService.get(username, Services.BOOKING);
        book.setDeliveryTime(bookingDetail.getDeliveryTime());
        book.setVolume(bookingDetail.getVolume());
        book.setGoodDescriptions(bookingDetail.getGoodDescriptions());
        book.setPrice(calculatePrice(bookingDetail));
        redisService.save(username, book, Services.BOOKING);
        return book;
    }

    private String calculatePrice(BookingDetail bookingDetail) {
        //todo
        return "";
    }

    public ResponseEntity setCustomerDetails(CustomerDetail customerDetail, String username) {
        try {
            BookingDetail book = redisService.get(username, Services.BOOKING);
            book.setCustomerDetail(customerDetail);
            redisService.save(username, book, Services.BOOKING);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception exp) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity booking(String username) {
        try {
            BookingDetail book = redisService.get(username, Services.BOOKING);
            saveInDb(book);

            redisService.invalidate(username, Services.BOOKING);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception exp) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    private void saveInDb(BookingDetail book) {
        locationRepository.save(book.getPickUp());
        locationRepository.save(book.getDropTo());
        customerDetailRepository.save(book.getCustomerDetail());
        bookingDetailRepository.save(book);
    }

    public ResponseEntity<BookingDetail> getBookingDetail(String username) {
        try {
            BookingDetail book = redisService.get(username, Services.BOOKING);
            return new ResponseEntity(book, HttpStatus.OK);
        } catch (Exception exp) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
