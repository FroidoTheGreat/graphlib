package graphlib;

import org.junit.jupiter.api.Test;
import java.io.FileInputStream;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class TestReachable {
    @Test
    public void testReachable() throws Exception {
        Graph graph;

        Node a;
        Node b;
        Node c;
        Node d;
        Node e;
        Node f;

        // simple
        graph = Graph.readUndirectedUnweightedGraph(new FileInputStream("datafiles/inverses/1.txt"));
        a = graph.getOrCreateNode("a");
        b = graph.getOrCreateNode("b");
        assertTrue(graph.getReachable(a).contains(b));

        // dense
        graph = Graph.readUndirectedUnweightedGraph(new FileInputStream("datafiles/inverses/dense.txt"));
        a = graph.getOrCreateNode("a");
        b = graph.getOrCreateNode("b");
        c = graph.getOrCreateNode("c");
        d = graph.getOrCreateNode("d");
        e = graph.getOrCreateNode("e");
        HashSet<Node> reachable = graph.getReachable(a);
        assertTrue(reachable.contains(b));
        assertTrue(reachable.contains(c));
        assertTrue(reachable.contains(d));
        assertTrue(reachable.contains(e));

        // sparse
        graph = Graph.readUndirectedUnweightedGraph(new FileInputStream("datafiles/inverses/sparse.txt"));
        a = graph.getOrCreateNode("a");
        b = graph.getOrCreateNode("b");
        c = graph.getOrCreateNode("c");
        d = graph.getOrCreateNode("d");
        e = graph.getOrCreateNode("e");
        f = graph.getOrCreateNode("f");
        reachable = graph.getReachable(a);
        assertTrue(reachable.contains(b));
        assertFalse(reachable.contains(c));
        assertFalse(reachable.contains(d));
        assertFalse(reachable.contains(e));
        assertFalse(reachable.contains(f));
    }
}
