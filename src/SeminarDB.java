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
public class SeminarDB {

    private Seminar rootSem;
    private BST bstId;
    private BST bstCost;
    private BST bstDate;
    private BST bstKey;
    private BinTree locationTree;
    private int worldSize;

    /**
     * Initializes a new instance of the SeminarDB with a given world size.
     * 
     * @param worldSize
     *            The size constraint for seminar locations.
     */
    public SeminarDB(int worldSize) {
        short a = 0;

        rootSem = new Seminar(0, "", "", 0, a, a, 0, new String[] { "" }, "");

        bstId = new BST("root", rootSem, false);
        bstCost = new BST("root", rootSem, true);
        bstDate = new BST("root", rootSem, true);
        bstKey = new BST("root", rootSem, true);
        locationTree = new BinTree(worldSize);
        this.worldSize = worldSize;
    }


    /**
     * Inserts a Seminar with a given ID.
     * 
     * @param key
     *            The ID key of the seminar.
     * @param sem
     *            The Seminar object to be inserted.
     * @return true if the seminar was successfully inserted, false otherwise.
     */
    public boolean idInsert(String key, Seminar sem) {
        if (sem.x() < worldSize && sem.y() < worldSize && sem.x() >= 0 && sem
            .y() >= 0) {
            if (bstId.insertInt(key, sem)) {
                System.out.println("Successfully inserted record with ID "
                    + key);
                System.out.println(sem.toString());
                return true;
            }
            System.out.println(
                "Insert FAILED - There is already a record with ID " + key);
            return false;
        }
        System.out.println("Insert FAILED - Bad x, y coordinates: " + sem.x()
            + ", " + sem.y());
        return false;
    }


    /**
     * Searches for a Seminar based on its ID.
     * 
     * @param id
     *            The ID of the Seminar to search for.
     * @return The BSTNode containing the Seminar, or null if not found.
     */
    public BSTNode idSearch(int id) {
        return bstId.search(id);
    }


    /**
     * Prints the seminars sorted by ID.
     */
    public void idPrint() {
        bstId.printTheTree();

    }


    /**
     * Prints the Date tree.
     */
    public void datePrint() {
        bstDate.printTheTree();
    }


    /**
     * Prints the Cost tree.
     */
    public void costPrint() {
        bstCost.printTheTree();
    }


    /**
     * Retrieves the count of nodes in the ID tree.
     * 
     * @return The number of nodes in the ID tree.
     */
    public int getIdNodeCount() {
        return bstId.getNodeCount();
    }


    /**
     * Inserts a seminar into the database based on its cost.
     * 
     * @param key
     *            The cost associated with the seminar.
     * @param sem
     *            The seminar object to insert.
     */
    public void costInsert(String key, Seminar sem) {
        bstCost.insertInt(key, sem);

    }


    /**
     * Retrieves the count of nodes in the Cost tree.
     * 
     * @return The number of nodes in the Cost tree.
     */
    public int getCostNodeCount() {
        return bstCost.getNodeCount();
    }


    /**
     * Inserts a seminar into the database based on its date.
     * 
     * @param key
     *            The date associated with the seminar.
     * @param sem
     *            The seminar object to insert.
     */
    public void dateInsert(String key, Seminar sem) {
        bstDate.insertStr(key, sem);
    }


    /**
     * Retrieves the count of nodes in the Date tree.
     * 
     * @return The number of nodes in the Date tree.
     */
    public int getDateNodeCount() {
        return bstDate.getNodeCount();
    }


    /**
     * Inserts multiple keywords for a seminar into the Keyword tree.
     * 
     * @param keywords
     *            Array of keywords associated with the seminar.
     * @param seminar
     *            The seminar object linked to the keywords.
     */
    public void wordInsert(String[] keywords, Seminar seminar) {
        for (String keyword : keywords) {
            bstKey.insertStr(keyword, seminar);
        }
    }


    /**
     * Searches for seminars associated with a specific keyword.
     * 
     * @param keyword
     *            The keyword to search for.
     */
    public void keywordSearch(String keyword) {
        Seminar[] seminars = bstKey.searchKeyword(keyword);
        if (seminars != null) {
            reverseArrayOrder(seminars);
        }
        if (seminars == null) {
            System.out.println("Seminars matching keyword " + keyword + ":");
        }
        else {
            System.out.println("Seminars matching keyword " + keyword + ":");
            for (Seminar sem : seminars) {
                if (sem != null)
                    System.out.println(sem.toString());
            }
        }
    }


