package main

import "fmt"

// I want this program to print "Hello world!", but it doesn't work.
func main() {
	ch := make(chan string, 10)

	ch <- "Hello world!"
	fmt.Println(<-ch)
}

// a deadlock occures, this is because the recieve waits for the send and the send waits for the recieve. This is because our ch is unbuffered. To solve this deadlock problem we can make ch buffered, giving it a size, e.g. 10.
