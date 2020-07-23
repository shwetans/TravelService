package com.travelservice.repositories;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.travelservice.db.WeatherEntity;

public interface WeatherRepository extends JpaRepository <WeatherEntity,Integer> {

	List<WeatherEntity> findByDate(@Param ("d") Date d); 
}
