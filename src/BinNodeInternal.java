/**
 * Project 2
 * 
 */

/**
 * Represents an internal node in a binary tree structure. The `BinNodeInternal`
 * class
 * contains two children: left and right. Being an internal node, it does not
 * contain
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
public class BinNodeInternal implements BinNode {
    private BinNode left;
    private BinNode right;

    /**
     * Constructs a new `BinNodeInternal` with the specified left and right
     * children.
     *
     * @param left
     *            The left child of the internal node.
     * @param right
     *            The right child of the internal node.
     */
    public BinNodeInternal(BinNode left, BinNode right) {
        this.left = left;
        this.right = right;
    }


    /**
     * Returns an empty list of seminars since internal nodes do not store
     * seminars.
     *
     * @return An empty SeminarLinkedList.
     */
    public SeminarLinkedList getSeminars() {
        // This is an internal node, so it doesn't have seminars.
        return new SeminarLinkedList();
    }


    /**
     * Inserts a seminar into the binary tree, directing the insertion operation
     * based on spatial coordinates and the current depth of the node.
     *
     * @param sem
     *            The seminar to be inserted.
     * @param x
     *            X-coordinate of the seminar's location.
     * @param y
     *            Y-coordinate of the seminar's location.
     * @param xSize
     *            Width of the bounding box for insertion.
     * @param ySize
     *            Height of the bounding box for insertion.
     * @param depth
     *            Current depth in the tree.
     * @param flyweight
     *            Shared instance for nodes without seminars.
     * @return The updated binary node after insertion.
     */
    public BinNode insert(
        Seminar sem,
        int x,
        int y,
        int xSize,
        int ySize,
        int depth,
        BinNode flyweight) {

        if (depth % 2 == 0) {
            if (sem.x() < x + (xSize / 2)) {
                left = left.insert(sem, x, y, xSize / 2, ySize, depth + 1,
                    flyweight);
            }
            else {
                right = right.insert(sem, x + (xSize / 2), y, xSize / 2, ySize,
                    depth + 1, flyweight);
            }
        }
        else {
            if (sem.y() < y + (ySize / 2)) {
                left = left.insert(sem, x, y, xSize, ySize / 2, depth + 1,
                    flyweight);
            }
            else {
                right = right.insert(sem, x, y + (ySize / 2), xSize, ySize / 2,
                    depth + 1, flyweight);
            }
        }

        return this;
    }


    /**
     * Deletes a seminar from the binary tree, directing the deletion operation
     * based on spatial coordinates and the current depth of the node.
     *
     * @param x
     *            The x-coordinate of the seminar.
     * @param y
     *            The y-coordinate of the seminar.
     * @param id
     *            The id of the seminar to delete.
     * @param xSearcher
     *            Initial X-coordinate for search boundary.
     * @param ySearcher
     *            Initial Y-coordinate for search boundary.
     * @param xSize
     *            Width of the bounding box for deletion.
     * @param ySize
     *            Height of the bounding box for deletion.
     * @param depth
     *            Current depth in the tree.
     * @param flyweight
     *            Shared instance for nodes without seminars.
     * @return The updated binary node after deletion.
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
// if (inBounds(x, y, xSearcher, ySearcher, xSize, ySize)) {
// if (depth % 2 == 0) { // Splitting vertically
// if (x < xSearcher + xSize / 2) {
// left = left.delete(x, y, id, xSearcher, ySearcher, xSize / 2, ySize / 2,
// depth + 1, flyweight);
// } else {
// right = right.delete(x, y, id, xSearcher + xSize / 2, ySearcher, xSize / 2,
// ySize / 2, depth + 1, flyweight);
// }
// } else { // Splitting horizontally
// if (y < ySearcher + ySize / 2) {
// left = left.delete(x, y, id, xSearcher, ySearcher, xSize / 2, ySize / 2,
// depth + 1, flyweight);
// } else {
// right = right.delete(x, y, id, xSearcher, ySearcher + ySize / 2, xSize / 2,
// ySize / 2, depth + 1, flyweight);
// }
// }
//
// // Check if all children are the flyweight node (empty nodes), and if so,
// collapse this internal node into a leaf node
// if (left == flyweight && right == flyweight) {
// return flyweight;
// }
// }
        return this;
    }

// public boolean inBounds(int x, int y, int xSearcher, int ySearcher, int
// xSize, int ySize) {
// return x >= xSearcher && x < xSearcher + xSize && y >= ySearcher && y <
// ySearcher + ySize;
// }


    /**
     * Provides a string representation of the node for visualization or
     * debugging purposes.
     *
     * @param depth
     *            The depth of the node in the tree.
     * @return A string representation of the node's details.
     */
    public String print(int depth) {
        String sol = "";

        for (int i = 0; i < depth * 2; i++) {
            sol += " ";
        }
        sol += "I\n" + left.print(depth + 1) + right.print(depth + 1);
        return sol;
    }


    /**
     * Searches for seminars within a given radius from a specific coordinate.
     *
     * @param x
     *            The x-coordinate from which to search.
     * @param y
     *            The y-coordinate from which to search.
     * @param radius
     *            The search radius.
     * @param xSearcher
     *            Initial X-coordinate for search boundary.
     * @param ySearcher
     *            Initial Y-coordinate for search boundary.
     * @param xSize
     *            Width of the bounding box for search.
     * @param ySize
     *            Height of the bounding box for search.
     * @param depth
     *            Current depth in the tree.
     * @param flyweight
     *            Shared instance for nodes without seminars.
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

// if (xSearcher - radius <= x + xSize && xSearcher + radius >= x &&
// ySearcher - radius <= y + ySize && ySearcher + radius >= y) {
// count += left.search(x, y, radius, xSearcher, ySearcher, xSize/2, ySize/2,
// depth+1, flyweight);
// count += right.search(x, y, radius, xSearcher, ySearcher, xSize/2, ySize/2,
// depth+1, flyweight);
// }
//
        return count;
    }

}
