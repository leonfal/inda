# A Study of Quicksort

Leon Fällman
2021-03-17
INDA Group Amanda
D-21

## Characteristics and Complexity

Quicksort is a widely known and used sorting algorithm. Most famous is of its name: it is quick. It partitions an array into two subarrays, and partitions those into new subarrays and so on. Until every partition is sorted.
Its runtime complexity is nlog(n) which for a sorting algorithm is very good compared to others that have far more in time complexity. Its characteristics is mostly its partition algorithm, where it sorts sub arrays recursively.

## Variations of Quicksort

The variations of Quicksort was implemented and tested with the abstract test class IntSorterTest. The different variations performed differently, which will be noted in later section of this report. The different variations of the algorithm included quicksort with:

    1. Fixed pivot
    2. Random pivot
    3. Fixed pivot with insertionsort
    4. Random pivot with insertionsort

Note that apart from the partition algorithm and some extra code for the insertionsort, these implementations look very similar. That is why this report will describe more thorough the partition algorithms rather than discussing the trivial helper methods.

### Fixed Pivot

This implementation uses a version of Hoare's partitioning algorithm to perform the sort. As mentioned before is quicksort all about the partioning of the array in a recursive way. This partion algorithm is fairly easy to understand hence it is very simple. It chooses a fixed pivot (the lowest element in this case), and have two pointers i and j. The left pointer i moves to the right whenever the element it's pointing to is less than or equal to the pivot. Stops when that is no longer true or it has reached the highest index. The right pointer j moves to the left as long as the element it's pointing to is greater than the pivot, stops when the pointer is pointing to the lowest index or the condition is no longer true.

```java
    // partition
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
```

### Random Pivot

This implementation uses the Lumuto's partition algorithm. It as well chooses a random pivot but for easier partitioning that random pivot is placed at a fixed postion (highes index in this case) each partition. The Lumuto's algorithm uses a well two pointers, i and j. However in this algorithm the pointer i starts at the first - 1 element, that means it doesn't point at anything in the beginning. Pointer j moves through the whole array from low to high and whenever the current element j is pointing to is less than the pivot element i increments by 1 and the i<sup>th</sup> element is swapped with the j<sup>th</sup> element. This process continues throughout the iteration of j moving from low to high. When the for loop stops the algorithm performs one last swap the pivot element with the next i<sup>th</sup> element (i+1).

```java
    // partition
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
```

### Fixed Pivot Insertion

This implementation uses a partition algorithm similar to the one used in **V1: Fixed Pivot**. However instead of nested while loops, this nests a while loop inside a do-while loop. This algorithm also is the purest Hoare's version, with the highest efficiency. This is because it performs the least swaps and this is possible because of the do-while loop.

```java
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
```

The special addition in this version of Quicksort is the insertionsort. The only difference from not using the insertionsort is an if statement with the call to the method. As seen down below the insertionsort kicks in when the array length is less than or equal to 15. This number 15 is a number that is widely discussed and argued if it is the best number as a cut-off. Some sources says any number between 10 and 20 is fine, so for the sake of simplicity this implementation uses 15 as the cut-off.

```java
      int size = (highIndex + 1) - lowIndex;
      if (lowIndex < highIndex) {

        if (size <= 15) {
          insertionsort(array, lowIndex, highIndex);
        }
        else {
          int pivot = partition(array, lowIndex, highIndex);
          quicksort(array, lowIndex, pivot); // left quicksort (pivot instead of pivot -1 because of the hoars implementation)
          quicksort(array, pivot + 1, highIndex); // right quicksort
```

### Random Pivot Insertion

The difference of this implementation than the previous one is that it chooses a random pivot and places it at a fixed position (highest index) each partition. However for the sake of learning other partitioning algorithms, a new one is implemented in this version. In the results later on, one may observe that this partitioning algorithm doesn't perform well at all. It is very similar to the one used in **V1: Fixed Pivot**. The difference is where the while loops conditions differ. In this implementation, no if statements are used, but changed conditions for the loop. Other than the partition it is very similar to the **V3** (the one previously discussed).

```java
    int left = lowIndex;
    int right = highIndex;
    int randomIndex = new Random().nextInt(highIndex - lowIndex) + lowIndex;
    int pivot = array[randomIndex];
    swap(array, randomIndex, highIndex);

    while (left < right) {
      while (array[left] <= pivot && left < right) {
        left++;
      }
      while (array[right] >= pivot && left < right) {
        right--;
      }
      swap(array, left, right);
    }
    swap(array, left, highIndex);
    return left;
```

## Methodology

The tests performed is of same problem size for each version of quicksort. For InsertionSort some larger problem sizes are excluded because the runtime was too long. The test data used is constructed from the Data class which constructs an array of ints in a preferred order. For each test the order of elements in the array differed. In Test 1 a random order is used. Test 2 uses an ascending order. Test 3 uses a descending order and Test 4 uses all equal elements (only 1s in this case). For each test the algorithms used are:

