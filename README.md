# TravelService
REST API for Weather Service as part of the TravelServiceApplication

The project uses SpringBoot in conjunction with H2 database to implement REST API service to add,query and delete weather information.
Fasterxml/Jackson is the JSON Parser used.

Example of weather data in JSON Format provided by the client: 
 {
  "id": "1",
  "date": "2000-01-01",
  "location": {
    "lat": 36.1189,
    "lon": -86.6892,
    "city": "Palo Alto",
    "state": "California"
  },
  "temperature": [
    37.3,
    36.8,
    36.4,
    36,
    35.6,
    35.3,
    35,
    34.9,
    35.8,
    38,
    40.2,
    42.3,
    43.8,
    44.9,
    45.5,
    45.7,
    44.9,
    43,
    41.7,
    40.8,
    39.9,
    39.2,
    38.6,
    38.1
  ]
}

Supported API:
1. Request Type: GET 
   URL: http://localhost:80080/weather
   Action: Retrieve all weather data stored on the server. The weather data will be sorted by Id.
   Response Status: Http Status Code : 200
   Response Body: Array of all weather data available on the server. In case of no entries on the server, the array is empty.

2. Request Type: GET 
   URL: http://localhost:8080/weather?date=YYYY-MM-DD
   Action: Retrieve all weather data stored on the server for the requested date.
   Response Status: Http Status Code : 200
   Response Body: Array of all weather data available on the server for the requested date. In case of no entries on the server, the array is empty.
   
3. Request Type: POST
   URL: http://localhost:8080/weather
   Action: Add new weather record(s) to the server. Accepts single or multiple weather json entries as an array.
   Response Status: Http Status Code: 401 - If a record already exists for an ID
                    Http Status Code: 201 - Records were added successfully. 
   Response Body: NA
  
4. Request Type:DELETE
   URL: http://localhost:8080/erase
   Action: Delete all of the weather data on the server.
   Response Status: Http Status Code: 200
   Response Body: NA

NOTES : 
1. Assumption: The client is responsible for ensuring the value of Id field in the weather JSON is unique. The server does not generate the Id.
2. Known Issues: The server only checks for duplicate Id, but allows an entry to be added more then once as long as the Id is modified to be unique.
3. Next Version Additions:
    1. Extend support for other databases.
    2. Add role based authorization.
    3. Add support to generate API documentation.
