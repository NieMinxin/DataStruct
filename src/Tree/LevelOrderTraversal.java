package Tree;

public class LevelOrderTraversal {
    //层序遍历
    private class Node{
        int data;
        Node left,right;
        public Node(int data){
            this.data =data;
            left=right=null;
        }
    }

    private  Node root;

    LevelOrderTraversal(){
        root = null;
    }

    public int  height(Node root){
        if(root==null){
            return  0;
        }
        else {
            return Math.max(height(root.left),height(root.right))+1;
        }
    }

    public void printLevel(){
        int height = height(root);
        int i;
        for(i=0;i<height;i++){
            printGivenLevel(root,i);
        }
    }

    public void printGivenLevel(Node root, int level){
        if(root ==null){
            return;
        }
        if(level==1){
            System.out.println(root.data);
        }else if(level>1) {
            printGivenLevel(root.left,level-1);
            printGivenLevel(root.right,level-1);
        }
    }
}
