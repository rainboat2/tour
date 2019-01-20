package graph.algorithm.short_path;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Stack;

public class Dijkstra implements ShortPath {

    public static void main(String[] args) throws IOException {
        Graph g = Graph.getGraph();
        Dijkstra d = new Dijkstra(g);

    }

    private Graph g;
    private int[] pathTo;
    private int[] distance;

    public Dijkstra(Graph g){
        this.g = g;
    }

    // 加入优先队列中的节点
    private class Node implements Comparable<Node>{
        int v;
        int distance;

        public Node(int v, int distance) {
            this.v = v;
            this.distance = distance;
        }

        // 到起点的距离越短，优先级越高
        public int compareTo(Node o) {
            return Integer.compare(o.distance, this.distance);
        }
    }

    public Stack<Vertex> pathTo(String start, String end) {
        int from = g.indexOf(start), to = g.indexOf(end);
        dijkstra(from, to);
        if (distance[to] == Integer.MAX_VALUE) return null;  //end不可达，无路径可以返回
        Stack<Vertex> stack = new Stack<>();
        // 将找到的最短路径压入栈中进行保存
        int i;
        for (i = to; i != pathTo[i]; i = pathTo[i])
            stack.push(g.getVertex(i));
        stack.push(g.getVertex(i));
        return stack;
    }

    /*
     * DijkstraIndex 算法
     * 基本思路：
     * 1. 初始化开始节点，加入到优先队列（costHeap，花费越小，优先级越高）
     * 2. 选出优先级最高的节点，并对该节点进行relax（放松）操作
     * 3. 到达终点后停止
     */
    private void dijkstra(int from, int dest){
        int N = g.size();
        distance = new int[N];
        pathTo = new int[N];
        for (int i = 0; i < N; i++){
            distance[i] = Integer.MAX_VALUE;
            pathTo[i] = from;
        }
        distance[from] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(from, 0));
        while (!pq.isEmpty()){
            int v = pq.poll().v;
            if (v == dest) break; //当节点被选中出来放松时，说明最短路径已经找到
            relax(v, pq);
        }
    }

    /*
     * “放松”操作
     * 假设两条路径，(v--w为图中的一条边）
     *      1.s-->……-->w,
     *      2.s-->……-->v-->w
     * 若路径2花费小于路径1，则将w的前驱节点设为v
     *
     * 该操作为最短路径算法的关键操作
     */
    private void relax(int v, PriorityQueue<Node> pq){
        Vertex a = g.getVertex(v);
        for (Edge e : g.getAdjacentEdges(v)){
            int w = g.indexOf(e.other(a));
            if (distance[w] > distance[v] + e.distance()){
                pathTo[w] = v;
                distance[w] = distance[v] + e.distance();
                pq.add(new Node(w, distance[w]));
            }
        }
    }
}
