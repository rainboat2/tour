package graph.algorithm.other;

import graph.Edge;
import graph.Graph;

import java.util.Arrays;


public class GraphViewer {

    public static void main(String[] args){
        Graph g = Graph.getGraph();
        System.out.println(Arrays.deepToString(getArray(g)));
    }

    //返回图的一个邻接矩阵
    public static int[][] getArray(Graph g){
        int N = g.size();
        int[][] a = new int[N][N];

        for (int i = 0; i < N; i++){
            for (int j = i; j < N; j++){
                if (i == j)  a[i][j] = 0;
                else{
                    Edge e = g.getEdge(i, j);
                    a[i][j] = (e == null) ? Integer.MAX_VALUE : e.distance();
                    a[j][i] = a[i][j];
                }
            }
        }
        return a;
    }
}
