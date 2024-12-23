/**
 * Project 2
 * 
 */

/**
 * The BinNode interface provides a set of methods to define the
 * behavior of binary nodes that can insert, delete, search seminars,
 * and print their details.
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
interface BinNode {

    /**
     * Inserts a seminar into the binary node structure.
     *
     * @param sem
     *            The seminar to be inserted.
     * @param x
     *            The x-coordinate of the seminar.
     * @param y
     *            The y-coordinate of the seminar.
     * @param xSize
     *            The width of the coordinate space.
     * @param ySize
     *            The height of the coordinate space.
     * @param depth
     *            The depth of the node in the tree.
     * @param flyweight
     *            A shared node instance to optimize memory usage.
     * @return The updated binary node after insertion.
     */
    BinNode insert(
        Seminar sem,
        int x,
        int y,
        int xSize,
        int ySize,
        int depth,
        BinNode flyweight);


    /**
     * Deletes a seminar from the binary node structure.
     *
     * @param x
     *            The x-coordinate of the seminar.
     * @param y
     *            The y-coordinate of the seminar.
     * @param id
     *            The ID of the seminar to be deleted.
     * @param xSearcher
     *            The starting x-coordinate for searching.
     * @param ySearcher
     *            The starting y-coordinate for searching.
     * @param xSize
     *            The width of the coordinate space.
     * @param ySize
     *            The height of the coordinate space.
     * @param depth
     *            The depth of the node in the tree.
     * @param flyweight
     *            A shared node instance to optimize memory usage.
     * @return The updated binary node after deletion.
     */
    BinNode delete(
        int x,
        int y,
        int id,
        int xSearcher,
        int ySearcher,
        int xSize,
        int ySize,
        int depth,
        BinNode flyweight);


    /**
     * Searches for seminars within a given radius from a specific coordinate.
     *
     * @param x
     *            The x-coordinate from which to search.
     * @param y
     *            The y-coordinate from which to search.
     * @param radius
     *            The radius within which seminars should be found.
     * @param xSearcher
     *            The starting x-coordinate for searching.
     * @param ySearcher
     *            The starting y-coordinate for searching.
     * @param xSize
     *            The width of the coordinate space.
     * @param ySize
     *            The height of the coordinate space.
     * @param depth
     *            The depth of the node in the tree.
     * @param flyweight
     *            A shared node instance to optimize memory usage.
     * @return The number of nodes visited during the search.
     */
    int search(
        int x,
        int y,
        double radius,
        int xSearcher,
        int ySearcher,
        int xSize,
        int ySize,
        int depth,
        BinNode flyweight);


    /**
     * Prints the details of the node.
     *
     * @param depth
     *            The depth of the node in the tree.
     * @return A string representation of the node's details.
     */
    String print(int depth);


    /**
     * Retrieves the list of seminars held by the node.
     *
     * @return The SeminarLinkedList containing seminars in the node.
     */
    SeminarLinkedList getSeminars();
}
