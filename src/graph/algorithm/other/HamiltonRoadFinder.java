package graph.algorithm.other;

import graph.Graph;
import graph.Vertex;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * 需要参数：
 * 1. 最小生成树 ：用于生成汉密尔顿回路序列
 * 2. 原图      ：用于根据一个节点序列生成一组路径
 * 3. 起点
 * <p>
 * 提供的功能：
 * 1.生成近似路径序列
 */
public class HamiltonRoadFinder {

    private Graph subTree;                    //由最小生成树算法得到的一个最小生成树
    private ArrayList<Vertex> path;           //存放求得的路径序列
    private boolean[] marked;

    public HamiltonRoadFinder(Graph subTree) {
        this.subTree = subTree;
        this.marked = new boolean[subTree.size()];
    }

    public ArrayList<Vertex> getSequence(int start) {
        if (start < 0 || start >= subTree.size())
            throw new NoSuchElementException("指定的节点不存在");
        path = new ArrayList<>(subTree.size());
        for (int i = 0; i < marked.length; i++)
            marked[i] = false;
        preOrder(start);
        path.add(subTree.getVertex(start));
        return path;
    }

    /*
     * 根据最小生成树进行先序遍历得到一组近似的路径序列
     */
    private void preOrder(int v) {
        path.add(subTree.getVertex(v));
        marked[v] = true;
        // 遍历v的所有邻接节点
        for (Vertex w : getAdj(v))
            if (!marked[subTree.indexOf(w)])
                preOrder(subTree.indexOf(w));
    }

    private Iterable<Vertex> getAdj(int v) {
        return subTree.getAdjacentVertex(v);
    }

    /*
     * 判断汉密尔顿回路是否存在
     *
     * 判定定理（仅为充分条件）：
     * 设 G 为具有 n 个节点的简单图，如果 G 中每一对节点度数之和大于等于 n-1，则在 G 中存在一条汉密尔顿路
     *
     * @return true ： 必定存在汉密尔顿图
     *         false： 可能存在或不存在汉密尔顿图
     */
    public static boolean hasHamiltonRoad(Graph g) {
        int N = g.size();
        for (int i = 0; i < g.size(); i++)
            for (int j = i + 1; j < g.size(); j++)
                if ((g.degree(i) + g.degree(j)) < N - 1)
                    return false;
        return true;
    }
}
