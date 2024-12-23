/**
 * Project 2
 */

/**
 * Represents a binary tree to handle operations related to seminars.
 * This binary tree supports insertion, deletion, search, and printing of
 * seminars.
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
public class BinTree {
    private int worldSize;
    private BinNode root;
    private int nodeNum;
    private BinNode flyweight;

    /**
     * Constructor for BinTree class.
     *
     * @param worldSize
     *            Size of the world for the binary tree
     */
    public BinTree(int worldSize) {
        this.worldSize = worldSize;
        flyweight = new BinNodeLeaf();
        this.root = flyweight;
        nodeNum = 0;
    }


    /**
     * Returns the size of the world.
     *
     * @return worldSize Size of the world
     */
    public int getWorldSize() {
        return worldSize;
    }


    /**
     * Returns the number of nodes in the binary tree.
     *
     * @return nodeNum Total number of nodes in the tree
     */
    public int getnodeNum() {
        return nodeNum;
    }


    /**
     * Returns the root node of the binary tree.
     *
     * @return root The root node of the tree
     */
    public BinNode getRoot() {
        return root;
    }


    /**
     * Checks if the binary tree is empty.
     *
     * @return boolean True if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return nodeNum == 0;
    }


    /**
     * Inserts a seminar into the binary tree.
     *
     * @param seminar
     *            The seminar object to be inserted
     */
    public void insert(Seminar seminar) {
        root = root.insert(seminar, 0, 0, worldSize, worldSize, 0, flyweight);
        nodeNum++;
    }


    /**
     * Deletes a seminar from the binary tree based on given x, y coordinates
     * and id.
     *
     * @param x
     *            x-coordinate
     * @param y
     *            y-coordinate
     * @param id
     *            Identifier for the seminar to be deleted
     */
    public void delete(int x, int y, int id) {
        root = root.delete(x, y, id, 0, 0, worldSize, worldSize, 0, flyweight);
    }


    /**
     * Searches for seminars within a given radius from specified x, y
     * coordinates.
     *
     * @param x
     *            x-coordinate
     * @param y
     *            y-coordinate
     * @param radius
     *            The search radius
     */
    public void search(int x, int y, double radius) {
        System.out.println("Seminars within " + radius + " units of " + x + ", "
            + y + ":");
        int nodesVisited = root.search(x, y, radius, 0, 0, worldSize, worldSize,
            0, flyweight);
        System.out.println(nodesVisited + " nodes visited in this search");
    }


    /**
     * Prints the structure and content of the binary tree.
     */
    public void printTree() {
        if (root == flyweight) {
            System.out.println("E");
        }
        else {
            String locTree = root.print(0);
            int lastNewLineIndex = locTree.lastIndexOf("\n");
            if (lastNewLineIndex == -1) {
            }
            System.out.println(locTree.substring(0, lastNewLineIndex));
        }

    }

}
