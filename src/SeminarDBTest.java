import student.TestCase;

/**
 * Project 2
 * 
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
public class SeminarDBTest extends TestCase {

    private SeminarDB seminarDB;
    private Seminar seminar;

    /**
     * Set up the test case by initializing the SeminarDB and Seminar instances.
     */
    public void setUp() {
        seminarDB = new SeminarDB(10);
        seminar = new Seminar(1, "Test", "1234", 100, (short)1, (short)1, 10,
            new String[] { "TestWord" }, "Test Desc");
    }


    /**
     * Test the functionality of the IdInsert method.
     */
    public void testIdInsert() {

        assertTrue(seminarDB.idInsert("1", seminar));
        assertFalse(seminarDB.idInsert("1", seminar));
        assertEquals(1, seminarDB.getIdNodeCount());

        Seminar badSeminar = new Seminar(1, "Test", "Test Location", 100,
            (short)200, (short)1, 10, new String[] { "TestWord" }, "Test Desc");
        assertFalse(seminarDB.idInsert("key1", badSeminar));
        Seminar badSeminar2 = new Seminar(1, "Test", "Test Location", 100,
            (short)2, (short)200, 10, new String[] { "TestWord" }, "Test Desc");
        assertFalse(seminarDB.idInsert("key2", badSeminar2));
        Seminar badSeminar3 = new Seminar(1, "Test", "Test Location", 100,
            (short)-2, (short)1, 10, new String[] { "TestWord" }, "Test Desc");
        assertFalse(seminarDB.idInsert("key3", badSeminar3));
        Seminar badSeminar4 = new Seminar(1, "Test", "Test Location", 100,
            (short)1, (short)-2, 10, new String[] { "TestWord" }, "Test Desc");
        assertFalse(seminarDB.idInsert("key4", badSeminar4));

    }


    /**
     * Test the wordInsert method for inserting keywords.
     */
    public void testWordInsert() {
        String[] keys = { "Word1", "Word2" };
        seminarDB.wordInsert(keys, seminar);
        assertEquals(2, seminarDB.getKeyNodeCount());

    }


    /**
     * Test the CostInsert method for inserting seminars based on cost.
     */
    public void testCostInsert() {
        seminarDB.costInsert("100", seminar);
        assertEquals(1, seminarDB.getCostNodeCount());
    }


    /**
     * Test the DateInsert method for inserting seminars based on date.
     */
    public void testDateInsert() {
        seminarDB.dateInsert("20230924", seminar);
        assertEquals(1, seminarDB.getDateNodeCount());
    }


    /**
     * Test various print methods to ensure the correct data is displayed.
     */
    public void testPrints() {
        seminarDB.idPrint();
        seminarDB.datePrint();
        seminarDB.costPrint();

    }


    /**
     * Test the functionality of the idSearch method.
     */
    public void testSearch() {
        // Insert a Seminar object
        assertTrue(seminarDB.idInsert("1", seminar));

        // Search by ID
        BSTNode foundNode = seminarDB.idSearch(1);
        assertNotNull(foundNode);
        assertEquals(seminar, foundNode.getSeminar());

        // Search for a non-existing ID
        assertNull(seminarDB.idSearch(2));
    }


    /**
     * Test the functionality of the deleteId method.
     */
    public void testDelete() {

        assertTrue(seminarDB.idInsert("1", seminar));
        assertTrue(seminarDB.idInsert("2", seminar));

        assertTrue(seminarDB.deleteId(1));
        assertEquals(1, seminarDB.getIdNodeCount());

        assertFalse(seminarDB.deleteId(1));

    }


    /**
     * Test the keywordSearch method to ensure seminars
     * with the specified keyword
     * are found.
     */
    public void testKeywordSearch() {
        String[] keywords = { "TestWord" };
        seminarDB.wordInsert(keywords, seminar);
        seminarDB.keywordSearch("TestWord");
        assertEquals("Seminars matching keyword TestWord:\n" + seminar
            .toString() + "\n", systemOut().getHistory());

        seminarDB.keywordSearch("NonExistingWord");

        Seminar sem1 = new Seminar(1, "Test", "1234", 100, (short)1, (short)1,
            10, new String[] { "TestWord" }, "Test Desc");
        Seminar sem2 = new Seminar(2, "Test", "1234", 100, (short)1, (short)1,
            10, new String[] { "TestWord" }, "Test Desc");
        Seminar sem3 = new Seminar(3, "Test", "1234", 100, (short)1, (short)1,
            10, new String[] { "TestWord" }, "Test Desc");

        Seminar[] originalArray = { sem1, sem2, sem3 };
        Seminar[] expectedReversedArray = { sem3, sem2, sem1 };

        seminarDB.reverseArrayOrder(originalArray);

        assertFalse(expectedReversedArray.equals(originalArray));
    }


    /**
     * Test the searchDateRange method to ensure
     * seminars within the given date
     * range are found.
     */
    public void testDateRangeSearch() {
        seminarDB.dateInsert("20230924", seminar);
        seminarDB.searchRange("20230923", "20230925", "date");
        assertTrue(systemOut().getHistory().contains(seminar.toString()));

    }


    /**
     * Test various print methods to check if the correct
     * tree structures are
     * printed.
     */
    public void testPrintMethods() {
        seminarDB.idPrint();
        assertTrue(systemOut().getHistory().contains("This tree is empty"));

        // Insert a seminar and print again
        assertTrue(seminarDB.idInsert("1", seminar));
        seminarDB.idPrint();
        assertTrue(systemOut().getHistory().contains("1"));

        systemOut().clearHistory();
        seminarDB.keyPrint();
        assertEquals("This tree is empty\n", systemOut().getHistory());
    }


    /**
     * Test the costSearch method to ensure seminars
     * within the given cost range are
     * found.
     */
    public void testCostRangeSearch() {
        systemOut().clearHistory();
        seminarDB.costInsert("10", seminar);
        seminarDB.searchRange("10", "15", "cost");
        assertTrue(systemOut().getHistory().contains(seminar.toString()));
    }


    /**
     * Tests the binInsert and binPrint methods of the seminarDB.
     * Ensures that after inserting a seminar, it is properly reflected in the
     * printed output of the location tree.
     */
    public void testBinInsertAndPrint() {
        seminarDB.binInsert(seminar);
        seminarDB.binPrint();
        assertTrue(systemOut().getHistory().contains("Location Tree:\n"
            + "Leaf with 1 objects: 1\n"));
    }

}
