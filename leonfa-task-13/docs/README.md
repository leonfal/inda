### Inheritance Exercise 1

#### a)

Person p1 = new Student(); is legal because Student is a subclass of Person.

Person p2 = new PhDStudent(); is legal because PhDStudent is a sub-subclass of Person.

PhDStudent phd1 = new Student(); is illegal because Student is a parentclass to PhDStudent.

Teacher t1 = new Person(); is illegal because Person is not a subclass to Teacher, it is a parentclass.

Student s1 = new PhDStudent(); is legal because PhDStudent is a subclass to Student.

#### b)

```java
Person p1 = new Person();
Person p2 = new Person();
PhDStudent phd1 = new PhDStudent();
Teacher t1 = new Teacher();
Student s1 = new Student();
```

s1 = p1; illegal because p1 is a Person which is not subclass to Student (s1).

s1 = p2; Illegal for the same reason as above.

p1 = s1; Legal because s1 is a Student and p1 a Person, Student is a subclass to Person.

t1 = s1; Illegal because Teacher and Student is not correlated.

s1 = phd1; Legal because PhDStudent is a subclass to Student.

phd1 = s1; Illegal because it would have to be the other way around. (as above).

### Inheritance Exercise 3

#### What behaviour happens if you removed the `extends Post` from the class definition of `EventPost` then call `NewsFeed.addPost`? Explain why you think this happens.

It will throw an error because the addPost method is using code and fields of Post.java. If we include (inherit) the Posts code to EventPost then addPost can fetch the Post.java code via EventPost.java.

#### What behaviour happens if you removed `super()` from the constructor of `EventPost` then call `NewsFeed.show`? Explain why you think this happens.

It will again give us an error. This happens because to inherit the Post.java, we must also inherit its constructor, which super allows us to do.

#### What behaviour happens if you removed `super.display()` from the display methods `EventPost`? then call `NewsFeed.show`? Explain why you think this happens.

It will not give an error, but not the output we want. It will not show the author, the number of likes, comments and how many seconds ago the post was created. Basically losing all of the format for the posts.

#### When we have two classes with an inheritance relationship and they have a method with the same signature, what is this called?

Method overriding.

### Induction exercise 2

#### For the first loop:

a)
Correlation between i and the res variable:

i = 0; res = x^1
i = 1; res = x^2
i = 2; res = x^3

the for loop can be rewritten as follow:

```
n
∏ x
i = 0
```

base case: i = 0
gives;

1 = x^0 = 1 which is true

induction step: assume all iterations up to n is true, then we will prove for n+1 as well (x^(n+1));

```
//
n+1           n
∏ x =     x * ∏ x =      { with our induction assumption } =     x * x^n which is equivalent to: x^(n+1)
i = 0         i = 0
//
```

this example now follows the principle of induction.

b) the Big-O describes the worst outcome of an algorithm in terms of time-complexity.
the first example has a worst case time complexity scenario of O(n).

#### For the second loop:

a)
with our previous induction proof we know that the expIterative(x, n) is true for all x of real numbers >= 0.
Base case: for n = 0 we retrun expIterative(x, 0) which is 1, which is also proven for real numbers x and n >= 0.

**Induction**:
Induction assumption: Assume that expRecursice(x, n) gives a correct result for all 0 <= k <= n

Induction step: Want to proove with our assumption that expRecursive(x, k+1) gives a correct result, x^k+1. Method call to expRecursive will return expRecursive(x, (k+1)/2) \* expRecursive(x, (k+2)/2) = {assume k is even} = expRecursive(x, k/2) \* expRecursive(x, (k+2)/2). Because both k/2 and (k+2)/2 is less than n must both expRecursive(x, k/2) and expRecursive(x, (k+2)/2) be correct. Which gives us x^(k/2) \* x^((k+2)/2) = x^k+1

If k is odd. expRecursive(x, (k+1)/2) _ expRecursive(x, (k+2)/2) = {k is odd} = expRecursive(x, (k+1)/2) \* expRecursive(x, (k+1)/2). According to our assumption these are correct calls, because (k+1)/2 is less than n. x^((k+1)/2) _ x^((k+1)/2) = x^k+1.

the example now follows the principle of induction.

b) for the time complexity, we can use the masters theorum:

O(n^c), if a < b^c
O(n^c log n), if a = b^c
O(n^(log_b a), if a > b^c

in

```java
  double expRecursive(double x, int n) {
    if (n <= 4) {
        return expIterative(x, n);
    }

    return expRecursive(x, n/2) *
           expRecursive(x, (n + 1)/2);
}
```

a = 2, b = 2 and c = 0.

that gives us a time complexity of O(n), because O(n^log<sub>2</sub>2) = O(n) according to masters theorem.
