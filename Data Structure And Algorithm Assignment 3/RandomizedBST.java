import java.util.*;
import java.io.*;

public class RandomizedBST implements TaxEvasionInterface{
    
private class TreeNode {
    public static void main(String []args){
        RandomizedBST tree = new RandomizedBST();
        int choice=11;

    while(choice!=0){
        Scanner in=new Scanner(System.in);  //main menu 
        System.out.println("Press the number of the function you want");
        System.out.println("1. Insert new depositor");
        System.out.println("2. Load depositor from file");
        System.out.println("3. Update the savings of an already existing depositor");
        System.out.println("4. Search a depositor by AFM");
        System.out.println("5. Search a depositor by last name");
        System.out.println("6. Remove a depositor by AFM");
        System.out.println("7. Get mean savings of the depositors");
        System.out.println("8. Print the top 'x' depositors that most likely tax envasion " );
        System.out.println("9. Print all depositors sorted in ascending order");
        System.out.println("0. Exit");
        choice=in.nextInt();
        if(choice==0){//exit
            System.out.print("exiting the program...");
            break;
        }if(choice==1){//Insert new depositor
           System.out.println(" AFM: "); 
           int AFM=in.nextInt();
           System.out.println(" First name of Depositor: "); 
           String firstName=in.nextLine();
           System.out.println(" Last name of Depositor: "); 
           String lastName=in.nextLine();
           System.out.println(" Savings of Depositor: "); 
           double savings=in.nextDouble();
           System.out.println("Taxed Income of Depositor: "); 
           double taxedIncome=in.nextDouble();
           LargeDepositor h=new LargeDepositor(AFM,firstName,lastName,savings,taxedIncome);
           
           tree.insert(h);

        }
        if(choice==2){//Load depositor from file
            System.out.println("Loading data from file, please wait... ");
            tree.load("filename.txt");
        }
        if(choice==3){//Update the savings of an already existing depositor
            System.out.println(" Write the AFM of the Depositor you're looking for"); 
            int AFM=in.nextInt();
            System.out.println("Write the updated savings of this Depositor");   
            double savings=in.nextDouble();
            tree.updateSavings(AFM,savings);
        }if(choice==4){//Search a depositor by AFM
            System.out.println(" Write the AFM of the Depositor you're looking for"); 
            int AFM=in.nextInt();
            tree.searchByAFM(AFM);
        }if(choice==5){//Search a depositor by last name
            System.out.println(" Write the last name of the Depositor you're looking for"); 
            String lastName=in.nextLine();
            tree.searchByLastName(lastName);
        }if(choice==6){//Remove a depositor by AFM
            System.out.println("Write the AFM of the Depositor you want to remove");
            int AFM=in.nextInt();
            tree.remove(AFM);
        }
        if(choice==7){//Get mean savings of the depositors
            tree.getMeanSavings();
        }
        if(choice==8){//Print the top 'x' depositors that most likely tax envasion
            System.out.println("What's your 'x'");
            int k=in.nextInt();
            tree.printTopLargeDepositors(k);
        }if(choice==9){//Taxed Income of Depositor
            System.out.println("Printing all Depositors  ");
            tree.printByAFM();
        }
    }
    }
   public LargeDepositor item;
    TreeNode left; // left kid
    TreeNode right; // right kid
    int N; //number of nodes in the subtree 
    int size;
     TreeNode(LargeDepositor item,TreeNode left,TreeNode right,int N){
        this.item=item;
        this.left=left;
        this.right=right;
        this.N=N;
    }
    TreeNode(LargeDepositor item){
        this.item=item;
    }

   

    public void setItem(LargeDepositor item) {
        this.item = item;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right){
        this.right=right;
    }

    public void setN(int N){
        this.N=N;
    }

    public LargeDepositor getItem(){
        return this.item;
    }
    public TreeNode getLeft(){
        return this.left;
    }
    public TreeNode getRight(){
        return this.right;
    }
    public int getN(){
        return this.N;
    }
    public int getSize(){
        return size;
    }
    public String toString(){
        return this.item+" "+this.left+" "+this.right+" "+this.N;
    }


}
private int key(TreeNode node){
    return node.item.key();
}
private TreeNode root; //riza toy dentrou
@Override

