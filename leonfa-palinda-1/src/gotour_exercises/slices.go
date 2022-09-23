package main

import "golang.org/x/tour/pic"

//function to make a picture
func Pic(dx, dy int) [][]uint8 {
	x := make([][]uint8, dy)
	for i:= range x {
		x[i] = make([]uint8, dx)
	}
	return x
}

func main() {
	pic.Show(Pic)
}