- Insertion sort
- Quicksort Fixed Pivot (V1)
- Quicksort Random Pivot (V2)
- Quicksort Fixed Pivot with Insertion sort (V3)
- Quicksort Random Pivot with Insertion sort (V4)

## Results

    The results for the different versions of the quicksort algorithms are shown down below.

    - V1: Fixed pivot
    - V2: Random pivot
    - V3: Fixed pivot with insertion sort
    - V4: Random pivot with insertion sort

| Test 1: Random Data |               |            |            |          |               |               |
| ------------------- | ------------- | ---------- | ---------- | -------- | ------------- | ------------- |
| Problem Size        | InsertionSort | Qs V1      | Qs V2      | Qs V3    | Qs V4         | Arrays.sort † |
| 100                 | 74582         | 77232      | 204475     | 50610    | 121825        | 71687         |
| 1000                | 2354247       | 704328     | 1732512    | 325758   | 607978        | 317521        |
| 10000               | 18049522      | 3174735    | 3372855    | 1242131  | 3902807       | 1871797       |
| 100000              | 887002220     | 56031445   | 34936916   | 9074925  | 120221418     | 7903618       |
| 1000000             | too long      | 5673056340 | 1718186211 | 57926382 | stackOverflow | 40308033      |

| Test 2: Sorted Data |               |            |            |          |               |               |
| ------------------- | ------------- | ---------- | ---------- | -------- | ------------- | ------------- |
| Problem Size        | InsertionSort | Qs V1      | Qs V2      | Qs V3    | Qs V4         | Arrays.sort † |
| 100                 | 12909         | 83174      | 149382     | 22565    | 85951         | 41512         |
| 1000                | 43566         | 1104407    | 1444626    | 144939   | 314670        | 25774         |
| 10000               | 376729        | 2654187    | 3195918    | 921316   | 10769750      | 202236        |
| 100000              | 2244766       | 87160569   | 33987147   | 5876584  | 95892003      | 1950844       |
| 1000000             | 4917367       | 7481691004 | 1952942665 | 27466991 | StackOverflow | 19099229      |

| Test 3: Reversed Data |               |               |            |          |               |               |
| --------------------- | ------------- | ------------- | ---------- | -------- | ------------- | ------------- |
| Problem Size          | InsertionSort | Qs V1         | Qs V2      | Qs V3    | Qs V4         | Arrays.sort † |
| 100                   | 124189        | 132625        | 241690     | 24538    | 121869        | 50802         |
| 1000                  | 3368484       | 1269723       | 1590039    | 213828   | 493098        | 37291         |
| 10000                 | 37498011      | 32088768      | 5140093    | 1148338  | 4227169       | 309831        |
| 100000                | 1760466242    | StackOverflow | 51844404   | 5813359  | 114856537     | 2968715       |
| 1000000               | too long      | StackOverflow | 1707575448 | 53957080 | StackOverflow | 30363041      |

| Test 4: Equal Data |               |               |               |          |               |               |
| ------------------ | ------------- | ------------- | ------------- | -------- | ------------- | ------------- |
| Problem Size       | InsertionSort | Qs V1         | Qs V2         | Qs V3    | Qs V4         | Arrays.sort † |
| 100                | 17533         | 106169        | 243508        | 45683    | 130355        | 11604         |
| 1000               | 48703         | 2009543       | 12485017      | 461009   | 4199962       | 23479         |
| 10000              | 331347        | 46592345      | StackOverflow | 6050336  | StackOverflow | 194512        |
| 100000             | 2429235       | StackOverflow | StackOverflow | 4781297  | StackOverflow | 1958531       |
| 1000000            | 5988385       | StackOverflow | StackOverflow | 45090811 | StackOverflow | 19735205      |

![](Test%201_%20logarithmic%20comparison.png)
![](Test%202_%20logarithmic%20comparison.png)
![](Test%203_%20logarithmic%20comparison.png)
![](Test%204_%20logarithmic%20comparison.png)

## Discussion

Note that the diagrams shows a logarithmic comparison between the implementations. When the bar is not present is because the test took too long to perform (or a StackOverflow error was thrown)

The general findings of this experiment was that the version with Fixed Pivot with Insertion performed the best compared to the other implementations. One may wonder why the fixed pivot insertion performed better than the random pivot insertion. It was a surprise to me as well however after som deep work on how a partition algorithm is most efficient it clarified. The fixed pivot insertion (V3) uses a much more efficient partition than the random pivot insertion (V4) uses. Because it is more efficient it takes much lesser time to sort and therefore **this** implementation was the best.

Further surprises was that the V2 performed only better in the larger arrays. At first thought I expected it to perform better than V1 in every single scenario. This may be because of the partition algorithm or simply bad rng.

What met my expectations was that the algorithm V3 was to be better performing than V1 and V2. That is because it uses further optimization with insertion sort which V1 and V2 does not.

The one closest to Arrays.sort was the V3: Fixed Pivot with InsertionSort (green bar) and for some tests it differed. For example in test 4 and test 2 where the array was either already sorted or an array of all equal elements insertion sort performed very similar to Arrays.sort.
