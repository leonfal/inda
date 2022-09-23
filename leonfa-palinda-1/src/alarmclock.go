package main

import (
	"fmt"
	"time"
)

func Remind(text string, delay time.Duration) {
	c := time.Tick(delay)
	for now := range c {
		fmt.Println("The time is", now.Format("15:04:05"), ":", text)
	}
}

func main() {
	go Remind("Time to eat", 10*time.Second)

	go Remind("Time to work", 30*time.Second)

	for {
		Remind("Time to sleep", 60*time.Second)
	}
}
