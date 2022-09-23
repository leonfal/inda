package main

import (
	"fmt"
	"io/ioutil"
	"log"
	"strings"
	"sync"
	"time"
)

const DataFile = "loremipsum.txt"

// Return the word frequencies of the text argument.
//
// Split load optimally across processor cores.
func WordCount(text string) map[string]int {

	size := 20000 // divide the work into threads doing 20000 words each
	words := strings.Fields(text)
	wg := new(sync.WaitGroup)

	ch := make(chan map[string]int, (len(words)/size) + 1)

		for i, j := 0, size; i < len(words); i, j = j, j+size {
			if j > len(words){
				j = len(words)
			}
			wg.Add(1)
			go func(x, y int) {
				defer wg.Done()
				freqs := make(map[string]int)
				for k := x; k < y; k++{
					words[k] = strings.Trim(words[k], ",.")
					words[k] = strings.ToLower(words[k])
					freqs[words[k]]++
				}
				ch <- freqs
			}(i, j)
		}
	wg.Wait()
	close(ch)

	results := make(map[string]int)
	for chunk := range ch {
		for k, v := range chunk {
			results[k] += v
		}
	}
	return results
}

// Benchmark how long it takes to count word frequencies in text numRuns times.
//
// Return the total time elapsed.
func benchmark(text string, numRuns int) int64 {
	start := time.Now()
	for i := 0; i < numRuns; i++ {
		WordCount(text)
	}
	runtimeMillis := time.Since(start).Nanoseconds() / 1e6

	return runtimeMillis
}

// Print the results of a benchmark
func printResults(runtimeMillis int64, numRuns int) {
	fmt.Printf("amount of runs: %d\n", numRuns)
	fmt.Printf("total time: %d ms\n", runtimeMillis)
	average := float64(runtimeMillis) / float64(numRuns)
	fmt.Printf("average time/run: %.2f ms\n", average)
}

func main() {
	// read in DataFile as a string called data
	data, err := ioutil.ReadFile("loremipsum.txt")

	if err != nil {
		log.Fatal(err)
	}
	fmt.Printf("%#v", WordCount(string(data)))

	numRuns := 100
	runtimeMillis := benchmark(string(data), numRuns)
	printResults(runtimeMillis, numRuns)
}
