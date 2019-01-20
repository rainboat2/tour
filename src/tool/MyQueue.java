package tool;

import java.util.NoSuchElementException;

/**
 * 自定义的队列（链表实现）
 * @param <Type> 需要放入队列中的元素的类型
 */
public class MyQueue<Type> {

    private Node first;
    private Node rear;

    private class Node {
        Type element;
        Node next;

        public Node(Type element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    public MyQueue() {
        clear();
    }

    /**
     * 清空队列中的所有元素
     */
    public void clear() {
        first = rear = new Node(null, null);
    }

    public boolean isEmpty() {
        return first == rear;
    }

    /**
     * 将一个元素加入到队列中
     * @param element 需要加入队列的元素
     */
    public void enQueue(Type element) {
        rear.next = new Node(element, null);
        rear = rear.next;
    }

    /**
     * @return 队首的元素
     */
    public Type deQueue() {
        if (isEmpty())
            throw new NoSuchElementException();
        first = first.next;
        return first.element;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Node temp = first.next; temp != null; temp = temp.next)
            s.append(temp.element);
        return s.toString();
    }
}
