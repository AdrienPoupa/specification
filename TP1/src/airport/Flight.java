package airport;

public class Flight {
    public static int NORMAL = 0;
    public static int QUICK  = 1;

    private int type;
    private int id;

    public Flight(){
        this.type = NORMAL;
        this.id = 0;
    }

    public Flight(int id, int type){
        this.type = type;
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }
}
