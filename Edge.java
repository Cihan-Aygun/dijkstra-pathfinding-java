public class Edge {
    private int toStopId;
    private int weight;   
    private int lineId;   

    public Edge(int toStopId, int weight, int lineId) {
        this.toStopId = toStopId;
        this.weight = weight;
        this.lineId = lineId;
    }

    public int getToStopId() { return toStopId; }
    public int getWeight() { return weight; }
    public int getLineId() { return lineId; }
}