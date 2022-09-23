public class Main {
    public static void main(String[] args){

        // Exercise PrimeChecker 4.XX
        PrimeChecker p1 = new PrimeChecker();
        for(int i = 1; i <= 25; i++){
            if(p1.isPrime(i)){
                System.out.println(i + " is a prime number!");
            }
            else{
                System.out.println(i + " is not a prime number!");
            }
        }
    }
}
