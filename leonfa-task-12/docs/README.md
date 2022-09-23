### Exercise 1

To develop a sense of the relationship between problem size and an algorithm's
order of growth, complete the table of running times below (we shall
presume that time is the amount of nanoseconds).

- Logarithmic = log<sub>2</sub>(n)
- Linear = n
- Linearithmic = n log n
- Quadratic = n<sup>2</sup>
- Cubic = n<sup>3</sup>
- Exponential = 2<sup>n</sup>
- Factorial = n!

| Size / complexity | log n | n    | n log n | n<sup>2</sup> | n<sup>3</sup> | 2<sup>n</sup> | n!       |
| ----------------- | ----- | ---- | ------- | ------------- | ------------- | ------------- | -------- |
| 1                 | 0     | 1    | 0       | 1             | 1             | 1             | 1        |
| 10                | 3.322 | 10   | 33.22   | 100           | 1000          | 1024          | 3.6e6    |
| 100               | 6.664 | 100  | 666.4   | 1e4           | 1e6           | 1.3e30        | 9.3e157  |
| 1000              | 9.966 | 1000 | 9966    | 1e6           | 1e9           | 1e301         | 4e2567   |
| 10000             | 13.29 | 1e4  | 1.3e4   | 1e8           | 1e12          | 2e3010        | &#x221e; |
| 100000            | 16.61 | 1e5  | 1.7e5   | 1e10          | 1e15          | &#x221e;      | &#x221e; |
| 1000000           | 19.93 | 1e6  | 2e6     | 1e12          | 1e18          | &#x221e;      | &#x221e; |

### Exercise 2

