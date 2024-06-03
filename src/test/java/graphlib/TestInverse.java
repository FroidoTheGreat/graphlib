package graphlib;

import org.junit.jupiter.api.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

public class TestInverse {
    @Test
    public void testInverse() throws FileNotFoundException
    {   
        Node a;
        Node b;
        Node c;
        Node d;
        Node e;
        Node f;

        // simple
        Graph graph = Graph.readUndirectedUnweightedGraph(new FileInputStream("datafiles/inverses/1.txt"));
        Graph inverse = graph.inverseGraph();
        a = inverse.getOrCreateNode("a");
        b = inverse.getOrCreateNode("b");
        assertFalse(a.hasEdge(b));

        // dense
        graph = Graph.readUndirectedUnweightedGraph(new FileInputStream("datafiles/inverses/dense.txt"));
        inverse = graph.inverseGraph();
        a = inverse.getOrCreateNode("a");
        b = inverse.getOrCreateNode("b");
        c = inverse.getOrCreateNode("c");
        d = inverse.getOrCreateNode("d");
        e = inverse.getOrCreateNode("e");
        // ensure that the number of edges for each node is 0
        assertEquals(1, a.getNeighbors().size());
        assertEquals(1, b.getNeighbors().size());
        assertEquals(1, c.getNeighbors().size());
        assertEquals(1, d.getNeighbors().size());
        assertEquals(1, e.getNeighbors().size());

        // sparse
        graph = Graph.readUndirectedUnweightedGraph(new FileInputStream("datafiles/inverses/sparse.txt"));
        inverse = graph.inverseGraph();
        a = inverse.getOrCreateNode("a");
        b = inverse.getOrCreateNode("b");
        c = inverse.getOrCreateNode("c");
        d = inverse.getOrCreateNode("d");
        e = inverse.getOrCreateNode("e");
        f = inverse.getOrCreateNode("f");
        // ensure that each node has the maximum number of edges
        assertEquals(5, a.getNeighbors().size());
        assertTrue(a.hasEdge(c) && a.hasEdge(d) && a.hasEdge(e) && a.hasEdge(f));
        assertEquals(5, b.getNeighbors().size());
        assertTrue(b.hasEdge(c) && b.hasEdge(d) && b.hasEdge(e) && b.hasEdge(f));
        assertEquals(5, c.getNeighbors().size());
        assertTrue(c.hasEdge(a) && c.hasEdge(b) && c.hasEdge(e) && c.hasEdge(f));
        assertEquals(5, d.getNeighbors().size());
        assertTrue(d.hasEdge(a) && d.hasEdge(b) && d.hasEdge(e) && d.hasEdge(f));
        assertEquals(5, e.getNeighbors().size());
        assertTrue(e.hasEdge(a) && e.hasEdge(b) && e.hasEdge(c) && e.hasEdge(d));
        assertEquals(5, f.getNeighbors().size());
        assertTrue(f.hasEdge(a) && f.hasEdge(b) && f.hasEdge(c) && f.hasEdge(d));
    }
}
