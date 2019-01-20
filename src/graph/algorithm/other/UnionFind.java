package graph.algorithm.other;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

/**
 * 基于路径压缩的quick-union算法
 *
 * 基本实现思路：
 * 使用树来记录每一个连通分量中包含的节点（使用id数组记录每一元素的父节点即可实现树结构），
 * 连通判定：当两个节点的根节点相同时，这两个节点连通
 *
 * 路径压缩：让每一个节点直接指向其根节点，提高查找效率
 */
public class UnionFind {

    private int[] id;    // 记录父节点，数组的每一个索引可以唯一对应一个图中节点
    private int count;   // 连通分量的数量

    /**
     * 初始化UnionFind对象
     * 最初所有的节点都不连通，每一个元素的父节点为自身
     * 连通分量数 = 图中节点个数
     *
     * @param size 图中节点个数
     */
    public UnionFind(int size) {
        id = new int[size];
        for (int i = 0; i < size; i++)
            id[i] = i;
        count = size;
    }

    /**
     * 该构造方法会得到指定图的一个并查集
     *
     * @param g 进行连图性分析的图
     */
    public UnionFind(Graph g) {
        this(g.size());
        for (Edge e : g.getAllEdge()) {
            Vertex a = e.either();
            Vertex b = e.other(a);
            union(g.indexOf(a), g.indexOf(b));
        }
    }

    /**
     * 通过对给定两个节点的根节点进行比较，判断连通性
     *
     * @param v 节点1
     * @param w 节点2
     * @return true ：v-w连通，
     * false: v-w不连通
     */
    public boolean isConnected(int v, int w) {
        return find(v) == find(w);
    }

    /**
     * 如果给定的两个节点已经连通，不做任何操作
     * 否则通过修改其中一个节点的根节点，将给定的两个节点连通起来：
     *
     * @param v 节点一
     * @param w 节点二
     */
    public void union(int v, int w) {
        int vRoot = find(v);
        int wRoot = find(w);
        if (vRoot == wRoot) return;
        id[vRoot] = wRoot;   // 将v树连接到w节点上去
        count--;
    }

    /**
     * @return 返回当前的连通分量个数
     */
    public int count() {
        return count;
    }

    /*
     * 给定一个节点，通过寻找父节点，找到其根节点root
     *
     * 压缩路径：找到根节点后，会重新遍历查找路径上的所有节点
     *          并将其父节点直接设为root
     */
    private int find(int v) {
        int p = v;
        while (!isRoot(p)) p = id[p]; // 找到节点v对应的根节点p
        while (!isRoot(v)) { //将查找路径上所有节点的父节点直接设为p
            int temp = v;
            v = id[v];
            id[temp] = p;
        }
        return p;
    }

    // 当所给节点v为根节点时，返回true
    private boolean isRoot(int v) {
        return v == id[v];
    }
}
