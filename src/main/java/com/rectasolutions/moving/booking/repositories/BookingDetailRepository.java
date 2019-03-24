package com.rectasolutions.moving.booking.repositories;

import com.rectasolutions.moving.booking.entities.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDetailRepository extends JpaRepository<BookingDetail,Integer> {
}
