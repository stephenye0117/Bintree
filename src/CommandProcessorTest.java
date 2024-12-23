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
public class CommandProcessorTest extends TestCase {

    private CommandProcessor tester;

    /**
     * Sets up the test fixture. Before every test case method, this method will
     * run.
     */
    public void setUp() {

        tester = new CommandProcessor(128, "testIn.txt");
    }


    /**
     * Test the insert command's output.
     */
    public void testInsertCommand() {

        assertTrue(systemOut().getHistory().contains(
            "Successfully inserted record with ID 1\n"));
        assertTrue(systemOut().getHistory().contains(
            "Insert FAILED - Bad x, y coordinates: -1, 10\n"));
        assertTrue(systemOut().getHistory().contains(
            "Successfully inserted record with ID 11\n"));
    }


    /**
     * Test the delete command's output.
     */
    public void testDeleteCommand() {
        assertTrue(systemOut().getHistory().contains(
            "Delete FAILED -- There is no record with ID 1"));
        assertTrue(systemOut().getHistory().contains(
            "Record with ID 1 successfully deleted from the database"));
    }


    /**
     * Test the search command's output.
     */
    public void testSearchCommand() {
        assertTrue(systemOut().getHistory().contains(
            "ID: 1, Title: Overview of HCI Research at VT\n"
                + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
                + "Description: This seminar will present "
                + "an overview of HCI research at VT\n"
                + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
                + "Search FAILED -- There is no record with ID 2\n"
                + "Seminars with dates in range 0 to 1:\n"
                + "ID: 1, Title: Overview of HCI Research at VT\n"
                + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
                + "Description: This seminar will present an "
                + "overview of HCI research at VT\n"
                + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
                + "3 nodes visited in this search\n"
                + "Seminars with dates in range 8 to 9:\n"
                + "2 nodes visited in this search\n"
                + "Seminars matching keyword VT:\n"
                + "ID: 1, Title: Overview of HCI Research at VT\n"
                + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
                + "Description: This seminar will present an "
                + "overview of HCI research at VT\n"
                + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
                + "Seminars matching keyword nope:\n"
                + "Seminars with costs in range 0 to 1:\n"
                + "2 nodes visited in this search\n"
                + "Seminars with costs in range 10 to 1:\n"
                + "2 nodes visited in this search\n"
                + "Seminars with costs in range 40 to 50:\n"
                + "ID: 1, Title: Overview of HCI Research at VT\n"
                + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
                + "Description: This seminar will present an "
                + "overview of HCI research at VT\n"
                + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
                + "3 nodes visited in this search"));

    }


    /**
     * Test the print command's output.
     */
    public void testPrintCommand() {
        assertTrue(systemOut().getHistory().contains("ID Tree:"));
        assertTrue(systemOut().getHistory().contains("Keyword Tree:"));
        assertTrue(systemOut().getHistory().contains("Date Tree:"));
    }


    /**
     * Test the scenario where the command provided is invalid.
     */
    public void testInvalidCommand() {
        assertTrue(systemOut().getHistory().contains(
            "Unrecognized command: wtf"));
    }


    /**
     * Test when the file path given is invalid.
     */
    public void testInvalidFilePath() {
        new CommandProcessor(128, "nonExistentFile.txt");
        assertTrue(systemOut().getHistory().contains(
            "Error processing commands:"));
    }


    /**
     * makes sure the whole output is correct
     */
    public void testAll() {
        String bigBoi = "Delete FAILED -- There is no record with ID 1\n"
            + "Insert FAILED - Bad x, y coordinates: -1, 10\n"
            + "Delete FAILED -- There is no record with ID 1\n"
            + "Successfully inserted record with ID 1\n"
            + "ID: 1, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present "
            + "an overview of HCI research at VT\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
            + "Record with ID 1 successfully deleted from the database\n"
            + "Successfully inserted record with ID 1\n"
            + "ID: 1, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an"
            + " overview of HCI research at VT\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
            + "Found record with ID 1:\n"
            + "ID: 1, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an"
            + " overview of HCI research at VT\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
            + "Search FAILED -- There is no record with ID 2\n"
            + "Seminars with dates in range 0 to 1:\n"
            + "ID: 1, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an "
            + "overview of HCI research at VT\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
            + "3 nodes visited in this search\n"
            + "Seminars with dates in range 8 to 9:\n"
            + "2 nodes visited in this search\n"
            + "Seminars matching keyword VT:\n"
            + "ID: 1, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an "
            + "overview of HCI research at VT\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
            + "Seminars matching keyword nope:\n"
            + "Seminars with costs in range 0 to 1:\n"
            + "2 nodes visited in this search\n"
            + "Seminars with costs in range 10 to 1:\n"
            + "2 nodes visited in this search\n"
            + "Seminars with costs in range 40 to 50:\n"
            + "ID: 1, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an "
            + "overview of HCI research at VT\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
            + "3 nodes visited in this search\n"
            + "Seminars with costs in range 0 to 1000000:\n"
            + "ID: 1, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an "
            + "overview of HCI research at VT\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
            + "3 nodes visited in this search\n"
            + "Seminars with costs in range 10 to 10:\n"
            + "2 nodes visited in this search\n"
            + "Seminars with costs in range 400 to 50:\n"
            + "2 nodes visited in this search\n" + "Location Tree:\n"
            + "Leaf with 2 objects: 1 1\n" + "ID Tree:\n" + "    null\n" + "1\n"
            + "    null\n" + "Number of records: 1\n" + "Date Tree:\n"
            + "    null\n" + "0610051600\n" + "    null\n"
            + "Number of records: 1\n" + "Keyword Tree:\n"
            + "            null\n" + "        Virginia_Tech\n"
            + "            null\n" + "    VT\n" + "        null\n" + "HCI\n"
            + "        null\n" + "    Computer_Science\n" + "        null\n"
            + "Number of records: 4\n" + "Cost Tree:\n" + "    null\n" + "45\n"
            + "    null\n" + "Number of records: 1\n"
            + "Seminars within 1.0 units of 1, 0:\n"
            + "1 nodes visited in this search\n"
            + "Successfully inserted record with ID 11\n"
            + "ID: 11, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an "
            + "overview of HCI research at VT\n" + "Keywords: l\n"
            + "Unrecognized command: wtf\n";
        assertEquals(systemOut().getHistory(), bigBoi);
    }

}
