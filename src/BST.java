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
public class BST {

    private BSTNode theRoot;
    private int nodeCount;
    private boolean allowDup;

    private Seminar[] rangeArr;
    private int rangeInc;

    /**
     * Initializes a new BST object.
     * 
     * @param key
     *            the initial key to be placed in the BST.
     * @param sem
     *            the Seminar object associated with the initial key.
     * @param allowDup
     *            indicates whether duplicate keys are allowed in the BST.
     */
    public BST(String key, Seminar sem, boolean allowDup) {
        this.theRoot = null;
        this.nodeCount = 0;
        this.allowDup = allowDup;

    }


    /**
     * Retrieves the number of nodes in the BST.
     *
     * @return the count of nodes in the BST.
     */
    public int getNodeCount() {
        return nodeCount;
    }


    /**
     * Inserts a new node into the BST with the specified integer key
     * and associated
     * Seminar object.
     *
     * @param key
     *            the integer key to be inserted into the BST.
     * @param seminar
     *            the Seminar object associated with the specified key.
     * @return true if a new node is added; false if a node with the same key
     *         already exists.
     */
    public boolean insertInt(String key, Seminar seminar) {
        int ogCount = nodeCount;

        theRoot = insertIntRec(theRoot, Integer.parseInt(key), seminar);

        if (ogCount < nodeCount) {
            return true; // New node added
        }
        else {
            return false; // Node with the same key already exists
        }
    }


    /**
     * Recursively inserts a new node into the BST
     * with the specified integer key
     * and associated Seminar object.
     *
     * @param root
     *            the root of the subtree in which the insertion is to be
     *            performed.
     * @param key
     *            the integer key to be inserted into the BST.
     * @param seminar
     *            the Seminar object associated with the specified key.
     * @return the root of the subtree after insertion.
     */
    private BSTNode insertIntRec(BSTNode root, int key, Seminar seminar) {

        if (root == null) {
            nodeCount++;
            return new BSTNode(String.valueOf(key), seminar);
        }

        if (key < Integer.parseInt(root.getKey())) {
            root.setLeft(insertIntRec(root.getLeft(), key, seminar));
        }
        else if (key > Integer.parseInt(root.getKey())) {
            root.setRight(insertIntRec(root.getRight(), key, seminar));
        }
        else {
            if (allowDup) {
                root.setRight(insertIntRec(root.getRight(), key, seminar));
            }
            else {
                // Update seminar for existing key (if duplicates not allowed)
                root.setSeminar(seminar);
            }
        }

        return root;
    }


    /**
     * Inserts a new node into the BST with the specified
     * string key and associated
     * Seminar object.
     *
     * @param key
     *            the string key to be inserted into the BST.
     * @param seminar
     *            the Seminar object associated with the specified key.
     * @return true if a new node is added; false if a node with the same key
     *         already exists.
     */
    public boolean insertStr(String key, Seminar seminar) {
        int ogCount = nodeCount;

        theRoot = insertStrRec(theRoot, key, seminar);

        if (ogCount < nodeCount) {
            return true; // New node added
        }
        else {
            return false; // Node with the same key already exists
        }
    }


    /**
     * Recursively inserts a node with a String key into the BST.
     *
     * @param root
     *            The root of the current subtree.
     * @param key
     *            The key of the node to insert.
     * @param seminar
     *            The seminar object to insert.
     * @return The new root of the subtree.
     */
    private BSTNode insertStrRec(BSTNode root, String key, Seminar seminar) {
        if (root == null) {
            nodeCount++;
            return new BSTNode(key, seminar);
        }

        int cmp = key.compareTo(root.getKey());
        if (cmp < 0) {
            root.setLeft(insertStrRec(root.getLeft(), key, seminar));
        }
        else if (cmp > 0) {
            root.setRight(insertStrRec(root.getRight(), key, seminar));
        }
        else {
            if (allowDup) {
                root.setRight(insertStrRec(root.getRight(), key, seminar));
            }
            else {
                // if duplicates are not allowed, just update the seminar
                root.setSeminar(seminar);
            }
        }

        return root;
    }


