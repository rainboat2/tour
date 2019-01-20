package graph.algorithm.ant;

import graph.Graph;

import java.util.Random;


public class Ant {


    private int[] tourPath;   //蚂蚁的路径
    private boolean[] marked; //记录节点是否被访问过
    private int tourLength;       //路径总长度
    private Graph g;

    public Ant(Graph g){
        this.g = g;
    }

    public void randomSelectCity(int start){
        int N = g.size();
        marked = new boolean[N];
        tourPath = new int[N+1];
        tourLength = 0;
        for (int i = 0; i < N; i++){
            tourPath[i] = -1;
            marked[i] = false;
        }
        marked[start] = true;
        tourPath[0] = start;
    }

    /**
     * 选择下一个城市
     *
     * 产生一个随机数，顺序计算各个城市的选中概率之和，直到大于该随机数，
     * 则选择循环系数代表的节点（前提是该节点没选过。）遍历完所有节点后，
     * 将起始节点加入到 tourPath中，形成一个完整回路。
     *
     * @param index 需要第index个节点
     * @param tao 蚂蚁在路径上留下的信息素
     * @param distance 距离矩阵
     */
    public void selectNextCity(int index, double[][] tao, int[][] distance){
        double[] p;  //记录概率
        p = new double[g.size()];
        // 计算选中概率所需的系数
        double alpha = 1.0;
        double beta = 2.0;
        double sum = 0;
        int currentCity = tourPath[index-1];
        // 计算公式中的分母
        for (int i = 0; i < g.size(); i++)
            if (!marked[i])
                sum += (Math.pow(tao[currentCity][i], alpha)* Math.pow(1.0/distance[currentCity][i], beta));
        // 计算每个城市中被选中的概率
        for (int i = 0; i < g.size(); i++) {
            if (marked[i]) p[i] = 0;
            else p[i] = (Math.pow(tao[currentCity][i], alpha) * Math.pow(1.0 / distance[currentCity][i], beta)) / sum;
        }
        Random random = new Random();
        double selectP = random.nextDouble();
        //轮盘赌选择一个城市
        double sumSelect = 0;
        int seleCity = -1;
        //城市选择随机，直到n个概率加起来大于随机数，则选择该城市
        for (int i = 0; i < g.size(); i++){
            sumSelect += p[i];
            if (sumSelect >= selectP){
                seleCity = i;
                break;
            }
        }
        tourPath[index] = seleCity;
        marked[seleCity] = true;
    }

    /**
     * @param distance 路径总长度
     */
    public void calTourLength(int[][] distance){
        tourLength = 0;
        tourPath[g.size()] = tourPath[0];
        for (int i = 0; i < g.size(); i++)
            tourLength += distance[tourPath[i]][tourPath[i+1]];
    }

    public int getTourLength(){
        return tourLength;
    }

    public int[] getTourPath(){
        return tourPath;
    }
}
