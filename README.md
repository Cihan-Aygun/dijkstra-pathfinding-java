# Adana Journey Planner (Dijkstra's Algorithm)

An intelligent pathfinding application that calculates the most efficient bus routes in Adana using custom-built graph structures.

## 🚀 Key Features
- [cite_start]**Graph Implementation:** Built a **Directed Weighted Graph** using an **Adjacency List** for memory efficiency[cite: 13, 14, 15].
- [cite_start]**Custom Dijkstra:** Implemented Dijkstra's algorithm from scratch without external libraries[cite: 18].
- [cite_start]**Dual Criteria Optimization:** 1. **Fewest Stops:** Uses BFS logic within Dijkstra by setting uniform weights[cite: 22].
  2. [cite_start]**Shortest Distance:** Uses actual meter-based distances for precision[cite: 23].

## 🛠 Tech Stack
- **Language:** Java
- [cite_start]**Data Structures:** HashMap (for Stop mapping), PriorityQueue (for node exploration), Adjacency List[cite: 11, 15, 19].
- [cite_start]**File I/O:** BufferedReader for efficient parsing of transit data[cite: 9].

## 📈 Technical Insight
The core of the project involves parsing spatial data and representing it as a network. To ensure optimal performance, I utilized a `PriorityQueue` to maintain a $O((V + E) \log V)$ time complexity, where $V$ is the number of stops and $E$ is the number of bus lines.
## 📊 Example Output
![Adana Journey Planner Output](output_example.png)
