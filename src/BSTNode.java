/**
 * Project 2
 */

/**
 * 
 *
 * @author {Stephen Ye}
 * @version {09/25/2023}
 */

// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

public class BSTNode {

    private String key;
    private Seminar seminar;
    private BSTNode left;
    private BSTNode right;

    /**
     * Constructs a new BSTNode with a given key and seminar.
     * 
     * @param key
     *            The key of the node.
     * @param sem
     *            The seminar object to be stored in the node.
     */
    public BSTNode(String key, Seminar sem) {
        this.key = key;
        this.seminar = sem;
        this.left = null;
        this.right = null;
    }


    /**
     * Retrieves the key of this record.
     * 
     * @return The key of the record.
     */
    public String getKey() {
        return key;
    }


    /**
     * Sets the key of this node to the given key.
     * 
     * @param k
     *            The new key for this node.
     */
    public void setKey(String k) {
        this.key = k;
    }


    /**
     * Retrieves the Seminar object associated with this record.
     * 
     * @return The Seminar object of the record.
     */
    public Seminar getSeminar() {
        return seminar;
    }


    /**
     * Sets the Seminar object of this node to the given seminar.
     * 
     * @param sem
     *            The new Seminar object for this node.
     */
    public void setSeminar(Seminar sem) {
        this.seminar = sem;
    }


    /**
     * Retrieves the left child of this node.
     * 
     * @return The left child of the node, or null if it doesn't have one.
     */
    public BSTNode getLeft() {
        return left;
    }


    /**
     * Sets the left child of this node to the given BSTNode.
     * 
     * @param left
     *            The new left child for this node.
     */
    public void setLeft(BSTNode left) {
        this.left = left;
    }


    /**
     * Retrieves the right child of this node.
     * 
     * @return The right child of the node, or null if it doesn't have one.
     */
    public BSTNode getRight() {
        return right;
    }


    /**
     * Sets the right child of this node to the given BSTNode.
     * 
     * @param right
     *            The new right child for this node.
     */
    public void setRight(BSTNode right) {
        this.right = right;
    }

}