    public void insert(LargeDepositor item) {//insert 
       root=insertAsRoot(item,root);
      
    }
     TreeNode insertAsRoot(LargeDepositor item, TreeNode root){
    if (root==null){
         root= new TreeNode(item);
         return root ;
     }
     if (item.getAFM() == root.getItem().getAFM()) {//se periptosi pou iparxi idi to afm
        System.out.println("Error, this afm already eists");
       
        return root;
    }
     if(Math.random() * root.getN()+1 <1.0){//randomly to vazei ton komvo
       if(item.getAFM()<root.getItem().getAFM()){
        return rotateToRoot(item, root);
       }
     }else{
     if((item.getAFM()<root.getItem().getAFM())){
        root.setLeft(insertAsRoot(item,root.getLeft()));
     }else{
        root.setRight(insertAsRoot(item,root.getRight()));
     }
    root.setN(root.getN()+1);
   
    }


    return root;
    }
    private TreeNode rotateToRoot(LargeDepositor item, TreeNode h) {
        if (item.getAFM() < h.getItem().getAFM()) {
            // aristeri peristrofi
            h.setLeft(rotateToRoot(item, h.getLeft()));
            // deksia peristrofi giro apo afton ton komvo
            return rotateRight(h);
        } else {
            // deksia peristrofi 
            h.setRight(rotateToRoot(item, h.getRight()));
            //Aristeri peristrofi giro apo afton ton komvo
            return rotateLeft(h);
        }
    }

   
    private TreeNode rotateLeft(TreeNode c2) { // Methodo aristeris peristrofis
        TreeNode c1 = c2.getRight();
        c2.setRight(c1.getLeft());
        c1.setLeft(c2);
        c1.setN(c2.getN());
        c2.setN(1 + size(c2.getLeft()) + size(c2.getRight()));
        return c1;
    }

    
    private TreeNode rotateRight(TreeNode c1) {//Methodos deksias peristrofis
        TreeNode c2 = c1.getLeft();
        c1.setLeft(c2.getRight());
        c2.setRight(c1);
        c2.setN(c1.getN());
        c1.setN(1 + size(c1.getLeft()) + size(c1.getRight()));
        return c2;
    }

    
    private int size(TreeNode n) {//Size
        return n.getN();
    }

    

@Override
public void load(String filename){//fortonei apo to arxeio ta dedomena
    try{
    BufferedReader reader= new BufferedReader(new FileReader(filename));
   String line;
  
   while((line=reader.readLine()) != null){
    String[] data= line.split("\\s+");
    int AFM=Integer.parseInt(data[0]);
    double SAVINGS=Double.parseDouble(data[3]);
    double TAXEDINCOME=Double.parseDouble(data[4]);
    LargeDepositor h=new LargeDepositor(AFM,data[1],data[2],SAVINGS,TAXEDINCOME);
    insert(h);

}
reader.close();
    }
   
catch(IOException e){
    e.printStackTrace();
}}
@Override
public void updateSavings(int AFM, double savings){
    updatesavingsLoop(root, AFM).setSavings(savings);
    System.out.println("The savings of your Depositor has been updated :) ");//successful update

}

private LargeDepositor updatesavingsLoop(TreeNode root, int AFM) {
    if (root == null) {
        System.out.println("There are no Depositors yet.");
        return null;
    }

    if (root.getItem().getAFM() == AFM) {
     return  root.getItem();
        
    } else if (AFM < root.getItem().getAFM()) {
        return updatesavingsLoop(root.getLeft(), AFM);
    } else {
        return updatesavingsLoop(root.getRight(), AFM);
    }
}


@Override
public LargeDepositor searchByAFM(int AFM) {//anazitisi vasi afm
    return searchByAFMLoop(root, AFM);
}

private LargeDepositor searchByAFMLoop(TreeNode root, int AFM) {
    if (root == null) {//adio dentro
        System.out.println("There are no Depositors yet.");
        return null;
    }

    if (root.getItem().getAFM() == AFM) {//vrethike to afm poy psaxno 
        System.out.println("The Depositor's data you're looking found are :\n"+"First Name of Depositor : " + root.getItem().getFirstName()+
         "Last Name of Depositor: " + root.getItem().getLastName()+"Savings of Depositor: " + root.getItem().getSavings()+"Taxed Income of Depositor: " +
          root.getItem().getTaxedIncome());
        return root.getItem();
    } else if (AFM < root.getItem().getAFM()) {
        return searchByAFMLoop(root.getLeft(), AFM);
    } else {
        return searchByAFMLoop(root.getRight(), AFM);
    }
}

private static class List {
    LargeDepositor data;
    List next;

