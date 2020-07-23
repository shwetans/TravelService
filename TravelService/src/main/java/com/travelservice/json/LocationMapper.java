package com.travelservice.json;


import com.travelservice.db.LocationEntity;
import com.travelservice.incoming.Location;

public class LocationMapper {

	public static LocationEntity mapIncomingToDB(Location loc) {
		LocationEntity entity = new LocationEntity();
		entity.setLatitude(loc.getLat());
		entity.setLongitude(loc.getLon());
		entity.setCity(loc.getCity());
		entity.setState(loc.getState());
		return entity;
	}
	
	public static Location mapDBToIncoming(LocationEntity entity) {
		Location loc = new Location();
		loc.setLat(entity.getLatitude());
		loc.setLon(entity.getLongitude());
		loc.setCity(entity.getCity());
		loc.setState(entity.getState());
		return loc;
	}

}
