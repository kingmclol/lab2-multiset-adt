public class Tree {
    public void delete(int item) {
        root = delete(root, item);
    }

    public boolean search(int item) {
        return search(root, item);
    }

    private boolean search(Node root, int item) {
        if (root == null) return false;
        if (item < root.value) return search(root.left, item);
        else if (item > root.value) return search(root.right, item);
        else return true;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int count(int item) {
        return count(root, item);
    }

    private int count(Node root, int item) {
        if (root == null) return 0;
        if (item < root.value) return count(root.left, item);
        else if (item > root.value) return count(root.right, item);
        else return root.count;
    }

    public int size() {
        return size(root);
    }

    private int size(Node root) {
        if (root == null) return 0;
        return root.count + size(root.left) + size(root.right);
    }

    // We recommend attempting this class last, as it hasn't been scaffolded for your team.
    // Even if your team doesn't have time to implement this class, it is a useful exercise
    // to think about how you might split up the work to get the Tree and TreeMultiSet
    // implemented.
    private static class Node {
        int value, count;
        Node left, right;

        Node(int value) {
            this.value = value;
            this.count = 1;
        }
    }

    private Node root;

    public void insert(int item) {
        root = insert(root, item);
    }

    private Node insert(Node node, int item) {
        if (node == null) return new Node(item);
        if (item < node.value) node.left = insert(node.left, item);
        else if (item > node.value) node.right = insert(node.right, item);
        else node.count++;
        return node;
    }

    Node delete(Node root, int item) {
        if (root == null) return root;
        if (item < root.value) root.left = delete(root.left, item);
        else if (item > root.value) root.right = delete(root.right, item);
        else {
            if (root.count > 1) {
                root.count--;
                return root;
            }
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            root.value = Math.toIntExact(minValue(root.right));
            root.count = 1;
            root.right = delete(root.right, root.value);
        }
        return root;
    }

    private long minValue(Node right) {
        long minv = right.value;
        while (right.left != null) {
            minv = right.left.value;
            right = right.left;
        }
        return minv;
    }
}
