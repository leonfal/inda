package main

import (
	"fmt"
	"math"
)

// function to square root a float number
func Sqrt(x float64) float64 {
	z := 1.0
	s := float64(0)
	for i := 1; i <= 10; i++ {
		z -= (z*z - x) / (2 * z)
		fmt.Println(z)
		if math.Abs(z-s) < (1e-6) {
			break
		}
		s = z
	}
	return z
}

func main() {
	fmt.Println(Sqrt(3))
}
