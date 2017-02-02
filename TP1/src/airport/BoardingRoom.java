package airport;

class BoardingRoom {
    private int id;
    private Boolean isFree;

    public BoardingRoom(){
        id = 0;
    }

    public BoardingRoom(int id, Boolean isFree){
        this.id = id;
        this.isFree = isFree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }
}
