package graph.algorithm.small_tree;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Prim最短路径算法，实现了Small_Tree接口
 *
 */
public class Prim implements Small_Tree {

    private boolean[] marked;
    private Graph subTree;      //使用图的一个子图来保存最小生成树
    private PriorityQueue<Edge> pq;

    /**
     * 构造函数中包含对计算最短生成树方法的调用，
     * 当Prim对象生成，对应的最小生成树就已经得出，直接调用subTree()方法获取即可
     *
     * @param g 需要计算最小生成树的图
     * @param start 最小生成树的起点
     */
    public Prim(Graph g, int start) {
        marked = new boolean[g.size()];
        subTree = g.subGraph(new ArrayList<>());
        pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.distance(), o1.distance()));
        mst(g, start);
    }

    /**
     * 计算最小生成树
     *
     * 基本思路：
     * 从一个节点开始构建树，将其所有邻接边加入到优先队列中
     * 每次选中一条离数最近的边，若不会构成回路，则将其加入树中
     * 直到所有的节点都被加入到树中为止
     *
     * @param g 需要计算最小生成树的图
     * @param start 最小生成树的起点
     */
    private void mst(Graph g, int start) {
        visit(start, g);
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            Vertex v = e.either();
            Vertex w = e.other(v);
            int a = g.indexOf(v.getName());
            int b = g.indexOf(w.getName());
            //当一条边的两个节点都被访问时，说明这两个节点以及在树中，此时如果添加该边会产生环
            if (isMarked(a) && isMarked(b)) continue;
            subTree.addEdge(e);
            if (!isMarked(a)) visit(a, g);
            if (!isMarked(b)) visit(b, g);
        }
    }

    private boolean isMarked(int v) {
        return marked[v];
    }

    private void visit(int v, Graph g) {
        marked[v] = true;
        for (Edge e : g.getAdjacentEdges(v))
            pq.add(e);
    }

    public Graph subTree() {
        return subTree;
    }
}
