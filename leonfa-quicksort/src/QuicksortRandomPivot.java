import java.util.Random;

public class QuicksortRandomPivot implements IntSorter{

  /**
   * Sorts an array of integers
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

  // lumutos partition algorithm.
  private int partition(int[] array, int low, int high) {
    int i = low - 1;
    int randomIndex = new Random().nextInt(high - low) + low;
    int pivot = array[randomIndex];
    swap(array, randomIndex, high); // makes it easier to partition

    for (int j = low; j <= high - 1; j++) {
      if (array[j] < pivot) {
        i++;
        swap(array, i, j);
      }
    }
    swap(array, i + 1, high);
    return (i + 1);
  }

  /**
   * Sorts an array of integers using quicksort algorithm
   * @param array the array to sort
   * @param low the first index of array
   * @param high the last index of array
   */
  public void quicksort(int[] array, int low, int high) {
    if (low >= high) {
      return;
    }
    int pivot = partition(array, low, high);

    quicksort(array, low, pivot - 1); // left quicksort
    quicksort(array, pivot + 1, high); // right quicksort
  }
}
