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
public class SemSearchTest extends TestCase {

    private SemSearch semSearch;

    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        semSearch = new SemSearch();
    }


    /**
     * Test with the first argument not being a power of 2.
     */
    public void testMainInvalidWorldSize() {
        String[] args = { "3", "someString" };
        SemSearch.main(args);
        // assert the output, it should be "invalid world size"
        String expected = "invalid world size\n";
        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Test with the first argument being a power of 2
     * and valid second argument.
     */
    public void testMainValidArgs() {
        String[] args = { "4", "empty.txt" };
        SemSearch.main(args);

        assertEquals("", systemOut().getHistory());
    }


    /**
     * Test with the first argument not being a power of 2.
     */
    public void testMainInvalidInputs() {
        String[] args = { "4", "someString", "uh oh" };
        SemSearch.main(args);
        // assert the output, it should be "invalid world size"
        String expected = "invalid inputs\n";
        assertEquals(expected, systemOut().getHistory());
    }

}
