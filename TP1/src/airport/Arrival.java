package airport;

import java.util.Collection;
import java.util.concurrent.Semaphore;

class Arrival extends Thread {
    private volatile Flight flight;
    private volatile Semaphore semGateway;
    private volatile Semaphore semBoardingRoom;
    private volatile Collection<Gateway> gateways;
    private volatile Collection<BoardingRoom> boardingRooms;
    private int type;

    public static int NORMAL = 0;
    public static int QUICK  = 1;

    public Arrival(Flight flight, int type, Semaphore semGateway, Semaphore semBoardingRoom, Collection<Gateway> gateways, Collection<BoardingRoom> boardingRooms){
        this.flight = flight;
        this.type = type;
        this.semGateway = semGateway;
        this.semBoardingRoom = semBoardingRoom;
        this.gateways = gateways;
        this.boardingRooms = boardingRooms;
    }

    @Override
    public void run(){
        System.out.println("## " + flight.getId() + " / Arrivee vol " + flight.getId() + " / "+ (type == NORMAL ? "NORMAL" : "QUICK"));

        try {
            if(type == QUICK) semBoardingRoom.acquire();
            semGateway.acquire();

            try {
                Gateway gatewayDeparture = null;
                BoardingRoom boardingRoomDeparture = null;

                synchronized (gateways) {
                    for (Gateway gateway : gateways) {
                        if (gateway.getFree()) {
                            gateway.setFree(false);
                            gatewayDeparture = gateway;
                            break;
                        }
                    }
                }

                if (type == QUICK) {
                    synchronized (boardingRooms) {
                        for (BoardingRoom boardingRoom : boardingRooms) {
                            if (boardingRoom.getFree()) {
                                boardingRoom.setFree(false);
                                boardingRoomDeparture = boardingRoom;
                                break;
                            }
                        }
                    }
                }

                Thread.sleep(250);

                System.out.println("# " + flight.getId() + " / Gateway : " + gatewayDeparture.getId());
                if (type == QUICK)
                    System.out.println("# " + flight.getId() + " / Boarding Room : " + boardingRoomDeparture.getId());

                gatewayDeparture.setFree(true);
                if (type == QUICK) boardingRoomDeparture.setFree(true);

                System.out.println("# " + flight.getId() + " / Libere Gateway : " + gatewayDeparture.getId());
                if (type == QUICK)
                    System.out.println("# " + flight.getId() + " / Libere Boarding Room : " + boardingRoomDeparture.getId());
            }
            finally {
                semGateway.release();
                if(type == QUICK) semBoardingRoom.release();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
