package tour;

import graph.Graph;
import graph.Vertex;
import tool.Sort;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class Search_Sort {

    private Graph g;

    public Search_Sort(Graph g) {
        this.g = g;
    }

    // 对所有的节点进行模糊匹配
    public Vertex[] search(String keyword) {
        List<Vertex> list = new LinkedList<>();
        for (Vertex v : g.getAllVertex())
            if (v.isContains(keyword))
                list.add(v);
        Vertex[] v = new Vertex[list.size()];
        v = list.toArray(v);
        return v;
    }

    public void sort(String pattern, Vertex[] v) {
        Sort<Vertex> s = new Sort<>();
        switch (pattern) {
            case "欢迎度":
                s.quick(v, Comparator.comparing(Vertex::getPopular));
                break;
            case "名称":
                s.quick(v, Comparator.comparing(Vertex::getName));
                break;
            case "休息区数量":
                s.quick(v, Comparator.comparing(Vertex::getRestArea));
                break;
            case "相邻景点数量":
                s.quick(v, Comparator.comparingInt(o -> g.degree(g.indexOf(o))));
                break;
            case "厕所数量":
                s.quick(v, Comparator.comparing(Vertex::getToilet));
                break;
            default:
                throw new UnsupportedOperationException("不支持的搜索模式：" + pattern);
        }
    }
}
