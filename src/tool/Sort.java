package tool;

import java.util.Comparator;

public class Sort<Type> {

    // 指定排序的比较方式，在调用排序算法时传入
    private Comparator<Type> comparator;

    /**
     * 快速排序
     * @param a 用于排序的数组
     * @param c 指定比较方式的Comparator
     */
    public void quick(Type[] a, Comparator<Type> c) {
        comparator = c;
        quick(a, 0, a.length - 1);
    }

    private void quick(Type[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = partition(a, lo, hi);
        quick(a, lo, mid - 1);
        quick(a, mid + 1, hi);
    }

    private int partition(Type[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        //循环中的++i和--j会跳过第一个i，j的值
        while (true) {
            while (cmp(a[++i], a[lo]) < 0) if (i >= hi) break;   //从左向右找到比a[lo]大的元素
            while (cmp(a[lo], a[--j]) < 0) if (j <= lo) break;   //从右向左找到比啊a[lo]小的元素

            if (i >= j) break;
            else swap(i, j, a);
        }
        /* 关于lo和j互换而不是与i互换的论证，分两种情况讨论
         * 1. i > j
         * 2. i = j
         */
        swap(lo, j, a);
        return j;
    }

    private int cmp(Type a, Type b) {
        return comparator.compare(a, b);
    }

    private void swap(int i, int j, Type[] a) {
        Type temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
