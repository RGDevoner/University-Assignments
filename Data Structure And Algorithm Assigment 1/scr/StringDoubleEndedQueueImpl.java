//ACHILLEAS ZOCHIOS p3220273, ALEXANDROS MAKRYGIANNHS p3210271
import java.util.NoSuchElementException;
import java.io.PrintStream;



class StringDoubleEndedQueueImpl implements StringDoubleEndedQueue {
    private class Node{
        String item;
        Node next;
        Node prev;
        Node(String item){
            this.item=item;
            this.next=null;
            this.prev=null;
        }
    }
    private Node head;
    private Node tail;
    private int size;
    
    public  StringDoubleEndedQueueImpl(){
    this.head=null;
    this.tail=null;
    this.size=0;
    }
 @Override   
 public boolean isEmpty() {
        return head == null;
    }
@Override    
public void addFirst(String item){
    Node n = new Node(item);

        if (size==0) {
            head = n;
            tail = n;
        } else {
            n.next= head ;
            head.prev = n;
            head = n;
        
        }
        size=size+1;
}
@Override
public String removeLast() throws NoSuchElementException{
    if (size == 0) {
        throw new NoSuchElementException();
    }
    String item = tail.item;
    if (size == 1) {
        head = null;
         tail = null;
    } else {
        tail = tail.prev;
        tail.next = null;
    }
    size=size-1;
    return item;
}
@Override
public String removeFirst() throws NoSuchElementException {
    if(size==0){
        throw new NoSuchElementException();
    }
    String item = head.item;
    if (size == 1) {
        head = null;
        tail = null;
    } else {
        head = head.next;
        head.prev = null;
    }
    size=size-1;
    return item;

}
@Override
public void addLast(String item){
    Node n = new Node(item);
    if (size==0) {
        head = n;
        tail = n;
    } else {
        n.prev=tail;
        tail.next=n;
        tail=n;

}
size=size+1;

}
@Override
public String getFirst(){
    if (size == 0) {
        throw new NoSuchElementException();
    }else{
    return head.item;}
}
@Override
public String getLast() {
    if (size == 0) {
        throw new NoSuchElementException();
    }else{
    return tail.item;
}
}
@Override
public int size() {
    return size;
}
@Override
public void printQueue(PrintStream stream){
    Node current = head;
    while (current != null) {
        stream.print(current.item + " ");
        current = current.next;
    }
    stream.println(); 
}
}

