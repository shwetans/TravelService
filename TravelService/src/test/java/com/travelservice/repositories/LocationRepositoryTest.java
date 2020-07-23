package com.travelservice.repositories;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.travelservice.db.LocationEntity;


@RunWith(SpringRunner.class)
@DataJpaTest
public class LocationRepositoryTest {

	private static final int NO_ENTRIES = 0;
	private static final int ONE_ENTRY = 1;
	private static final Float lat = 29.7858F;
	private static final Float lon = 95.8245F; 
	private static final String city="Katy";
	private static final String state = "Texas";
	
    @Autowired
    private LocationRepository locRepository;
    
    private LocationEntity entity ;
    
	@Before
	public void setUp() throws Exception {
	        entity = new LocationEntity();
	        entity.setCity(city);
	        entity.setState(state);
	        entity.setLatitude(lat);
	        entity.setLongitude(lon);

    }
   
    @Test
    public void testRetrieveWhenEmpty() {

        List<LocationEntity> entries = locRepository.findAll();
     
        assertEquals(NO_ENTRIES,entries.size());
    }   

    @Test
    public void testSave() {
    	
         LocationEntity saved = locRepository.save(entity);
        
        assertEquals(entity.getCity(), saved.getCity());

    }   
    
    @Test
    public void testRetrieve() {

     	locRepository.save(entity);
     
        List<LocationEntity> entries = locRepository.findAll();
     
        assertEquals(ONE_ENTRY,entries.size());
        assertEquals(entity.getCity(), entries.get(0).getCity());
    }   

    @Test
    public void testMatchOnRetrieveByLatAndLon() {

    	locRepository.save(entity);
     
        LocationEntity match = locRepository.findLocationByLatAndLon(lat, lon);
        assertNotNull(match);
        assertEquals(entity.getCity(), match.getCity());
    }   

    @Test
    public void testNoMatchOnRetrieveByLatAndLon() {

    	locRepository.save(entity);
     
        LocationEntity match = locRepository.findLocationByLatAndLon(lat +10, lon-20);
        assertNull(match);
    }   

    @Test
    public void testDeleteAll() {

    	locRepository.save(entity);
     
        locRepository.deleteAll();
     
        List<LocationEntity> entries = locRepository.findAll();
        
        assertEquals(NO_ENTRIES,entries.size());
    }
    
    
}
