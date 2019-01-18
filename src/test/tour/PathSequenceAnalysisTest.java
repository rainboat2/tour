package test.tour;

import graph.Graph;
import graph.Vertex;
import graph.algorithm.other.HamiltonRoadFinder;
import graph.algorithm.other.PathSequenceAnalysis;
import graph.algorithm.small_tree.Prim;
import graph.algorithm.small_tree.Small_Tree;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.List;

/**
 * PathSequenceAnalysis Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>一月 18, 2019</pre>
 */
public class PathSequenceAnalysisTest {

    private Graph g;
    private List<Vertex> sequence;
    private PathSequenceAnalysis analysis;

    @Before
    public void before() throws Exception {
        this.g = Graph.getGraph();
        Small_Tree st = new Prim(g, 0);
        HamiltonRoadFinder hamiltonRoadFinder = new HamiltonRoadFinder(st.subTree());
        sequence = hamiltonRoadFinder.getSequence(0);
        analysis = new PathSequenceAnalysis(g, sequence);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: showTourPath()
     */
    @Test
    public void testShowTourPath() throws Exception {
        System.out.println(analysis.showTourPath());
    }

    /**
     * Method: getMissPathAnalyzeResult()
     */
    @Test
    public void testGetMissPathAnalyzeResult() throws Exception {
        System.out.println(analysis.getMissPathAnalyzeResult());
    }


    /**
     * Method: missPathAnalyze(ShortPath shortPath)
     */
    @Test
    public void testMissPathAnalyze() throws Exception {
//TODO: Test goes here...
/*
try {
   Method method = PathSequenceAnalysis.getClass().getMethod("missPathAnalyze", ShortPath.class);
   method.setAccessible(true);
   method.invoke(<Object>, <Parameters>);
} catch(NoSuchMethodException e) {
} catch(IllegalAccessException e) {
} catch(InvocationTargetException e) {
}
*/
    }

    /**
     * Method: getTourPath()
     */
    @Test
    public void testGetTourPath() throws Exception {
//TODO: Test goes here...
/*
try {
   Method method = PathSequenceAnalysis.getClass().getMethod("getTourPath");
   method.setAccessible(true);
   method.invoke(<Object>, <Parameters>);
} catch(NoSuchMethodException e) {
} catch(IllegalAccessException e) {
} catch(InvocationTargetException e) {
}
*/
    }

    /**
     * Method: miss(int start)
     */
    @Test
    public void testMiss() throws Exception {
//TODO: Test goes here...
/*
try {
   Method method = PathSequenceAnalysis.getClass().getMethod("miss", int.class);
   method.setAccessible(true);
   method.invoke(<Object>, <Parameters>);
} catch(NoSuchMethodException e) {
} catch(IllegalAccessException e) {
} catch(InvocationTargetException e) {
}
*/
    }

    /**
     * Method: setPath(Stack<Vertex> stack)
     */
    @Test
    public void testSetPath() throws Exception {
//TODO: Test goes here...
/*
try {
   Method method = PathSequenceAnalysis.getClass().getMethod("setPath", Stack<Vertex>.class);
   method.setAccessible(true);
   method.invoke(<Object>, <Parameters>);
} catch(NoSuchMethodException e) {
} catch(IllegalAccessException e) {
} catch(InvocationTargetException e) {
}
*/
    }

}
