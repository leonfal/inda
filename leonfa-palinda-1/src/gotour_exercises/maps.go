package main

import (
	"strings"

	"golang.org/x/tour/wc"
)

// function to count occuring words
func WordCount(s string) map[string]int {
	slices := make(map[string]int)
	words := strings.Fields(s)

	// blank identifier '_' only interested in v value, key is the index of the words
	for _,v := range words {
		slices[v]++
	}
	return slices
}

func main() {
	wc.Test(WordCount)
}
