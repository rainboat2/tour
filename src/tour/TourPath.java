package tour;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import graph.algorithm.other.HamiltonRoadFinder;
import graph.algorithm.other.PathSequenceAnalyser;
import graph.algorithm.small_tree.Kruskal;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 本类为 GUI 中以下两个功能提供支持
 * 管理员，导游路径分析
 * 用户，获取导游路径
 *
 *  基本实现思路：
 *  1. 指定起点，使用 HamiltonRoadFinder 获取一个节点序列（理想最优路线）
 *  2. 使用 PathSequenceAnalyser 对获取的节点进行分析
 *  3. 获取分析的结果（导游路线，缺失路径)用于显示
 */
public class TourPath {

    private Graph g;
    private HamiltonRoadFinder finder;

    public TourPath(Graph g) {
        this(g, new HamiltonRoadFinder(new Kruskal(g).subTree()));
    }

    public TourPath(Graph g, HamiltonRoadFinder finder){
        this.g = g;
        this.finder = finder;
    }

    /**
     * 通过调用PathSequenceAnalysis对节点序列进行分析处理
     * 得到一个导游路线（近似解）
     *
     * @param start 导游路线的起点
     * @return 计算所得的一个近似导游路线图
     */
    public String tourPath(String start) {
        if (g.getVertex(start) == null)
            throw new NoSuchElementException("找不到指定节点");
        PathSequenceAnalyser analysis = getAnalyser(start);
        return showTourPath(analysis.getTourPath());
    }


    /**
     * 通过调用PathSequenceAnalysis中的相关方法，
     * 得到所需的分析结果，并对结果进行处理
     *
     * @param start 导游图的起点
     * @return  分析结果，包括以下内容
     *          1. 起点
     *          2. 理想路径（由finder找到，用于分析的序列）
     *          3. 缺少路径
     *          4. 最终生成导游路径
     * @see PathSequenceAnalyser
     */
    public String getTourPathAnalysis(String start){
        if (g.getVertex(start) == null)
            throw new NoSuchElementException("找不到指定节点");

        // 对序列进行分析，并获得分析结果
        PathSequenceAnalyser analyser = getAnalyser(start);
        Iterator<Vertex> it = analyser.getSequence().iterator();
        List<Vertex> tourPath = analyser.getTourPath();
        String missPath = analyser.getMissPathAnalyzeResult();

        // 对上述结果进行处理，使用字符串记录
        StringBuilder s = new StringBuilder();
        s.append("起点: ").append(start).append("\n\n");
        s.append("理想路径: \n");
        while (it.hasNext()){
            Vertex v = it.next();
            if (it.hasNext()) s.append(v.getName()).append("-->");
            else              s.append(v.getName()).append("\n\n");
        }
        s.append("缺失路径: \n").append(missPath).append("\n");
        s.append("最终生成导游路线：\n");
        s.append(showTourPath(tourPath));
        return s.toString();
    }

    /**
     * 生成一个用于对路径进行分析的对象
     *
     * @param  start 路径起点
     * @return 路径分析对象
     * @see PathSequenceAnalyser
     */
    private PathSequenceAnalyser getAnalyser(String start) {
        List<Vertex> sequence = finder.getSequence(g.indexOf(start));  // 使用finder获取用于分析的节点序列
        PathSequenceAnalyser analyser = new PathSequenceAnalyser(g, sequence);
        return analyser;
    }

    /**
     * 接受一个路径序列，生成一条用字符串表示的路径
     * 并且包含对距离和耗费时间的估计
     *
     * @param path 路径序列
     * @return 可供显示的路径字符串
     */
    private String showTourPath(List<Vertex> path) {
        StringBuilder s = new StringBuilder();
        Iterator<Vertex> it = path.iterator();

        int distance = 0;
        Vertex cur = it.next();
        while (it.hasNext()) {
            Vertex next = it.next();
            Edge e = g.getEdge(cur, next);
            distance += e.distance();
            s.append(cur.getName());
            if (it.hasNext())
                s.append("-->");
            cur = next;
        }
        s.append(String.format("\n总距离:%d, 预计耗费时间%d小时", distance, distance / 24));
        return s.toString();
    }
}
