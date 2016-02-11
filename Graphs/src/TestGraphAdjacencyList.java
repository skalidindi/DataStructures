import java.util.List;
import java.util.stream.Collectors;

public class TestGraphAdjacencyList {
	public static void main(String[] args) {
		GraphAdjacencyList<String> g = new GraphAdjacencyList<String> ();
		g.addVertex("A");
		g.addVertex("B");
		g.addVertex("C");
		g.addVertex("D");
		g.addVertex("E");
		g.addVertex("F");
		g.addVertex("G");
		g.addVertex("H");

		g.connect("A", "B", 20);
		g.connect("A", "D", 80);
		g.connect("A", "G", 90);
		g.connect("B", "F", 10);
		g.connect("C", "H", 20);
		g.connect("C", "F", 50);
		g.connect("C", "D", 10);
		g.connect("D", "G", 20);
		g.connect("D", "C", 10);
		g.connect("E", "B", 50);
		g.connect("E", "G", 30);
		g.connect("F", "C", 10);
		g.connect("F", "D", 40);
		g.connect("G", "A", 20);
		
		System.out.println("Number of vertices: " + g.numVertices());
		System.out.println("Number of edges: " + g.numEdges());
		for (Vertex<String> vertex : g.vertices()) {
			System.out.print(vertex.data() + ": ");
			
			String neighbors = vertex.getVerticesConnectedTo().stream().
					map(neighbor -> neighbor.data()).collect(Collectors.joining(", "));
			System.out.println(neighbors);
		}
		System.out.println("-----------------------------------------------------------");
//		List<String> order = g.shortestPathDijkstras("A", "E");
//		System.out.println(g.shortestDistanceDijkstras("A", "E"));
//		for (String s : order) {
//			System.out.println(s);
//		}
		List<String> order = g.shortestPathBfs("A", "F");
		for (String s : order) {
			System.out.println(s);
		}
	}
}
