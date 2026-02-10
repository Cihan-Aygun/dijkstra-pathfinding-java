import java.util.*;

public class DijkstraSolver {

  
    public static class JourneyResult {
        public List<Stop> path = new ArrayList<>();
        public int totalDistance;
        public Set<Integer> linesUsed = new HashSet<>();
        public int transfers;
    }

  
    private class Node implements Comparable<Node> {
        int id;
        int cost; 

        public Node(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }

       
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public JourneyResult findShortestPath(Graph graph, int startId, int endId, int criteria) {
       
        Map<Integer, Integer> dist = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>(); // Yolu geri bulmak için
        Map<Integer, Integer> lineUsedToReach = new HashMap<>(); // O durağa hangi hatla geldik
        PriorityQueue<Node> pq = new PriorityQueue<>();

       
        
        dist.put(startId, 0);
        pq.add(new Node(startId, 0));

        
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.id;

            
            if (u == endId) break;

            
            if (dist.containsKey(u) && current.cost > dist.get(u)) continue;

           
            List<Edge> neighbors = graph.getNeighbors(u);
            if (neighbors == null) continue;

            for (Edge edge : neighbors) {
                int v = edge.getToStopId();
                
                
                int weight = (criteria == 1) ? 1 : edge.getWeight();
                
                int newDist = dist.getOrDefault(u, Integer.MAX_VALUE) + weight;
                int oldDist = dist.getOrDefault(v, Integer.MAX_VALUE);

                
                if (newDist < oldDist) {
                    dist.put(v, newDist);
                    parent.put(v, u);
                    lineUsedToReach.put(v, edge.getLineId());
                    pq.add(new Node(v, newDist));
                }
            }
        }

      
        if (!parent.containsKey(endId)) {
            return null; 
        }

        JourneyResult result = new JourneyResult();
        int curr = endId;
        int lastLine = -1;
        int transferCount = 0;

       
        while (curr != startId) {
            result.path.add(0, graph.getStop(curr)); 
            
            int p = parent.get(curr);
            int lineId = lineUsedToReach.get(curr);
            result.linesUsed.add(lineId);

          
            if (lastLine != -1 && lastLine != lineId) {
                transferCount++;
            }
            lastLine = lineId;
            
          
            for(Edge e : graph.getNeighbors(p)) {
                if(e.getToStopId() == curr && e.getLineId() == lineId) {
                    result.totalDistance += e.getWeight();
                    break;
                }
            }

            curr = p; 
        }
        
        result.path.add(0, graph.getStop(startId));
        
        
        result.transfers = transferCount; 
        
        return result;
    }
}