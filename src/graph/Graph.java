package graph;

import java.io.*;
import java.util.*;

/**
 * 本类定义的图的基本数据结构
 */
public class Graph {

    private static Graph graph;  //保存一个图的对象，用于单态设计模式

    private Map<String, Integer> map;  //记录每个节点名字对应的索引
    private Node[] entry; //邻接表

    /**
     * 邻接表的存储方式
     */
    private class Node {
        Vertex vertex;
        List<Edge> edges;

        Node(Vertex v) {
            this.vertex = v;
            this.edges = new LinkedList<>();
        }

        // 获取从当前节点到给定节点v的一条边
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

    private Graph() throws IOException {
        load();
    }

    private Graph(Node[] entry, Map<String, Integer> map) {
        this.entry = entry;
        this.map = map;
    }

    /**
     * 单态设计模式，确保程序运行的时候只有一个对象
     * @return 一个图的对象
     */
    public static Graph getGraph() throws IOException {
        if (graph == null)
            graph = new Graph();
        return graph;
    }

    /**
     * 给定子图中所应该包含的边，生成一个原图的子图
     * 该图子图与原图共享的Vertex对象
     *
     * @param edges 子图中应该包含的边
     * @return 一个包含edges中所有边的子图
     */
    public Graph subGraph(Collection<Edge> edges) {
        Node[] entry = new Node[size()];
        //复制Vertex对象
        for (int i = 0; i < size(); i++)
            entry[i] = new Node(this.entry[i].vertex);
        Map<String, Integer> map = new HashMap<>();
        //复制map
        for (String key : this.map.keySet())
            map.put(key, this.map.get(key));
        Graph g = new Graph(entry, map);
        for (Edge e : edges)
            g.addEdge(e);
        return g;
    }

    /**
     * @return 返回图中节点的数量
     */
    public int size() {
        return map.size();
    }

    /**
     * 节点在图中的索引
     * @param v 节点
     * @return 节点索引，当找不到节点的时候，返回-1
     */
    public int indexOf(Vertex v) {
        if (v == null) return -1;
        return indexOf(v.getName());
    }

    /**
     * 给出节点名称在图中的一个索引
     * @param name 节点名称
     * @return 节点索引，当找不到节点的时候，返回-1
     */
    public int indexOf(String name) {
        Integer i = map.get(name);
        if (i == null) return -1;
        else return i;
    }

    /**
     * 向图中添加一个节点
     * @param v 需要添加的节点对象
     * @throws RuntimeException 图中的节点名称不应该重复，当添加的节点名称重复时
     *                          抛出该异常，阻止节点被添加
     */
    public void addVertex(Vertex v) {
        if (map.size() == entry.length)
            resize(2 * map.size());
        if (map.get(v.getName()) != null)
            throw new RuntimeException("添加失败，景点名字不能重复");

        int index = map.size();
        map.put(v.getName(), index);
        entry[index] = new Node(v);
    }

    /**
     * 给定节点名称，获取图中的一个节点
     * @param v 节点的名字
     * @return 若找到，放回节点对象，否则返回null
     */
    public Vertex getVertex(String v) {
        return getVertex(indexOf(v));
    }

    /**
     * 图根据名字来记录索引，因此更改结点名字的时候必需要更新map
     * @param v 需要改名的结点
     * @param name 修改后的名字
     */
    public void renameVertex(Vertex v, String name){
        int index = indexOf(v);
        if (index == -1)
            throw new NoSuchElementException("指定结点不存在");
        if (indexOf(name) != -1 && !v.getName().equals(name))
            throw new UnsupportedOperationException("结点名称不能重复");
        // 更新map并且改变结点的名字
        map.remove(v.getName());
        map.put(name, index);
        v.setName(name);
    }


    /**
     * 给定节点在图中的索引，返回节点对象
     * @param v 节点在图中的索引
     * @return 索引合法 返回节点对象
     *         索引超过范围，返回null
     */
    public Vertex getVertex(int v) {
        if (v < 0 || v >= size())
            return null;
        return entry[v].vertex;
    }

    /**
     * 给定图中节点的一个索引，
     * 获得该节点的所有邻接节点
     * @param v 节点索引
     * @return 索引合法 返回给定节点的邻接节点
     *         索引超过范围，返回null
     */
    public Iterable<Vertex> getAdjacentVertex(int v) {
        if (v < 0 || v >= size())
            return null;
        return entry[v].getVertices();
    }

