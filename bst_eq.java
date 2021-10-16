/**
 * This class will use BinarySearchTree to organize Earthquake data.
 * 
 * @author Cole Hill 
 * @version April 17, 2021
 */
import java.util.List;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.base.BSTElement;
import bridges.data_src_dependent.EarthquakeUSGS;

public class bst_eq {
    public static void main(String[] args) throws Exception {

        //create the Bridges object
        Bridges bridges = new Bridges(36, "chill", "772935405491");

        // set title, description
        bridges.setTitle("A Binary Search Tree Example with Earthquake Data");
        bridges.setDescription("This example illustrates retrieving USGS earthquake data records and inserted into a binary search tree. Attributes of the quake are displayed at each node.");

        // set max quakes for this example
        final int MaxQuakes = 25;

        // Retrieve a list of MaxQuakes earthquake records 
        // from USGS using BRIDGES
        DataSource ds = bridges.getDataSource();
        List<EarthquakeUSGS> eqlist = ds.getEarthquakeUSGSData(MaxQuakes);

        // create a binary search tree and insert the EQ records into the tree
        BSTElement<Double, EarthquakeUSGS> root = null;
        for (int k = 0; k < MaxQuakes; k++) {
            BSTElement<Double, EarthquakeUSGS> bst_node =
                new BSTElement<Double, EarthquakeUSGS>(eqlist.get(k).getMagnitude(), eqlist.get(k));
            // set label of the node
            bst_node.setLabel(eqlist.get(k).getTitle() + "\n\nLat/Long: ( " +
                eqlist.get(k).getLatit() + "," + eqlist.get(k).getLongit() + " )\n\n" +
                eqlist.get(k).getTime());
            root = insert_R (root, bst_node);
        }

        // set some visual attributes
        // setting root node to red
        root.setColor("red");

        // TODO: Modify the insert function to color all the nodes, except the
        //       root node to "blue"
        // TODO: Write a function to traverse the tree and highlight the 
        // largest quake (color it in a different color and set it to a larger
        // size (using the setSize(sz) size ranges 1-50

        //call findLargest
        findLargest(root);
        //call findSmallest
        findSmallest(root);
        //call highlightRange
        highlightRange(root,2.0,5.0);
        //set visualizer type
        bridges.setDataStructure(root);
        // visualize the tree
        bridges.visualize();
    }
    // recursive insert method to insert nodes into a binary search tree
    public static BSTElement<Double, EarthquakeUSGS> insert_R (BSTElement<Double, EarthquakeUSGS> rt,
    BSTElement<Double, EarthquakeUSGS> new_el) {
        if (rt == null)
            return new_el;
        else if (new_el.getKey() < rt.getKey()){
            rt.setLeft(insert_R(rt.getLeft(), new_el));
            new_el.setColor("blue");
        }
        else{
            rt.setRight(insert_R(rt.getRight(), new_el));
            new_el.setColor("blue");
        }
        return rt;
    }

    //findLargest method
    //traverses the tree (in-order)
    //enlarges and changes color of node with largest magnitude
    public static void findLargest (BSTElement<Double, EarthquakeUSGS> rt) {
        //base case
        if(rt == null){
            return;
        }
        //searching right side of tree
        //if there is a node to the right, it will become the new max
        if (rt.getRight() == null){
            rt.setSize(50);
            rt.setColor("yellow");
        }
        //continues to call until we are at the right most node with max magnitude
        findLargest(rt.getRight());
    }

    //findSmallest method
    //traverses the tree (in-order)
    //decreases size and changes color of node with smallest magnitude
    public static void findSmallest (BSTElement<Double, EarthquakeUSGS> rt) {
        //base case
        if(rt == null){
            return;
        }
        //searching left side of tree
        //if there is a node to the left, it will become the new min
        if(rt.getLeft() == null){
            rt.setSize(5);
            rt.setColor("purple");
        }
        //continues to call until we are at the left most node with min magnitude
        findSmallest(rt.getLeft());

    }

    //highlight range method
    //traverse the tree (in-order)
    //change color and shape of highlighted nodes
    public static void highlightRange(BSTElement<Double, EarthquakeUSGS> rt,double min, double max){
        //base case
        if(rt == null){
            return;
        }
        else{
            //if the magnitude is greater than range search left side of root
            if(rt.getValue().getMagnitude() > max){
                highlightRange(rt.getLeft(),min,max);
            }
            //if magnitude is less than range search right of the root
            else if(rt.getValue().getMagnitude() < min){
                highlightRange(rt.getRight(),min,max);
            }
            //magnitude is in the range, now look to left and right to find more in range
            else{
                rt.setShape("triangle");
                rt.setColor("green");
                highlightRange(rt.getLeft(),min,max);
                highlightRange(rt.getRight(),min,max);
            }

        }
    }
}

