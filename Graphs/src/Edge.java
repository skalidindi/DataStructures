/**
 * 
 * @author skalidindi
 *
 */
public class Edge<T extends Comparable<? super T>> {

    private Vertex<T> from;
    private Vertex<T> to;

    private int distance;

    public Edge(Vertex<T> from, Vertex<T> to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Vertex<T> fromVertex() {
        return from;
    }

    public Vertex<T> toVertex() {
        return to;
    }
    
    public int distance() {
    	return distance;
    }

    public boolean isBetween(Vertex<T> from, Vertex<T> to) {
        return (this.from.equals(from) && this.to.equals(to));
    }
}