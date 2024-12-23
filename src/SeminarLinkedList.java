import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Project 2
 * 
 */

/**
 * A simple linked list class that provides basic operations for storing and
 * managing Seminar objects.
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
class SeminarLinkedList implements Iterable<Seminar> {
    private SeminarNode head;

    /**
     * Provides an iterator for the list that can be used for enhanced for-loops
     * and other iteration tasks.
     * 
     * @return Iterator for the SeminarLinkedList.
     */
    public Iterator<Seminar> iterator() {
        return new Iterator<Seminar>() {
            private SeminarNode current = head;

            public boolean hasNext() {
                return current != null;
            }


            public Seminar next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Seminar seminar = current.seminar;
                current = current.next;
                return seminar;
            }
        };
    }


    /**
     * Adds a new seminar to the front of the list.
     * 
     * @param sem
     *            Seminar object to add.
     */
    public void add(Seminar sem) {
        SeminarNode newNode = new SeminarNode(sem);
        newNode.next = head;
        head = newNode;
    }


    /**
     * Removes a seminar from the list. If the seminar is not present, no action
     * is taken.
     * 
     * @param sem
     *            Seminar object to remove.
     */
    public void remove(Seminar sem) {
        if (head == null)
            return; // List is empty, nothing to remove

        // If the head node holds the seminar to be deleted
        if (head.seminar.equals(sem)) {
            head = head.next;
            return;
        }

        SeminarNode current = head;
        while (current.next != null && !current.next.seminar.equals(sem)) {
            current = current.next;
        }

        // If the seminar was not found
        if (current.next == null)
            return;

        // Otherwise, remove the next node
        current.next = current.next.next;
    }


    /**
     * Checks if the list is empty.
     * 
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return head == null;
    }


    /**
     * Retrieves the first seminar from the list.
     * 
     * @return the first seminar or null if the list is empty.
     */
    public Seminar getFirstSeminar() {
        return head == null ? null : head.seminar;
    }


    /**
     * Counts the number of seminars in the list.
     * 
     * @return the number of seminars.
     */
    public int size() {
        int count = 0;
        SeminarNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }


    /**
     * Retrieves the seminar at the specified index.
     * 
     * @param index
     *            Index of the seminar to retrieve.
     * @return the seminar at the specified index or null if index is out of
     *         bounds.
     */
    public Seminar get(int index) {
        int count = 0;
        SeminarNode current = head;
        while (current != null && count < index) {
            count++;
            current = current.next;
        }
        if (current != null) {
            return current.seminar;
        }
        return null; // Or throw an exception.
    }

    /**
     * Private inner class to represent a node in the list.
     */
    private class SeminarNode {
        private Seminar seminar;
        private SeminarNode next;

        /**
         * Constructs a new node with the specified seminar.
         * 
         * @param sem
         *            Seminar to store in the node.
         */
        SeminarNode(Seminar sem) {
            this.seminar = sem;
            this.next = null;
        }
    }

}