Let T(n) be the time in nanoseconds (1e-9 seconds) to solve a given problem of
size n with a certain algorithm. As before in [Exercise 1](#exercise-1), assume
that the computer performs **1 operation per nanosecond**.

For each function T(n) and for each time t in the table, give the largest value
of n, for which the algorithm can solve the problem in time t. For example, if
we have an algorithm with linear complexity n, and one hour of time, then we can
simply count the number of nanoseconds to discover the problem size: 60 minutes _ 60 seconds _ 1e9 nanoseconds = 3.6e12.

Use [e-notation](https://en.wikipedia.org/wiki/Scientific_notation#E-notation)
for large values (e.g. `3e9` instead of `3*10^9` or `3000000000`)

Absolutely humongous numbers can be rounded off to infinity. Copy the html code
from the 1 hour cell of `logn` if you want the actual symbol, or just write
`inf`!

| T(n)          | 1 second | 1 minute | 1 hour   | 1 day    | 1 year   |
| ------------- | -------- | -------- | -------- | -------- | -------- |
| log n         | &#x221e; | &#x221e; | &#x221e; | &#x221e; | &#x221e; |
| n             | 1e9      | 6e10     | 3.6e12   | 8.6e13   | 3.2e16   |
| n log n       | 4e7      | 1.9e9    | 9.8e10   | 2.1e12   | 6.4e14   |
| n<sup>2</sup> | 3.1e4    | 2.4e5    | 1.8e6    | 9.3e6    | 1.8e8    |
| n<sup>3</sup> | 1000     | 3915     | 15326    | 4.4e4    | 3.2e5    |
| 2<sup>n</sup> | 29.9     | 35.80    | 41       | 46.3     | 54.8     |
| n!            | 12.29    | 13.86    | 15       | 16       | 18       |

> **Assistant's note:** Solving for n in an equation on the form `n*log(n) = x`
> is not possible arithmetically. For the mathematically inclined, the solution
> is approximated by `n = e^W(x)`, where `W` is the
> [Lambert W function](https://en.wikipedia.org/wiki/Lambert_W_function). For
> the _less_ mathematically inclined (like this humble assistant), the
> solution is given by typing `n*log2(n) = x, solve for n` into
> [Wolfram Alpha](https://www.wolframalpha.com) :D.

### Exercise 3

Arrange the functions in the following list in ascending order based on their
rate of growth. That is, the function f(n) should come before the function g(n)
in the list if f(n) is O(g(n)).

f4(n) = n + 100

f3(n) = n log n

f1(n) = n<sup>1.5</sup>

f5(n) = 2<sup>n</sup>

f2(n) = 10<sup>n</sup>

Which of the following statements are true? Justify your answer.

n (n + 1) / 2 = O(n<sup>3</sup>) <-- True for c = 1 and n<sub>0</sub> = 1 when satisfying the equation 1/2(n<sup>2</sup>+n) <= cn<sup>3</sup>

n (n + 1) / 2 = O(n<sup>2</sup>) <-- True for c = 1 and n<sub>0</sub> = 1 when satisfying the equation 1/2(n<sup>2</sup>+n) <= cn<sup>2</sup>

n (n + 1) / 2 = Θ(n<sup>3</sup>) <-- False, by definition if our worst case is n<sup>2</sup> then our "average case" cannot be n<sup>3</sup>

n (n + 1) / 2 = Ω(n) <-- True the equation is true for c = 1 and n<sub>0</sub> = 1 which satisfies the equation 1/2(n<sup>2</sup>+n) >= cn

### Exercise 4

Give a tight O-estimation, as a function of n, of the worst case time
complexity of the following five loops:

```
Algorithm Loop1(n):
   a = 0
   for i = 1 to n
      a += i
// worst case would be O(n)

Algorithm Loop2(n):
   b = 1
   for i = 1 to 4n
      b++
// worst case would be O(n)

Algorithm Loop3(n):
   c = 1
   for i = 1 to n^2
      c--
// worst case would be O(n^2)

Algorithm Loop4(n):
   d = 5
   for i = 1 to 3n
      for j = 1 to i
         d = d + j
// worst case would be O(n^2)

Algorithm Loop5(n):
   e = 5
   for i = 1 to n^2
      for j = 1 to i
         e = e + j
// worst case would be O(n^4)
```

### Exercise 5

Explain why (n+1)<sup>3</sup> is O(n<sup>3</sup>). Use the following
definition: f(n) is O(g(n)) if there exists positive constants c and
n<sub>0</sub> such that f(n) &le; c &times; g(n) for all n &ge; n<sub>0</sub>.

Because if c = 4 and n<sub>0</sub> = 2 the inequality is true for all n >= n<sub>0</sub>.

### Exercise 6.1

The following algorithm reverses a collection. Answer the following:

- What is the basic operation for this algorithm?
- Describe the time complexity of this algorithm

```python
Reverse (A):
    # Input: an array A storing n elements.
    # Output: the same array with the elements in reversed order.
    for i = 1 to n-1
       x = A[i]
       for j = i down to 1
           A[j] = A[j-1] // the basic operation (=the operation most commonly executed)
       A[0] = x
       // big-O a.k.a the time complexity (worst case) would be O(n^2).
```

### Exercise 6.2

Design a linear time O(n) algorithm to reverse a collection and implement two
versions in Java, the first with arrays and the second with lists.

Your implementation should count the number of basic operations to ensure that
the complexity is O(n) for a given collection of size n.

Please implement the two methods in the code skeleton in
[`src/Reverse.java`](src/Reverse.java) (and don't forget to remove the
exceptions that are currently thrown!). See the [Testing](#testing) section for
instructions on how to test your implementations.

> **Assistant's requirement:** Both versions of `reversed` should return a
> reversed _copy_ of the argument, and are _not_ allowed to mutate the
> argument.

### Exercise 7

Insertion Sort and Selection Sort have similar worst case runtime complexity
O(n<sup>2</sup>). Explain:

- How they differ in best case (a sorted collection) and mostly sorted case in
  terms of the runtime complexity of each algorithm, and
- Which should be preferred as a sorting algorithm with justification.

1. They differ in best case (big - &Omega;) because selection sorts best case would be still &Omega;(n^2) however insertion sort would have a best case of &Omega;(n). If an array is already sorted, selection will still iterate n^2 times, meanwhile insertion will only iterate n times.

2. The insertion sort would be preferred because it is stable. It has a better case, in most cases. The array is in most cases partially sorted, then insertion is better. If the array would be reversed, then selection would be better - but that is very uncommon for an arbitrary array to be.

### Testing

For this week's testing, you have been provided with a test skeleton with
implementations of tests for `Reverse.reversed(int[])`. Read through
the tests and make sure you understand what they do, and then implement
all of the tests for `Reverse.reversed(List<Integer>)`. The test class
is located in [`src/ReverseTest.java`](src/ReverseTest.java).
As usual, all of the tests that you need to implement hava a _fail_ statement
in them that looks like this:

```java
fail("Not implemented!");
```

Remove these and implement the tests!