    public List(LargeDepositor data) {
        this.data = data;
        this.next = null;
    }
}



public List searchByLastName(String last_name) {// we tried to make it work :( not enough time 
    List result = null;  // 
    int count = 0;  //metritis gia aftoys poy exoyn idio last name

    searchByLastNameLoop(root, last_name, result, count);

    if (result == null) {
        System.out.println("No Depositors found with last name: " + last_name);//den vrike me idio epitheto
    } else {
        System.out.println("Depositors found with last name: ");//entiponei ta stoixia me aftoys me to idio epitheno
        printDepositors(result);
    }

    return result;
}

private void searchByLastNameLoop(TreeNode root, String last_name, List result, int count) {
    if (root == null) {//adio dentro 
        return;
    }

    searchByLastNameLoop(root.getLeft(), last_name, result, count);

    if (root.getItem().getLastName().equals(last_name)) {
        if (count < 5) {
            List newNode = new List(root.getItem());
            newNode.next = result;
            result = newNode;
        } else {
            System.out.println("More than 5 depositors found. Stopping further adding.");//pano apo 5 me idio epitheto
        }
        count++;
    }

    searchByLastNameLoop(root.getRight(), last_name, result, count);
}

private void printDepositors(List result) {//ektiponei  tous depositors
    while (result != null) {
        System.out.println("Depositor: " + result.data);
        result = result.next;
    }
}





private Comparator comparator;

@Override
public void remove(int AFM){//remove depositor apo to dentro 
    TreeNode current=root;
    TreeNode parent=null;

    while(true){
        if(current==null)
        return;
        if(current.getItem().getAFM()==AFM){
            break;
        }
    parent=current;
    if(comparator.compare(current.getItem(),AFM)<0){
        current = current.getRight();
    }else{
        current= current.getLeft();
    }
    TreeNode replace = null;

    if(current.getLeft()==null){
        replace=current.getRight();
    }else if(current.getRight()==null){
        replace=current.getLeft();
    }else{
        TreeNode findCurrent = current.getRight();
        while (true){
            if(findCurrent.getLeft() !=null){
                findCurrent = findCurrent.getLeft();
            }else{
                break;
            }
        remove(findCurrent.getItem().getAFM());
        
        findCurrent.setLeft(current.getLeft());
        findCurrent.setRight(current.getRight());
        replace = findCurrent;

        }
        if(parent== null){
            root=replace;
        }else{
            if(parent.getLeft()==current){
                parent.setLeft(replace);
            }
            if(parent.getRight()==current){
                parent.setRight(replace);
            }
        }
    }
    }
}
@Override
public double getMeanSavings(){//mesos oros ton savings apo toys katathetes
     
    double sumSavings = 0;
    int N = 0;

 
    inOrderTraversal(root, sumSavings, N);

    if (N > 0) {//gia apofigi dieresis me to 0 
        return (sumSavings / N);
    } else {
        return -1; 
    }
}


private void inOrderTraversal(TreeNode root, double sumSavings, int N) {
    if (root != null) {
      
        inOrderTraversal(root.getLeft(), sumSavings, N);

       
        sumSavings += root.getItem().getSavings();

       
        N++;

        
        inOrderTraversal(root.getRight(), sumSavings, N);
    }
}
@Override
public void printTopLargeDepositors(int k) {//oi top k depositors poy logika forodiafevgoun 
    
    if (root == null) {
        System.out.println("There are no depositors yet.");//no depositors genika
        return;
    }

    
    LargeDepositor[] topDepositors = new LargeDepositor[k];

    initTopDepositors(root, topDepositors, 0, k);

   
    if (topDepositors[k - 1] != null) {
     
        for (LargeDepositor depositor : topDepositors) {
            if (depositor != null) {
                System.out.println(depositor);
            }
        }
    } else {
        System.out.println("There are fewer than " + k + " depositors.");//exei ligoterous apo k depositors olo to dentro 
    }
}


private void initTopDepositors(TreeNode node, LargeDepositor[] topDepositors, int index, int k) {
    if (node != null) {
      
        initTopDepositors(node.getRight(), topDepositors, index, k);

       
        if (index < k) {
            topDepositors[index] = node.getItem();
            index++;
        }

        initTopDepositors(node.getLeft(), topDepositors, index, k);
    }
}

@Override
public void printByAFM(){//ektiposi kata afkisi vasi afk 
    inOrderTraversal(root);
    }

    private void inOrderTraversal(TreeNode root) {
        if (root != null) {
           
            inOrderTraversal(root.getLeft());

            System.out.println(root.getItem().toString());

            inOrderTraversal(root.getRight());
        }
    }
    

}



