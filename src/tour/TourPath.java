package tour;

import graph.Graph;
import graph.Vertex;
import graph.algorithm.other.HamiltonRoadFinder;
import graph.algorithm.other.PathSequenceAnalysis;
import graph.algorithm.small_tree.Small_Tree;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class TourPath {

    private Graph g;
    private HamiltonRoadFinder finder;
    private HashMap<String, List<Vertex>> sequenceMap;

    public TourPath(Graph g, Small_Tree st){
        this.g = g;
        this.finder = new HamiltonRoadFinder(st.subTree());
        sequenceMap = new HashMap<>();
    }

    public String getTourPath(String start){
        if (g.getVertex(start) == null)
            throw new NoSuchElementException("找不到指定节点");
        return getAnalysis(start).showTourPath();
    }

    public String getMissPath(String start){
        if (g.getVertex(start) == null)
            throw new NoSuchElementException("找不到指定节点");
        return getAnalysis(start).getMissPathAnalyzeResult();
    }

    public List<Vertex> getSequence(String start){
        List<Vertex> sequence = sequenceMap.get(start);
        if (sequence == null)
            sequence = finder.getSequence(g.indexOf(start));
        return sequence;
    }

    private PathSequenceAnalysis getAnalysis(String start){
        List<Vertex> sequence = sequenceMap.get(start);
        if (sequence == null)
            sequence = finder.getSequence(g.indexOf(start));
        PathSequenceAnalysis analysis = new PathSequenceAnalysis(g, sequence);
        return analysis;
    }
}
