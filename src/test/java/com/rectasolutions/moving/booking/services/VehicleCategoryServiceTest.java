package com.rectasolutions.moving.booking.services;

import com.rectasolutions.moving.booking.api.VehicleApi;
import com.rectasolutions.moving.booking.entities.BookingDetail;
import com.rectasolutions.moving.booking.entities.Services;
import com.rectasolutions.moving.booking.entities.VehicleCategory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VehicleCategoryServiceTest {
    VehicleCategory vehicleCategoryBike = new VehicleCategory();
    VehicleCategory vehicleCategoryCar = new VehicleCategory();



    @Mock
    RedisService redisService;
    @Mock
    VehicleApi vehicleApi;
    @InjectMocks
    VehicleCategoryService vehicleCategoryService;

    @Before
    public void getVehicleCategoryBike() {
        vehicleCategoryBike.setId(1);
        vehicleCategoryBike.setType("1");
        vehicleCategoryBike.setCallDistance(5);
        vehicleCategoryBike.setDeliveryDistance(25.55);
        vehicleCategoryBike.setPayload(5);
        vehicleCategoryBike.setVolume(10.55);

    }
    @Before
    public void getVehicleCategoryCar() {
        vehicleCategoryCar.setId(2);
        vehicleCategoryCar.setType("2");
        vehicleCategoryCar.setCallDistance(20.33);
        vehicleCategoryCar.setDeliveryDistance(50.45);
        vehicleCategoryCar.setPayload(15.5);
        vehicleCategoryCar.setVolume(20);
    }


    @Test
    public void getVehicleCategoryByDistanceTest(){
        when(vehicleApi.getVehicleCategoryByDistance(10)).thenReturn(vehicleCategoryBike);
        when(vehicleApi.getVehicleCategoryByDistance(40.55)).thenReturn(vehicleCategoryCar);
        when(vehicleApi.getVehicleCategoryByDistance(60)).thenReturn(new VehicleCategory());

        BookingDetail detailDistance = new BookingDetail();
        detailDistance.setDistance(10);

        assertEquals(HttpStatus.OK, vehicleCategoryService.getVehicleCategoryByDistance(detailDistance, "testuser").getStatusCode());
        assertEquals("Was not correct", 25.55,
                vehicleCategoryService.getVehicleCategoryByDistance(detailDistance, "testuser").getBody().getDeliveryDistance(), 0);
        assertEquals(1,
                vehicleCategoryService.getVehicleCategoryByDistance(detailDistance, "testuser").getBody().getId());

        detailDistance.setDistance(40.55);
        assertEquals(HttpStatus.OK, vehicleCategoryService.getVehicleCategoryByDistance(detailDistance, "testuser").getStatusCode());
        assertEquals("Was not correct", 50.45,
                vehicleCategoryService.getVehicleCategoryByDistance(detailDistance, "testuser").getBody().getDeliveryDistance(), 0);
        assertEquals(2,
                vehicleCategoryService.getVehicleCategoryByDistance(detailDistance, "testuser").getBody().getId());


        detailDistance.setDistance(60);
        assertEquals(HttpStatus.OK, vehicleCategoryService.getVehicleCategoryByDistance(detailDistance, "testuser").getStatusCode());
        assertEquals(0, vehicleCategoryService.getVehicleCategoryByDistance(detailDistance, "testuser").getBody().getId());

    }

    @Test
    public void getVehicleCategoryByPayloadTest(){

        when(vehicleApi.getVehicleCategoryByPayload(10,3.5)).thenReturn(vehicleCategoryBike);
        when(vehicleApi.getVehicleCategoryByPayload(10,11.1)).thenReturn(vehicleCategoryCar);
        when(vehicleApi.getVehicleCategoryByPayload(30,3.2)).thenReturn(vehicleCategoryCar);
        when(vehicleApi.getVehicleCategoryByPayload(60,3.2)).thenReturn(new VehicleCategory());
        when(vehicleApi.getVehicleCategoryByPayload(10,20)).thenReturn(new VehicleCategory());

        BookingDetail detailPayload = new BookingDetail();
        detailPayload.setDistance(10);
        when(redisService.get("userWithDistance10",Services.BOOKING)).thenReturn(detailPayload);
        assertEquals(HttpStatus.OK, vehicleCategoryService.getVehicleCategoryByPayload("userWithDistance10",3.5).getStatusCode());

        assertEquals("Was not correct", 25.55,
                vehicleCategoryService.getVehicleCategoryByPayload("userWithDistance10",3.5).getBody().getDeliveryDistance(), 0);

        assertEquals("Was not correct", 50.45,
                vehicleCategoryService.getVehicleCategoryByPayload("userWithDistance10",11.1).getBody().getDeliveryDistance(), 0);

        assertEquals("Was not correct", 0,
                vehicleCategoryService.getVehicleCategoryByPayload("userWithDistance10",20).getBody().getDeliveryDistance(), 0);


        detailPayload.setDistance(30);

        when(redisService.get("userWithDistance30",Services.BOOKING)).thenReturn(detailPayload);

        assertEquals(HttpStatus.OK, vehicleCategoryService.getVehicleCategoryByPayload("userWithDistance30",3.2).getStatusCode());

        assertEquals("Was not correct", 50.45,
                vehicleCategoryService.getVehicleCategoryByPayload("userWithDistance30",3.2).getBody().getDeliveryDistance(), 0);

        detailPayload.setDistance(60);
        when(redisService.get("userWithDistance60",Services.BOOKING)).thenReturn(detailPayload);

        assertEquals(HttpStatus.OK, vehicleCategoryService.getVehicleCategoryByPayload("userWithDistance60",3.2).getStatusCode());

        assertEquals("Was not correct", 0,
                vehicleCategoryService.getVehicleCategoryByPayload("userWithDistance60",3.2).getBody().getDeliveryDistance(), 0);


    }

}
