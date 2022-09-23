import java.util.Arrays;
import java.util.Random;

public class QuicksortRandomPivotInsertion implements IntSorter{

  @Override
  public void sort(int[] v) {
    if (v == null || v.length == 0) {
      return;
    }
    quicksort(v, 0, v.length - 1);
  }

  /**
   * Sorts an array preferably of smaller size
   * @param array to sort
   * @param start first index of array
   * @param end last index of array
   */
  public void insertionsort(int[] array, int start, int end)
  {
      for (int x = start + 1; x <= end; x++)
      {
          int current = array[x];
          int j = x - 1;
          while (j >= 0 && array[j] > current)
          {
            array[j + 1] = array[j];
            j--;
          }
          array[j + 1] = current;
        }
    }

    /**
     * Recusively sorts an array, smaller with insertionsort and larger arrays with quicksort
     * @param array to sort
     * @param lowIndex first index of array
     * @param highIndex last index of array
     */
    public void quicksort(int[] array, int lowIndex, int highIndex) {
      int size = (highIndex + 1) - lowIndex;
      if (lowIndex < highIndex) {

        if (size <= 65) {
          insertionsort(array, lowIndex, highIndex);
        }
        else {
          int pivot = partition(array, lowIndex, highIndex);
          quicksort(array, lowIndex, pivot - 1); // left quicksort
          quicksort(array, pivot + 1, highIndex); // right quicksort
      }
    }
  }

  // another take on Hoar's algorithm
  private int partition(int[] array, int lowIndex, int highIndex) {
    int left = lowIndex;
    int right = highIndex;
    int randomIndex = new Random().nextInt(highIndex - lowIndex) + lowIndex;
    int pivot = array[randomIndex];
    swap(array, randomIndex, highIndex);

    while (left < right) {
      // left moves from left to right in the array, stopping if the left is greater than the pivot or the left and right pointers are equal
      while (array[left] <= pivot && left < right) {
        left++;
      }
      // right moves from right to left in the array, stopping if the right is less than the pivot or the left and right pointers are equal
      while (array[right] >= pivot && left < right) {
        right--;
      }
      swap(array, left, right); // swapping the elements stored at left and right pointer indexes (partitions the array)
    }
    swap(array, left, highIndex);
    return left; // last swap, where the left = right we set that as our pivot and swaps the elements
  }

  private void swap(int[] array, int index1, int index2) {
    int temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
  }

}
