// Stefan Nilsson 2013-03-13

// This program implements an ELIZA-like oracle (en.wikipedia.org/wiki/ELIZA).
package main

import (
	"bufio"
	"fmt"
	"math/rand"
	"os"
	"strings"
	"time"
)

const (
	star   = "Pythia"
	venue  = "Delphi"
	prompt = "> "
)

func main() {
	fmt.Printf("Welcome to %s, the oracle at %s.\n", star, venue)
	fmt.Println("Your questions will be answered in due time.")

	questions := Oracle()
	reader := bufio.NewReader(os.Stdin)
	for {
		fmt.Print(prompt)
		line, _ := reader.ReadString('\n')
		line = strings.TrimSpace(line)
		if line == "" {
			continue
		}
		fmt.Printf("%s heard: %s\n", star, line)
		questions <- line // The channel doesn't block.
	}
}

// Oracle returns a channel on which you can send your questions to the oracle.
// You may send as many questions as you like on this channel, it never blocks.
// The answers arrive on stdout, but only when the oracle so decides.
// The oracle also prints sporadic prophecies to stdout even without being asked.
func Oracle() chan<- string {

	questions := make(chan string)
	answers := make(chan string)

	go answerQuestions(questions, answers)
	go makeNonsense(answers)
	go printProphecies(answers)

	return questions
}

// function to answer questions
func answerQuestions(questions <-chan string, answers chan string) {
	for question := range questions {
		// make prophecies based on input
		go func() {
			time.Sleep(time.Duration(1+rand.Intn(3)) * time.Second) // random time to 'think'
			prophecy(question, answers)
		}()
	}
}

// function that runs indefinite that prints nonsense
func makeNonsense(answers chan<- string) {

		time.Sleep(time.Duration(10+rand.Intn(5)) * time.Second)
		nonsense := []string{
			"The sky is a place.",
			"The earth is round.",
			"You are a squirrel in ten years time",
			"Don't ... go all in AMC",
		}
		answers <- nonsense[rand.Intn(len(nonsense))]
		makeNonsense(answers) // makes it loop indefinite
}

// prints the predictions and nonsense
func printProphecies(answers <-chan string) {

	for s := range answers {
		chars := []rune(s)
		for _, x := range chars {
			time.Sleep(100 * time.Millisecond)
			fmt.Printf("%c", x)
		}
		time.Sleep(100 * time.Millisecond)
		fmt.Print("\n",star,": ")
	}

}

// This is the oracle's secret algorithm.
// It waits for a while and then sends a message on the answer channel.
// TODO: make it better.
func prophecy(question string, answer chan<- string) {
	// Keep them waiting. Pythia, the original oracle at Delphi,
	// only gave prophecies on the seventh day of each month.
	time.Sleep(time.Duration(2+rand.Intn(3)) * time.Second)

	// Find the longest word.
	longestWord := ""
	words := strings.Fields(question) // Fields extracts the words into a slice.
	for _, w := range words {
		if len(w) > len(longestWord) {
			longestWord = w
		}
	}

	// Cook up some pointless nonsense.
	nonsense := []string{
		"The moon is dark.",
		"The sun is bright.",
		"ah, life!...",
	}
	if (question == "What is the meaning of life?"){
		answer <- nonsense[2]
	} else {
		answer <- longestWord + "... " + nonsense[rand.Intn(len(nonsense))]
	}
}

func init() { // Functions called "init" are executed before the main function.
	// Use new pseudo random numbers every time.
	rand.Seed(time.Now().Unix())
}