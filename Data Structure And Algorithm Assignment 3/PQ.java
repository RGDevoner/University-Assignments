    public class PQ {
    private City[] PQueue;
    private int size;
    public void PriorityQueue(){
    this.PQueue=new City[20];
    this.size=0;
    }
public boolean isEmpty() {
    return size == 0;
    }
public int size() {
    return size;
    }
public void insert(City x){
    if(size>=PQueue.length*(3/4)){//afksanei sto diplo tou pinaka an exei perasei to 75% i xoritikotita toy 
    resize();
    }
    size=size+1;
    int hsize=size;
    PQueue[hsize]=x;
    
    while ((hsize > 1) && (PQueue[hsize].compareTo(PQueue[hsize / 2]) < 0)){
    City temp = PQueue[hsize];
    PQueue[hsize] = PQueue[hsize/2];
    PQueue[hsize/2] = temp;  
    hsize=hsize/2;
    }
    }
public void resize(){//afksanei sto diplo tou pinaka an exei perasei to 75% i xoritikotita toy (edo to kanei afto)
    City[] NewPQueue= new City[PQueue.length*2];
    for (int i = 1; i < PQueue.length; i++){
    NewPQueue[i]=PQueue[i];
    }
    PQueue=NewPQueue;
    }
public City min() {
    if (isEmpty()) {
    return null;
    }
    return  PQueue[1];
    }
public City remove(int ID) {
    int hsize=0 ;
    for (int i = 1; i <= size; i++) {
    if (PQueue[i].getID() == ID) {
    hsize = i;
    break;
    }
    }
    if (hsize == 0) {
    return null;
    }

    City CityR = PQueue[hsize];
    PQueue[hsize] = PQueue[size];
    size=size-1;


    heapify(hsize);

    return CityR;
    }
private void heapify(int s) {
        int left = 2 * s;
        int right = 2 * s + 1;
        int heap = s;

        if ((left <= size) && (PQueue[left].compareTo(PQueue[heap])) < 0) {
            heap = left;
        }

        if ((right <= size) && (PQueue[right].compareTo(PQueue[heap])) < 0) {
            heap = right;
        }

        if (heap != s) {
             City temp = PQueue[s];
            PQueue[s] = PQueue[heap];
            PQueue[heap] = temp;
            heapify(heap);
        }
    }
    }

