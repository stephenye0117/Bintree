import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import student.TestCase;

/**
 * Project 2
 * 
 */

/**
 * Test class for BinNodeLeaf.
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
public class BinNodeLeafTest extends TestCase {

    private BinNodeLeaf leaf;
    private BinNodeLeaf fw;
    private Seminar seminar1;
    private Seminar seminar2;
    private Seminar seminar3;

    /**
     * Set up before every test case.
     * This method initializes some objects that will be used in the tests.
     */
    public void setUp() {
        seminar1 = new Seminar(1, "Seminar1", "01-01-2023", 1, (short)10,
            (short)10, 100, new String[] { "key1" }, "Description1");
        seminar2 = new Seminar(2, "Seminar2", "02-01-2023", 2, (short)20,
            (short)20, 200, new String[] { "key2" }, "Description2");
        seminar3 = new Seminar(3, "Seminar3", "03-01-2023", 3, (short)30,
            (short)30, 300, new String[] { "key3" }, "Description3");
        leaf = new BinNodeLeaf(seminar1);
        fw = new BinNodeLeaf();
    }


    /**
     * Test the initialization state of BinNodeLeaf objects.
     */
    public void testStartUp() {
        assertEquals("E\n", fw.print(0));

        String expectedOutput = "Leaf with 1 objects: 1\n";
        assertEquals(expectedOutput, leaf.print(0));
    }


    /**
     * Test the insert functionality and printing results after insertion.
     */
    public void testInsertAndPrint() {
        leaf.insert(seminar2, 10, 10, 1, 1, 0, fw);
        String expectedOutput = "Leaf with 1 objects: 1\n";
        assertEquals(expectedOutput, leaf.print(0));

        BinNodeLeaf largeLeaf = new BinNodeLeaf(seminar1);
        BinNode result = largeLeaf.insert(seminar2, 0, 0, 100, 100, 0, fw);
        assertNotEquals(largeLeaf, result);

        fw.insert(seminar3, 30, 30, 1, 1, 0, leaf);
        String expectedOutput2 = "Leaf with 1 objects: 1\n";
        assertEquals(expectedOutput2, leaf.print(0));

        Seminar seminar = new Seminar(4, "TestSeminar", "04-01-2023", 1,
            (short)10, (short)10, 100, new String[] { "keyTest" },
            "TestDescription");
        BinNode resultNode = leaf.insert(seminar, 10, 10, 1, 1, 0, fw);
        assertEquals(leaf, resultNode);

    }


    /**
     * Test the print functionality with depth-based indentation.
     */
    public void testPrintIndentationBasedOnDepth() {
        // Insert a seminar to ensure leaf isn't empty.
        Seminar seminar = new Seminar(4, "TestSeminar", "04-01-2023", 1,
            (short)10, (short)10, 100, new String[] { "keyTest" },
            "TestDescription");
        leaf.insert(seminar, 10, 10, 2, 2, 0, fw);

        // Test print for various depths:
        int depth = 0;
        String expectedOutput = "Leaf with 2 objects: 4 1\n";
        assertEquals(expectedOutput, leaf.print(depth));

        depth = 1;
        expectedOutput = "  Leaf with 2 objects: 4 1\n"; // Two spaces added for
                                                         // depth 1
        assertEquals(expectedOutput, leaf.print(depth));

    }


    /**
     * Test inserting multiple seminars at the same location.
     */
    public void testInsertAtSameLocation() {
        Seminar seminar = new Seminar(4, "TestSeminar", "04-01-2023", 1,
            (short)10, (short)10, 100, new String[] { "keyTest" },
            "TestDescription");
        leaf.insert(seminar, 10, 10, 2, 2, 0, fw);
        String expectedOutput = "Leaf with 2 objects: 4 1\n";
        assertEquals(expectedOutput, leaf.print(0));

        Seminar seminarDuplicate = new Seminar(5, "DupSeminar", "05-01-2023", 1,
            (short)10, (short)10, 100, new String[] { "keyDup" },
            "DupDescription");
        leaf.insert(seminarDuplicate, 10, 10, 2, 2, 0, fw);
        expectedOutput = "Leaf with 3 objects: 5 4 1\n";
        assertEquals(expectedOutput, leaf.print(0));

        seminarDuplicate = new Seminar(5, "DupSeminar", "05-01-2023", 1,
            (short)10, (short)11, 100, new String[] { "keyDup" },
            "DupDescription");
        leaf.insert(seminarDuplicate, 10, 10, 2, 2, 0, fw);
        expectedOutput = "Leaf with 3 objects: 5 4 1\n";
        assertEquals(expectedOutput, leaf.print(0));

        seminarDuplicate = new Seminar(5, "DupSeminar", "05-01-2023", 1,
            (short)11, (short)10, 100, new String[] { "keyDup" },
            "DupDescription");
        leaf.insert(seminarDuplicate, 10, 10, 2, 2, 0, fw);
        expectedOutput = "Leaf with 3 objects: 5 4 1\n";
        assertEquals(expectedOutput, leaf.print(0));
    }


    /**
     * Test the case where the node region's
     * dimensions are xSize = 1 and ySize
     * = 1.
     */
    public void testInsertSingleNodeRegion() {
        Seminar seminar = new Seminar(6, "SingleSeminar", "06-01-2023", 1,
            (short)15, (short)15, 100, new String[] { "keySingle" },
            "SingleDescription");

        // Insert a seminar into a region of size 1x1.
        BinNodeLeaf singleLeaf = new BinNodeLeaf(seminar);
        singleLeaf.insert(seminar, 15, 15, 1, 1, 0, fw);
        String expectedOutput = "Leaf with 2 objects: 6 6\n";
        assertEquals(expectedOutput, singleLeaf.print(0));

        Seminar seminarDifferent = new Seminar(7, "DiffSeminar", "07-01-2023",
            1, (short)16, (short)16, 100, new String[] { "keyDiff" },
            "DiffDescription");
        singleLeaf.insert(seminarDifferent, 16, 16, 2, 1, 0, fw);
        assertEquals(expectedOutput, singleLeaf.print(0));

        seminarDifferent = new Seminar(7, "DiffSeminar", "07-01-2023", 1,
            (short)16, (short)16, 100, new String[] { "keyDiff" },
            "DiffDescription");
        singleLeaf.insert(seminarDifferent, 16, 16, 1, 2, 0, fw);
        assertEquals(expectedOutput, singleLeaf.print(0));
    }


    /**
     * Test the delete functionality of BinNodeLeaf.
     */
    public void testDelete() {

        fw.delete(10, 10, 1, 0, 0, 32, 32, 0, leaf);
        assertEquals("E\n", fw.print(0));

        leaf.delete(10, 10, 5, 0, 0, 32, 32, 0, fw);
        String expectedOutput = "Leaf with 1 objects: 1\n";
        assertEquals(expectedOutput, leaf.print(0));

        Seminar seminar5 = new Seminar(5, "Seminar5", "01-01-2023", 5,
            (short)10, (short)10, 100, new String[] { "key5" }, "Description5");
        leaf.insert(seminar5, 10, 10, 1, 1, 0, fw);
        leaf.delete(10, 10, 5, 0, 0, 32, 32, 0, fw);
        expectedOutput = "Leaf with 2 objects: 5 1\n";
        assertEquals(expectedOutput, leaf.print(0));
    }


    /**
     * Test the search functionality of BinNodeLeaf.
     */
    public void testSearch() {
        int count = leaf.search(10, 10, 5, 0, 0, 32, 32, 0, fw);
        assertEquals(1, count);

        count = leaf.search(0, 0, 1, 0, 0, 32, 32, 0, fw);
        assertEquals(1, count);

        count = fw.search(10, 10, 5, 0, 0, 32, 32, 0, leaf);
        assertEquals(1, count);
    }


    /**
     * Test the getSeminars functionality to ensure seminars are correctly
     * retrieved.
     */
    public void testGetSeminars() {
        SeminarLinkedList seminarsList = leaf.getSeminars();
        assertEquals(1, seminarsList.size()); // since leaf was initialized with
                                              // one seminar
        assertEquals(seminar1, seminarsList.get(0));
    }

}
