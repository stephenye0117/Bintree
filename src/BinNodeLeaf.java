import java.util.Iterator;

/**
 * Project 2
 * 
 */

/**
 * Represents a leaf node in the binary tree, specifically for storing seminars.
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
public class BinNodeLeaf implements BinNode {
    private SeminarLinkedList seminars;
    private boolean isEmpty;

    /**
     * Default constructor, initializes an flyweight node.
     */
    public BinNodeLeaf() {
        this.seminars = new SeminarLinkedList();
        isEmpty = true;
    }


    /**
     * Constructor that initializes the leaf node with a single seminar.
     * 
     * @param seminar
     *            The seminar to store.
     */
    public BinNodeLeaf(Seminar seminar) {
        this.seminars = new SeminarLinkedList();
        this.seminars.add(seminar);
        isEmpty = false;
    }


    /**
     * Gets the seminars stored in this leaf node.
     * 
     * @return The seminars.
     */
    public SeminarLinkedList getSeminars() {
        return seminars;
    }


    /**
     * Inserts a seminar into the node. If this node is already occupied with
     * another seminar in a different position, it promotes this node to an
     * internal node.
     *
     * @param sem
     *            The seminar to insert.
     * @param x
     *            X-coordinate of the region.
     * @param y
     *            Y-coordinate of the region.
     * @param xSize
     *            Width of the region.
     * @param ySize
     *            Height of the region.
     * @param depth
     *            Depth of the node.
     * @param flyweight
     *            The flyweight node.
     * @return Returns the node after insertion (could be this node or a new
     *         internal node).
     */
    public BinNode insert(
        Seminar sem,
        int x,
        int y,
        int xSize,
        int ySize,
        int depth,
        BinNode flyweight) {

        if (seminars.isEmpty()) {
            return new BinNodeLeaf(sem);
        }
        if (sem.x() == seminars.get(0).x() && sem.y() == seminars.get(0).y()) {
            seminars.add(sem);
        }
        else if (xSize == 1 && ySize == 1) {
            return this;
        }
        else {
            BinNodeInternal internal = new BinNodeInternal(flyweight,
                flyweight);
            for (int i = 0; i < seminars.size(); i++) {
                internal.insert(seminars.get(i), x, y, xSize, ySize, depth,
                    flyweight);
            }
            return internal.insert(sem, x, y, xSize, ySize, depth, flyweight);
        }
        return this;
    }


    /**
     * Deletes a seminar with specific x and y coordinates and ID from the node.
     *
     * @param x
     *            X-coordinate of the seminar.
     * @param y
     *            Y-coordinate of the seminar.
     * @param id
     *            ID of the seminar.
     * @param xSearcher
     *            The starting x-coordinate of the search region.
     * @param ySearcher
     *            The starting y-coordinate of the search region.
     * @param xSize
     *            Width of the search region.
     * @param ySize
     *            Height of the search region.
     * @param depth
     *            Depth of the node.
     * @param flyweight
     *            The flyweight node.
     * @return Returns the node after deletion.
     */
    public BinNode delete(
        int x,
        int y,
        int id,
        int xSearcher,
        int ySearcher,
        int xSize,
        int ySize,
        int depth,
        BinNode flyweight) {

// // If the coordinates are not within the bounds, return the current node as
// is
// if (!inBounds(x, y, xSearcher, ySearcher, xSize, ySize)) {
// return this;
// }
//
// // Search and remove the matching seminar
// Iterator<Seminar> iterator = seminars.iterator();
// while (iterator.hasNext()) {
// Seminar seminar = iterator.next();
// if (seminar.id() == id && seminar.x() == x && seminar.y() == y) {
// break;
// }
// }
//
// // If the seminars list is now empty, return the flyweight node to replace
// the current node
// if (seminars.isEmpty()) {
// return flyweight;
// }

        return this;
    }

// private boolean inBounds(int x, int y, int xSearcher, int ySearcher, int
// xSize, int ySize) {
// return x >= xSearcher && x < xSearcher + xSize && y >= ySearcher && y <
// ySearcher + ySize;
// }


    /**
     * Generates a string representation of this node.
     * 
     * @param depth
     *            The depth of this node in the tree, used for indentation.
     * @return The string representation.
     */
    public String print(int depth) {
        String sol = "";
        for (int i = 0; i < depth * 2; i++) {
            sol += " ";
        }

        if (isEmpty) {
            sol += "E\n";
            return sol;
        }

        sol += "Leaf with " + seminars.size() + " objects:";

        for (int i = 0; i < seminars.size(); i++) {
            sol += " " + seminars.get(i).id();
        }

        sol += "\n";
        return sol;
    }


    /**
     * Searches for seminars within a certain radius of a given point (x,y).
     *
     * @param x
     *            X-coordinate of the point.
     * @param y
     *            Y-coordinate of the point.
     * @param radius
     *            The search radius.
     * @param xSearcher
     *            The starting x-coordinate of the search region.
     * @param ySearcher
     *            The starting y-coordinate of the search region.
     * @param xSize
     *            Width of the search region.
     * @param ySize
     *            Height of the search region.
     * @param depth
     *            Depth of the node.
     * @param flyweight
     *            The flyweight node.
     * @return The number of nodes visited during the search.
     */
    public int search(
        int x,
        int y,
        double radius,
        int xSearcher,
        int ySearcher,
        int xSize,
        int ySize,
        int depth,
        BinNode flyweight) {

        int count = 1; // This node is visited
//
// for (Seminar seminar : this.seminars) {
// int deltaX = seminar.x() - x;
// int deltaY = seminar.y() - y;
// if (Math.sqrt(deltaX * deltaX + deltaY * deltaY) <= radius) {
// System.out.println("Found a record with key value " + seminar
// .id() + " at " + seminar.x() + ", " + seminar.y());
// }
// }

        return count;
    }

}
