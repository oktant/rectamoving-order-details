package com.rectasolutions.moving.booking.services;

import com.rectasolutions.moving.booking.entities.BookingDetail;
import com.rectasolutions.moving.booking.entities.Services;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class RedisServiceTest {

    BookingDetail bookingDetail = new BookingDetail();

    @Mock
    RedisTemplate<String, Object> redisTemplate;
    @Mock
    RedisService redisService;

    @BeforeEach
    void setUp() {
        redisService = new RedisService(redisTemplate);
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void getBookingDetailRequest() {
        bookingDetail.setVolume(120.20);
        bookingDetail.setDeliveryTime("100");
        bookingDetail.setGoodDescriptions("Fruits");
        bookingDetail.setPrice("10$");
    }

    @Test
    public void saveTest(){
        doNothing().when(redisService).save("testuser",bookingDetail,Services.BOOKING);
        redisService.save("testuser",bookingDetail,Services.BOOKING);
        verify(redisService).save("testuser",bookingDetail,Services.BOOKING);
    }

    @Test
    public void getTest(){
        when(redisService.get("testuser",Services.BOOKING)).thenReturn(bookingDetail);
        redisService.get("testuser",Services.BOOKING);
        assertEquals(redisService.get("testuser",Services.BOOKING).getClass(),BookingDetail.class);
    }
    @Test
    public void invalidateTest(){
        doNothing().when(redisService).invalidate("testuser",Services.BOOKING);
        redisService.invalidate("testuser",Services.BOOKING);
        verify(redisService).invalidate("testuser",Services.BOOKING);
    }

}
