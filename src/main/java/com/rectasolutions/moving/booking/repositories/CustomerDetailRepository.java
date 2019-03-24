package com.rectasolutions.moving.booking.repositories;

import com.rectasolutions.moving.booking.entities.CustomerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDetailRepository extends JpaRepository<CustomerDetail,Integer> {
}
