package com.rectasolutions.moving.booking.services;

import com.rectasolutions.moving.booking.api.VehicleApi;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VehicleCategoryServiceTest {
    @Mock
    RedisService redisService;
    @Mock
    VehicleApi vehicleApi;
    @InjectMocks
    VehicleCategoryService vehicleCategoryService;


}
