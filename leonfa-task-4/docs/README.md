#### Exercise 3.9
```java
! (4 < 5) // returns false 
! false // returns true
(2 > 2) || ((4 == 4) && (1 < 0)) // returns false
(2 > 2) || (4 == 4) && (1 < 0) // returns false
(34 != 33) && ! false // returns true
```

#### Exercise 3.10
```java
    a == b // returns true whether both are true or false
```

#### Exercise 3.11
```java
    (a && !b) || (!a && b)
```

#### Exercise 3.12
```java
    !(!a || !b) 
```

#### Exercise 3.21
```java
/*     public void increment()
    {
        value = (value + 1) % limit;
    }
*/ // previous increment method ^ 

// writing it with an if else statement instead of modulo
    public void increment(){
        if(((value+1)/limit) != 0){
            value = 0;
        }

        else{
            value++;
        }
    }
```

#### Exercise 3.26
```java
    Editor(String, int) // not sure if "readme.txt" is of type string or a file? but " " indicates it is a string.
```

#### Exercise 3.27
```java
    private Rectangle window;
    window = new Rectangle(10, 20);
```

#### Exercise 3.30
```java
    // example of method calls
    p1.print("readme.md", false);
    p1.getStatus(60);
    p1.print("textfile.txt", true);
    p1.getStatus(120);
```