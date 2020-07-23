package com.travelservice.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestApiWeatherTest {

	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private TestRestTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void getWeatherTest() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "weather",
                String.class);
        
        String expected = "[]";
        assertEquals(MediaType.APPLICATION_JSON , response.getHeaders().getContentType() );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void addWeatherTestWithInvalidJSON() throws Exception {
    	
        String wInfo = "[{\"id\":3,\"date\":\"2000-01-03\",\"location\":{\"lat\":36.1189\"lon\":-86.6892,\"city\":\"Palo Alto\",\"state\":\"California\"},\"temperature\":[\"37.3\",\"36.8\",\"36.4\",\"36\",\"35.6\",\"35.3\",\"35\",\"34.9\",\"35.8\",\"38\",\"40.2\",\"42.3\",\"43.8\",\"44.9\",\"45.5\",\"45.7\",\"44.9\",\"43\",\"41.7\",\"40.8\",\"39.9\",\"39.2\",\"38.6\",\"38.1\"]}]";    	
        ResponseEntity<String> response = template.postForEntity(base.toString() + "weather",wInfo, String.class);
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    
    @Test
    public void addWeatherTestWithInvalidDate() throws Exception {
    	
        String wInfo = "[{\"id\":3,\"date\":\"2000-23-03\",\"location\":{\"lat\":36.1189,\"lon\":-86.6892,\"city\":\"Palo Alto\",\"state\":\"California\"},\"temperature\":[\"37.3\",\"36.8\",\"36.4\",\"36\",\"35.6\",\"35.3\",\"35\",\"34.9\",\"35.8\",\"38\",\"40.2\",\"42.3\",\"43.8\",\"44.9\",\"45.5\",\"45.7\",\"44.9\",\"43\",\"41.7\",\"40.8\",\"39.9\",\"39.2\",\"38.6\",\"38.1\"]}]";    	
        ResponseEntity<String> response = template.postForEntity(base.toString() + "weather",wInfo, String.class);
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    
    @Test
    public void addWeatherTest() throws Exception {
    	
        String wInfo = "[{\"id\":3,\"date\":\"2000-01-03\",\"location\":{\"lat\":36.1189,\"lon\":-86.6892,\"city\":\"Palo Alto\",\"state\":\"California\"},\"temperature\":[\"37.3\",\"36.8\",\"36.4\",\"36\",\"35.6\",\"35.3\",\"35\",\"34.9\",\"35.8\",\"38\",\"40.2\",\"42.3\",\"43.8\",\"44.9\",\"45.5\",\"45.7\",\"44.9\",\"43\",\"41.7\",\"40.8\",\"39.9\",\"39.2\",\"38.6\",\"38.1\"]}]";    	
        ResponseEntity<String> response = template.postForEntity(base.toString() + "weather",wInfo, String.class);
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        ResponseEntity<String> response1 = template.getForEntity(base.toString() + "weather", String.class);
        
        assertEquals(MediaType.APPLICATION_JSON , response1.getHeaders().getContentType() );
        assertEquals(HttpStatus.OK, response1.getStatusCode());
        assertEquals(wInfo, response1.getBody());
    }
    
    @Test
    public void deleteWeatherData() throws Exception {

        String expected = "[]";
        
    	template.delete(base.toString() + "erase");
        
        ResponseEntity<String> response = template.getForEntity(base.toString() + "weather", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }
}



/*
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class RestApiWeatherTest {
	@Autowired
	private MockMvc mvc;

	@Test
	public void getAllWeather() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/weather").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void addWeather() throws Exception {
		String content = 
		mvc.perform(MockMvcRequestBuilders.post("/weather").contentType(MediaType.APPLICATION_JSON).content(content))
				
				.andExpect(status().isOk());
	}
	
}

*/
