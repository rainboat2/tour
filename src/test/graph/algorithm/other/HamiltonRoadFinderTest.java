package test.graph.algorithm.other;

import graph.Graph;
import graph.Vertex;
import graph.algorithm.other.HamiltonRoadFinder;
import graph.algorithm.small_tree.Prim;
import graph.algorithm.small_tree.Small_Tree;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.List;

/**
 * HamiltonRoadFinder Tester.
 *
 * @author <Authors name>
 * @since <pre> 18, 2019</pre>
 * @version 1.0
 */
public class HamiltonRoadFinderTest {

    private HamiltonRoadFinder hamiltonRoadFinder;

    @Before
    public void before() throws Exception {
        Graph g = Graph.getGraph();
        Small_Tree st = new Prim(g, 0);
        hamiltonRoadFinder = new HamiltonRoadFinder(st.subTree());
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: getSequence(int start)
     *
     */
    @Test
    public void testGetSequence() throws Exception {
        List<Vertex> sequence = hamiltonRoadFinder.getSequence(0);
        for (Vertex v : sequence)
            System.out.print(v.getName() + " ");
    }

    /**
     *
     * Method: hasHamiltonRoad(Graph g)
     *
     */
    @Test
    public void testHasHamiltonRoad() throws Exception {
    //TODO: Test goes here...
    }


    /**
     *
     * Method: preOrder(int v)
     *
     */
    @Test
    public void testPreOrder() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = HamiltonRoadFinder.getClass().getMethod("preOrder", int.class);
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

    /**
     *
     * Method: getAdj(int v)
     *
     */
    @Test
    public void testGetAdj() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = HamiltonRoadFinder.getClass().getMethod("getAdj", int.class);
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

}
