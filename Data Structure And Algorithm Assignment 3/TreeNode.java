class TreeNode {
    LargeDepositor item;
    TreeNode left; // pointer to left subtree
    TreeNode right; // pointer to right subtree
    int N; //number of nodes in the subtree rooted at this TreeNode
    TreeNode(LargeDepositor item,TreeNode left,TreeNode right,int N){
        this.item=item;
        this.left=left;
        this.right=right;
        this.N=N;
    }
    TreeNode(LargeDepositor item){
        this.item=item;
        this.N=1;
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
    public String toString(){
        return this.item+" "+this.left+" "+this.right+" "+this.N;
    }


}
    
