package com.travelservice.web;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.travelservice.db.DbService;
import com.travelservice.exceptions.EntryAlreadyExistsException;
import com.travelservice.exceptions.InvalidJsonException;
import com.travelservice.incoming.Weather;
import com.travelservice.json.Parser;

@Controller
public class RestApiWeather {

	Logger logger = LoggerFactory.getLogger(RestApiWeather.class);
	
	@Autowired 
	private DbService dbService;
	
	/**
	 * Save the received JSON format weather data to the database. Duplicate entries are not allowed and 
	 * flagged by sending a HttpStatus.BAD_REQUEST to the client. Allows adding an array of weather entries 
	 * in JSON format.
	 * @param jsonWeatherInfo  Weather information to be saved.
	 * @return HttpStatus.BAD_REQUEST - Entry already exists for an Id, JSON format is invalid, JSON could not be parsed.
	 * 			HttpStatus.OK  Weather information added successfully. 
	 * @throws Exception
	 */
	@RequestMapping(value="/weather", method=RequestMethod.POST)
	@ResponseStatus
	public ResponseEntity<String> addWeather(@RequestBody String jsonWeatherInfo) throws Exception{
		try {

			logger.info("Received : Adding weather entr(y|ies).");
			Weather[] wInfoList = Parser.parseIncomingData(jsonWeatherInfo);
			
			for(Weather winfo : wInfoList)
				dbService.addWeatherEntry(winfo);
		}
		catch(EntryAlreadyExistsException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		} 
		catch(InvalidJsonException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<String>(HttpStatus.CREATED);		 
	}

	/**
	 * Retrieve all weather information for a specific date when the optional date query param is specified in the format ?date=YYYY-MM-DD
	 * else Retrieve all weather information available. 
	 * @param date	 requested date
	 * @return	List of Weather information in JSON  
	 */
	@RequestMapping(value="/weather", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getWeatherData(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Optional<Date> date){
		
		List <Weather> winfoList = null;
		try {
			logger.debug("Fetching Weather data ");
			
			if(date.isPresent())
				winfoList = dbService.retrieveWeatherDataByDate(date.get());
			else
				winfoList = dbService.retrieveAllWeatherDataSortedById();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(winfoList);
	}
	
	/**
	 * Erase all weather information.
	 * @return HttpStatus.OK
	 */
	
	@RequestMapping(value="/erase" , method=RequestMethod.DELETE)
	@ResponseStatus
	public ResponseEntity<String> deleteWeatherEntry() {
	
		logger.debug("Deleting all weather data" );
		dbService.eraseWeatherData();
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
}
