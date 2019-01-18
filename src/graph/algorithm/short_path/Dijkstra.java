package graph.algorithm.short_path;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.util.Stack;

public class Dijkstra implements ShortPath {

    // 测试代码，查找每一对节点
    public static void main(String[] args){
        Graph g = Graph.getGraph();
        Dijkstra d = new Dijkstra(Graph.getGraph());
        int N = g.size();
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                System.out.printf("from %s to %s\n", g.getVertex(i).getName(), g.getVertex(j).getName());
                Stack<Vertex> stack = d.pathTo(g.getVertex(i).getName(), g.getVertex(j).getName());
                while (!stack.isEmpty())
                    System.out.println(stack.pop().getName());
            }
        }
    }

    private Graph g;
    private int[] pathTo;
    private int[] distance;

    public Dijkstra(Graph g){
        this.g = g;
    }

    public Stack<Vertex> pathTo(String start, String end) {
        int from = g.indexOf(start), to = g.indexOf(end);
        cal_route(from, to);
        if (distance[to] == Integer.MAX_VALUE) return null;  //end不可达，无路径可以返回
        Stack<Vertex> stack = new Stack<>();
        int i;
        for (i = to; i != pathTo[i]; i = pathTo[i])
            stack.push(g.getVertex(i));
        stack.push(g.getVertex(i));
        return stack;
    }

    public void cal_route(int from, int dest){
        int N = g.size();
        distance = new int[N];
        pathTo = new int[N];
        boolean[] marked = new boolean[N];
        for (int i = 0; i < N; i++){
            distance[i] = Integer.MAX_VALUE;
            pathTo[i] = from;
            marked[i] = false;
        }
        distance[from] = 0;
        for (int i = 0; i < N; i++){
            int v = getMin(marked);
            if (v == -1)   break;     //已经找不到下一个可达的节点，算法结束
            if (v == dest) break;     //已经找到到达dest的最短路径，不用再继续寻找下去
            relax(v, distance, pathTo);
            marked[v] = true;
        }
    }



    private int getMin(boolean[] marked){
        int min = 0;
        while (min < distance.length && marked[min]) min++;
        for (int i = min; i < distance.length; i++)
            if (!marked[i] && distance[min] > distance[i])
                min = i;
        if (distance[min] == Integer.MAX_VALUE) min = -1;
        return min;
    }


    private void relax(int v, int[] cost, int[] pathTo){
        Vertex a = g.getVertex(v);
        for (Edge e : g.getAdjacentEdges(v)){
            int w = g.indexOf(e.other(a).getName());
            if (cost[w] > cost[v] + e.distance()){
                cost[w] = cost[v] + e.distance();
                pathTo[w] = v;
            }
        }
    }
}
