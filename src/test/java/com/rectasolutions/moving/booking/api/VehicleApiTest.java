package com.rectasolutions.moving.booking.api;

import com.rectasolutions.moving.booking.entities.VehicleCategory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VehicleApiTest {
    VehicleCategory vehicleCategory = new VehicleCategory();

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    VehicleApi vehicleApi;

    @Before
    public void getVehicleCategoryBike() {
        vehicleCategory.setId(1);
        vehicleCategory.setType("1");
        vehicleCategory.setCallDistance(5);
        vehicleCategory.setDeliveryDistance(25.55);
        vehicleCategory.setPayload(5);
        vehicleCategory.setVolume(10.55);
    }

    @Test
    public void getVehicleCategoryByDistanceTest(){
        when(vehicleApi.getVehicleCategoryByDistance(5)).thenReturn(vehicleCategory);
        VehicleCategory expectedCategory = vehicleApi.getVehicleCategoryByDistance(5);
        assertEquals(expectedCategory,vehicleCategory);
    }
    @Test
    public void getVehicleCategoryByPayloadTest(){
        when(vehicleApi.getVehicleCategoryByPayload(5,5)).thenReturn(vehicleCategory);
        VehicleCategory expectedCategory = vehicleApi.getVehicleCategoryByPayload(5,5);
        assertEquals(expectedCategory,vehicleCategory);
    }
}
