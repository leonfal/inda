import java.util.Arrays;
/**
 * Implementation of the Sieve of Eratosthenes algorithm for checking if a
 * number is prime or not. The implementation is lacking in error-checking
 * and optimization, and needs some patching up!
 *
 * @author Leon Fällman
 * @version 2021-11-15
 */
public class Sieve {

    private final int max = (int)Math.pow(2, 26);
    private boolean[] primeCache = new boolean[]{};

    // Exercise S.4
    private void exceptionIfIllegalArg(int number){
        if(number < 2 || number > max){
            throw new IllegalArgumentException("the least possible prime is 2");
        }
    }
    // Exercise S.4
    private boolean[] sieve(int number){
        boolean[] prime = new boolean[number + 1]; // + 1 because of 0-indexing
        Arrays.fill(prime, true); // assume all numbers are prime
        int sqrt = (int) Math.floor(Math.sqrt(number));
        for (int i = 2; i <= sqrt; i++) {
            if (prime[i]) {
                for (int j = i*2; j < prime.length; j+=i) {
                    prime[j] = false; // mark multiples of i as not prime
                }
            }
        }
        return prime;
    }

    // Exercise S.2 and S.3 and S.4 and S.5 and S.6
    /**
     * Method that stores and returns prime numbers
     * @param number that want to check if prime
     * @return true if number is prime false if not
     */
    public boolean isPrime(int number) {

        exceptionIfIllegalArg(number);
        
        if (number < primeCache.length){
            return primeCache[number];
        }

        boolean[] prime = sieve(number);
        
        // Optimized
        if(prime.length >= primeCache.length){
            primeCache = prime;
        }
        return prime[number];
    }
}
