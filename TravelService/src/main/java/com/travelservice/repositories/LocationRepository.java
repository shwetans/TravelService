package com.travelservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.travelservice.db.LocationEntity;

public interface LocationRepository extends JpaRepository <LocationEntity,Integer> {
	
	@Query ("select l from LocationEntity l where l.latitude = :latitude and l.longitude = :longitude") 
	LocationEntity findLocationByLatAndLon(@Param("latitude") float lat, @Param("longitude")float lon);

}
