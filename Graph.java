import java.util.*;

public class Graph {
    private Map<Integer, Stop> stops = new HashMap<>();
    private Map<Integer, List<Edge>> adjList = new HashMap<>();
    
    
    private Map<Integer, String> lineIdToName = new HashMap<>();

    public void addStop(Stop stop) {
        stops.put(stop.getId(), stop);
        adjList.put(stop.getId(), new ArrayList<>());
    }

    public void addEdge(int fromId, int toId, int distance, int lineId) {
        if (!adjList.containsKey(fromId)) {
            adjList.put(fromId, new ArrayList<>());
        }
        adjList.get(fromId).add(new Edge(toId, distance, lineId));
    }
    
    
    public void addLineName(int id, String visibleName) {
        lineIdToName.put(id, visibleName);
    }

   
    public String getLineName(int id) {
        return lineIdToName.getOrDefault(id, String.valueOf(id));
    }

    public Stop getStop(int id) { return stops.get(id); }
    public List<Edge> getNeighbors(int id) { return adjList.get(id); }
    public int getStopCount() { return stops.size(); }
}