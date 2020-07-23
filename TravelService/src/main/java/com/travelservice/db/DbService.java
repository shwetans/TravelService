package com.travelservice.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.travelservice.exceptions.EntryAlreadyExistsException;
import com.travelservice.incoming.Weather;
import com.travelservice.json.WeatherMapper;
import com.travelservice.repositories.LocationRepository;
import com.travelservice.repositories.WeatherRepository;

@Service
public class DbService {


	Logger logger = LoggerFactory.getLogger(DbService.class);
	
	@Autowired
	private LocationRepository locRepository;
	
	@Autowired 
	private WeatherRepository wInfoRepository;
	
	/**
	 * Persist weather info to DB.
	 * @param winfo      Weather information to be persisted.  
	 * @throws EntryAlreadyExistsException
	 * @return 
	 */
	public void addWeatherEntry(Weather winfo) throws EntryAlreadyExistsException{
		
		Integer wInfoId = winfo.getId();
		if(wInfoRepository.existsById(wInfoId))
		{
			logger.info("ERROR : ENTRY ALREADY EXISTS . Id = " + wInfoId);
			throw new EntryAlreadyExistsException(" ENTRY ALREADY EXISTS . Id =" + wInfoId);
		}

		WeatherEntity wEntity = WeatherMapper.mapIncomingToDB(winfo);

		LocationEntity locEntity = locRepository.findLocationByLatAndLon(winfo.getLocation().getLat(),winfo.getLocation().getLon());
		
		if(locEntity != null) {
			wEntity.setLocation(locEntity);
		}
		
		if(wEntity != null)
			wInfoRepository.save(wEntity); 
		
	}
	
	/**
	 * Retrieve weather date for a specific date from DB.
	 * @param date requested date for weather data 
	 * @return List of Weather data for the specified date.
	 */
	public List<Weather> retrieveWeatherDataByDate(Date date) {
		
		logger.debug("Fetching Weather data ");
		List <WeatherEntity> wEntityList = wInfoRepository.findByDate(date);
		List <Weather> weatList = new ArrayList<Weather>();
		for(WeatherEntity entity: wEntityList)
			weatList.add(WeatherMapper.mapDBToIncoming(entity));
		return weatList;
	}

	/**
	 * Retrieve all weather data sorted by Id.
	 * @return List of all Weather data from the DB.
	 */
	public List<Weather>retrieveAllWeatherDataSortedById() {
		logger.debug("Fetching Weather data ");

		List <WeatherEntity> wEntityList = wInfoRepository.findAll(Sort.by("Id"));
			
		List <Weather> weatList = new ArrayList<Weather>();
		for(WeatherEntity entity: wEntityList)
			weatList.add(WeatherMapper.mapDBToIncoming(entity));
		return weatList;
	}
	
	/**
	 * Erase the Weather data from DB.
	 */
	public void eraseWeatherData() {
		
		logger.debug("Deleting all weather data" );
		wInfoRepository.deleteAll();
		locRepository.deleteAll();
	}	
}
