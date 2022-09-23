import java.util.ArrayList;
import java.util.List;
/**
 * A class for reversing List and array types.
 *
 * @author PUT YOUR NAME HERE
 * @version 2017-08-09
 */
public class Reverse {

    /**
     * Return a reversed copy of the argument array.
     * The passed array is NOT mutated.
     *
     * @param array An array.
     * @return A reversed copy of array.
     */
    public int[] reversed(int[] array) {
        int clonedArray[] = array.clone();
        for (int i = 0; i < clonedArray.length; i++) {
            clonedArray[i] = array[clonedArray.length-1 - i];
        }
        return clonedArray;
    }

    /**
     * Return a reversed copy of the argument List.
     * The passed List is NOT mutated.
     *
     * @param list A List.
     * @return A reversed copy of list.
     */
    public List<Integer> reversed(List<Integer> list) {
        List<Integer> clonedList = new ArrayList<>(list);
        for (int i = 0; i < clonedList.size(); i++) {
            clonedList.set(i, list.get(clonedList.size() - 1 - i));
        }
        return clonedList;
    }
}
