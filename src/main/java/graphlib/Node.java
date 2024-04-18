package graphlib;

import java.util.Collection;
import java.util.Map;

public class Node<T extends Number>
{
    private String name;
    private Map<Node<T>, T> neighbors;

    public Node(String name)
    {
        this.name = name;
        neighbors = new java.util.HashMap<>();
    }

    public String getName()
    {
        return name;
    }

    public void addDirectedEdge(Node<T> neighbor, T weight)
    {
        neighbors.put(neighbor, weight);
    }

    public void addUndirectedEdge(Node<T> neighbor, T weight)
    {
        addDirectedEdge(neighbor, weight);
        neighbor.addDirectedEdge(this, weight);
    }

    public void addUnweightedDirectedEdge(Node<T> neighbor)
    {
        //addDirectedEdge(neighbor, makeDefault());
        addDirectedEdge(neighbor, (T) Integer.valueOf(1));
    }

    public void addUnweightedUndirectedEdge(Node<T> neighbor)
    {
        addUnweightedDirectedEdge(neighbor);
        neighbor.addUnweightedDirectedEdge(this);
    }

    public Collection<Node<T>> getNeighbors()
    {
        return neighbors.keySet();
    }

    public T getWeight(Node<T> neighbor)
    {
        return neighbors.get(neighbor);
    }

    
    public String toString()
    {
        return name;
    }

    public boolean hasEdge(Node<T> neighbor)
    {
        return neighbors.containsKey(neighbor);
    }

    
}
