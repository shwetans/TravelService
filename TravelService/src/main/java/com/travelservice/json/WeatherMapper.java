package com.travelservice.json;

import com.travelservice.db.WeatherEntity;
import com.travelservice.incoming.Weather;

public class WeatherMapper {

	public static WeatherEntity mapIncomingToDB(Weather weat) {
		WeatherEntity entity = new WeatherEntity();
		entity.setId(weat.getId());
		entity.setDate(weat.getDate());
		entity.setLocation(LocationMapper.mapIncomingToDB(weat.getLocation()));
		entity.setTemperature(weat.getTemperature());
		return entity;
	}
	
	public static Weather mapDBToIncoming(WeatherEntity entity) {
		Weather weat = new Weather();
		weat.setId(entity.getId());
		weat.setDate(entity.getDate());
		weat.setLocation(LocationMapper.mapDBToIncoming(entity.getLocation()));
		weat.setTemperature(entity.getTemperature());
		return weat;
	}

}
