import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.stream.Collectors;

public class Vertex<T extends Comparable<? super T>> {
	private T data;
	private List<Edge<T>> edges;
	
	Vertex(T data) {
		this.data = data;
		this.edges = new Vector<>();
	}
	
	Vertex(T data, List<Edge<T>> edges) {
		this.data = data;
		this.edges = edges;
	}
	
	public T data() {
		return data;
	}
	
	public List<Edge<T>> edges() {
        return edges;
    }
	
	public boolean addEdge(Vertex<T> v, int distance) {
        if (hasEdge(v)) {
            return false;
        }
        Edge<T> newEdge = new Edge<>(this, v, distance);
        return edges.add(newEdge);
    }
	
	public boolean removeEdge(Vertex<T> v) {
		Optional<Edge<T>> edge = findEdge(v);
        if (edge.isPresent()) {
            return edges.remove(edge);
        }
        return false;
    }

    public int getEdgeCount() {
        return edges.size();
    }
	
	public boolean hasEdge(Vertex<T> v) {
        return findEdge(v).isPresent();
    }
	
	public List<Vertex<T>> getVerticesConnectedTo() {
		return edges().stream().
				map(edge -> edge.toVertex()).collect(Collectors.toList());
	}
	
	private Optional<Edge<T>> findEdge(Vertex<T> v) {		
		return v.edges().stream().
				filter(edge -> edge.isBetween(this, v)).findFirst();
    }
	
	public Vertex<T> findMinimumDistanceVertex() {
		Optional<Edge<T>> minEdge = 
				this.edges.stream().min(Comparator.comparing(Edge::distance));
		if (minEdge.isPresent()) {
            return minEdge.get().toVertex();
        }
		return null;
	}
	
	public int getEdgeDistanceTo(Vertex<T> to) {		
		Optional<Edge<T>> edgeTo = 
				this.edges.stream().filter(edge -> edge.toVertex() == to).findFirst();
		if (edgeTo.isPresent()) {
			return edgeTo.get().distance();
		}
		return -1;
	}

}
