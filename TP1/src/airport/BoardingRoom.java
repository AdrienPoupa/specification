package airport;

public class BoardingRoom {
    private int id;

    public BoardingRoom(){
        id = 0;
    }

    public BoardingRoom(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
