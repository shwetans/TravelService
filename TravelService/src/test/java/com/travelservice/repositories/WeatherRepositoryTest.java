package com.travelservice.repositories;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.junit4.SpringRunner;

import com.travelservice.db.LocationEntity;
import com.travelservice.db.WeatherEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WeatherRepositoryTest {

	private static final int NO_ENTRY = 0;
	private static final int ONE_ENTRY = 1;
	private static final float lat = 29.7858F;
	private static final float lon = 95.8245F; 
	private static final String city="Katy";
	private static final String state = "Texas";
	private static final int id = 1;
	
	 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private static final Date currDate = new Date();

	private static String[] temp = {"37.3", "36.8", "36.4", "36.0", "35.6", "35.3", "35.0", "34.9", "35.8", "38.0", "40.2", "42.3", "43.8", "44.9", "45.5", "45.7", "44.9", "43.0", "41.7",
			 				"40.8",	"39.9", "39.2", "38.6", "38.1" };

    @Autowired
    private WeatherRepository wRepository;
    
    private LocationEntity entity ;
    
    private WeatherEntity wEntity;
    
	@Before
	public void setUp() throws Exception {
		entity = new LocationEntity();
		entity.setCity(city);
		entity.setState(state);
		entity.setLatitude(lat);
		entity.setLongitude(lon);
		wEntity = new WeatherEntity();
		wEntity.setId(id);
		wEntity.setDate(currDate);
		wEntity.setLocation(entity);
		wEntity.setTemperature(temp);
    }

    @Test
    public void testRetrieveWhenEmpty() {

        List<WeatherEntity> entries = wRepository.findAll();
     
        assertEquals(NO_ENTRY,entries.size());
    }   

    @Test
    public void testSave() {
    	
        WeatherEntity saved = wRepository.save(wEntity);
        
        assertEquals(wEntity.getId(), saved.getId());

    }   
    
    @Test
    public void testRetrieve() {

        WeatherEntity saved = wRepository.save(wEntity);
     
        List<WeatherEntity> entries = wRepository.findAll();
     
        assertEquals(ONE_ENTRY,entries.size());
        assertEquals(wEntity.getId(), saved.getId());
    }   

    @Test
    public void testMatchOnRetrieveByDate() {

        wRepository.save(wEntity);
        
        List<WeatherEntity> matchList = wRepository.findByDate(currDate);
        assertEquals(ONE_ENTRY,matchList.size());
        assertEquals(wEntity.getId(), matchList.get(0).getId());
    }   

    @Test
    public void testNoMatchOnRetrieveByLatAndLon() {

    	String pattern = "yyyy-MM-dd";
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    	Date testDate;
		try {
			testDate = simpleDateFormat.parse("2001-01-01");
	    	wRepository.save(wEntity);
	        
	        List<WeatherEntity> matchList = wRepository.findByDate(testDate);
	        assertEquals(NO_ENTRY,matchList.size());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }   

    @Test
    public void testDeleteAll() {

    	wRepository.save(wEntity);
     
        wRepository.deleteAll();
     
        List<WeatherEntity> entries = wRepository.findAll();
        
        assertEquals(NO_ENTRY,entries.size());
    }
   
}
