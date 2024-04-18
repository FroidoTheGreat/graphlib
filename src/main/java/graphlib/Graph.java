package graphlib;

import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

public class Graph<T extends Number>
{
    private Map<String, Node<T>> nodes;

    public Graph()
    {
        nodes = new java.util.HashMap<>();
    }

    public Node<T> getOrCreateNode(String name)
    {
        Node<T> node = nodes.get(name);
        if (node == null)
        {
            node = new Node<T>(name);
            nodes.put(name, node);
        }
        return node;
    }

    public boolean containsNode(String name)
    {
        return nodes.containsKey(name);
    }

    /**
     * Returns a string representation of the graph in GraphViz format.
     * 
     * This is for an <b>undirected</b>, <b>weighted</b> graph
     * @return
     */
    public String toUndirectedWeightedGraphViz()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("graph G {\n");
        for (Node<T> node : nodes.values())
        {
            for (Node<T> neighbor : node.getNeighbors())
            {
                // make sure we only add each edge once
                if (node.getName().compareTo(neighbor.getName()) < 0)
                {
                    // TODO: generics
                    sb.append(String.format("  %s -- %s [label=\"%f\"];\n", node.getName(), neighbor.getName(), node.getWeight(neighbor)));
                }
            }
        }
        sb.append("}\n");
        return sb.toString();
    }
    /**
     * Returns a string representation of the graph in GraphViz format.
     * 
     * This is for an <b>directed</b>, <b>weighted</b> graph
     * @return
     */
    public String toDirectedWeightedGraphViz()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph G {\n");
        for (Node<T> node : nodes.values())
        {
            for (Node<T> neighbor : node.getNeighbors())
            {
                // append using String.format
                sb.append(String.format("  %s -> %s [label=\"%f\"];\n", node.getName(), neighbor.getName(), node.getWeight(neighbor)));
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    /**
     * Returns a string representation of the graph in GraphViz format.
     * 
     * This is for an <b>directed</b>, <b>unweighted</b> graph
     * @return
     */
    public String toDirectedUnweightedGraphViz()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph G {\n");
        for (Node<T> node : nodes.values())
        {
            for (Node<T> neighbor : node.getNeighbors())
            {
                // append using String.format
                sb.append(String.format("  %s -> %s;\n", node.getName(), neighbor.getName()));
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    /**
     * Returns a string representation of the graph in GraphViz format.
     * 
     * This is for an <b>undirected</b>, <b>unweighted</b> graph
     * @return
     */
    public String toUndirectedUnweightedGraphViz()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("graph G {\n");
        for (Node<T> node : nodes.values())
        {
            for (Node<T> neighbor : node.getNeighbors())
            {
                // make sure we only add each edge once
                if (node.getName().compareTo(neighbor.getName()) < 0)
                {
                    sb.append("  " + node.getName() + " -- " + neighbor.getName() + ";\n");
                }
            }
        }
        sb.append("}\n");
        return sb.toString();
    }
    
    public static <T extends Number> Graph<T> readUndirectedUnweightedGraph(InputStream in)
    {
        Graph<T> graph = new Graph<T>();
        Scanner scanner = new Scanner(in);
        while (scanner.hasNext())
        {
            String nameA = scanner.next();
            String nameB = scanner.next();
            Node<T> nodeA = graph.getOrCreateNode(nameA);
            Node<T> nodeB = graph.getOrCreateNode(nameB);
            nodeA.addUnweightedUndirectedEdge(nodeB);
        }
        scanner.close();
        return graph;
    }

    public static <T extends Number> Graph<T> readDirectedUnweightedGraph(InputStream in)
    {
        Graph<T> graph = new Graph<>();
        Scanner scanner = new Scanner(in);
        while (scanner.hasNext())
        {
            String nameA = scanner.next();
            String nameB = scanner.next();
            Node<T> nodeA = graph.getOrCreateNode(nameA);
            Node<T> nodeB = graph.getOrCreateNode(nameB);
            nodeA.addUnweightedDirectedEdge(nodeB);
        }
        scanner.close();
        return graph;
    }

    public static Graph<Double> readUndirectedWeightedDoubleGraph(InputStream in)
    {
        return readUndirectedWeightedGraph(in, Double.class);
    }

    public static Graph<Integer> readUndirectedWeightedIntGraph(InputStream in)
    {
        return readDirectedWeightedGraph(in, Integer.class);
    }

    private static <T extends Number> Graph<T> readUndirectedWeightedGraph(InputStream in, Class<T> clazz)
    {
        Graph<T> graph = new Graph<>();
        Scanner scanner = new Scanner(in);
        while (scanner.hasNext())
        {
            String nameA = scanner.next();
            String nameB = scanner.next();
            // nextDouble() can also read int and float as a double
            double weight = scanner.nextDouble();
            // convert double to T using the given class
            // this lets us use the same method for different number types
            T weightT = convertToT(weight, clazz);
            Node<T> nodeA = graph.getOrCreateNode(nameA);
            Node<T> nodeB = graph.getOrCreateNode(nameB);
            nodeA.addUndirectedEdge(nodeB, weightT);
        }
        scanner.close();
        return graph;
    }

    public static Graph<Double> readDirectedWeightedDoubleGraph(InputStream in)
    {
        return readDirectedWeightedGraph(in, Double.class);
    }

    public static Graph<Integer> readDirectedWeightedIntGraph(InputStream in)
    {
        return readDirectedWeightedGraph(in, Integer.class);
    }

    private static <T extends Number> Graph<T> readDirectedWeightedGraph(InputStream in, Class<T> clazz)
    {
        Graph<T> graph = new Graph<>();
        Scanner scanner = new Scanner(in);
        while (scanner.hasNext())
        {
            String nameA = scanner.next();
            String nameB = scanner.next();
            // nextDouble() can also read int and float as a double
            double weight = scanner.nextDouble();
            // convert double to T using the given class
            T weightT = convertToT(weight, clazz);
            Node<T> nodeA = graph.getOrCreateNode(nameA);
            Node<T> nodeB = graph.getOrCreateNode(nameB);
            nodeA.addDirectedEdge(nodeB, weightT);
        }
        scanner.close();
        return graph;
    }

    /**
     * Converts a double to an instance of the given type of class clazz.
     * 
     * Basically this is a way to convert a double to an int, float, or double
     * based on the given class. This is used when reading a graph from a file
     * 
     * @param <T>
     * @param value
     * @param clazz
     * @return
     */
    private static <T extends Number> T convertToT(double value, Class<T> clazz)
    {
        try {
            if (clazz == Double.class) {
                return clazz.cast(Double.valueOf(value));
            } else if (clazz == Integer.class) {
                return clazz.cast((int) value);
            } else if (clazz == Float.class) {
                return clazz.cast((float) value);
            } else {
                throw new IllegalArgumentException("Unsupported number class");
            }
        } catch (ClassCastException e) {
            throw new RuntimeException(e);
        }
    }

}
