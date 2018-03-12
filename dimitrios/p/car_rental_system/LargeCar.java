package dimitrios.p.car_rental_system;

/**
 * class LargeCar is a class for generating large car objects.
 *
 * @Dimitrios P.
 * @24-2-2018
 */
 final class LargeCar extends Car implements CarInterface
{
    private final static int FUEL_CONSUMPTION_RATE1=10;
    private final static int FUEL_CONSUMPTION_RATE2=15;
    private final static Integer FUEL_CAPACITY=60;
    private int fuel;

    /**
     * Constructor for objects of class LargeCar//constructor + factory methods + immutability to be fixed.
     */
    public LargeCar() throws UniqueNumberException
    {

        super(FUEL_CAPACITY);
        this.fuel=FUEL_CAPACITY;
    }
    
    /**
     * Method fuelLeft() to return this object's fuel left.
     * 
     */
    public int fuelLeft()
    {
        return fuel;
    }
    
    /**
     * Method getCapacity() to return this object's fuel capacity in litres.
     * 
     */
    public int getCapacity()
    {
        return FUEL_CAPACITY;
    }
    
    /**
     * Method getFull() to return whether this object's tank is full or not.
     * 
     */
    public boolean getFull()
    {
        return FUEL_CAPACITY==fuel;

    }
    
    /** Method drive() to drive a car for a number of kilometres, calculates the fuel consumed
     * (10 Kilometres/Litre for the first 50 Kilometres of a journey, 15 Kilometres/Litre for the remainder)
     * and returns the amount of fuel consumed (integers only.)
     * @param int kms: the number of kilometres driven.
     */
    public int drive(int kms) throws Exception
    {
        int fuelconsumed=0;
        if (kms<=0)
        throw new IllegalArgumentException("Car cannot be driven for 0 or less kms.");
            if (getRented()==false)
        {
            throw new Exception("Car is not rented.");
            
        }
        else if (fuelLeft()<=0)
        {
            throw new Exception("Not enough fuel.");
            
        }
        else if (kms<=50)
        {

            fuelconsumed=kms/FUEL_CONSUMPTION_RATE1;
            fuel=fuelLeft()-fuelconsumed;
            
            
        }

        else
        {
            int remdistance=kms-50;
            fuelconsumed = remdistance<=15 ? fuelconsumed=50/FUEL_CONSUMPTION_RATE1+1:
            50/FUEL_CONSUMPTION_RATE1+remdistance/FUEL_CONSUMPTION_RATE2;
                        
            fuel=fuelLeft()-fuelconsumed;
            

        }
        return fuelconsumed;
    }
    
    /** Method addFuel which adds fuel up to the tank's capacity if the car is not full or not rented.
     * Provides indication and returns the amount of fuel added.
     * 
     */
    public int addFuel(int extraFuel)
    {
        if (extraFuel<=0)
        throw new IllegalArgumentException("Fuel added cannot be 0 or less than zero.");
        if (getRented()==false || getFull()==true)
            return 0;
        else
        {
            fuel=fuelLeft()+extraFuel;
            if (fuel>FUEL_CAPACITY)
                fuel=FUEL_CAPACITY;
            System.out.println("Fuel added: "+ fuel + " lt.");
            return fuel;
        }
    }
}
