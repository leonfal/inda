public class QuicksortFixedPivot implements IntSorter {

  /**
   * Sorts an array in ascending order.
   */
  public void sort(int[] v) {
    if (v == null || v.length == 0) {
      return;
    }
    quicksort(v, 0, v.length - 1);
  }

  private void swap(int[] array, int index1, int index2) {
    int temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
  }

  // A version of Hoars algorithm
  private int partition(int[] array, int lowIndex, int highIndex) {
    int i = lowIndex;
    int j = highIndex;
    int pivot = array[lowIndex];

    while (true) {
      while (array[i] <= pivot) {
        if (i == highIndex)
          break;
        i++;
      }

      while (array[j] > pivot) {
        if (j == lowIndex)
          break;
        j--;
      }

      if (i >= j)
        break;
      swap(array, i, j);
    }
    swap(array, lowIndex, j);

    return j;
  }

  /**
   * recusively sorts an array of integers using quicksort algorithm
   * @param array the array to sort
   * @param lowIndex first index of array
   * @param highIndex last index of array
   */
  public void quicksort(int[] array, int lowIndex, int highIndex) {
    if (lowIndex >= highIndex) {
      return;
    }
    int pivot = partition(array, lowIndex, highIndex);
    quicksort(array, lowIndex, pivot - 1); // left quicksort
    quicksort(array, pivot + 1, highIndex); // right quicksort
  }
}
