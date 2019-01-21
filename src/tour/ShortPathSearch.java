package tour;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import graph.algorithm.short_path.ShortPath;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * 本类为 gui 中查找最短路径的功能提供支持
 *
 * 基本思路：
 * 1. 调用最短路径算法获得最短路径的节点序列
 * 2. 将所得节点序列转化为可供显示的字符串
 */
public class ShortPathSearch {

    private Graph g;

    public ShortPathSearch(Graph g) {
        this.g = g;
    }

    /**
     * 根据输入，调用最短路径算法获得路径序列
     * 并且将所得序列转为字符串用于显示
     *
     * @param start 起点节点的名称
     * @param end   终点的名称
     * @param shortPath 最短路径算法的接口，可以传入不同的最短路径算法
     * @return 显示最短路径的字符串
     * @throws NoSuchElementException 当传入错误的名字，导致在图中无法找到对应节点时
     *                                 会抛出该异常
     *
     * @see ShortPath
     */
    public String shortPath(String start, String end, ShortPath shortPath) {
        if (g.getVertex(start) == null || g.getVertex(end) == null)
            throw new NoSuchElementException("输入的景点名称不存在");
        Stack<Vertex> path = shortPath.pathTo(start, end);
        return recover_path(path);
    }

    /**
     * 接受一个路径序列，生成一条用字符串表示的路径
     * 并且包含对距离的估计
     *
     * @param path 保存路径的栈（栈顶为开始节点）
     * @return 可供显示的路径字符串
     */
    private String recover_path(Stack<Vertex> path) {
        if (path == null) return "无可用路径";
        if (path.size() == 1)
            return String.format("仅包括一个节点%s，无法生成路径", path.peek().getName());

        int distance = 0;
        StringBuilder s = new StringBuilder();
        Vertex cur = path.pop();
        s.append(cur.getName()).append(" --> ");
        while (!path.isEmpty()) {
            Vertex w = path.pop();
            Edge e = g.getEdge(cur.getName(), w.getName());
            distance += e.distance();
            //生成路径
            s.append(w.getName());
            if (!path.isEmpty())
                s.append(" --> ");
            cur = w;
        }
        return String.format("推荐的路径\n %s\n总距离：%d", s.toString(), distance);
    }


}