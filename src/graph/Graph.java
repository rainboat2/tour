package graph;

import java.io.*;
import java.util.*;


public class Graph {

    private Map<String, Integer> map;
    private Node[] entry;
    private static Graph graph;

    private class Node {
        Vertex vertex;
        List<Edge> edges;

        Node(Vertex v) {
            this.vertex = v;
            this.edges = new LinkedList<>();
        }

        Edge getEdgeTo(Vertex w) {
            for (Edge e : edges)
                if (e.other(vertex).equals(w))
                    return e;
            return null;
        }

        // 返回所有邻接节点
        Iterable<Vertex> getVertices() {
            LinkedList<Vertex> list = new LinkedList<>();
            for (Edge e : edges)
                list.add(e.other(vertex));
            return list;
        }
    }

    private Graph() {
        load();
    }

    private Graph(Node[] entry, Map<String, Integer> map) {
        this.entry = entry;
        this.map = map;
    }

    public static Graph getGraph() {
        if (graph == null)
            graph = new Graph();
        return graph;
    }

    // 返回原图的一个子图
    public Graph subGraph(Collection<Edge> edges) {
        Node[] entry = new Node[size()];
        for (int i = 0; i < size(); i++)
            entry[i] = new Node(this.entry[i].vertex);
        Graph g = new Graph(entry, this.map);
        for (Edge e : edges)
            g.addEdge(e);
        return g;
    }

    public int size() {
        return map.size();
    }

    public int indexOf(Vertex v) {
        return indexOf(v.getName());
    }

    public int indexOf(String name) {
        Integer i = map.get(name);
        if (i == null) return -1;
        else return i;
    }

    public void addVertex(Vertex v) {
        if (map.size() == entry.length)
            resize(2 * map.size());
        if (map.get(v.getName()) != null)
            throw new RuntimeException("添加失败，景点名字不能重复");

        int index = map.size();
        map.put(v.getName(), index);
        entry[index] = new Node(v);
    }

    public Vertex getVertex(String v) {
        return getVertex(indexOf(v));
    }

    public Vertex getVertex(int v) {
        if (v < 0 || v >= size())
            return null;
        return entry[v].vertex;
    }

    public Iterable<Vertex> getAdjacentVertex(int v) {
        if (v < 0 || v >= size())
            return null;
        return entry[v].getVertices();
    }

    public Iterable<Vertex> getAllVertex() {
        LinkedList<Vertex> list = new LinkedList<>();
        for (int i = 0; i < size(); i++)
            list.add(entry[i].vertex);
        return list;
    }

    /**
     * 删除指定的节点
     * 1. 当删除节点为entry数组的最后一项时，直接删除
     * 2. 当删除节点位于entry数组中时，删除该元素，并将最后一项移到该位置以填补空缺
     *
     * @param v 需要删除的节点的名字
     * @throws NoSuchElementException 当指定节点不存在时会抛出该异常
     */
    public void removeVertex(String v) {
        int index = indexOf(v);
        if (index == -1)
            throw new NoSuchElementException("指定元素不存在");
        int end = size() - 1;
        map.remove(v);
        if (index != end) {
            Node n = entry[end];
            entry[index] = n;                     // 移动末尾元素以填补空缺
            map.put(n.vertex.getName(), index);   //更新n在map中的索引
        }
        entry[end] = null;   //删除末尾的引用，便于对象回收
    }

    public void addEdge(String v, String w, int distance) {
        int a = indexOf(v);
        int b = indexOf(w);
        if (a == -1 || b == -1)
            throw new NoSuchElementException("指定节点不存在!");
        Edge e = new Edge(getVertex(a), getVertex(b), distance);
        entry[a].edges.add(e);
        entry[b].edges.add(e);
    }

    public void addEdge(Edge e) {
        Vertex v = e.either();
        Vertex w = e.other(v);
        addEdge(v.getName(), w.getName(), e.distance());
    }

    public Iterable<Edge> getAllEdge() {
        Set<Edge> set = new HashSet<>();
        for (int i = 0; i < size(); i++)
            set.addAll(entry[i].edges);
        return set;
    }

    public void removeEdge(String v, String w) {
        int a = indexOf(v), b = indexOf(w);
        Edge e = getEdge(a, b);
        entry[a].edges.remove(e);
        entry[b].edges.remove(e);
    }

    public Edge getEdge(int a, int b) {
        if ((a < 0 || a > size()) || b < 0 || b > size())
            throw new NoSuchElementException("指定节点不存在");
        return entry[a].getEdgeTo(getVertex(b));
    }

    public Edge getEdge(String a, String b) {
        return getEdge(indexOf(a), indexOf(b));
    }

    public Edge getEdge(Vertex v, Vertex w) {
        return getEdge(indexOf(v), indexOf(w));
    }

    public Iterable<Edge> getAdjacentEdges(int index) {
        if (index == -1)
            throw new NoSuchElementException("指定节点不存在!");
        return entry[index].edges;
    }

    public int degree(int v) {
        return entry[v].edges.size();
    }

    public String[][] toArray() {
        int N = size();
        String[][] array = new String[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            array[i][0] = getVertex(i).getName();
            array[0][i] = getVertex(i).getName();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Edge e = getEdge(i, j);
                if (e == null) array[i + 1][j + 1] = Integer.MAX_VALUE + "";
                else array[i + 1][j + 1] = e.distance() + "";
            }
        }
        return array;
    }

    private void resize(int size) {
        Node[] temp = new Node[size];
        System.arraycopy(entry, 0, temp, 0, entry.length);
        entry = temp;
    }

    private void load() {
        try {
            FileInputStream in = new FileInputStream("file/information.txt");
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            load(r);
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load(BufferedReader r) throws IOException {
        String line;
        //读取图的节点数
        int size = Integer.parseInt(r.readLine());
        map = new HashMap<>(2 * size);
        entry = new Node[size];

        // 读取行  eg：飞流瀑^0^0^0^暂无说明^path
        while (!(line = r.readLine()).equals("<<")) {
            String[] info = line.split("\\^");
            addVertex(new Vertex(info));
        }

        // 读取行  eg：北门——狮子山——9
        while ((line = r.readLine()) != null) {
            String[] info = line.split("——");
            addEdge(info[0], info[1], Integer.parseInt(info[2]));
        }
    }

    public void save() {
        try {
            FileWriter writer = new FileWriter("file/information.txt");
            BufferedWriter w = new BufferedWriter(writer);
            save(w);
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save(BufferedWriter w) throws IOException {
        // 写入节点数
        w.write(map.size() + "\n");

        //写入节点信息
        for (int i = 0; i < size(); i++)
            w.write(entry[i].vertex + "\n");
        w.write("<<");
        w.newLine();

        //写入边
        for (Edge e : getAllEdge())
            w.write(e + "\n");
    }
}


class Test {
    public static void main(String[] args) {
        Graph g = Graph.getGraph();
        g.addVertex(new Vertex("temp", "", 0, 0, 0, ""));
        // 图的初始化状态
        System.out.println(g.size());
        for (Vertex v : g.getAllVertex())
            System.out.print(v.getName() + "  ");

        g.removeVertex("temp");
        System.out.println(g.size());
        for (Vertex w : g.getAllVertex())
            System.out.println(w.getName());
    }
}