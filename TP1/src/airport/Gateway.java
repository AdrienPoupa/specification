package airport;

public class Gateway {
    private int id;

    public Gateway(){
        id = 0;
    }

    public Gateway(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
