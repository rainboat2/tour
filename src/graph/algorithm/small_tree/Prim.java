package graph.algorithm.small_tree;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Prim implements Small_Tree{

    public static void main(String[] args){
        Prim p = new Prim(Graph.getGraph(), 0);
        Graph subTree = p.subTree();
        System.out.println();
    }

    private boolean[] marked;
    private Graph subTree;
    private PriorityQueue<Edge> pq;

    public Prim(Graph g, int start){
        marked = new boolean[g.size()];
        subTree = g.subGraph(new ArrayList<>());
        pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.distance(), o1.distance()));
        mst(g, start);
    }

    private void mst(Graph g, int start){
        visit(start, g);
        while (!pq.isEmpty()){
            Edge e = pq.poll();
            Vertex v = e.either();
            Vertex w = e.other(v);
            int a = g.indexOf(v.getName());
            int b = g.indexOf(w.getName());
            if (isMarked(a) && isMarked(b)) continue; //该边已经在树中
            subTree.addEdge(e);
            if (!isMarked(a)) visit(a, g);
            if (!isMarked(b)) visit(b, g);
        }
    }

    private boolean isMarked(int v){
        return marked[v];
    }

    private void visit(int v, Graph g){
        marked[v] = true;
        for (Edge e : g.getAdjacentEdges(v))
            pq.add(e);
    }

    public Graph subTree(){
        return subTree;
    }
}
