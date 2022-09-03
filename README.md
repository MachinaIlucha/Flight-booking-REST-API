# Flight booking system

Some time ago, when I was writing a social network project, 
I faced the problem of writing unit tests, then I decided that I needed a small project to better understand this topic.
So I wrote this project.

I hope I coped well with this task at least at the initial level.

## About project

It's basic REST spring boot application with PostgreSQL database.
With this application, users will be able to buy flight tickets.

## Technologies i have used

- Java (Programming Language)
- Spring Boot (Application Platform)
- Spring Data JPA (Data persistence)
- PostgreSQL (Database)
- JUnit, with Spring Testing (Unit & Integration Testing)
- Logger 
- lombok

## Tests
For the beginning, as we are working with json datas, I decided to create an abstract class for tests(AbstractTest)
where we will connect our MockMvc and WebApplicationContext for testing our REST application.
I also wrote a couple of methods for the convenience of working with json.
```java
@SpringBootTest
@AutoConfigureMockMvc
public class AbstractTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}
```

All test for my entities are very similar, that why i will describe only one of them:

### Lets start with Passenger tests
First test is test for getting list of all passengers,
We check that if, after request of "/passengers" url, the status 200(OK) is returned to us and the list of our passengers is greater than 0, that's mean that
there at least 1 passenger, then everything works correctly.
```java
    @Test
    public void getPassengersList() throws Exception {
        logger.info("Test started - getPassengersList");
        String url = "/passengers";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Passenger[] passengersList = super.mapFromJson(content, Passenger[].class);
        assertTrue(passengersList.length > 0);
        logger.info("Test ended - getPassengersList");
    }
```

Second test is test for getting passenger by his id,
We check that if, after request of "/passengers/4" url, the status 200(OK) is returned to us the passenger and he is not null, then everything works correctly.
```java
    @Test
    public void getPassengerById() throws Exception {
        logger.info("Test started - getPassengerById");
        String url = "/passengers/4";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Passenger returnedPassengers = super.mapFromJson(content, Passenger.class);
        assertNotNull(returnedPassengers);
        logger.info("\n" + returnedPassengers + "\n" + "Bookings:" + returnedPassengers.getBookings());
        logger.info("Test ended - getPassengerById");
    }
```

Third test is test for creating passenger,
We check that if, after request of "/passengers" with POST method, the status 201(CREATED), and is returned to us the same passenger
as we gave him, then everything works correctly.
```java
    @Test
    public void createPassenger() throws Exception {
        logger.info("Test started - createPassenger");
        String url = "/passengers";
        Passenger passenger = new Passenger(6L, "firstName", "lastName", "email", null);
        String inputJson = super.mapToJson(passenger);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        Passenger returnedPassenger = super.mapFromJson(content, Passenger.class);
        assertEquals(returnedPassenger, passenger);
        logger.info("Test ended - createPassenger");
    }
```

## A few REST end-points of my application: 

- GET  /bookings                            - get all bookings
- POST /bookings                            - save booking
- GET  /bookings/{bookingId}                - get booking by id
- GET  /bookings/flight/{flightId}          - get all bookings by flight
- GET  /bookings/flight/{flightId}/count    - get count of bookings by flight

### End-point that provide flights information:
- GET  /flights                    - get all flights
- GET  /flights/{flightId}         - get flight by id
- POST /flights                    - save flight

### End-point that provide airport information:
- GET  /airports                   - get all airports
- GET  /airports/{flightId}        - get airport by id
- POST /airports                   - save airport

### End-point that provide passenger information:
- GET  /passengers                 - get all passengers
- GET  /passengers/{flightId}      - get passenger by id
- POST /passengers                 - save passenger
