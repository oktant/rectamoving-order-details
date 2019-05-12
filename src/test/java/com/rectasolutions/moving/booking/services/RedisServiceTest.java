package com.rectasolutions.moving.booking.services;

import com.rectasolutions.moving.booking.entities.BookingDetail;
import com.rectasolutions.moving.booking.entities.Services;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class RedisServiceTest {

    BookingDetail bookingDetail = new BookingDetail();

    @Mock
    RedisTemplate<String, Object> redisTemplate;
    @Mock
    ValueOperations valueOperations;
    @Spy
    @InjectMocks
    RedisService redisService;


    @Before
    public void getBookingDetailRequest() {
        bookingDetail.setVolume(120.20);
        bookingDetail.setDeliveryTime("100");
        bookingDetail.setGoodDescriptions("Fruits");
        bookingDetail.setPrice("10$");
    }

    @Test
    public void saveTest() {
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        redisService.save("testuser", bookingDetail, Services.BOOKING);
        verify(redisService).save("testuser", bookingDetail, Services.BOOKING);
    }

    @Test
    public void getTest() {
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(valueOperations.get(any())).thenReturn(bookingDetail);
        assertEquals(redisService.get("testuser", Services.BOOKING).getClass(), BookingDetail.class);
    }

    @Test
    public void invalidateTest() {
        when(redisTemplate.delete(any(String.class))).thenReturn(true);
        redisService.invalidate("testuser", Services.BOOKING);
        verify(redisService).invalidate("testuser", Services.BOOKING);
    }

}
