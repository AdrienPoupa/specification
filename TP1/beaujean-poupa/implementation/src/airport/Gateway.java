package airport;

class Gateway {
    private int id;
    private Boolean isFree;

    public Gateway(){
        id = 0;
    }

    public Gateway(int id, Boolean isFree){
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
