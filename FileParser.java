import java.io.*;
import java.util.*;

public class FileParser {

    public Graph createGraphFromFiles() {
        Graph graph = new Graph();
        System.out.println("Loading Adana Bus Network...");

        try {
            
            BufferedReader stopReader = new BufferedReader(new FileReader("Stop.txt"));
            stopReader.readLine(); 
            String line;
            while ((line = stopReader.readLine()) != null) {
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1].trim();
                double x = Double.parseDouble(parts[2].replace(",", "."));
                double y = Double.parseDouble(parts[3].replace(",", "."));
                graph.addStop(new Stop(id, name, x, y));
            }
            stopReader.close();

            
            BufferedReader lineReader = new BufferedReader(new FileReader("Line.txt"));
            lineReader.readLine(); 
            while ((line = lineReader.readLine()) != null) {
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);      
                String visibleNo = parts[1].trim();     
                graph.addLineName(id, visibleNo);
            }
            lineReader.close();

           
            Map<String, Integer> distanceMap = new HashMap<>();
            BufferedReader distReader = new BufferedReader(new FileReader("Distance.txt"));
            distReader.readLine(); 
            while ((line = distReader.readLine()) != null) {
                String[] parts = line.split(";");
                distanceMap.put(parts[0] + "-" + parts[1], Integer.parseInt(parts[2]));
            }
            distReader.close();

           
            BufferedReader tripReader = new BufferedReader(new FileReader("Trip.txt"));
            tripReader.readLine();
            
            Integer prevStopId = null;
            String prevLineKey = "";

            while ((line = tripReader.readLine()) != null) {
                String[] parts = line.split(";");
                int lineId = Integer.parseInt(parts[0]);
                int direction = Integer.parseInt(parts[1]);
                int order = Integer.parseInt(parts[2]);
                int currentStopId = Integer.parseInt(parts[3]);

                String currentLineKey = lineId + "-" + direction;

                if (order == 1 || !currentLineKey.equals(prevLineKey)) {
                    prevStopId = currentStopId;
                    prevLineKey = currentLineKey;
                    continue;
                }

                if (prevStopId != null) {
                    String key = prevStopId + "-" + currentStopId;
                    int dist = distanceMap.getOrDefault(key, 0);
                   
                    if (dist == 0) dist = distanceMap.getOrDefault(currentStopId + "-" + prevStopId, 0);

                    graph.addEdge(prevStopId, currentStopId, dist, lineId);
                }
                prevStopId = currentStopId;
                prevLineKey = currentLineKey;
            }
            tripReader.close();

        } catch (IOException e) {
            System.out.println("Error reading files: " + e.getMessage());
        }
        return graph;
    }
}