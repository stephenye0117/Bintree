import static org.junit.Assert.assertNotEquals;
import org.junit.Test;
import student.TestCase;

/**
 * Project 2
 * 
 */

/**
 * Tests the functionality of the BinTree class.
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
public class BinTreeTest extends TestCase {

    private BinTree binTree;
    private Seminar seminar1;
    private Seminar seminar2;

    /**
     * Sets up the tests by initializing the binTree and two seminar instances.
     */
    public void setUp() {
        binTree = new BinTree(100); // Assuming worldSize is 100 for this test
        seminar1 = new Seminar(1, "Seminar1", "01-01-2023", 1, (short)10,
            (short)10, 100, new String[] { "key1" }, "Description1");
        seminar2 = new Seminar(2, "Seminar2", "02-01-2023", 2, (short)60,
            (short)60, 200, new String[] { "key2" }, "Description2");
    }


    /**
     * Tests the insert functionality of the BinTree.
     * Checks if the seminars are correctly inserted into the tree.
     */
    public void testInsert() {
        assertEquals(100, binTree.getWorldSize());

        binTree.insert(seminar1);
        assertNotNull(binTree.getRoot());
        assertEquals(seminar1, binTree.getRoot().getSeminars()
            .getFirstSeminar());

        binTree.insert(seminar2);
        assertNotEquals(seminar2, binTree.getRoot().getSeminars()
            .getFirstSeminar());
    }


    /**
     * Tests the isEmpty functionality.
     * Checks if the tree correctly reports its state of being empty or not.
     */
    public void testIsEmpty() {
        assertTrue(binTree.isEmpty());
        binTree.insert(seminar2);
        assertFalse(binTree.isEmpty());
    }


    /**
     * Tests the print functionality of the BinTree.
     * Checks if the tree is printed correctly in its different states.
     */
    public void testPrintTree() {
        binTree.printTree();
        assertEquals(systemOut().getHistory(), "E\n");
        binTree.insert(seminar1);
        binTree.printTree();
        assertTrue(systemOut().getHistory().contains("Leaf with 1 objects: 1"));
    }


    /**
     * Tests the delete functionality of the BinTree.
     * Checks if the seminars are correctly deleted from the tree.
     */
    public void testDelete() {
        binTree.insert(seminar1);
        binTree.insert(seminar2);
        assertNotNull(binTree.getRoot());
        assertEquals(2, binTree.getnodeNum());

        binTree.delete(10, 10, 1);
        assertNotEquals(seminar1, binTree.getRoot().getSeminars()
            .getFirstSeminar());
        assertEquals(2, binTree.getnodeNum());

    }


    /**
     * Tests the search functionality of the BinTree.
     * Checks if the tree correctly finds seminars within a radius.
     */
    public void testSearch() {
        binTree.insert(seminar1);
        binTree.insert(seminar2);
        binTree.search(10, 10, 20);
        assertEquals(systemOut().getHistory(),
            "Seminars within 20.0 units of 10, 10:\n"
                + "1 nodes visited in this search\n");

    }

}
