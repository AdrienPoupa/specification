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

        for(int i = 0; i < 10000; i++){
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

/*
 * On utillise deux sémaphores une pour les salles d'attente initialisée à 4 et une pour les portes initialisée à 3.
 * Chaque thread fera ensuite un acquire sur ces deux sémaphores ce qui garantira qu'il pourra avoir accès à toutes les
 * ressources nécessaires.
 *
 * L'ordre des acquire est également le même pour chaque thread ce qui permet d'éviter les situations où 2 threads
 * acquiert chacun respectivement la dernière porte et la dernière salle puis demande ensuite à acquérir une salle et
 * porte ce qui donnerait une deadlock
 *
 * Au niveau des deadlocks, si jamais dans le code des threads, après l'acquisition des ressources une exception est
 * levée empêchant l'exécution complète nous avons placé les releases des sempahores dans une clause finally pour être
 * sûr que toutes les ressources soient bien libérées
 *
 * Dans ce programme, on pourra obtenir une deadlock dans le cas où les 4 salles d'embarquement sont réservées pour les
 * départs et que pendant le temps d'attente, 3 vols avec escale réservent les portes.
 *
 * On génère le reacability graph et avec l'outil salt on teste les propriétés.
 *  - On essaie de savoir s'il n'y a jamais de deadlock : - <> dead (non eventually dead), le programme trouve un contre
 *    exemple, il y a donc deadlock
 *
 * On change le cahier des charges pour résoudre ce problème :
 *  - A l'embarquement, on réserve en même temps la salle et la porte
 */