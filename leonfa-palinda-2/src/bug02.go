package main

import (
	"fmt"
	"sync"
	"time"
)

var wg sync.WaitGroup
// This program should go to 11, but it seemingly only prints 1 to 10.
// the problem is that the main thread completes before the goroutine has time to print the last element 11. We can declare a waitGroup as a solution so that the goroutine has time to print the last element before the main closes.
func main() {
	ch := make(chan int)
	wg.Add(1) // adds the print 'thread'
	go Print(ch)
	wg.Add(1) // adds the previous main 'thread' now as a seperate goroutine.
	go func() {
		for i := 1; i <= 11; i++ {
			ch <- i
		}
		close(ch) // shuts down after all messages
		wg.Done()
	}()
	wg.Wait()
}
// Print prints all numbers sent on the channel.
// The function returns when the channel is closed.
func Print(ch <-chan int) {
	defer wg.Done()
	for n := range ch { // reads from channel until it's closed
		time.Sleep(10 * time.Millisecond) // simulate processing time
		fmt.Println(n)
	}
}
