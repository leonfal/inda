public class Loops {

    //Exercise 4.30
    public void multiplesOfFive(){
        int i = 10;
        while(i <= 95){
            if(i%5 == 0){
                System.out.println(i);
            }
            i++;
        }
    }

    // Exercise 4.31
    public void printSum(){
        int sum = 0;
        int i = 1;
        while(i <= 10){
            sum += i;
            i++;
        }
        System.out.println(sum);
    }

    // Exercise 4.32
    public int sum(int a, int b){
        int sum = 0;
        if(a > b){
            int temp; // creates a temp int, used for swapping a with b.
            temp = a; // temp takes the value of a
            a = b; // a takes the value of b
            b = temp; // b takes the initial value of a
        }
        while(a <= b){
            sum += a;
            a++;
        }
        return sum;
    }

    // Exercise 4.33
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


    public static void main(String[] args) {
        Loops L1 = new Loops();
        L1.multiplesOfFive();
        L1.printSum();
        L1.sum(6, 1);
        System.out.println(L1.isPrime(9));
    }
}
