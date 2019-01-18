package graph.algorithm.short_path;

import java.util.NoSuchElementException;

public class IndexPQ {

    private Node[] a;
    private int size;

    private class Node{
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }

    public IndexPQ(){
        this(10);
    }

    public IndexPQ(int size){
        a = new Node[size];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(int index, int cost){
        Node n = new Node(index, cost);
        if (size == a.length) resize(size*2);
        a[size++] = n;
        swim(size-1);
    }

    public int peek(){
        if (isEmpty())
            throw new NoSuchElementException();
        return a[0].index;
    }

    public int getMin(){
        Node result = a[0];
        exchange(0, --size);
        sink(0);
        a[size] = null;
        return result.index;
    }

    public boolean contains(int index){
        return find(index) != -1;
    }

    public int find(int index){
        for (int i = 0; i < size; i++)
            if (a[i].index == index)
                return i;
        return -1;
    }

    public void change(int index, int distance){
        int i = find(index);
        if (i == -1) return;
        int before = a[index].distance;
        a[i].distance = distance;
        if (before < distance)   sink(i);
        else                     swim(i);
    }

    private void resize(int newSize){
        Node[] temp = new Node[newSize];
        System.arraycopy(a, 0, temp, 0, a.length);
        a = temp;
    }

    /*
     * 将较大的节点下沉
     * 若当前节点大于子节点中任意一个，与子节点中最小的一个进行替换
     */
    private void sink(int i){
        while ((2*i+1) <= size-1){
            int j = 2*i+1;
            if (j < size-1 && cmp(j, j+1) > 0)  j++;
            if (cmp(i, j) < 0)                    break;
            exchange(i, j);
            i = j;
        }
    }

    /*
     * 将较小的节点上浮
     * 若当前节点小于其父节点，将其与父节点交换
     */
    private void swim(int i){
        while(i != 0){
            int j = (i - 1) / 2;
            if (cmp(i, j) > 0)  break;
            exchange(i, j);
            i = j;
        }
    }

    private void exchange(int i, int j){
        Node temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private int cmp(int i, int j){
        return Integer.compare(a[i].distance, a[j].distance);
    }
}
