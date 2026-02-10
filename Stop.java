public class Stop {
    
    private int id;
    private String name;
    private double x;
    private double y;

    
    public Stop(int id, String name, double x, double y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }

   
    public int getId() { return id; }
    public String getName() { return name; }

    
    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}