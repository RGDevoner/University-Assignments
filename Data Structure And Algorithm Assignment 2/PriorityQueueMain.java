package edu.aueb.ds.lab6.SimpleHeapPriorityQueue;

public class PriorityQueueMain {
    public static void main(String[] args) {
        PriorityQueueInterface pq = new HeapPriorityQueue(new IntegerComparator());

        System.out.println("Peeking on empty queue: " + pq.peek());
        System.out.println("GetMax on empty queue: " + pq.getMax());
        pq.add(4);
        pq.add(6);
        pq.add(1);
        pq.add(3);
        pq.add(2);
        pq.add(30);

        System.out.println("Peeking queue (30): " + pq.peek());
        System.out.println("GetMax on queue (30) : " + pq.getMax());

        System.out.println("GetMax on queue (6) : " + pq.getMax());

        pq.add(30);
        System.out.println("GetMax on queue (30) : " + pq.getMax());

        System.out.println("GetMax on queue (4) : " + pq.getMax());

        System.out.println("GetMax on queue (3) : " + pq.getMax());

        System.out.println("GetMax on queue (2) : " + pq.getMax());

        System.out.println("GetMax on queue (1) : " + pq.getMax());

        System.out.println("GetMax on queue (null) : " + pq.getMax());
        System.out.println("GetMax on queue (null) : " + pq.getMax());
    }
}