    /**
     * @return 返回图中所有节点的一个迭代器
     */
    public Iterable<Vertex> getAllVertex() {
        ArrayList<Vertex> list = new ArrayList<>(size());
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
     * @throws NoSuchElementException 指定节点不存在
     */
    public void removeVertex(String v) {
        int index = indexOf(v);
        if (index == -1)
            throw new NoSuchElementException("指定元素不存在");
        removeAllEdges(entry[index].vertex);
        int end = size() - 1;
        map.remove(v);
        if (index != end) {
            Node n = entry[end];
            entry[index] = n;                     // 移动末尾元素以填补空缺
            map.put(n.vertex.getName(), index);   // 更新n在map中的索引
        }
        entry[end] = null;   //删除末尾的引用，便于对象回收
    }

    /**
     * 删除一个结点的所有邻接边
     * @param v 需要删除边的结点
     */
    private void removeAllEdges(Vertex v){
        int index = indexOf(v);
        for (Edge e : getAdjacentEdges(index)){
            Vertex w = e.other(v);
            removeEdge(v.getName(), w.getName());
        }
    }

    /**
     * 向图中添加一条边
     * @param v 节点一
     * @param w  节点二
     * @param distance 边所对应的距离
     * @throws NoSuchElementException 指定的节点不存在
     */
    public void addEdge(String v, String w, int distance) {
        int a = indexOf(v);
        int b = indexOf(w);
        if (a == -1 || b == -1)
            throw new NoSuchElementException("指定节点不存在!");
        Edge e = new Edge(getVertex(a), getVertex(b), distance);
        entry[a].edges.add(e);
        entry[b].edges.add(e);
    }

    /**
     * 向图中添加一条边
     * @param e 边对象
     * @throws NoSuchElementException 当添加的边所对应的两个节点不在图中的时候
     */
    public void addEdge(Edge e) {
        Vertex v = e.either();
        Vertex w = e.other(v);
        addEdge(v.getName(), w.getName(), e.distance());
    }

    /**
     * @return 包含图中的所有边的一个可迭代对象
     */
    public Iterable<Edge> getAllEdge() {
        Set<Edge> set = new HashSet<>();
        for (int i = 0; i < size(); i++)
            set.addAll(entry[i].edges);
        return set;
    }

    /**
     * 移除一条边
     * @param v 节点一
     * @param w 节点二
     * @throws NoSuchElementException 指定节点不存在时抛出该异常
     */
    public void removeEdge(String v, String w) {
        int a = indexOf(v), b = indexOf(w);
        Edge e = getEdge(a, b);
        entry[a].edges.remove(e);
        entry[b].edges.remove(e);
    }

    /**
     * 根据两个节点获取图中的一条边
     * @param a 节点一
     * @param b 节点二
     * @return 连接节点a，b的一条边，若改边不存在则返回null
     * @throws NoSuchElementException 当指定边对应的两个结点不存在的时候抛出该异常
     */
    public Edge getEdge(int a, int b) {
        if ((a < 0 || a > size()) || b < 0 || b > size())
            throw new NoSuchElementException("指定节点不存在");
        return entry[a].getEdgeTo(getVertex(b));
    }

    /**
     * 根据两个节点获取图中的一条边
     * @param a 节点一的名称
     * @param b 节点二的名称
     * @return 连接节点a，b的一条边，若改边不存在则返回null
     */
    public Edge getEdge(String a, String b) {
        return getEdge(indexOf(a), indexOf(b));
    }

    /**
     * 根据两个节点获取图中的一条边
     * @param v 节点一对象
     * @param w 节点二对象
     * @return 连接节点a，b的一条边，若改边不存在则返回null
     */
    public Edge getEdge(Vertex v, Vertex w) {
        return getEdge(indexOf(v), indexOf(w));
    }

    /**
     * 根据索引找到对应节点的所有邻接边
     * @param index 节点索引
     * @return 索引index对应节点的邻接边
     * @throws NoSuchElementException 当指定节点不存在时抛出该异常
     */
    public Iterable<Edge> getAdjacentEdges(int index) {
        if (index == -1)
            throw new NoSuchElementException("指定节点不存在!");
        return entry[index].edges;
    }

    /**
     * 返回指定节点的度
     * 节点的度 == 邻接节点数 == 邻接边数
     * @param v 节点索引
     * @return 对应节点的度
     * @throws IndexOutOfBoundsException 当给定索引越界时会抛出该异常
     */
    public int degree(int v) {
        return entry[v].edges.size();
    }

    /**
     * 图中的边数等于节点总度数的二分之一
     *
     * @return 图中边的个数
     */
    public int numberOfEdges(){
        int totalDegree = 0;
        for (int i = 0; i < size(); i++)
            totalDegree += degree(i);
        return totalDegree/2;
    }

    /**
     * 将图以邻接矩阵的形式展示出来
     * @return 一个邻接数组
     */
    public String[][] toArray() {
        int N = size();
        String[][] array = new String[N + 1][N + 1];
        array[0][0] = "";
        // 确定数组的表头（将节点名称填入第一列和第一行）
        for (int i = 1; i <= N; i++) {
            array[i][0] = getVertex(i-1).getName();
            array[0][i] = getVertex(i-1).getName();
        }
        // 将节点间的距离填入到对应的地方
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Edge e = getEdge(i, j);
                if (e == null) array[i + 1][j + 1] = Integer.MAX_VALUE + "";
                else array[i + 1][j + 1] = e.distance() + "";
            }
        }
        return array;
    }

    /**
     * 添加节点的时候，可能会碰到容量存放节点的数组entry已经满了的情况
     * 调用此方法可以改变entry数组大小对数组进行扩容
     *
     * @param size 扩容后的大小
     */
    private void resize(int size) {
        Node[] temp = new Node[size];
        System.arraycopy(entry, 0, temp, 0, entry.length);
        entry = temp;
    }

    /**
     * 将图从文件中读取出来
     *
     * 基本思路：
     * 确定图中结点个数：文件第一行
     * 读取所有的结点
     * 读取所有的边（保存结点的行和保存边的行使用"<<"进行分割）
     * @throws IOException 读取时发生错误
     * @throws NumberFormatException 文本文件的第一行不是数字
     */
    private void load() throws IOException {
        FileInputStream in = new FileInputStream("file/information.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(in));

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

        r.close();
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
        w.write("<<"); //"<<"符号用作分隔符
        w.newLine();

        //写入边
        for (Edge e : getAllEdge())
            w.write(e + "\n");
    }
}