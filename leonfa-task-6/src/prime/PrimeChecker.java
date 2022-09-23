public class PrimeChecker{

    // its and empty constructor just to be
    // able to create an instance of an object of PrimeChecker
    public boolean isPrime(int n){
        int i = 2; // i is given the value 2, so the indexing is easier

        while(i <= (n-1)){
            if (n % i == 0) {
                return false; // if n leaves a remainder of 0 other than itself and 1 its not a primenr
            }
            i++; // adds 1 to the indexing
        }
        return true;
    }
}
