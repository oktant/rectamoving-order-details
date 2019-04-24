package com.rectasolutions.moving.booking.services;

import com.rectasolutions.moving.booking.entities.BookingDetail;
import com.rectasolutions.moving.booking.entities.CustomerDetail;
import com.rectasolutions.moving.booking.entities.Location;
import com.rectasolutions.moving.booking.entities.Services;
import com.rectasolutions.moving.booking.repositories.BookingDetailRepository;
import com.rectasolutions.moving.booking.repositories.CustomerDetailRepository;
import com.rectasolutions.moving.booking.repositories.LocationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {

    BookingDetail bookingDetail = new BookingDetail();
    CustomerDetail customerDetail = new CustomerDetail();
    @Mock
    BookingDetailRepository bookingDetailRepository;
    @Mock
    CustomerDetailRepository customerDetailRepository;
    @Mock
    RedisService redisService;
    @Mock
    LocationRepository locationRepository;
    @InjectMocks
    BookingService bookingService;

    @Before
    public void getBookingDetailRequest() {
        bookingDetail.setVolume(120.20);
        bookingDetail.setDeliveryTime("100");
        bookingDetail.setGoodDescriptions("Fruits");
        bookingDetail.setPrice("10$");
        customerDetail.setSender("Safo");
        customerDetail.setReceiver("Zaur");
        customerDetail.setDeliveryNote("Test");
    }

    @Test
    public void setMainInfoTest() {
        when(redisService.get("testuser", Services.BOOKING)).thenReturn(new BookingDetail());
        assertEquals("Was not correct", 120.20, bookingService.setMainInfo(bookingDetail, "testuser").getVolume(), 0);
        assertEquals("100", bookingService.setMainInfo(bookingDetail, "testuser").getDeliveryTime());
        assertEquals("Fruits", bookingService.setMainInfo(bookingDetail, "testuser").getGoodDescriptions());
        assertEquals("", bookingService.setMainInfo(bookingDetail, "testuser").getPrice());

    }

    @Test
    public void setCustomerDetailsTest() {
        when(redisService.get("testuser", Services.BOOKING)).thenReturn(new BookingDetail());
        assertEquals(HttpStatus.OK, bookingService.setCustomerDetails(customerDetail, "testuser").getStatusCode());
        assertNotEquals(HttpStatus.OK, bookingService.setCustomerDetails(customerDetail, "wrongUser").getStatusCode());
    }

    @Test
    public void bookingTest() {
        when(redisService.get("testuser", Services.BOOKING)).thenReturn(new BookingDetail());
        assertEquals(HttpStatus.OK, bookingService.booking("testuser").getStatusCode());
        assertNotEquals(HttpStatus.OK, bookingService.booking("wrongUser").getStatusCode());
    }

    @Test
    public void getBookingDetailTest() {
        when(redisService.get("testuser", Services.BOOKING)).thenReturn(bookingDetail);
        assertEquals(HttpStatus.OK, bookingService.getBookingDetail("testuser").getStatusCode());
        assertNotEquals(HttpStatus.OK, bookingService.getBookingDetail("wrongUser").getStatusCode());

        assertEquals("Was not correct", 120.20, bookingService.getBookingDetail("testuser").getBody().getVolume(), 0);
        assertEquals("100", bookingService.getBookingDetail("testuser").getBody().getDeliveryTime());
        assertEquals("Fruits", bookingService.getBookingDetail("testuser").getBody().getGoodDescriptions());
        assertEquals("10$", bookingService.getBookingDetail("testuser").getBody().getPrice());
    }
}

