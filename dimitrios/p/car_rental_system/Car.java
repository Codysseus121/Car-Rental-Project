package dimitrios.p.car_rental_system;

/**
 * Abstract class Car that provides basic functionality for immutable Car objects.
 *
 * @Dimitrios P.
 * @23-2-2018
 */
public abstract class Car implements CarInterface
{
   
    private final RegistrationNumber r;
    private boolean full;
    private boolean rented;
    

    /**
     * Constructor for objects of class Car
     */
    public Car(int capacity) throws UniqueNumberException
    {
        this.r= RegistrationNumber.generateRegNo();
        if (r==null)
        throw new IllegalArgumentException("Registration Number cannot be null.");
        this.full=true;
        this.rented=rented;
    }

    /**
     * Abstract method drive() to drive a car for a certain amount of kilometres.
     * 
     * @param int kms: the amount of kilometres driven.
     */
    public abstract int drive(int kms) throws Exception;

    /**
     * Abstract method getCapacity() to return this object's fuel capacity in litres.
     * 
     */
    public abstract int getCapacity();

    /**
     * Abstract method fuelLeft() to return this object's fuel left.
     * 
     */
    public abstract int fuelLeft();

    /** Method addFuel which adds fuel up to the tank's capacity if the car is not full or not rented.
     * Provides indication and returns the amount of fuel added.
     * 
     */
    public abstract int addFuel(int extraFuel);

    /**
     * Abstract method getFull() to return whether this object's tank is full or not.
     * 
     */
    public abstract boolean getFull();

    /**
     * Method getRegNo() to return this object's registration number
     * 
     */
    public RegistrationNumber getRegNo()
    {

        return r;
    }

    
    /**
     * Method getRented() to return this object's rented status.      
     */
    public boolean getRented()
    {
           return rented;
    }

    /**
     * Method setRented() to change this object's rented status.
     * 
     */
    public void setRented(boolean r)
    {
        rented = r;
    }

}