    /**
     * Deletes a node with the given ID.
     *
     * @param id
     *            The ID of the node to delete.
     * @return True if the node is successfully deleted, false otherwise.
     */
    public boolean delete(int id) {
        int[] oldCount = new int[1];
        oldCount[0] = this.nodeCount;
        theRoot = deleteRec(theRoot, id, oldCount);
        return oldCount[0] - 1 == this.nodeCount;
    }


    /**
     * Recursively deletes a node with the given ID from the BST.
     *
     * @param root
     *            The root of the current subtree.
     * @param id
     *            The ID of the node to delete.
     * @param oldCount
     *            The count of nodes before deletion.
     * @return The new root of the subtree.
     */
    private BSTNode deleteRec(BSTNode root, int id, int[] oldCount) {
        if (root == null)
            return null;

        int rootId = Integer.parseInt(root.getKey());
        if (id < rootId)
            root.setLeft(deleteRec(root.getLeft(), id, oldCount));
        else if (id > rootId)
            root.setRight(deleteRec(root.getRight(), id, oldCount));
        else {
            this.nodeCount--;
            if (root.getLeft() == null)
                return root.getRight();
            else if (root.getRight() == null)
                return root.getLeft();

            root.setKey(minValue(root.getRight()));
            root.setRight(deleteRec(root.getRight(), Integer.parseInt(root
                .getKey()), oldCount));
        }
        return root;
    }


    /**
     * Deletes a node with the given key.
     *
     * @param key
     *            The key of the node to delete.
     */
    public void deleteByKey(String key) {
        theRoot = deleteByKeyRec(theRoot, key);
        nodeCount--;
    }


    /**
     * Recursively deletes a node with the given key from the BST.
     *
     * @param root
     *            The root of the current subtree.
     * @param key
     *            The key of the node to delete.
     * @return The new root of the subtree.
     */
    private BSTNode deleteByKeyRec(BSTNode root, String key) {
        if (root == null)
            return null;

        int cmp = key.compareTo(root.getKey());
        if (cmp < 0) {
            root.setLeft(deleteByKeyRec(root.getLeft(), key));
        }
        else if (cmp > 0) {
            root.setRight(deleteByKeyRec(root.getRight(), key));
        }
        else {
            // Node with the key found, now delete it
// nodeCount--;
            if (root.getLeft() == null)
                return root.getRight();
            else if (root.getRight() == null)
                return root.getLeft();

            root.setKey(minValue(root.getRight()));
            root.setRight(deleteByKeyRec(root.getRight(), root.getKey()));
        }
        return root;
    }


    /**
     * Finds the node with the minimum key in the BST.
     *
     * @param root
     *            The root of the current subtree.
     * @return The key of the node with the minimum key in the BST.
     */
    private String minValue(BSTNode root) {
        String minKey = root.getKey();
        while (root.getLeft() != null) {
            minKey = root.getLeft().getKey();
            root = root.getLeft();
        }
        return minKey;
    }


    /**
     * Searches for a node in the BST with the specified integer key.
     *
     * @param key
     *            the integer key to be searched in the BST.
     * @return the node if found, null if not found.
     */
    public BSTNode search(int key) {
        return searchRec(theRoot, key);
    }


    /**
     * Recursively searches for a node in the BST with
     * the specified integer key.
     *
     * @param root
     *            the root of the subtree in which the
     *            search is to be performed.
     * @param key
     *            the integer key to be searched in the BST.
     * @return the node if found, null if not found.
     */
    private BSTNode searchRec(BSTNode root, int key) {
        if (root == null)
            return null;

        int rootKey = Integer.parseInt(root.getKey());
        if (rootKey == key)
            return root;

        if (rootKey < key)
            return searchRec(root.getRight(), key);

        return searchRec(root.getLeft(), key);
    }


    /**
     * Prints the seminars with dates in a specified range.
     *
     * @param low
     *            the lower bound of the cost range.
     * @param high
     *            the upper bound of the cost range.
     */
    public void rangeSearch(String low, String high) {

        int nodesVisited = rangeSearchRec(theRoot, low, high);
        System.out.println(nodesVisited + " nodes visited in this search");

    }


