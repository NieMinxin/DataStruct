
package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
    二叉查找树实现完毕


* */
public class BinaryTree {
    List list = new ArrayList();
    //节点
    private class Node{
        private Node left;//左子树
        private Node right;//右子树
        private Node parent;//双亲
        private int data;//数据内容
        public Node(int value) {
            data = value;
            left = null;
            right = null;
            parent = null;
        }
    }
    //根节点用来初始化
    private Node root;

    //构造器初始化
    BinaryTree(){
        this.root = null;
    }
    //返回根节点
    public Node getRoot(){
        return root;
    }
    //查找结点
    public  Node find(int  key){
        Node current = root;
        while(current!=null){
            //如果data<父节点就为左子节点
            if(key<current.data){
                if(current.left==null){//左子为空
                    return current;
                }
                current=current.left;
            }else if(key>current.data){
                if(current.right==null){
                    return current;
                }
                current=current.right;
            }else {
                return current;//找到节点后返回
            }
        }
        return null;
    }
    //插入，二叉树具有排序效果
    public void put(int value){
        Node newNode = new Node(value);
        if(root==null){
            root = newNode;
        }else {
            Node parent = find(value);//找到父节点
            if(value<parent.data){
                parent.left = newNode;
                parent.left.parent = parent;
                return;
            }else {
                parent.right= newNode;
                parent.right.parent = parent;
                return;
            }

        }

    }

    /*
    删除算法全过程总结：首先通过值找到相应的节点，
    准备删除该节点：共有三种情况，每种情况单独判断是否为根节点，并且还要判断temp自身是左子树还是右子树，从而链接上一个节点。
    1.该节点没有树叶
    2.该节点有一个子树
    3.该节点有两个子树
    * */
    public boolean remove(int value){
        Node temp = find(value);//先找到节点
        if(temp.data!=value){
            return false;
        }
        //节点没有子节点
        if(temp.left==null && temp.right==null){
            if(temp==root){
                root =null;//删除root节点
            }else if(temp.parent.data<temp.data){
                temp.parent.right=null;
            }else {
                temp.parent.left=null;
            }
            return true;
        }
        //两个节点
        else if(temp.left != null && temp.right != null){
            Node successor = findSuccessor(temp);
            //将temp的左子数直接嫁接在temp子孙的左子数
            successor.left = temp.left;
            successor.left.parent=successor;//设置父亲节点

            //如果继承者有合适的子节点，那么子节点的祖父母就为该子节点的新父母
            if(successor.parent!=temp){
                if(successor.right!=null){
                    successor.right.parent=successor.parent;
                    successor.parent.left=successor.right;
                    successor.right=temp.right;
                    successor.right.parent=successor;
                }else {
                    successor.parent.left=null;
                    successor.right=temp.right;
                    successor.right.parent=successor;
                }
            }

            //如果temp为root节点
            if(temp==root){
                successor.parent=null;
                root=successor;
                return true;
            }else {
                //如果不是根节点，判断该temp节点为左节点还是右节点
                successor.parent=temp.parent;//指向temp的父节点，删掉temp。temp的左节点已经为继承者的左子树
                if(temp.parent.data<temp.data){
                    //这种情况为右子树
                    temp.parent.right=successor;
                }else {
                    temp.parent.left = successor;
                }
                return true;
            }


        }
        //如果为一个节点
        else {
            if(temp.right!=null){

                if(temp==root){
                    root=temp.right;
                    return true;
                }
                temp.right.parent=temp.parent;
                //判断temp是左数，还是右数
                if(temp.data<temp.parent.data){
                    temp.parent.left=temp.right;
                }else {
                    temp.parent.right=temp.right;
                }
                return true;
            }
            else {
                //同理
                if (temp == root) {
                    root = temp.left;
                    return true;
                }

                temp.left.parent = temp.parent;

                //Assigns temp to left or right side
                if (temp.data < temp.parent.data)
                    temp.parent.left = temp.left;
                else
                    temp.parent.right = temp.left;
                return true;
            }



        }

    }

   /* //找到子孙节点，如果该节点没有右子节点返回本身
      //如果有有子节点，则寻找右子节点较左的子节点
   * */
    private Node findSuccessor(Node node){
        if(node.right==null){
            return node;
        }
        Node  current = node.right;
        Node  parent = node.right;
        while (current!=null){
            parent = current;
            current= current.left;
        }
        return parent;
    }

    //前序遍历，先打印双亲节点
    public void preOrder(Node root){
        if(root!=null){
            System.out.println(root.data);
            preOrder(root.left);
            preOrder(root.right);
        }
    }
    //中序遍历
    public List inOrder(Node root){

        if(root!=null){
            inOrder(root.left);
            list.add(root.data);
            inOrder(root.right);
        }
        return list;
    }
    //后序遍历
    public void postOrder(Node root){
        if(root!=null){
            preOrder(root.left);
            preOrder(root.right);
            System.out.println(root.data);
        }
    }
    public int  height(Node root){
        if(root==null){
            return  0;
        }
        else {
            return Math.max(height(root.left),height(root.right))+1;
        }
    }
    //层序遍历

    public void printLevel(){
        int height = height(root);
        int i;
        for(i=1;i<=height;i++){
            System.out.println("第"+i+"层:");
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
    //队列层序遍历

    public void printLevelWithQueue(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node temp =queue.poll();
            System.out.println(temp.data);
            if(temp.left!=null){
                queue.add(temp.left);
            }
            if(temp.right!=null){
                queue.add(temp.right);
            }
        }
    }

    public static void main(String[] args) {

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.put(10);
        binaryTree.put(8);
        binaryTree.put(11);
        binaryTree.put(7);
        binaryTree.put(9);
        binaryTree.remove(9);
        System.out.println("前序遍历：=========");
        binaryTree.preOrder(binaryTree.root);
        System.out.println("中序遍历==========");
        System.out.println(binaryTree.inOrder(binaryTree.root));
        System.out.println("后序遍历");
        binaryTree.postOrder(binaryTree.root);

        System.out.println("层序遍历：---------------");
        binaryTree.printLevel();


        System.out.println("队列层序遍历:---------------");
        binaryTree.printLevelWithQueue();
    }

}
