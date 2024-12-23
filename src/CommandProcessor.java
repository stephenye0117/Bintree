import java.io.File;
import java.util.Scanner;

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
public class CommandProcessor {

    private SeminarDB database;
    private Seminar sem;
    private int worldSize;

    /**
     * Constructs a new CommandProcessor that uses the provided database and
     * processes commands from the given file path.
     *
     * @param worldSize
     *            The seminar database to use.
     * @param filePath
     *            Path to the file containing commands.
     */
    public CommandProcessor(int worldSize, String filePath) {
        database = new SeminarDB(worldSize);
        processCommands(filePath);
        this.worldSize = worldSize;

    }


    /**
     * Reads and processes commands from the provided file path.
     * Supported commands
     * include "insert", "delete", "search", and "print". Each
     * command results in a
     * specific operation being executed on the SeminarDB.
     *
     * @param filePath
     *            Path to the file containing commands.
     */
    void processCommands(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {

            while (scanner.hasNext()) {
                String command = scanner.next();

                switch (command) {
                    case "insert":
                        int id = scanner.nextInt();
                        String idStr = String.valueOf(id);
                        scanner.nextLine(); // Consume the rest of the line
                                            // after reading integer
                        String title = scanner.nextLine();
                        String date = scanner.next();
                        int length = scanner.nextInt();
                        short x = scanner.nextShort();
                        short y = scanner.nextShort();
                        int cost = scanner.nextInt();
                        String costStr = String.valueOf(cost);
                        scanner.nextLine();
                        String[] keywords = scanner.nextLine().trim().split(
                            "\\s+");
                        for (int i = 0; i < keywords.length; i++) {
                            keywords[i] = (keywords[i].trim());
                        }
                        String desc = scanner.nextLine().trim();
                        Seminar seminar = new Seminar(id, title, date, length,
                            x, y, cost, keywords, desc);

                        if (database.idInsert(idStr, seminar)) {
                            database.costInsert(costStr, seminar);
                            database.dateInsert(date, seminar);
                            database.wordInsert(keywords, seminar);
                            database.binInsert(seminar);
                        }

                        break;

                    case "delete":
                        id = scanner.nextInt();
                        database.deleteId(id);

                        break;

                    case "search":
                        String type = scanner.next();

                        if (type.equals("location")) {
                            String key = scanner.next();
                            String key2 = scanner.next();
                            String key3 = scanner.nextLine().trim();
                            database.searchBinTree(key, key2, key3);

                        }
                        else if (type.equals("ID")) {
                            String key = scanner.next();
                            String key2 = scanner.nextLine().trim();
                            int intK = Integer.parseInt(key);
                            BSTNode foundNode = database.idSearch(intK);
                            if (foundNode != null) {
                                System.out.println("Found record with ID "
                                    + intK + ":\n" + foundNode.getSeminar()
                                        .toString());
                            }
                            else {
                                System.out.println(
                                    "Search FAILED -- There is no record "
                                        + "with ID " + intK);
                            }

                        }
                        else if (type.equals("date")) {
                            String key = scanner.next();
                            String key2 = scanner.nextLine().trim();
                            database.searchRange(key, key2, type);

                        }
                        else if (type.equals("keyword")) {
                            String key = scanner.next();
                            String key2 = scanner.nextLine().trim();
                            database.keywordSearch(key);

                        }
                        else if (type.equals("cost")) {
                            String key = scanner.next();
                            String key2 = scanner.nextLine().trim();

                            database.searchRange(key, key2, type);

                        }

                        break;

                    case "print":
                        String printWhich = scanner.next();
                        if (printWhich.equals("location")) {
                            database.binPrint();

                        }
                        if (printWhich.equals("ID")) {
                            System.out.println("ID Tree:");
                            database.idPrint();

                        }
                        else if (printWhich.equals("date")) {
                            System.out.println("Date Tree:");
                            database.datePrint();

                        }
                        else if (printWhich.equals("keyword")) {
                            System.out.println("Keyword Tree:");
                            database.keyPrint();

                        }
                        else if (printWhich.equals("cost")) {
                            System.out.println("Cost Tree:");
                            database.costPrint();

                        }
                        break;

                    default:
                        System.out.println("Unrecognized command: " + command);
                        break;
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error processing commands: " + e.getMessage());
        }
    }
}
