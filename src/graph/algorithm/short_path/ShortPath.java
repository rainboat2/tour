package graph.algorithm.short_path;

import graph.Vertex;

import java.util.Stack;

public interface ShortPath {

    /**
     * 计算给定两个节点之间的最短路径，
     * 结果使用一个栈来保存
     * @param start 起点
     * @param end 终点
     * @return null   最短路径序列不存在
     *         Stack  保存最短路径的栈
     */
    Stack<Vertex> pathTo(String start, String end);
}
