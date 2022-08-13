## Technologies used

1. Java (Programming Language)
2. Spring Boot (Application Platform)
3. Spring Data JPA (Data persistence)
4. PostgreSQL (Database)
5. JUnit, with Spring Testing (Unit & Integration Testing)

## Features

This application has a few REST end-points that provide bookings information: 

1. GET  /bookings                            - get all bookings
2. POST /bookings                            - save booking
3. GET  /bookings/{bookingId}                - get booking by id
4. GET  /bookings/flight/{flightId}          - get all bookings by flight
5. GET  /bookings/flight/{flightId}/count    - get count of bookings by flight

. End-point that provide flights information:
1. GET  /flights                    - get all flights
2. GET  /flights/{flightId}         - get flight by id
3. POST /flights                    - save flight

. End-point that provide airport information:
1. GET  /airports                   - get all airports
2. GET  /airports/{flightId}        - get airport by id
3. POST /airports                   - save airport

. End-point that provide passenger information:
1. GET  /passengers                 - get all passengers
2. GET  /passengers/{flightId}      - get passenger by id
3. POST /passengers                 - save passenger
