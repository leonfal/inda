import java.util.NoSuchElementException;
import java.util.EmptyStackException;
/**
 * A singly linked list.
 *
 * @author Leon Fällman
 * @version 2022-02-01
 */
public class LinkedList<T> implements Stack<T> {
    private ListElement<T> first;   // First element in list.
    private ListElement<T> last;    // Last element in list.
    private int size;               // Number of elements in list.

    /**
     * A list element.
     */
    private static class ListElement<T>{
        public T data;
        public ListElement<T> next;

        public ListElement(T data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Creates an empty list.
     */
    public LinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Inserts the given element at the beginning of this list.
     *
     * @param element An element to insert into the list.
     */
    public void addFirst(T element) {
        ListElement<T> newElement = new ListElement<>(element);
        newElement.next = first;
        first = newElement;

        if (size == 0) {
            last = newElement;
        }
        size++;
    }

    /**
     * Inserts the given element at the end of this list.
     *
     * @param element An element to insert into the list.
     */
    public void addLast(T element) {
        ListElement<T> newElement = new ListElement<>(element);
        ListElement<T> prevElement = last;
        if (isEmpty()) {
            first = newElement;
        }
        else {
            prevElement.next = newElement;
        }
        last = newElement;
        size++;
    }

    /**
     * @return The head of the list.
     * @throws NoSuchElementException if the list is empty.
     */
    public T getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return first.data;
    }

    /**
     * @return The tail of the list.
     * @throws NoSuchElementException if the list is empty.
     */
    public T getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return last.data;
    }

    /**
     * Returns an element from a specified index.
     *
     * @param index A list index.
     * @return The element at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int i = 0;
        for (ListElement<T> element = first; i < size; element = element.next) {
            if (index == i) {
                return element.data;
            }
            i++;
        }
        return null;
    }

    /**
     * Removes the first element from the list.
     *
     * @return The removed element.
     * @throws NoSuchElementException if the list is empty.
     */
    public T removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        ListElement<T> newFirst = first;
        first = first.next;
        size--;

        if (size == 0) {
            last = null;
        }
        return newFirst.data;
    }

    /**
     * Removes all of the elements from the list.
     */
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Creates a string representation of this list. The string
     * representation consists of a list of the elements enclosed in
     * square brackets ("[]"). Adjacent elements are separated by the
     * characters ", " (comma and space). Elements are converted to
     * strings by the method toString() inherited from Object.
     *
     * Examples:
     *  "[1, 4, 2, 3, 44]"
     *  "[]"
     *
     * @return A string representing the list.
     */
    public String toString() {

      String string = "[";
      for (ListElement<T> element = first; element != null; element = element.next) {
        string = string + element.data.toString();
        if (element.next != null) {
          string = string + ", ";
        }
      }
      string = string + "]";
      return string;
    }
/**
   * Inserts the given element at the beginning of this list.
   *
   * @param element An element to insert into the list.
   */
  public void push(T elem) {
    ListElement<T> newElement = new ListElement<>(elem);
    newElement.next = first;
    first = newElement;

    if (size == 0) {
      first = newElement;
    }
    size++;
  }

  /**
   * @return The head of the list.
   * @throws EmptyStackException if the list is empty.
   */
  public T top() {
    if (size == 0) {
      throw new EmptyStackException();
    }
    return first.data;
  }

  /**
   * Removes the first element from the list.
   *
   * @return The removed element.
   * @throws EmptyStackException if the list is empty.
   */
  public T pop() {
    if (size == 0) {
      throw new EmptyStackException();
    }
    ListElement<T> newFirst = first;
    first = newFirst.next;
    size--;

    if (size == 0) {
      last = null;
    }
    return newFirst.data;
  }

  /**
   * @return The number of elements in the list.
   */
  public int size() {
    return size;
  }

  /**
   * Note that by definition, the list is empty if both first and last
   * are null, regardless of what value the size field holds (it should
   * be 0, otherwise something is wrong).
   *
   * @return <code>true</code> if this list contains no elements.
   */
  public boolean isEmpty() {
    return first == null && last == null;
  }
}
