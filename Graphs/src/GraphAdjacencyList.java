import java.util.AbstractMap;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * @author skalidindi
 *
 */
public class GraphAdjacencyList<T extends Comparable<? super T>> {
    private final Map<T, Vertex<T>> adjacencyList;
    
    public GraphAdjacencyList() {
    	adjacencyList = new HashMap<T, Vertex<T>>();
    }
    
    public boolean containsVertex(T data) {
    	return adjacencyList.containsKey(data);
    }
    
    public boolean addVertex(T data) {
    	if (containsVertex(data)) {
    		return false;
    	}
    	adjacencyList.put(data, new Vertex<T>(data));
    	return true;
    }

	public boolean connect(T fromData, T toData, int distance) {
		if (containsVertex(fromData)) {
			Vertex<T> fromVertex = adjacencyList.get(fromData);
			Vertex<T> toVertex = adjacencyList.get(toData);
			fromVertex.addEdge(toVertex, distance);
			return true;
		}
		return false;
	}

	public Collection<Vertex<T>> vertices() {
		return adjacencyList.values();
	}
	
	public int numVertices() {
		return adjacencyList.size();
	}
	
	public int numEdges() {
		return adjacencyList.values().stream().mapToInt(Vertex::getEdgeCount).sum();
	}

	public List<Vertex<T>> getVerticesConnectedTo(T data) {
		return adjacencyList.get(data).getVerticesConnectedTo();
	}
	
	public List<Edge<T>> getEdgesConnectedTo(T data) {
		return adjacencyList.get(data).edges();
	}
	
	public void depthFirstSearch(T data) {
		if (!containsVertex(data)) {
			return;
		}
		Set<Vertex<T>> visitedMap = new HashSet<Vertex<T>>();
		_depthFirstSearch(adjacencyList.get(data), visitedMap);
	}
	
	public void _depthFirstSearch(Vertex<T> vertex, Set<Vertex<T>> visitedMap) {
		System.out.println(vertex.data());
		visitedMap.add(vertex);
		for (Vertex<T> neighbor : vertex.getVerticesConnectedTo()) {
			if (!visitedMap.contains(neighbor)) {
				_depthFirstSearch(neighbor, visitedMap);
			}
		}
	}
	
	public void breathFirstSearch(T data) {
		if (!containsVertex(data)) {
			return;
		}
		Set<Vertex<T>> visitedMap = new HashSet<Vertex<T>>();
		_breathFirstSearch(adjacencyList.get(data), visitedMap);		
	}
	
	public void _breathFirstSearch(Vertex<T> vertex, Set<Vertex<T>> visitedMap) {
		Queue<Vertex<T>> q = new LinkedList<Vertex<T>>();
		q.add(vertex);
		
		while (!q.isEmpty()) {
			Vertex<T> current = q.poll();
			System.out.println(current.data());
			visitedMap.add(current);
			
			for (Vertex<T> neighbor : current.getVerticesConnectedTo()) {
				if (!visitedMap.contains(neighbor)) {
					q.add(neighbor);
				}
			}
		}
	}
	
	public List<T> shortestPathDijkstras(T from, T to) {
		if (!containsVertex(from) || !containsVertex(to)) {
			return new LinkedList<>();
		}
		
		Vertex<T> start = adjacencyList.get(from);
		Vertex<T> end = adjacencyList.get(to);
		
		Map.Entry<Map<Vertex<T>, Vertex<T>>, Map<Vertex<T>, Integer>> 
			resultMaps = 
				_dijkstras(start, end);
		Map<Vertex<T>, Vertex<T>> prevMap = resultMaps.getKey();
		
		// Backtracking the path
		Vertex<T> backTrackVertex = end;
		List<T> path = new LinkedList<>();
		path.add(backTrackVertex.data());
		
		while (backTrackVertex != start) {
			backTrackVertex = prevMap.get(backTrackVertex);
			if (backTrackVertex == null) {
				return new LinkedList<>();
			}
			path.add(backTrackVertex.data());
		}
		
		Collections.reverse(path);
		return path;
	}
	
	public int shortestDistanceDijkstras(T from, T to) {
		if (!containsVertex(from) || !containsVertex(to)) {
			return 0;
		}
		
		Vertex<T> start = adjacencyList.get(from);
		Vertex<T> end = adjacencyList.get(to);
		
		Map.Entry<Map<Vertex<T>, Vertex<T>>, Map<Vertex<T>, Integer>> resultMaps = 
				_dijkstras(start, end);
		Map<Vertex<T>, Integer> distanceMap = resultMaps.getValue();
		
		
		// Backtracking the distance
		return distanceMap.get(end);
	}
	
	private Map.Entry<Map<Vertex<T>, Vertex<T>>, Map<Vertex<T>, Integer>> 
		_dijkstras(Vertex<T> start, Vertex<T> end) {
		
		Map<Vertex<T>, Vertex<T>> prevMap = new HashMap<>();
		Map<Vertex<T>, Integer> distanceMap = new HashMap<>();
		
		Queue<Vertex<T>> queue =
			    new PriorityQueue<>(Comparator.comparing(vertex -> {
			    	if (distanceMap.get(vertex) != null) {
			    		return distanceMap.get(vertex);
			    	}
			    	return Integer.MAX_VALUE;
			    }));

		// Initialize distance for all vertices
		for (Vertex<T> v: vertices()) {
			distanceMap.put(v, Integer.MAX_VALUE);
			queue.add(v);
		}
		distanceMap.put(start, 0);
		
		while (!queue.isEmpty()) {
			Vertex<T> minDistanceVertex = queue.poll();
			Integer minDistance = distanceMap.get(minDistanceVertex);

			for (Vertex<T> neighbor : minDistanceVertex.getVerticesConnectedTo()) {
				if (minDistance != Integer.MAX_VALUE) {
					int altDistance = minDistance + 
							minDistanceVertex.getEdgeDistanceTo(neighbor);
					if (altDistance < distanceMap.get(neighbor)) {
						distanceMap.put(neighbor, altDistance);
						prevMap.put(neighbor, minDistanceVertex);
					}
				}
			}
		}
		
		return new AbstractMap.SimpleEntry<>(prevMap, distanceMap);
	}
	
	public List<T> shortestPathBfs(T from, T to) {
		if (!containsVertex(from) || !containsVertex(to)) {
			return new LinkedList<>();
		}
		
		Vertex<T> start = adjacencyList.get(from);
		Vertex<T> end = adjacencyList.get(to);
		Set<Vertex<T>> visitedMap = new HashSet<Vertex<T>>();
		Map<Vertex<T>, Vertex<T>> prevMap = new HashMap<>();
		
		Queue<Vertex<T>> q = new LinkedList<Vertex<T>>();
		q.add(start);
		
		while (!q.isEmpty()) {
			Vertex<T> current = q.poll();
			visitedMap.add(current);
			
			
			for (Vertex<T> neighbor : current.getVerticesConnectedTo()) {
				if (!visitedMap.contains(neighbor)) {
					
					prevMap.put(neighbor, current);
					q.add(neighbor);
					
				}
			}
		}
		// Backtracking the path
		Vertex<T> backTrackVertex = end;
		List<T> path = new LinkedList<>();
		path.add(backTrackVertex.data());
				
		while (backTrackVertex != start) {
			backTrackVertex = prevMap.get(backTrackVertex);
			if (backTrackVertex == null) {
				return new LinkedList<>();
			}
			path.add(backTrackVertex.data());
		}
				
		Collections.reverse(path);
		return path;
	}

}
