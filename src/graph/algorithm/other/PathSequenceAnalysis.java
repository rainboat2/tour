package graph.algorithm.other;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import graph.algorithm.short_path.Dijkstra;
import graph.algorithm.short_path.ShortPath;

import java.util.*;

/**
 * 接受图以及一组节点序列
 * <p>
 * 功能：
 * 1. 缺失路径分析
 * 2. 自动填补路径生成导游图
 */
public class PathSequenceAnalysis {

    private Graph g;
    private List<Vertex> sequence;    //记录用于分析的节点序列
    private List<Node> missPath;

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

        // 将最短路径算法生成的路径栈转化为路径数组
        private void setPath(Stack<Vertex> stack) {
            path = new ArrayList<>(stack.size());
            while (!stack.isEmpty())
                path.add(stack.pop());
        }
    }

    public PathSequenceAnalysis(Graph g, List<Vertex> sequence) {
        this.g = g;
        this.sequence = sequence;
        this.missPath = new LinkedList<>();
    }

    public String showTourPath() {
        List<Vertex> path = getTourPath();
        StringBuilder s = new StringBuilder();
        Iterator<Vertex> it = path.iterator();

        int distance = 0;
        Vertex cur = it.next();
        while (it.hasNext()) {
            Vertex next = it.next();
            Edge e = g.getEdge(cur, next);
            distance += e.distance();
            s.append(cur.getName());
            if (it.hasNext())
                s.append("-->");
            cur = next;
        }
        s.append(String.format("\n总距离:%d, 预计耗费时间%d小时", distance, distance / 24));
        return s.toString();
    }

    public String getMissPathAnalyzeResult() {
        missPathAnalyze(new Dijkstra(g));
        StringBuilder s = new StringBuilder();
        for (Node n : missPath)
            s.append(getVertex(n.start).getName()).append("--").append(sequence.get(n.end).getName()).append("\n");
        return s.toString();
    }

    /*
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


    /*
     * 通过遍历sequence，路径存在，直接加入导游路径序列
     * 路径不存在，则将找到的最短路径添加进去
     *
     * 当 n == null 时  i，i+1两个节点之间有直接回路
     */
    private List<Vertex> getTourPath() {
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

    /*
     * 调用该方法前必需要先调用missPathAnalyze方法
     *
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
