## Task 1

#### Ex. 9.11

Device only needs the code for the code to compile hence Printer is a subclass of Device.

#### Ex. 9.12

If both classes have the method getName, the Printer class will override the inherited getName from Device.

#### Ex. 9.13

It will compile, because toString is defined in Object class which is parent to all classes in Java. It will print the memory reference i.e Student@12467878.

#### Ex. 9.14

Yes because the lines will print out the reference to the memory. For example Student@12462s64.

#### Ex. 9.15

The code will compile and will print out the student's names. Because by default every class inherits the toString() method from the superclass Object. Now the Student class overrides that method with its own and that will be now prioritized.

#### Ex. 9.16

```Java
T x = new D();
```

## Task 2

#### 2: time-complexities

```java
  LinkedList() //has a time complexity of O(1)
```

```java
  addFirst() && addLast() //has a time complexity of O(1)
```

```java
  getFirst() && getLast() //has a time-complexity of O(1)
```

```java
  get(index) //has a time complexity of O(n)
```

```java
  removeFirst() //has a time complexity of O(1)
```

```java
  clear() //has a time complexity of O(1)
```

```java
  size() //has a time complexity of O(1)
```

```java
  isEmpty() //has a time complexity of O(1)
```

```java
  toString() //has a time complexity of O(n)
```
