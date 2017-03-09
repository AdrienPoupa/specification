package airport;

class Flight {
    private int type;
    private int id;

    public Flight(){
        this.id = 0;
    }

    public Flight(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
