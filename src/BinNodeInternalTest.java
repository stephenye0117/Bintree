import student.TestCase;

/**
 * Project 2
 * 
 * Test cases for the BinNodeInternal class.
 */

/**
 * 
 * Test cases for the BinNodeInternal class.
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
public class BinNodeInternalTest extends TestCase {

    private BinNodeInternal internalNode;
    private BinNode fw;
    private Seminar seminar1;
    private Seminar seminar2;
    private Seminar seminar3;

    /**
     * Set up initial data for each test case.
     */
    public void setUp() {
        fw = new BinNodeLeaf();
        internalNode = new BinNodeInternal(fw, fw);
        seminar1 = new Seminar(1, "Seminar1", "01-01-2023", 1, (short)5,
            (short)5, 100, new String[] { "key1" }, "Description");
        seminar2 = new Seminar(2, "Seminar2", "01-01-2023", 2, (short)15,
            (short)15, 100, new String[] { "key2" }, "Description");
        seminar3 = new Seminar(3, "Seminar3", "01-01-2023", 3, (short)25,
            (short)25, 100, new String[] { "key3" }, "Description");
    }


    /**
     * Test the getSeminars() method.
     */
    public void testGetSeminars() {
        assertTrue(internalNode.getSeminars().isEmpty());
    }


    /**
     * Test inserting a single seminar and printing the structure.
     */
    public void testInsertOneSeminarAndPrint() {
        internalNode.insert(seminar1, 0, 0, 32, 32, 0, fw);

        String expectedOutput = "I\n  Leaf with 1 objects: 1\n  E\n";
        assertEquals(expectedOutput, internalNode.print(0));
    }


    /**
     * Test inserting multiple seminars and printing the structure.
     */
    public void testInsertMultipleSeminarsAndPrint() {
        internalNode.insert(seminar1, 0, 0, 32, 32, 0, fw);
        internalNode.insert(seminar2, 0, 0, 32, 32, 0, fw);
        internalNode.insert(seminar3, 0, 0, 32, 32, 0, fw);
        internalNode.insert(seminar1, 0, 0, 32, 16, 1, fw);

        String expectedOutput =
            "I\n  I\n    I\n      Leaf with 1 objects: 1\n      I\n"
                + "        Leaf with 1 objects: 1\n        "
                + "Leaf with 1 objects: 2\n    E\n  "
                + "Leaf with 1 objects: 3\n";
        assertEquals(expectedOutput, internalNode.print(0));
    }


    /**
     * Test inserting a seminar on the right side of the x-axis.
     */
    public void testInsertXAxisRight() {
        seminar1 = new Seminar(1, "Seminar1", "01-01-2023", 1, (short)20,
            (short)5, 100, new String[] { "key1" }, "Description");
        internalNode.insert(seminar1, 0, 0, 32, 32, 0, fw);

        String expectedOutput = "I\n  E\n  Leaf with 1 objects: 1\n";
        assertEquals(expectedOutput, internalNode.print(0));

        seminar2 = new Seminar(2, "Seminar2", "01-01-2023", 1, (short)20,
            (short)5, 100, new String[] { "key2" }, "Description");
        internalNode.insert(seminar1, 0, 0, 33, 33, 1, fw);

        expectedOutput = "I\n  Leaf with 1 objects: 1\n"
            + "  Leaf with 1 objects: 1\n";
        assertEquals(expectedOutput, internalNode.print(0));
    }


    /**
     * Test inserting a seminar on the right side of the y-axis.
     */
    public void testInsertYAxisRight() {
        seminar1 = new Seminar(1, "Seminar1", "01-01-2023", 1, (short)5,
            (short)20, 100, new String[] { "key1" }, "Description");
        internalNode.insert(seminar1, 0, 0, 32, 32, 1, fw);

        String expectedOutput = "I\n  E\n  Leaf with 1 objects: 1\n";
        assertEquals(expectedOutput, internalNode.print(0));

        Seminar semYRight = new Seminar(5, "SeminarYRight", "01-01-2023", 5,
            (short)5, (short)20, 100, new String[] { "key5" }, "Description");
        internalNode.insert(semYRight, 0, 0, 32, 32, 0, fw);

        expectedOutput = "I\n" + "  Leaf with 1 objects: 5\n"
            + "  Leaf with 1 objects: 1\n";
        assertEquals(expectedOutput, internalNode.print(0));
    }


    /**
     * Test inserting seminars deeper into the structure.
     */
    public void testInsertDeeper() {
        // Adding two seminars to force the structure deeper
        seminar1 = new Seminar(1, "Seminar1", "01-01-2023", 1, (short)5,
            (short)5, 100, new String[] { "key1" }, "Description");
        seminar2 = new Seminar(2, "Seminar2", "01-01-2023", 2, (short)15,
            (short)15, 100, new String[] { "key2" }, "Description");
        internalNode.insert(seminar1, 0, 0, 32, 32, 0, fw);
        internalNode.insert(seminar2, 0, 0, 32, 32, 0, fw);

        String expectedOutput =
            "I\n  I\n    I\n      Leaf with 1 objects: 1\n     "
                + " Leaf with 1 objects: 2\n    E\n  E\n";
        assertEquals(expectedOutput, internalNode.print(0));
        assertEquals(expectedOutput, internalNode.print(0));

        Seminar semYLeft = new Seminar(4, "SeminarYLeft", "01-01-2023", 4,
            (short)5, (short)10, 100, new String[] { "key4" }, "Description");
        internalNode.insert(semYLeft, 0, 0, 32, 32, 0, fw);

        expectedOutput =
            "I\n  I\n    I\n      I\n        Leaf with 1 objects: 1\n  "
                + "      Leaf with 1 objects: 4\n      Leaf with 1 objects: "
                + "2\n    E\n  E\n";
        assertEquals(expectedOutput, internalNode.print(0));

        Seminar semBoundary = new Seminar(6, "SemBoundary", "01-01-2023", 6,
            (short)0, (short)32, 100, new String[] { "key6" }, "Description");
        internalNode.insert(semBoundary, 0, 0, 32, 32, 0, fw);

        expectedOutput = "I\n" + "  I\n" + "    I\n" + "      I\n"
            + "        Leaf with 1 objects: 1\n"
            + "        Leaf with 1 objects: 4\n"
            + "      Leaf with 1 objects: 2\n" + "    Leaf with 1 objects: 6\n"
            + "  E\n";
        assertEquals(expectedOutput, internalNode.print(0));
    }


    /**
     * Test deleting seminars from the structure.
     */
    public void testDeleteSeminar() {
        internalNode.insert(seminar1, 0, 0, 32, 32, 0, fw);
        internalNode.insert(seminar2, 0, 0, 32, 32, 0, fw);
        internalNode.delete(15, 15, 2, 0, 0, 32, 32, 0, fw);

        String expectedOutput = "I\n  I\n    I\n   "
            + "   Leaf with 1 objects: 1\n      Leaf with 1 objects: 2\n"
            + "    E\n  E\n";
        assertEquals(expectedOutput, internalNode.print(0));

        internalNode.insert(seminar1, 0, 0, 32, 32, 0, fw);
        internalNode.insert(seminar2, 0, 0, 32, 32, 0, fw);
        internalNode.insert(seminar3, 0, 0, 32, 32, 0, fw);

        // Delete seminar2, which is deeper in the tree
        internalNode.delete(15, 15, 2, 0, 0, 32, 32, 0, fw);

        expectedOutput = "I\n  I\n    I\n   "
            + "   Leaf with 2 objects: 1 1\n      Leaf with 2 objects: 2 2\n"
            + "    E\n  Leaf with 1 objects: 3\n";
        assertEquals(expectedOutput, internalNode.print(0));
    }


    /**
     * Test searching within a certain radius.
     */
    public void testSearch() {
        internalNode.insert(seminar1, 0, 0, 32, 32, 0, fw);
        internalNode.insert(seminar2, 0, 0, 32, 32, 0, fw);

        int nodesVisited = internalNode.search(5, 5, 10, 0, 0, 32, 32, 0, fw);
    }

}
