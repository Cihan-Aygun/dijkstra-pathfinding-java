import java.util.*;

public class Main {
    public static void main(String[] args) {

        FileParser parser = new FileParser();
        Graph graph = parser.createGraphFromFiles();
        
        if (graph.getStopCount() == 0) {
            System.out.println("HATA: Duraklar yüklenemedi. Dosyaların proje klasöründe olduğundan emin olun.");
            return;
        }

        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter origin stop ID: ");
        int originId = scanner.nextInt();

        System.out.print("Enter destination stop ID: ");
        int destId = scanner.nextInt();

        System.out.print("Choose criteria (1= fewest stops, 2 shortest distance): ");
        int criteria = scanner.nextInt();

       
        DijkstraSolver solver = new DijkstraSolver();
        DijkstraSolver.JourneyResult result = solver.findShortestPath(graph, originId, destId, criteria);
        
        
        System.out.println(); 
        
        if (result == null) {
            System.out.println("No path found via public transport.");
        } else {
            Stop originStop = graph.getStop(originId);
            Stop destStop = graph.getStop(destId);

            
            System.out.println("Origin: " + originStop.getName() + " (" + originStop.getId() + ")");
            
          
            System.out.println("Destination: " + destStop.getName() + " (" + destStop.getId() + ")");
            
          
            System.out.println("Path:");
            for (int i = 0; i < result.path.size(); i++) {
                Stop s = result.path.get(i);
                
                System.out.print(s.getId() + " (" + s.getName() + ")");
                
                if (i < result.path.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println(); 

            
            System.out.println("Total Distance: " + result.totalDistance + " meters");
            
            
            System.out.print("Lines Used: ");
            List<String> visibleLineNumbers = new ArrayList<>();
            for (int internalId : result.linesUsed) {
              
                visibleLineNumbers.add(graph.getLineName(internalId));
            }
            
            System.out.println(String.join(", ", visibleLineNumbers));

          
            System.out.println("Transfers: " + result.transfers);
        }
        
        scanner.close();
    }
}