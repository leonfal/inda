build:
	javac -cp ".:lib/hamcrest-core-2.2.jar:lib/junit-4.13.2.jar" src/*.java

run: build
	java -cp ".:lib/hamcrest-core-2.2.jar:lib/junit-4.13.2.jar:src/" IntSorterTest

clean:
	rm src/*.class
