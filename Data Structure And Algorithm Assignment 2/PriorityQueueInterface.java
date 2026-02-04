package edu.aueb.ds.lab6.SimpleHeapPriorityQueue;


/**
 * Priority Queue interface
 *
 */
public interface PriorityQueueInterface {

    /**
     * Inserts the specified element into this priority queue.
     *
     * @param item
     */
    void add(Object item);


    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
     *
     * @return the head of the queue
     */
    Object peek();


    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty.
     *
     * @return the head of the queue
     */
    Object getMax();
}
