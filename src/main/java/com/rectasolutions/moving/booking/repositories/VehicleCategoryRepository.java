package com.rectasolutions.moving.booking.repositories;

import com.rectasolutions.moving.booking.entities.VehicleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VehicleCategoryRepository extends JpaRepository<VehicleCategory,Integer> {

    @Query(value = "SELECT u.* FROM Vehicle_Category u WHERE u.delivery_Distance >= ?1 order by u.id limit 1",
            nativeQuery = true)
    Optional<VehicleCategory> findFirstByDistance(double distance);

    @Query(value = "SELECT u.* FROM Vehicle_Category u WHERE u.delivery_Distance >= ?1" +
            " and u.payload >=?2 order by u.id limit 1",
            nativeQuery = true)
    Optional<VehicleCategory> findFirstByPayload (double distance,double payload);
}