    /**
     * reverses the order of seminars in an array
     * 
     * @param seminars
     *            the array of seminars
     */
    public void reverseArrayOrder(Seminar[] seminars) {
        int length = seminars.length;
        for (int i = 0; i < length / 2; i++) {
            Seminar temp = seminars[i];
            seminars[i] = seminars[length - 1 - i];
            seminars[length - 1 - i] = temp;
        }
    }


    /**
     * Prints the Keyword tree.
     */
    public void keyPrint() {
        bstKey.printTheTree();
    }


    /**
     * Retrieves the count of nodes in the Keyword tree.
     * 
     * @return The number of nodes in the Keyword tree.
     */
    public int getKeyNodeCount() {
        return bstKey.getNodeCount();
    }


    /**
     * Deletes a seminar from the database based on its ID.
     * 
     * @param id
     *            The ID of the seminar to delete.
     * @return true if the deletion is successful, otherwise false.
     */
    public boolean deleteId(int id) {
        // Search for the node in the id tree first and get the seminar
        BSTNode nodeToDelete = bstId.search(id);

        if (nodeToDelete == null) {
            System.out.println("Delete FAILED -- There is no record with ID "
                + id);
            return false;
        }

        // Get the Seminar object from the node
        Seminar seminarToDelete = nodeToDelete.getSeminar();

        // Delete the node from the id tree
        boolean isDeletedFromIdTree = bstId.delete(id);
        String dateKey = seminarToDelete.date();
        int costKey = seminarToDelete.cost();
        String[] keywordKeys = seminarToDelete.keywords();

        // Delete from date tree
        bstDate.delete(Integer.parseInt(dateKey));
        bstCost.delete(costKey);

        // Delete keywords from keyword tree
        for (String keyword : keywordKeys) {

            bstKey.deleteByKey(keyword);
        }
        locationTree.delete(seminarToDelete.x(), seminarToDelete.y(),
            seminarToDelete.id());

        System.out.println("Record with ID " + id
            + " successfully deleted from the database");
        return true;
    }


    /**
     * Searches for seminars within a specific date range.
     * 
     * @param low
     *            The start of the date range.
     * @param high
     *            The end of the date range.
     * @param type
     *            The type of range search (either "date" or "cost").
     */
    public void searchRange(String low, String high, String type) {
        if ("date".equals(type)) {
            System.out.println("Seminars with dates in range " + low + " to "
                + high + ":");
            bstDate.rangeSearch(low, high);
        }
        else {
            System.out.println("Seminars with costs in range " + low + " to "
                + high + ":");
            int lowInt = Integer.parseInt(low);
            int highInt = Integer.parseInt(high);
            bstCost.rangeSearch(lowInt, highInt);
        }
    }


    /**
     * Inserts a given seminar into the location tree.
     *
     * @param seminar
     *            The seminar to be inserted.
     */
    public void binInsert(Seminar seminar) {
        locationTree.insert(seminar);
    }


    /**
     * Prints the location tree.
     * Prefaces the tree printout with a descriptive header.
     */
    public void binPrint() {
        System.out.println("Location Tree:");
        locationTree.printTree();
    }


    /**
     * Searches the location tree for seminars within a given radius of a
     * specified point.
     * The x, y coordinates and the radius are provided as strings and parsed to
     * their respective types.
     *
     * @param xStr
     *            The x-coordinate of the point, provided as a string.
     * @param yStr
     *            The y-coordinate of the point, provided as a string.
     * @param radiusStr
     *            The radius within which to search for seminars, provided as a
     *            string.
     */
    public void searchBinTree(String xStr, String yStr, String radiusStr) {
        try {
            int x = Integer.parseInt(xStr);
            int y = Integer.parseInt(yStr);
            double radius = Double.parseDouble(radiusStr);
            locationTree.search(x, y, radius);

        }
        catch (NumberFormatException e) {
            System.out.println("Invalid input. Please provide valid "
                + "integers for x, y, and radius.");
        }
    }

}
