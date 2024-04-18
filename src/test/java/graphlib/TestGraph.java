package graphlib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestGraph 
{

    @Test
    public void testGetOrCreateNode()
    {
        Graph<Integer> graph = new Graph<>();
        Node<Integer> a = graph.getOrCreateNode("A");
        Node<Integer> b = graph.getOrCreateNode("B");
        a.addUndirectedEdge(b, 1);
        assert a.getNeighbors().contains(b);
        assert b.getNeighbors().contains(a);
        assert a.getWeight(b) == 1;
        assert b.getWeight(a) == 1;
    }

    @Test
    public void testNoDuplicates()
    {
        Graph<Integer> graph = new Graph<Integer>();
        Node<Integer> a = graph.getOrCreateNode("A");
        Node<Integer> b = graph.getOrCreateNode("A");
        assert a == b;
    }

    @Test
    public void testStaticFactoryMethod() throws Exception
    {
        Graph<Integer> g = Graph.readUndirectedUnweightedGraph(new FileInputStream("datafiles/graph1.txt"));
        Node<Integer> zero = g.getOrCreateNode("0");
        Node<Integer> one = g.getOrCreateNode("1");
        Node<Integer> two = g.getOrCreateNode("2");
        Node<Integer> three = g.getOrCreateNode("3");
        Node<Integer> four = g.getOrCreateNode("4");
        assert zero.hasEdge(one);
        assert one.hasEdge(two);
        assert three.hasEdge(four);
        assert four.hasEdge(three);
    }

}
