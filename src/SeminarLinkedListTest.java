import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;
import student.TestCase;

/**
 * Project 2
 * 
 */

/**
 * Test class for the SeminarLinkedList class.
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
public class SeminarLinkedListTest extends TestCase {

    private SeminarLinkedList seminarList;
    private SeminarLinkedList otherList;
    private Seminar seminar1;
    private Seminar seminar2;
    private Seminar seminar3;

    /**
     * Initial setup for the tests. This method gets called before every test
     * case.
     */
    public void setUp() {
        seminarList = new SeminarLinkedList();
        otherList = new SeminarLinkedList();
        seminar1 = new Seminar(1, "Seminar1", "01-01-2023", 1, (short)10,
            (short)10, 100, new String[] { "key1" }, "Description1");
        seminar2 = new Seminar(2, "Seminar2", "02-01-2023", 2, (short)60,
            (short)60, 200, new String[] { "key2" }, "Description2");
        seminar3 = new Seminar(3, "Seminar3", "03-01-2023", 3, (short)30,
            (short)30, 300, new String[] { "key3" }, "Description3");
    }


    /**
     * Tests the 'add' and 'size' methods of the SeminarLinkedList.
     */
    public void testAddAndSize() {
        assertTrue(seminarList.isEmpty());
        assertEquals(0, seminarList.size());
        assertNull(seminarList.getFirstSeminar());

        seminarList.add(seminar1);
        assertEquals(1, seminarList.size());
        assertEquals(seminar1, seminarList.getFirstSeminar());
        assertFalse(seminarList.isEmpty());

        seminarList.add(seminar2);
        assertEquals(2, seminarList.size());
        assertEquals(seminar2, seminarList.getFirstSeminar());
    }


    /**
     * Tests the 'remove' method of the SeminarLinkedList.
     */
    public void testRemove() {
        seminarList.remove(seminar1);
        assertEquals(0, seminarList.size());

        seminarList.add(seminar1);
        seminarList.add(seminar2);
        seminarList.add(seminar1); // Adding seminar1 again to create a
                                   // duplicate

        // Removing a seminar that doesn't exist in the list
        seminarList.remove(seminar3);
        assertEquals(3, seminarList.size()); // Size should remain unchanged

        // Removing the first occurrence of a duplicated seminar
        seminarList.remove(seminar1);
        assertEquals(2, seminarList.size()); // Size should decrease by 1
        assertEquals(seminar2, seminarList.getFirstSeminar());
        assertEquals(seminar1, seminarList.get(1)); // Ensure the duplicate
                                                    // remains

        // Removing the remaining duplicate
        seminarList.remove(seminar1);
        assertEquals(1, seminarList.size());
        assertEquals(seminar2, seminarList.getFirstSeminar());
    }


    /**
     * Tests the iterator provided by the SeminarLinkedList.
     */
    public void testIterator() {
        seminarList.add(seminar1);
        seminarList.add(seminar2);
        seminarList.add(seminar3);

        Iterator<Seminar> iterator = seminarList.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(seminar3, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(seminar2, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(seminar1, iterator.next());

        assertFalse(iterator.hasNext());
    }


    /**
     * Tests if the iterator throws an exception when it should.
     */
    public void testIteratorException() {
        Iterator<Seminar> iterator = seminarList.iterator();

        try {
            iterator.next();
            fail("Expected an NoSuchElementException to be thrown");
        }
        catch (NoSuchElementException e) {
            // empty on purpose
        }
    }


    /**
     * Tests the 'get' method of the SeminarLinkedList.
     */
    public void testGet() {
        seminarList.add(seminar1);
        seminarList.add(seminar2);

        assertEquals(seminar1, seminarList.get(1));
        assertEquals(seminar2, seminarList.get(0));
        assertNull(seminarList.get(2)); // Test index out of bounds
    }
}