    /**
     * Recursively performs a range search in the BST
     * and adds seminars with dates
     * in the specified range to rangeArr.
     *
     * @param root
     *            the root of the subtree in which the
     *            range search is to
     *            be performed.
     * @param low
     *            the lower bound of the cost range.
     * @param high
     *            the upper bound of the cost range.
     * @param visitedNodes
     *            an array to store the count of visited
     *            nodes during the
     *            range search.
     */
    private int rangeSearchRec(BSTNode root, String low, String high) {
        if (root == null) {
            return 1;
        }
        int nodesVisited = 1;
        if (root.getKey().compareTo(low) >= 0) {
            nodesVisited += rangeSearchRec(root.getLeft(), low, high);
        }

        if ((root.getKey().compareTo(low) >= 0) && (root.getKey().compareTo(
            high)) <= 0) {
            System.out.print(root.getSeminar().toString() + "\n");

        }
        if (root.getKey().compareTo(high) < 0) {
            nodesVisited += rangeSearchRec(root.getRight(), low, high);
        }
        return nodesVisited;
    }


    /**
     * Prints the seminars with costs in a specified range.
     *
     * @param low
     *            the lower bound of the cost range.
     * @param high
     *            the upper bound of the cost range.
     */
    public void rangeSearch(int low, int high) {

        int nodesVisited = rangeSearchRec(theRoot, low, high);
        System.out.println(nodesVisited + " nodes visited in this search");

    }


    /**
     * Recursively performs a range search in the BST
     * and adds seminars with costs
     * in the specified range to rangeArr.
     *
     * @param root
     *            the root of the subtree in which the
     *            range search is to
     *            be performed.
     * @param low
     *            the lower bound of the cost range.
     * @param high
     *            the upper bound of the cost range.
     * @param visitedNodes
     *            an array to store the count of visited
     *            nodes during the
     *            range search.
     */
    private int rangeSearchRec(BSTNode root, int low, int high) {
        if (root == null) {
            return 1;
        }
        int nodesVisited = 1;
        int node = Integer.parseInt(root.getKey());
        if (node >= (low)) {
            nodesVisited += rangeSearchRec(root.getLeft(), low, high);
        }

        if ((node >= low) && (node <= high)) {
            System.out.print(root.getSeminar().toString() + "\n");

        }
        if (node < high) {
            nodesVisited += rangeSearchRec(root.getRight(), low, high);
        }
        return nodesVisited;
    }


    /**
     * Searches for seminars with a given keyword.
     *
     * @param keyword
     *            The keyword to search for.
     * @return An array of seminars that match the keyword.
     */
    public Seminar[] searchKeyword(String keyword) {
        int estimatedSize = this.getNodeCount();
        Seminar[] seminars = new Seminar[estimatedSize];
        int[] counter = new int[1];

        searchKeywordRec(theRoot, keyword, seminars, counter);

        if (counter[0] == 0)
            return null;
        return seminars;
    }


    /**
     * Recursively searches for seminars with a given keyword.
     *
     * @param root
     *            The root of the current subtree.
     * @param keyword
     *            The keyword to search for.
     * @param seminars
     *            An array to hold the matching seminars.
     * @param counter
     *            A counter for the number of matched seminars.
     */
    private void searchKeywordRec(
        BSTNode root,
        String keyword,
        Seminar[] seminars,
        int[] counter) {
        if (root == null)
            return;

        if (root.getKey().equals(keyword)) {
            seminars[counter[0]] = root.getSeminar();
            counter[0]++;
        }

        searchKeywordRec(root.getLeft(), keyword, seminars, counter);
        searchKeywordRec(root.getRight(), keyword, seminars, counter);
    }


    /**
     * Prints the tree to the console.
     */
    public void printTheTree() {
        if (nodeCount != 0) {
            printTree(theRoot, 0);
            System.out.println("Number of records: " + nodeCount);
        }
        else {
            System.out.println("This tree is empty");
        }
    }


    /**
     * Recursively prints the nodes of the BST to the console.
     *
     * @param node
     *            The current node.
     * @param level
     *            The current level of the tree.
     */
    private void printTree(BSTNode node, int level) {
        if (node != null) {
            printTree(node.getRight(), level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("    "); // Four spaces per level
            }
            System.out.println(node.getKey());
            printTree(node.getLeft(), level + 1);
        }
        else {
            for (int i = 0; i < level; i++) {
                System.out.print("    "); // Four spaces per level
            }
            System.out.println("null");
        }
    }

}
