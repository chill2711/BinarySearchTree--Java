
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester
{
    public static void main(String[] args){
    BinarySearchTree tree = new BinarySearchTree();
    tree.insert(10);
    tree.insert(27);
    tree.insert(52);
    tree.insert(12);
    tree.insert(71);
    
    System.out.print(tree.find(10));
    
    tree.display();
    
}
}
