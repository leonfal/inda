interface Stack<T> {

  /**
   * Adds the element to the top of the stack.
   * @param elem the element to add,
   */
  void push(T elem);

  /**
   * Removes the top element of the stack.
   * @return the removed element.
   */
  T pop();

  /**
   * Only returns the top element.
   * @return the top element.
   */
  T top();

  /**
   * Method to check size of stack.
   * @return the number of elements in the stack.
   */
  int size();

  /**
   * Check if the stack is empty.
   * @return true if the stack is empty, false if not.
   */
  boolean isEmpty();
}
