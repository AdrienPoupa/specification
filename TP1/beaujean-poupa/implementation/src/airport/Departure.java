package airport;


import java.util.Collection;
import java.util.concurrent.Semaphore;

public class Departure extends Thread {
    private volatile Flight flight;
    private volatile Semaphore semGateway;
    private volatile Semaphore semBoardingRoom;
    private volatile Collection<Gateway> gateways;
    private volatile Collection<BoardingRoom> boardingRooms;

    public Departure(Flight flight, Semaphore semGateway, Semaphore semBoardingRoom, Collection<Gateway> gateways, Collection<BoardingRoom> boardingRooms){
        this.flight = flight;
        this.semGateway = semGateway;
        this.semBoardingRoom = semBoardingRoom;
        this.gateways = gateways;
        this.boardingRooms = boardingRooms;
    }

    @Override
    public void run(){
        System.out.println("## " + flight.getId() + " / Depart vol " + flight.getId());

        try {
            semBoardingRoom.acquire();
            semGateway.acquire();

            Gateway gatewayDeparture = null;
            BoardingRoom boardingRoomDeparture = null;

            try {
                synchronized (boardingRooms) {
                    for (BoardingRoom boardingRoom : boardingRooms) {
                        if (boardingRoom.getFree()) {
                            boardingRoom.setFree(false);
                            boardingRoomDeparture = boardingRoom;
                            break;
                        }
                    }
                }

                synchronized (gateways) {
                    for (Gateway gateway : gateways) {
                        if (gateway.getFree()) {
                            gateway.setFree(false);
                            gatewayDeparture = gateway;
                            break;
                        }
                    }
                }

                Thread.sleep(500);

                System.out.println("# " + flight.getId() + " / Boarding Room : " + boardingRoomDeparture.getId());
                System.out.println("# " + flight.getId() + " / Gateway : " + gatewayDeparture.getId());

                System.out.println("# " + flight.getId() + " / Libere Boarding Room : " + boardingRoomDeparture.getId());
                System.out.println("# " + flight.getId() + " / Libere Gateway : " + gatewayDeparture.getId());
            }
            finally {
                if(boardingRoomDeparture != null) boardingRoomDeparture.setFree(true);
                if(gatewayDeparture != null) gatewayDeparture.setFree(true);

                semGateway.release();
                semBoardingRoom.release();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
