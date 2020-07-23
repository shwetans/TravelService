package com.travelservice.json;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.travelservice.exceptions.InvalidJsonException;
import com.travelservice.incoming.Weather;

public class Parser {
	
	static Logger logger = LoggerFactory.getLogger(Parser.class);	

	static ObjectMapper mapper = new ObjectMapper();
	
    public static boolean isJSONValid(String jsonInString) {
        try {
            mapper.readTree(jsonInString);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
	/*
	 * Check if received json weather data is valid and convert to Weather Object   
	 */
    public static Weather[] parseIncomingData(String jsonWeatherInfo) throws Exception{
    	
    	try {
    		if(!isJSONValid(jsonWeatherInfo))
    		{
    			logger.debug("Invalid JSON.");
    			throw new InvalidJsonException("Invalid JSON Format." );
    		}
    	
    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    		mapper.setDateFormat(df);
    		mapper.setDefaultLeniency(false);
    		
    		// Enables us to read array of weather data with 1 or more elements .
    		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY); 
    		
    		Weather[] arrWInfo = mapper.readValue(jsonWeatherInfo, Weather[].class);
        		
    		return arrWInfo;
    	}
    	catch(InvalidFormatException e) {
    		logger.debug(e.getMessage());	
    		throw new InvalidJsonException("Invalid Date format.");
    	}
    	catch(Exception e) {
    		logger.debug(e.getMessage());
    		throw e;
    	}
    }
}
