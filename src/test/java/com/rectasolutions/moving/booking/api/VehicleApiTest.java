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
import static org.mockito.Mockito.any;

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
        when(restTemplate.getForObject("http://localhost:8090/api/v1/vehicle/categories/distance/5", VehicleCategory.class)).thenReturn(vehicleCategory);
        when(vehicleApi.getVehicleCategoryByDistance(5)).thenReturn(vehicleCategory);
        VehicleCategory expectedCategory = vehicleApi.getVehicleCategoryByDistance(5);
        assertEquals(expectedCategory,vehicleCategory);
    }
    @Test
    public void getVehicleCategoryByPayloadTest(){
        when(restTemplate.getForObject("http://localhost:8090/api/v1/vehicle/categories/distance/5/payload/5", VehicleCategory.class)).thenReturn(vehicleCategory);
        when(vehicleApi.getVehicleCategoryByPayload(5,5)).thenReturn(vehicleCategory);
        VehicleCategory expectedCategory = vehicleApi.getVehicleCategoryByPayload(5,5);
        assertEquals(expectedCategory,vehicleCategory);
    }
}
