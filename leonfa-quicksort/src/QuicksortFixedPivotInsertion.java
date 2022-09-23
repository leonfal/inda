public class QuicksortFixedPivotInsertion implements IntSorter{

  @Override
  public void sort(int[] v) {
    quicksort(v, 0, v.length-1);
  }

  /**
   * Sorts an array, preferably one of a smaller size
   * @param array the array to sort
   * @param start the index at the start of the array
   * @param end the last index of the array
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
     * quicksorts an arbitrary array of integers, larger with quicksort, smaller with insertion sort
     * @param array the array to sort
     * @param lowIndex the lowest index of the array
     * @param highIndex the highest index of the array
     */
    public void quicksort(int[] array, int lowIndex, int highIndex) {
      int size = (highIndex + 1) - lowIndex;
      if (lowIndex < highIndex) {

        if (size <= 15) {
          insertionsort(array, lowIndex, highIndex);
        }
        else {
          int pivot = partition(array, lowIndex, highIndex);
          quicksort(array, lowIndex, pivot); // left quicksort (pivot instead of pivot -1 because of the hoars implementation)
          quicksort(array, pivot + 1, highIndex); // right quicksort
      }
    }
  }

  //Hoars algorithm
  private int partition(int[] array, int lowIndex, int highIndex) {
    int pivot = array[(highIndex+lowIndex)/2]; // efficiency increase

    int i = lowIndex - 1;
    int j = highIndex + 1;
    while(true)
    {
        do {
            i++;
        } while(array[i] < pivot);

        do {
            j--;
          } while (array[j] > pivot);

        if(i >= j)
            return j;
        swap(array, i, j);
    }
}

  private void swap(int[] array, int index1, int index2) {
    int temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
  }

}
