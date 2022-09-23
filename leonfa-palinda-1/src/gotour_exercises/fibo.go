package main

import "fmt"

// fibonacci is a function that returns
// a function that returns an int.
func fibonacci() func() int {
	var first, second = 0, 1

	// a closure func "remembers" variables after exec
	return func() int {
		current := first
		first, second = second, first+second
		return current
	}

}

func main() {
	f := fibonacci()
	for i := 0; i < 10; i++ {
		fmt.Println(f())
	}
}
