package com.spicis.utils;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class TopKUtils<T> {

    private PriorityQueue<T> priorityQueue;
    private int k;
    private Comparator<T> comparator;

    public TopKUtils(int k, Comparator<T> comparator) {
        this.k = k;
        this.comparator = comparator;
        priorityQueue = new PriorityQueue<>(k, comparator);
    }

    public boolean offer(T t) {
        if (priorityQueue.size() < k) {
            priorityQueue.offer(t);
        } else if (comparator.compare(priorityQueue.peek(), t) < 0) {
            priorityQueue.poll();
            priorityQueue.offer(t);
        }
        return true;
    }

    public List<T> getTopK() {
        int size = priorityQueue.size();
        List<T> res = new LinkedList<>();
        while (size-- > 0) {
            res.add(priorityQueue.poll());
        }
        return res;
    }

    public void clear() {
        priorityQueue.clear();;
    }
}
