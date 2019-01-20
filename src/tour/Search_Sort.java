package tour;

import graph.Graph;
import graph.Vertex;
import tool.Sort;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 提供两个功能
 *  1. 按照关键字对图进行搜索
 *  2. 调用排序算法，对指定的节点序列进行排序
 *
 */
public class Search_Sort {

    private Graph g;

    public Search_Sort(Graph g) {
        this.g = g;
    }

    /**
     * 从图中获取所有节点，选出匹配的关键字
     * @param keyword 关键字
     * @return 名称或描述中包含关键字的节点
     * @see Vertex
     */
    public Vertex[] search(String keyword) {
        List<Vertex> list = new LinkedList<>();
        for (Vertex v : g.getAllVertex())
            if (v.isContains(keyword))  //Vertex中包含一个
                list.add(v);
        Vertex[] v = new Vertex[list.size()];
        v = list.toArray(v);
        return v;
    }

    /**
     * 使用 Comparator来确定节点间比较大小的方式
     *
     * @param pattern 指定排序的方式
     * @param v 需要排序的节点序列
     * @throws UnsupportedOperationException 当传入的pattern不等于任何一个case条件时，
     *         本方法无法处理，会抛出该异常
     */
    public void sort(String pattern, Vertex[] v) {
        Sort<Vertex> s = new Sort<>();
        switch (pattern) {
            case "欢迎度":
                s.quick(v, (o1, o2) -> Integer.compare(o2.getPopular(), o1.getPopular()));
                break;
            case "名称":
                s.quick(v, Comparator.comparing(Vertex::getName));
                break;
            case "休息区数量":
                s.quick(v, (o1, o2) -> Integer.compare(o2.getRestArea(), o1.getRestArea()));
                break;
            case "相邻景点数量":
                s.quick(v, Comparator.comparingInt(o -> g.degree(g.indexOf(o))));
                break;
            case "厕所数量":
                s.quick(v, (o1, o2) -> Integer.compare(o2.getToilet(), o1.getToilet()));
                break;
            default:
                throw new UnsupportedOperationException("不支持的搜索模式：" + pattern);
        }
    }
}
