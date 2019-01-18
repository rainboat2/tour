package graph;

public class Edge implements Comparable<Edge> {

    private Vertex v;
    private Vertex w;
    private int distance;

    public Edge(Vertex v, Vertex w, int distance) {
        this.v = v;
        this.w = w;
        this.distance = distance;
    }

    public int distance() {
        return distance;
    }

    /**
     * @return 返回两个节点中的任意一个
     */
    public Vertex either() {
        return v;
    }

    /**
     * @param s 边对应的两个节点中的任意一个
     * @return 同于给定节点的
     */
    public Vertex other(Vertex s) {
        if (s.equals(v)) return w;
        else if (s.equals(w)) return v;
        else throw new RuntimeException("inconsistent edge");
    }

    public int compareTo(Edge that) {
        return Integer.compare(this.distance, that.distance);
    }

    public String toString() {
        return String.format("%s——%s——%d", v.getName(), w.getName(), distance);
    }
}
