import com.natera.graphexample.Edge
import com.natera.graphexample.Graph
import com.natera.graphexample.Vertex
import org.testng.Assert.assertTrue
import org.testng.annotations.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

/**
 * Test class for [Graph]
 * @author SidorovIV
 * @ created 02.06.2020
 * @ 1$
 */
class GraphTest {

    /**
     * test check creation graph
     * expected result: not null graph
     */
    @Test
    fun testCreateGraph() {
        val graph: Graph<String> = Graph()
        graph.addVertex("Bob")
        graph.addVertex("Alice")
        graph.addVertex("Mark")
        graph.addVertex("Rob")
        graph.addVertex("Maria")

        graph.addEdge("Bob", "Alice")
        graph.addEdge("Bob", "Rob")
        graph.addEdge("Alice", "Mark")
        graph.addEdge("Rob", "Mark")
        graph.addEdge("Alice", "Maria")
        graph.addEdge("Rob", "Maria")

        assertNotNull(graph)
    }

    /**
     * test creation graph with non-existing vertex
     * expected result: NPE
     */
    @Test
    fun testFailCreateGraph() {
        val graph: Graph<String> = Graph()
        graph.addVertex("Maria")
        graph.addVertex("Bob")

        assertFailsWith<NullPointerException> {
            graph.addEdge("Bob", "Alice")
        }
    }

    /**
     * test [Graph.getPath]
     * expected result: the same List of Edges
     */
    @Test
    fun testGetPath() {
        val graph: Graph<String> = Graph()
        graph.addVertex("Bob")
        graph.addVertex("Alice")
        graph.addVertex("Mark")
        graph.addVertex("Rob")
        graph.addVertex("Maria")

        graph.addEdge("Bob", "Alice")
        graph.addEdge("Bob", "Rob")
        graph.addEdge("Alice", "Mark")
        graph.addEdge("Rob", "Mark")
        graph.addEdge("Alice", "Maria")
        graph.addEdge("Rob", "Maria")

        val listEdge = listOf<Edge>(Edge(Vertex("Bob"), Vertex("Rob")), Edge(Vertex("Rob"), Vertex("Maria")))
        val path = graph.getPath("Bob", "Maria")
        assertTrue(listEdge.containsAll(path))
        assertEquals(2, path.size)
    }

    /**
     * test [Graph.getPath]
     * expected result: the same List of Edges
     */
    @Test
    fun testGetPathWithDirection() {
        val graph: Graph<String> = Graph()
        graph.addVertex("Bob")
        graph.addVertex("Alice")
        graph.addVertex("Mark")
        graph.addVertex("Rob")
        graph.addVertex("Maria")

        graph.addEdgeWithDirection("Bob", "Alice")
        graph.addEdgeWithDirection("Bob", "Rob")
        graph.addEdgeWithDirection("Alice", "Mark")
        graph.addEdgeWithDirection("Rob", "Mark")
        graph.addEdgeWithDirection("Alice", "Maria")
        graph.addEdgeWithDirection("Rob", "Maria")

        val listEdge = listOf<Edge>(Edge(Vertex("Bob"), Vertex("Rob")), Edge(Vertex("Rob"), Vertex("Maria")))
        val path = graph.getPath("Bob", "Maria")
        assertTrue(listEdge.containsAll(path))
        assertEquals(2, path.size)
    }

}
