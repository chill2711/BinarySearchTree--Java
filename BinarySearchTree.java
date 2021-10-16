
/**
 * Write a description of class BinarySearchTree here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BinarySearchTree
{
    class Node{
        private int data;
        private Node left;
        private Node right;

        public Node(int Newdata) {
           this.data = Newdata;
           left = null;
           right = null;
           
        }
    }   
    private  Node root;
    int count;
    

    public BinarySearchTree(){
        root = null; 
    }
    
    public void insert ( int id){
        root = insertAux (root,id);
        count++;
    }

    private Node insertAux (Node current, int id){
        if (current == null){
            return new Node (id);
        }
        if(id < current.data){
            current.left = insertAux(current.left,id);
        }
        else if (id > current.data){
            current.right = insertAux(current.right,id);
        }
        else{
            return current;
        }
        return current;
    }
    
    public boolean findAux (Node root, int id){
        if (root == null){
            System.out.println("Tree is empty");
            return false;
        }
        if(root.data == id){
            return true;
        }
        if(id < root.data){
            findAux(root.left,id);
        }
        else if(id > root.data){
            findAux(root.right,id);
        }
        return false;
    }
    
    public boolean find(int id){
        return findAux(root,id);
    }
        
    
    public void inOrderTraversal (){
        display1(root);
      }
      
    private void display1(Node root){
        //System.out.print(root.data);
        if(root!=null) {
           display1(root.left);
           System.out.print(" " + root.data);
           display1(root.right);
        }   
    }
    
    public void preOrderTraversal (){
        display2(root);
    }
      
    private void display2(Node root){
        //System.out.print(root.data);
        if(root!=null) {
           System.out.print(" " + root.data);
           display2(root.left);
           display2(root.right);
        }   
    }
    
    public void postOrderTraversal (){
        display3(root);
    }
      
    private void display3(Node root){
        //System.out.print(root.data);
        if(root!=null) {
           display3(root.left);
           display3(root.right);
           System.out.print(" " + root.data);
        }   
    }
    
    public Node getSuccessor(Node deleteNode){
       Node successor = null; 
       Node sucessorParent = null;
       Node current = deleteNode.right;
       while(current != null) {
          sucessorParent = successor; 
          successor = current;
          current = current.left;
       }  
       if(successor != deleteNode.right) {
         sucessorParent.left = successor.right;
         successor.right = deleteNode.right;
       } 
       return successor;
    }    
    
    public boolean delete(int id){
        Node parent = root;
        Node current = root;
        boolean isLeftChild = false;
        
        while(current.data!=id){
            parent = current;
            if(current.data>id){
                isLeftChild = true;
                current = current.left;
            }else{
                isLeftChild = false;
                current = current.right;
            }
            if(current ==null){
                return false;
            }
        }
        //if i am here that means we have found the node
        //Case 1: if node to be deleted has no children
        if(current.left==null && current.right==null){
            if(current==root){
                root = null;
            }
            if(isLeftChild ==true){
                parent.left = null;
            }else{
                parent.right = null;
            }
        }
        //Case 2 : if node to be deleted has only one child
        else if(current.right==null){
            if(current==root){
                root = current.left;
            }else if(isLeftChild){
                parent.left = current.left;
            }else{
                parent.right = current.left;
            }
        }
        else if(current.left==null){
            if(current==root){
                root = current.right;
            }else if(isLeftChild){
                parent.left = current.right;
            }else{
                parent.right = current.right;
            }
        }else if(current.left!=null && current.right!=null){

            //now we have found the minimum element in the right sub tree
            Node successor   = getSuccessor(current);
            if(current==root){
                root = successor;
            }else if(isLeftChild){
                parent.left = successor;
            }else{
                parent.right = successor;
            }           
            successor.left = current.left;
        }       
        return true;        
    }
    
    /*
    public void inorder (Node root){
        if(root != null) {
            
        }
    }
    */
    
    
    
    public static void main(String args[]){
        //Node root =null;
        BinarySearchTree tree = new BinarySearchTree();
        
        System.out.print("hi");
        //tree.insert(10);
       // System.out.print("hi");
        //tree.insert(3);
        //tree.insert(1);
        //tree.inorder(root);
        tree.preOrderTraversal();
    }
}

