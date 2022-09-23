import java.lang.Comparable;
import java.lang.Math;

/**
 * Class to create a static (non-dynamic) binary search tree.
 *
 * @author Leon Fällman
 * @version 2022-02-16
 */
public class Tree<T extends Comparable<T>> {
  private Node<T> root;
  private int size;
  LinkedList<Node<T>> queue = new LinkedList<>();

  private static class Node<T> {
    public T key;
    public Node<T> left;
    public Node<T> right;

    /**
     * Constructs a node with two empty children, left and right.
     * @param key the value unique to the node.
     */
    public Node(T key) {
      this.key = key;
      this.left = null;
      this.right = null;
    }
  }

  /**
   * Constructs an empty tree.
   */
  public Tree() {
    root = null;
    size = 0;
  }

  /**
   * Searches through the tree for an element.
   * @param elem the element to search for.
   * @return true if element was found, false otherwise.
   */
  public boolean search(T elem) {
    if (root == null) {
      return false;
    }
    Node<T> currentRoot = root;
    while (currentRoot != null) {

      if (elem.compareTo(currentRoot.key) == 0) {
        return true;
      }
      else if (elem.compareTo(currentRoot.key) > 0) {
        currentRoot = currentRoot.right;
      }
      else if (elem.compareTo(currentRoot.key) < 0) {
        currentRoot = currentRoot.left;
      }

    }
    return false;
  }

  /**
   * Inserts an element in the tree if it is not already present.
   * @param elem the element to insert in the tree.
   * @return true if the element was inserted, false otherwise.
   */
  public boolean insert(T elem) {

    Node<T> newNode = new Node<T>(elem);
    Node<T> currentRoot = root; //copies properties of root.
    Node<T> parent = null; // will be used to compare after loop.


    if (root == null) {
      root = newNode;
      size++;
      return true;
    }

    while (currentRoot != null) {

      parent = currentRoot; // will be the last value before currentRoot comes to null.
      if (newNode.key.compareTo(currentRoot.key) > 0) {
        currentRoot = currentRoot.right;
      }
      else if (newNode.key.compareTo(currentRoot.key) < 0) {
        currentRoot = currentRoot.left;
      }
      else if(newNode.key.compareTo(currentRoot.key) == 0){
        return false; // no dublicates allowed (elem found in tree).
      }
    }

    if (newNode.key.compareTo(parent.key) > 0) {
      parent.right = newNode;
      size++;
    }
    else{
      parent.left = newNode;
      size++;
    }
    return true;
  }

  /**
   * gets the current size of the tree.
   * @return the size.
   */
  public int size() {
    return size;
  }

  /**
   * calculates the height of the tree recursively (height = 2n - 1 if n = # layers)
   * @return the height.
   */
  public int height() {
    if (root == null) {
      return 0;
    }
    else {
      Node<T> node = root;
      return heightFinder(node);
    }
  }

  private int heightFinder(Node<T> node) {
    int left = 0;
    int right = 0;
    if (node == null) {
      return -1;
    }

    left = 1 + heightFinder(node.left);
    right = 1 + heightFinder(node.right);

    return Math.max(left, right);

  }

  /**
   * Calculates how many leaves the tree has. (leaves = nodes with empty children)
   * @return the amount of leaves.
   */
  public int leaves() {
    Node<T> node = root;
    return leavesFinder(node);
  }

  private int leavesFinder(Node<T> node) {
    if (node == null) {
      return 0;
    }
    else if (node.left != null || node.right != null) {
      return leavesFinder(node.left) + leavesFinder(node.right);
    }
    else {
      return 1;
    }
  }

  /**
   * Method to showcase the tree in ascending order in array format as string.
   * @return the string.
   */
  @Override
  public String toString() {
    Node<T> node = root;
    String bst = "[";
    bst += inorder(node);
    if (!inorder(node).isEmpty()) {
      bst = bst.substring(0, bst.length() - 2); // brute force hehe
    }
    bst += "]";
    return bst;
  }

  private String inorder(Node<T> node) {
    String s = "";
    if (node != null) {
      s += inorder(node.left);
      s += node.key.toString() + ", ";
      s += inorder(node.right);
    }
    return s;
  }

  public String toStringbfs() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    Node<T> node = root;

    if (node == null) {
      return "";
    } else {
      queue.addLast(node);
    }

    while (!queue.isEmpty()) {
      queue.addLast(node.left);
      queue.addLast(node.right);
      sb.append(node).append(", ");
      queue.removeFirst();
    }

    sb.append("]");
    return sb.toString();
  }


}
