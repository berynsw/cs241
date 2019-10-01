/*
	BinarySearchTree.java
	
	Demonstration of instance methods to implement BinarySearchTree.
	
	Counts the number of occurrences of words from a text file
	specified on the command line
	
*/


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Represents a BinarySearchTree.
 * Finished on 11/10/2018 by Drew Gawlinski and Beryn Staub-Waldenberg.
 */
public class BinarySearchTree {

    // inner class for tree nodes
    private class TreeNode {

        String data;    // the word stored at this node
        int count;        // occurrence count of this word
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        int height;

        public TreeNode(String data, TreeNode parent) {
            this.data = data;
            this.count = 1;
            this.left = null;
            this.right = null;
            this.parent = parent;
        }

        /**
         * Update the height of this node.
         */
        public void updateHeight() {
            this.height = Math.max(height(left), height(right)) + 1;
        }

        @Override
        public String toString() {
            return nodeToString(this) + ", " + count + ", " + nodeToString(parent) + ", " + nodeToString(left)
                    + ", " + nodeToString(right) + ", " + height;
        }
    }

    // -----------end of inner class TreeNode---------------
    private static String nodeToString(TreeNode node) {
        return node != null && node.data != null ? node.data : "*";
    }

    /**
     * Check the height of a TreeNode, which is potentially null.
     * @param node The node to check the height of.
     * @return nodeHeight
     */
    private static int checkNodeHeight(TreeNode node) {
        return node != null ? node.height : -1;
    }

    // the root of the BinarySearchTree
    private TreeNode root = null;

    /**
     * Insert a word, or increment an existing word count.
     * @param tree The current root node.
     * @param word The word to insert or increment.
     * @return newTreeRoot
     */
    private TreeNode insert(TreeNode tree, String word) {
        TreeNode parent = null;
        TreeNode current = tree;

        // find location for this word in the tree
        while (current != null) {
            parent = current;

            int compareDiff = word.compareToIgnoreCase(current.data);
            if (compareDiff == 0) { // Word is already present, increment its count.
                current.count++;
                return tree;
            } else if (compareDiff < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        // this word is not in the tree so create a new tree node
        TreeNode newNode = new TreeNode(word, parent);

        // set the parent's left or right to the new node
        if (parent == null) { // New tree.
            return newNode;
        } else if (word.compareToIgnoreCase(parent.data) < 0) { // Otherwise, add the new node to the tree.
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        return rebalance(newNode);
    }

    /**
     * Insert an element into this tree.
     * @param word The word to insert.
     */
    public void insert(String word) {
        root = insert(root, word);
    }

    /**
     * Rebalance a tree, with a new node.
     * @param newNode The new node.
     */
    public TreeNode rebalance(TreeNode newNode) {
        newNode.updateHeight();

        int heightDiff = height(newNode.left) - height(newNode.right);
        if (heightDiff == 2) {
            if (height(newNode.left.left) >= height(newNode.left.right)) {
                newNode = rotateRight(newNode);
            } else {
                newNode = rotateLeftRight(newNode);
            }
        } else if (heightDiff == -2) {
            if (height(newNode.right.right) >= height(newNode.right.left)) {
                newNode = rotateLeft(newNode);
            } else {
                newNode = rotateRightLeft(newNode);
            }
        }

        return newNode.parent != null ? rebalance(newNode.parent) : newNode;
    }

    /**
     * Gets the height of a TreeNode. If it does not exist, it returns -1.
     * @param tree The node to get the height of.
     * @return nodeHeight
     */
    public int height(TreeNode tree) {
        return checkNodeHeight(tree);
    }

    /**
     * Rotate a left-heavy node right.
     * @param newChild The node to become the child.
     * @return parent
     */
    public TreeNode rotateRight(TreeNode newChild) {
        TreeNode newParent = newChild.left;
        newParent.parent = newChild.parent;

        newChild.left = newParent.right;

        if (newChild.left != null)
            newChild.left.parent = newChild;

        newParent.right = newChild;
        return onRotate(newChild, newParent);
    }

    /**
     * Rotate a left-heavy node right.
     * @param newChild The node which should be the child after rotation.
     * @return parent
     */
    public TreeNode rotateLeft(TreeNode newChild) {
        TreeNode newParent = newChild.right;
        newParent.parent = newChild.parent;

        newChild.right = newParent.left;
        if (newChild.right != null)
            newChild.right.parent = newChild;

        newParent.left = newChild;
        return onRotate(newChild, newParent);
    }

    private TreeNode onRotate(TreeNode newChild, TreeNode newParent) {
        newChild.parent = newParent;

        if (newParent.parent != null) {
            if (newParent.parent.right == newChild) {
                newParent.parent.right = newParent;
            } else {
                newParent.parent.left = newParent;
            }
        }

        newChild.updateHeight();
        newParent.updateHeight();
        return newParent;
    }

    /**
     * Rotate a heavy node left then right.
     * @param tree The node to become the parent.
     * @return newParentNode
     */
    public TreeNode rotateLeftRight(TreeNode tree) {
        tree.left = rotateLeft(tree.left);
        return rotateRight(tree);
    }

    /**
     * Rotate a heavy node right then left.
     * @param tree The node to become the parent.
     * @return newParentNode
     */
    public TreeNode rotateRightLeft(TreeNode tree) {
        tree.right = rotateRight(tree.right);
        return rotateLeft(tree);
    }

    /**
     * Dump tree information to the console.
     * Format: "word, count, parentWord, leftChild, rightChild, height"
     * When null is output, * is written instead.
     * Example: "pig, 1, walrus, *, *, 0"
     */
    public void dump() {
        dumpNode(root);
    }

    /**
     * Recursively dump tree data to the console.
     * @param node The root node to dump.
     */
    private static void dumpNode(TreeNode node) {
        if (node == null)
            return;

        System.out.println(node.toString());
        dumpNode(node.left);
        dumpNode(node.right);
    }

    // test the BinarySearchTree
    public static void main(String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("ALL")) {
            main(new String[]{"file.txt"});
            main(new String[]{"left_test.txt"});
            main(new String[]{"right_test.txt"});
            main(new String[]{"leftRight_test.txt"});
            main(new String[]{"rightLeft_test.txt"});
            main(new String[]{"number.txt"});
            return;
        }

        // should be able to read input from a file
        // specified in the first command-line argument
        BufferedReader in;
        System.out.println("Usage: java BinarySearchTree file.txt");
        try {
            in = new BufferedReader(new FileReader(args[0]));
        } catch (Exception e) {
            System.out.println("Cannot open file " + args[0]);
            return;
        }

        BinarySearchTree tree = new BinarySearchTree();
        String delimiters = " \t,;.!@#$%^&*()_+-=<>?[]{}:\"'";

        // read each line of the file
        try {
            String line = in.readLine();
            while (line != null) {

                // get each word from the line add it to the tree
                StringTokenizer tokenizer = new StringTokenizer(line, delimiters);
                while (tokenizer.hasMoreTokens())
                    tree.insert(tokenizer.nextToken());

                line = in.readLine();
            }
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }

        tree.dump();
    }

}
