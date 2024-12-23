import student.TestCase;

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
public class BSTTest extends TestCase {

    private BST dupTree;
    private BST nonDupTree;
    private Seminar seminar;

    /**
     * Set up the test environment before each test case. This initializes
     * two BST
     * instances (one allowing duplicates and the other not) and a
     * Seminar object.
     */
    public void setUp() {
        seminar = new Seminar(1, "Test", "Test Location", 100, (short)1,
            (short)1, 10, new String[] { "TestWord" }, "Test Desc");
        dupTree = new BST("key1", seminar, true);
        nonDupTree = new BST("key1", seminar, false);

    }


    /**
     * Tests the insertion of unique keys in the BST.
     */
    public void testInsertUnique() {
        dupTree.insertStr("key2", seminar);
        assertEquals(1, dupTree.getNodeCount());
        dupTree.insertStr("key1", seminar);
        assertEquals(2, dupTree.getNodeCount());
        dupTree.insertStr("key0", seminar);
        assertEquals(3, dupTree.getNodeCount());
    }


    /**
     * Tests that insertion of duplicate keys is not allowed when
     * the tree is set to
     * disallow duplicates.
     */
    public void testInsertDuplicateNotAllowed() {
        nonDupTree.insertInt("1", seminar);
        assertEquals(1, nonDupTree.getNodeCount());
        assertFalse(nonDupTree.insertInt("1", seminar));
        assertEquals(1, nonDupTree.getNodeCount());
        nonDupTree.insertStr("key1", seminar);
        assertFalse(nonDupTree.insertStr("key1", seminar));
    }


    /**
     * Tests that insertion of duplicate keys is allowed when the tree
     * is set to
     * allow duplicates.
     */
    public void testInsertDuplicateAllowed() {
        dupTree.insertStr("key1", seminar);
        assertEquals(1, dupTree.getNodeCount());
        dupTree.insertStr("key1", seminar);
        assertEquals(2, dupTree.getNodeCount());

    }


    /**
     * Tests insertion of integer keys recursively into the BST.
     */
    public void testInsertIntRec() {

        BST intTree = new BST("root", seminar, true);
        // Insert integer keys in a specific order
        assertTrue(intTree.insertInt("5", seminar));
        assertTrue(intTree.insertInt("3", seminar));
        assertTrue(intTree.insertInt("8", seminar));
        assertTrue(intTree.insertInt("3", seminar));

        // Verify the tree structure after insertion
        assertEquals(4, intTree.getNodeCount());

        BST intTree2 = new BST("root", seminar, false);
        assertTrue(intTree2.insertInt("3", seminar));
        assertTrue(intTree2.insertInt("8", seminar));
        assertFalse(intTree2.insertInt("3", seminar));
    }


    /**
     * Tests the print functionality of the BST.
     */
    public void testPrint() {
        dupTree.printTheTree();
        assertEquals("This tree is empty\n", systemOut().getHistory());

        dupTree.insertInt("5", seminar);
        dupTree.insertInt("4", seminar);
        dupTree.insertInt("6", seminar);
        dupTree.insertInt("5", seminar);
        dupTree.printTheTree();
        assertTrue(systemOut().getHistory().contains("null\n" + "    6\n"
            + "            null\n" + "        5\n" + "            null\n"
            + "5\n" + "        null\n" + "    4\n" + "        null\n"
            + "Number of records: 4"));
    }


    /**
     * Tests the delete function to remove nodes from the BST using
     * integer keys.
     */
    public void testDelete() {
        BST tree = new BST("5", seminar, true);
        tree.insertInt("3", seminar);
        tree.insertInt("7", seminar);

        assertTrue(tree.delete(3));
        assertEquals(1, tree.getNodeCount());

        assertFalse(tree.delete(3));
        assertEquals(1, tree.getNodeCount());

        assertTrue(tree.delete(7));
        assertEquals(0, tree.getNodeCount());

        tree = new BST("5", new Seminar(), false);
        tree.insertInt("7", new Seminar());
        tree.insertInt("6", new Seminar());
        tree.delete(7);
        assertNull(tree.search(7));
        assertNotNull(tree.search(6));
        assertEquals(1, tree.getNodeCount());

        tree = new BST("5", new Seminar(), false);
        tree.insertInt("3", new Seminar());
        tree.insertInt("7", new Seminar());
        tree.insertInt("6", new Seminar());
        tree.insertInt("8", new Seminar());
        tree.delete(7);
        assertNull(tree.search(7));
        assertNotNull(tree.search(6));
        assertNotNull(tree.search(8));
        assertEquals(2, tree.getNodeCount());
    }


    /**
     * Tests the delete function to remove nodes from the BST using
     * string keys.
     */
    public void testDeleteByKey() {

        BST tree = new BST("root", new Seminar(), false);
        tree.insertStr("key3", new Seminar());
        tree.insertStr("key2", new Seminar());
        tree.deleteByKey("key3");
        assertEquals(1, tree.getNodeCount());

    }


    /**
     * Tests the retrieval of the minimum value from the BST.
     */
    public void testMinValue() {
        BST intTree = new BST("key", seminar, false);
        intTree.insertInt("3", seminar);
        intTree.insertInt("8", seminar);
        intTree.insertInt("7", seminar);
        intTree.insertInt("9", seminar);

        assertEquals(4, intTree.getNodeCount());

        intTree.deleteByKey("8");
        assertEquals(3, intTree.getNodeCount());

        assertNull(intTree.search(8));
        // Searching for node with key 9 should return a non-null node
        assertNotNull(intTree.search(9));
    }


    /**
     * Tests the search functionality of the BST to find nodes with
     * specific keys.
     */
    public void testSearch() {
        BST tree = new BST("key", seminar, true);
        tree.insertInt("3", seminar);
        tree.insertInt("7", seminar);

        assertNotNull(tree.search(3));
        assertNotNull(tree.search(7));
        assertNull(tree.search(2));

    }


    /**
     * Tests the range search functionality of the BST to find
     * seminars within a
     * specific cost range.
     */
    public void testRangeSearch() {
        BST tree = new BST("date", seminar, true);
        tree.insertStr("3", seminar);
        tree.insertStr("7", seminar);

        tree.rangeSearch("3", "7");
        assertTrue(systemOut().getHistory().contains("ID: 1, Title: Test\n"
            + "Date: Test Location, " + "Length: 100, X: 1, Y: 1, Cost: 10\n"
            + "Description: Test Desc\n" + "Keywords: TestWord"));

    }


    /**
     * Tests the search functionality of the BST to find
     * seminars with specific
     * keywords.
     */
    public void testSearchKeyword() {
        BST tree = new BST("key", seminar, true);
        tree.insertStr("TestWord", seminar);
        Seminar[] seminars = tree.searchKeyword("TestWord");
        assertEquals(1, seminars.length);
        assertEquals(seminar, seminars[0]);

        assertNull(tree.searchKeyword("no_key"));
    }

}
