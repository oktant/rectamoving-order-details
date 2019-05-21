package com.rectasolutions.moving.booking.controllers;

import com.rectasolutions.moving.booking.entities.BookingDetail;
import com.rectasolutions.moving.booking.entities.CustomerDetail;
import com.rectasolutions.moving.booking.entities.VehicleCategory;
import com.rectasolutions.moving.booking.services.BookingService;
import com.rectasolutions.moving.booking.services.VehicleCategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.security.Principal;

@RunWith(MockitoJUnitRunner.class)
public class BookingControllerTest {
    @InjectMocks
    BookingController bookingController;

    @Mock
    private VehicleCategoryService vehicleCategoryService;

    @Mock
    private BookingService bookingService;

    private Principal principal1;
    private BookingDetail bookingDetail = new BookingDetail();
    private CustomerDetail customerDetail = new CustomerDetail();
    private ResponseEntity response = new ResponseEntity(HttpStatus.OK);
    @Before
    public void setUp(){
        principal1= new Principal() {
            @Override
            public String getName() {
                return "aaa";
            }
        };
        bookingDetail.setVolume(120.20);
        bookingDetail.setDeliveryTime("100");
        bookingDetail.setGoodDescriptions("Fruits");
        bookingDetail.setPrice("10$");
        customerDetail.setSender("Safo");
        customerDetail.setReceiver("Oku");
        customerDetail.setDeliveryNote("Test");
    }

    @Test
    public void helloWorld(){
        assertEquals(bookingController.helloWorld(principal1),"Hello aaa");
    }

    @Test
    public void getVehicleCategoryByDistance(){

        when(vehicleCategoryService.getVehicleCategoryByDistance(bookingDetail, principal1.getName())).thenReturn(response);
        assertEquals(HttpStatus.OK,bookingController.getVehicleCategoryByDistance(bookingDetail,principal1).getStatusCode());
    }

    @Test
    public void getVehicleCategoryByPayload(){

        when(vehicleCategoryService.getVehicleCategoryByPayload(principal1.getName(),20)).thenReturn(response);
        assertEquals(HttpStatus.OK,bookingController.getVehicleCategoryByPayload(20,principal1).getStatusCode());
    }
    @Test
    public void calculatePrice() {
        when( bookingService.setMainInfo(bookingDetail, principal1.getName())).thenReturn(new BookingDetail());
        assertEquals(HttpStatus.OK, bookingController.calculatePrice(bookingDetail,principal1).getStatusCode());
    }
    @Test
    public void setCustomerDetails(){
        when(bookingService.setCustomerDetails(customerDetail, principal1.getName())).thenReturn(response);
        assertEquals(HttpStatus.OK, bookingController.setCustomerDetails(customerDetail,principal1).getStatusCode());
    }
    @Test
    public void setBookingDetail(){
        when(bookingService.booking(principal1.getName())).thenReturn(response);
        assertEquals(HttpStatus.OK, bookingController.setBookingDetail(principal1).getStatusCode());
    }
    @Test
    public void  getBookingDetail(){
        when(bookingService.getBookingDetail(principal1.getName())).thenReturn(response);
        assertEquals(HttpStatus.OK, bookingController.getBookingDetail(principal1).getStatusCode());
    }
}
