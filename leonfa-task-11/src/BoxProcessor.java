import java.util.List;

/**
 * Class to search and sort boxes in respect to their volume
 * @author Leon Fällman
 * @version 2021-12-02
 */
public class BoxProcessor {

  /**
   * Exercise 2
   * Using insertion sort, sorting the box array
   * @param array to sort
   */
  public void sort(Box[] array) {
    for (int i = 1; i < array.length; i++) {
      int j = i;
      while (j > 0 && array[j - 1].compareTo(array[j]) > 0) {
        Box temp = array[j];
        array[j] = array[j - 1];
        array[j - 1] = temp;
        j = j-1;
      }
    }
  }

  /**
   * Exercise 2
   * Using selection sort, sorting the collection
   * @param list to sort
   */
  public void sort(List<Box> list) {

    for (int i = 0; i < list.size(); i++) {

      int pos = i;
      for (int j = i + 1; j < list.size(); j++) {
        if (list.get(j).compareTo(list.get(pos)) < 0) {
          pos = j; // minimum element found
        }
      }
      Box min = list.get(pos);
      list.set(pos, list.get(i));
      list.set(i, min);
    }
  }

  /**
   * Exercise 3
   * Method to search after a box's volume
   * @param array the array to perform the search in
   * @param box the box to search for in respect to volume
   * @return the index of the box with correct volume if found, -1 if not
   */
  public int sequentialSearch(Box[] array, Box box) {
    for (int i = 0; i < array.length; i++) {
      if (array[i].compareTo(box) == 0) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Exercise 3
   * Method to search after a box's volume
   * @param list the list to perform the search in
   * @param box the box to search for in respect to volume
   * @return the index of the box with correct volume if found, -1 if not
   */
  public int sequentialSearch(List<Box> list, Box box) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).compareTo(box) == 0) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Exercise 4 for a box that is comparably equal to a specified box (in volume) Assuming it is already sorted.
   * Method to search
   * @param array the sorted array to search in
   * @param box the box to search for in respect to volume
   * @return the index of the box if found, -1 in not found
   */
  public int binarySearch(Box[] array, Box box) {
    int lowerBound = 0;
    int upperBound = array.length - 1;

    while (lowerBound <= upperBound) {
      int index = (int)((lowerBound + upperBound)/2); // rounds it down.

      if (array[index].compareTo(box) == 0) {
        return index; //box with searched volume found.
      } else if (array[index].compareTo(box) > 0) {
        upperBound = index - 1;
      } else if (array[index].compareTo(box) < 0) {
        lowerBound = index + 1;
      }
    }
    return -1;
  }

  /**
   * Exercise 4
   * Method to search for a Box that is comparably equal to a specified Box
   * (in volume) Assuming it is already sorted.
   * @param list the sorted list to search in
   * @param box the box to search for in respect to volume
   * @return the index of the box if found, -1 if not found
   */
  public int binarySearch(List<Box> list, Box box) {
    int lowerBound = 0;
    int upperBound = list.size() - 1;

    while (lowerBound <= upperBound) {
      int index = (int)((lowerBound + upperBound)/2);

      if (list.get(index).compareTo(box) == 0) {
        return index; //box with searched volume found.
      } else if (list.get(index).compareTo(box) > 0) {
        upperBound = index - 1;
      } else if (list.get(index).compareTo(box) < 0) {
        lowerBound = index + 1;
      }
    }
    return -1;
  }
}
