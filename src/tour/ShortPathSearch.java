package tour;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import graph.algorithm.short_path.ShortPath;

import java.util.NoSuchElementException;
import java.util.Stack;

public class ShortPathSearch {

    private Graph g;

    public ShortPathSearch(Graph g) {
        this.g = g;
    }

    public String shortPath(String start, String end, ShortPath shortPath){
        if (!isExist(start, end))
            throw new NoSuchElementException("输入的景点名称不存在");
        Stack<Vertex> path = shortPath.pathTo(start, end);
        return recover_path(path);
    }

    private String recover_path(Stack<Vertex> path){
        if (path == null) return "无可用路径";
        int distance = 0;
        StringBuilder s = new StringBuilder();
        Vertex cur = path.pop();
        s.append(cur.getName()).append(" --> ");
        while (!path.isEmpty()){
            Vertex w = path.pop();
            Edge e = g.getEdge(cur.getName(), w.getName());
            distance += e.distance();
            //生成路径
            s.append(w.getName());
            if (!path.isEmpty())
                s.append(" --> ");
            cur = w;
        }
        return String.format("推荐的路径：\n总路程： %d\n ", distance) + s.toString();
    }

    private boolean isExist(String start, String end){
        Vertex a = g.getVertex(start);
        Vertex b = g.getVertex(end);
        return ( a != null && b != null);
    }

}