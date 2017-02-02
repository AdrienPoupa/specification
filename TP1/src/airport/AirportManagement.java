package airport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class AirportManagement {
    private volatile Flight flight;
    private volatile Semaphore semGateway;
    private volatile Semaphore semBoardingRoom;
    private volatile Collection<Gateway> gateways;
    private volatile Collection<BoardingRoom> boardingRooms;

    public AirportManagement(int gatewaySize, int boardingRoomSize) {
        Semaphore semGateway = new Semaphore(gatewaySize);
        Semaphore semBoardingRoom = new Semaphore(boardingRoomSize);

        Collection<Gateway> gateways = Collections.synchronizedCollection(new ArrayList<Gateway>() {{
            add(new Gateway(1, true));
            add(new Gateway(2, true));
            add(new Gateway(3, true));
        }});

        Collection<BoardingRoom> boardingRooms = Collections.synchronizedList(new ArrayList<BoardingRoom>() {{
            add(new BoardingRoom(1, true));
            add(new BoardingRoom(2, true));
            add(new BoardingRoom(3, true));
            add(new BoardingRoom(4, true));
        }});

        Random randDepartureOrArrival = new Random();
        Random randArrivalNormalOrQuick = new Random();

        for(int i = 0; i < 100; i++){
            Flight flight = new Flight(i);

            if(randDepartureOrArrival.nextBoolean()) { //Departure
                Departure departure = new Departure(flight, semGateway, semBoardingRoom, gateways, boardingRooms);
                departure.start();
            }
            else { //Arrival
                int type = randArrivalNormalOrQuick.nextBoolean() ? Arrival.NORMAL : Arrival.QUICK;
                Arrival arrival = new Arrival(flight, type ,semGateway, semBoardingRoom, gateways, boardingRooms);
                arrival.start();
            }
        }
    }
}
