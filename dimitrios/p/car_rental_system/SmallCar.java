package dimitrios.p.car_rental_system;

/**
 * class SmallCar is a class for generating small car objects.
 *
 * @Dimitrios P.
 * @24-2-2018
 */
final class SmallCar extends Car implements CarInterface
{
    private static final Integer FUEL_CONSUMPTION_RATE=20;
    private static final Integer FUEL_CAPACITY=49;
    private int fuel;

    /**
     * Constructor for objects of class SmallCar
     */
    public SmallCar() throws UniqueNumberException
    {
        super(FUEL_CAPACITY);
        this.fuel=FUEL_CAPACITY;

    }

    /**
     * Method fuelLeft() to return this object's fuel left in the tank.
     * 
     */
    public int fuelLeft()
    {
        return fuel;
    }

    /**
     * Method getFull() return true if this object's tank is full.
     * 
     */
    public boolean getFull()
    {
        return FUEL_CAPACITY==fuel;

    }

    /**
     * Method getCapacity() returns this object's fuel capacity in litres.
     * 
     */
    public int getCapacity()
    {
        return FUEL_CAPACITY;
    }

    
    /**
     * Method drive() to drive a car for a certain amount of kilometres.
     * 
     * @param int kms: the amount of kilometres driven.
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
        else
        {
            fuelconsumed=kms/FUEL_CONSUMPTION_RATE;
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
