package test.tour;

import graph.Graph;
import graph.algorithm.small_tree.Prim;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import tour.TourPath;

/**
 * TourPath Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>一月 18, 2019</pre>
 */
public class TourPathTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getTourPath(String start)
     */
    @Test
    public void testGetTourPath() throws Exception {
        Graph g = Graph.getGraph();
        TourPath t = new TourPath(g, new Prim(g, 0));
        System.out.println(t.getTourPath("一线天"));
        System.out.println(t.getTourPath("观云台"));
        System.out.println(t.getTourPath("仙武湖"));
    }

    /**
     * Method: getMissPath(String start)
     */
    @Test
    public void testGetMissPath() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: getSequence(String start)
     */
    @Test
    public void testGetSequence() throws Exception {
//TODO: Test goes here...
    }


    /**
     * Method: getAnalysis(String start)
     */
    @Test
    public void testGetAnalysis() throws Exception {
//TODO: Test goes here...
/*
try {
   Method method = TourPath.getClass().getMethod("getAnalysis", String.class);
   method.setAccessible(true);
   method.invoke(<Object>, <Parameters>);
} catch(NoSuchMethodException e) {
} catch(IllegalAccessException e) {
} catch(InvocationTargetException e) {
}
*/
    }

}
