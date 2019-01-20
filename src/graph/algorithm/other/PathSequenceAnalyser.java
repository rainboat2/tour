package graph.algorithm.other;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import graph.algorithm.short_path.Dijkstra;
import graph.algorithm.short_path.ShortPath;

import java.util.*;

/**
 * 接受图以及一组节点序列
 *
 * 功能：
 * 1. 缺失路径分析
 * 2. 自动填补序列生成完整路径
 *
 */
public class PathSequenceAnalyser {

    private Graph g;
    private List<Node> missPath;            // 记录缺少的路径
    private final List<Vertex> sequence;    // 记录用于分析的节点序列

    /**
     * 若节点序列中两个相邻的节点之间不存在直接相连的边时，使用该类来保存
     * 保存两个节点之间的一个最短路径
     */
    private class Node {
        int start;
        int end;
        List<Vertex> path;

        // start，end记录对应节点在sequence中的位置
        private Node(int start, int end, Stack<Vertex> path) {
            this.start = start;
            this.end = end;
            setPath(path);
        }

        /*
         * 将最短路径算法生成的路径栈转化为路径数组
         * 便于后面的计算
         */
        private void setPath(Stack<Vertex> stack) {
            if (stack == null)
                throw new RuntimeException("错误的节点序列：节点不连通");
            path = new ArrayList<>(stack.size());
            while (!stack.isEmpty())
                path.add(stack.pop());
        }
    }

    public PathSequenceAnalyser(Graph g, List<Vertex> sequence) {
        this.g = g;
        this.sequence = sequence;
        this.missPath = new LinkedList<>();
    }

    /**
     * @return 所给序列中，所有无法直接到达的相连节点对
     */
    public String getMissPathAnalyzeResult() {
        missPathAnalyze(new Dijkstra(g));
        StringBuilder s = new StringBuilder();
        for (Node n : missPath)
            s.append(getVertex(n.start).getName()).append("--").append(sequence.get(n.end).getName()).append("\n");
        return s.toString();
    }


    /**
     *  通过遍历sequence，路径存在，直接加入导游路径序列
     *  路径不存在，则将找到的最短路径添加进去
     * @return 导游路径
     */
    public List<Vertex> getTourPath() {
        missPathAnalyze(new Dijkstra(g));
        List<Vertex> tourPath = new LinkedList<>();
        for (int i = 0; i < sequence.size(); i++) {
            Node n = miss(i);
            if (n == null) {
                tourPath.add(sequence.get(i));
            } else {
                // 最短路径最后一项为终点，若加入则会引起重复
                for (int j = 0; j < n.path.size() - 1; j++)
                    tourPath.add(n.path.get(j));
            }
        }
        return tourPath;
    }

    /**
     * @return 用于分析的节点序列
     */
    public Iterable<Vertex> getSequence(){
        return sequence;
    }

    /**
     * 通过遍历序列，找到所有直接不可达的节点对
     *
     * 并且使用最短路径算法对缺少的路径进行填补
     * 通过传入不同的最短路径算法，可以使用不同方式进行填补
     */
    private void missPathAnalyze(ShortPath shortPath) {
        for (int i = 0; i < sequence.size() - 1; i++) {
            Vertex a = sequence.get(i);
            Vertex b = sequence.get(i + 1);
            Edge e = g.getEdge(a, b);
            if (e == null)
                missPath.add(new Node(i, i + 1, shortPath.pathTo(a.getName(), b.getName())));
        }
    }

    /**
     * 调用该方法前必需要先调用missPathAnalyze方法
     * 用于确定给定节点start和其后的一个节点之间是否存在直接到达的边
     *
     * @param start 开始节点在sequence中的索引
     * @return node   start，start+1之间不存在直接到到的边
     *         null   start，start+1之间直接可达
     */
    private Node miss(int start) {
        if (missPath.isEmpty()) return null;
        Node n = missPath.get(0);
        if (n.start == start) return missPath.remove(0);
        else if (n.start < start) throw new RuntimeException("非法调用");
        else return null;
    }

    private Vertex getVertex(int i) {
        return sequence.get(i);
    }
}
