package graph.algorithm.small_tree;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import graph.algorithm.other.UnionFind;

import java.util.ArrayList;
import java.util.PriorityQueue;


/**
 * Kruskal最小生成树算法
 */
public class Kruskal implements Small_Tree{

    private Graph subTree; //使用图的子图来记录最小生成树

    /**
     * 在构造函数中就已经调用了最小生成树算法，
     * 因此直接调用subTree()方法即可获得最小生成树
     *
     * @param g 计算最小生成树所用的原图
     */
    public Kruskal(Graph g){
        subTree = g.subGraph(new ArrayList<>());
        mst(g);
    }

    /**
     * 计算最小生成树
     *
     * 基本思路
     * 将所有的边都加入到优先队列中（距离越短，优先级越高）
     * 每次从优先队列中选中一个边， 通过并查集判断是否会形成环
     * 若不会形成环，加入到最小生成树中
     * 直到优先队列为空为止
     *
     * @param g 计算最小生成树所用的原图
     */
    private void mst(Graph g){
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.distance(), o1.distance()));
        for (Edge e : g.getAllEdge())
            pq.add(e);
        UnionFind uf = new UnionFind(g.size());

        while (!pq.isEmpty()){
            Edge e = pq.poll();
            Vertex v = e.either();
            Vertex w = e.other(v);
            int a = g.indexOf(v);
            int b = g.indexOf(w);
            if (uf.isConnected(a, b)) continue;
            subTree.addEdge(e);
            uf.union(a, b);
        }
    }

    /**
     * @return 最小生成树
     * @see Small_Tree
     */
    public Graph subTree() {
        return subTree;
    }
}
