package com.rectasolutions.moving.booking.api;

import com.rectasolutions.moving.booking.entities.VehicleCategory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class VehicleApi {

    @Value("${vehicleApiPath}")
    private String vehicleApiPath;

    private RestTemplate restTemplate;

    public VehicleApi (){
        restTemplate =new RestTemplate();
    }

    public VehicleCategory getVehicleCategoryByDistance(double distance) {
        String path = vehicleApiPath+"/categories/distance/"+distance;
        VehicleCategory vehicleCategory = restTemplate.getForObject(path,VehicleCategory.class);
        return vehicleCategory;
    }
    public VehicleCategory getVehicleCategoryByPayload(double distance,double payload){
        String path = vehicleApiPath+"/categories/distance/"+distance+"/payload/"+payload;
        VehicleCategory vehicleCategory = restTemplate.getForObject(path,VehicleCategory.class);
        return vehicleCategory;
    }


}
