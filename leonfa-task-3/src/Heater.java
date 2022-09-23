public class Heater{

    // everyting of this code is part of exercises 2.92 and 2.93
    // bits and pieces of both exercises are scattered through the code
    // to make the code more easy readable.
    
    //Exercise 2.92
    private double temperature;
    //Exercise 2.93
    private double min;
    
    private double max;

    private double increment;

    public Heater(double min, double max){ // sets all default values of double variables.
        temperature = 15.0;
        increment = 5.0;
        this.min = min;
        this.max = max;
    }

    public void warmer(){
        if ((temperature+increment) < max){ // makes the temp not go further than max
            temperature += increment;
        }
        else{ // if temp is at maximum level, dont increment
            temperature = max;
        }
    }

    public void cooler(){
        if ((temperature-increment) > min){ // makes the temp not go under than min
            temperature -= increment;
        }
        else{ // if temp is at minimum level, dont increment
            temperature = min;
        }
    }

    
    public void setIncrement(double i){
        if (i > 0){ // prevents increment from being negative, sets the increment to 0 instead.
            increment = i;
        }
        else{
            System.out.println("cannot set increment to negative, default set to 0");
        }
    }

    public double getTemp(){
        return temperature;
    }

    public static void main(String[] args) {
        Heater h1 = new Heater(0, 30);
        // checks if warmer and cooler mutators work and the min and max work
        System.out.println(h1.getTemp());
        h1.warmer();
        System.out.println(h1.getTemp());
        h1.warmer();
        System.out.println(h1.getTemp());
        h1.setIncrement(10); // checks that negative increment won't work, makes the increment 0.
        h1.cooler();
        System.out.println(h1.getTemp());
    }
}