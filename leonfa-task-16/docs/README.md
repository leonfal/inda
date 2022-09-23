## Task 2

| Operation        | Sorted Array | Unsorted Singly Linked List | Hashtable (average) | Hashtable (worst) |
| :--------------- | :----------- | :-------------------------- | :------------------ | :---------------- |
| Search for key X | O(1)         | O(n)                        | O(1)                | O(n)              |
| Insert X         | O(1)         | O(1)                        | O(1)                | O(n)              |
| Remove X         | O(1)         | O(1)                        | O(1)                | O(n)              |

### Motivation

- Sorted Array
  - Search for key O(1) because it is sorted.
  - Insert is O(1) because we can add on an arbitrary index.
  - REmove is O(1) becuase we can remove at an arbitrary index.
- Unsorted Singly Linked List
  - Search for key has O(n) time complexity because we have to traverse all elements in worst case.
  - Insert and remove has a constant time complexity O(1) if we know the location, ie first or last. If not it needs to iterate through the list which takes O(n) time.
- Hashtable (average)
  - Calculated on Amortized time complexity the hash tables **Average** time complexity is O(1) for the three operations.
- Hashtable (worst)
  - The worst case scenario is O(n) for all operations. This will happen if all elements hash to the same bucket. For example if the hashtable only consist of 1 bucket, then the time complexity will for sure be O(n).

## Task 3

1. What is the initial capacity of an `ArrayList`'s internal array?
   - The initial capacity is 10.
2. Starting with the `add(E e)` method (ln:442), find the line number for the conditional statement used to determine that the internal array needs to grow.
   - On ln:443 the method call to the **ensureCapacityInternal** ensures the internal array to grow. The **ensureCapacityInternal** method (ln:207) is declared here.
3. Looking at the `grow(int minCapacity)` method (ln:237), which operator is used to decide the new size of the internal array?
   - The >> operator is used which essentially is "half of". >> = 0.5\*.
4. What is the scaling factor for growth of the internal array?
   - The scaling factor for growth is **1.5**. because it's int newCapacity = oldCapacity + (oldCapcity >> 1) is the same as int newCapacity = oldCapacity + 0.5\*oldCapacity.
5. If 20 elements had been added to an empty ArrayList, what would the size of the internal array be?
   - It will have the size = 22. Because the 11th element will increase the size to 15 (scaling factor 1.5). The 16th element will increase size to 22 (15 + 15/2 = 22 (int)).
6. What is the worst, average, and best-case time complexity of the `add(E e)`
   method of `Arraylist`?
   - The best case is if we have enough memory in the internal array, that will lead to O(1) time complexity (adding to the very last index). In worst case, when the size has to grow, the time-complexity to add n elements will take O(n) time. In the average case the time comp. is O(1) because it has to grow dynamically, although the bigger the array the lesser times it has to grow, so in general it does need to resize so often.
