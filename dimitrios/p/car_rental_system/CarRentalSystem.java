package dimitrios.p.car_rental_system;

import java.util.*;

/**
 * class CarRentalSystem creates a singleton CarRentalSystem object to manage car rentals.
 *
 * @Dimitrios P.
 * @24-2-2018
 */
class CarRentalSystem
{
    private static final int LARGE_FLEET=20;
    private static final int SMALL_FLEET=30;    
    private static final CarRentalSystem INSTANCE = new CarRentalSystem();
    private static final Map<DrivingLicence,Car> RENTED_CARS=new HashMap<DrivingLicence,Car>();
    private static final Set <Car> SMALL_CARS_AVAILABLE=new HashSet<Car>();
    private static final Set <Car> LARGE_CARS_AVAILABLE=new HashSet<Car>();
  
    /**
     * Constructor for singleton object of class CarRentalSystem
     */
    private CarRentalSystem()
    {

    }

    /**A static factory method to create singleton object.
     * 
     */
    public static CarRentalSystem getInstance()
    {
        return INSTANCE;
    }

    /**
     * Method issueCar() issues a car to a specific driving licence
     *
     * @param Car c: the car to be issued
     * @DrivingLicence dl: the driving licence to which this car will be issued.
     * 
     * Cars are issued according to the following specification:
     * 1. the person renting the car has a full driving licence,
     * 2. the tank is full,
     * 3. the person requesting the car cannot rent more than one car at a time
     * 4. to rent a small car, they must be at least 21 years old and must have held their licence for at least 1 year.
     * 5. to rent a large car, they must be at least 25 years old and must have held their licence for at least 5 years. 
     */
    public void issueCar(Car c, DrivingLicence dl)
    {
        if (c==null)
        throw new IllegalArgumentException("Car cannot be null.");
        if (dl==null)
        throw new IllegalArgumentException("Driving licence cannot be null.");        
        //Determine eligibility by checking the date of birth and date of issue.
        Date now = new Date();
        final int dage=getDiffYears(dl.getDriversDob(), now);
        final int timeheld=getDiffYears(dl.getDateOfIssue(), now);
        //issue car according to specification. 

        final int availability=availableCars(c);
        if (c instanceof SmallCar && c.getFull()==true && dl.isFull()==true && c.getRented()==false && dage>=21 && timeheld>=1)
        {

            if (!RENTED_CARS.containsKey(dl) && SMALL_FLEET-availability>0)
            {
                RENTED_CARS.put(dl,(SmallCar) c);
                c.setRented(true);
                SMALL_CARS_AVAILABLE.remove(c);
                
            }
            
        }
        else if (c instanceof LargeCar && c.getFull()==true && c.getRented()==false && dl.isFull()==true && dage>=25 && timeheld>=5)
        {
            if (!RENTED_CARS.containsKey(dl) && LARGE_FLEET-availability>0)
            {
                RENTED_CARS.put(dl, (LargeCar) c);
                c.setRented(true);
                LARGE_CARS_AVAILABLE.remove(c);
                
            }
            
        }
        else
            System.out.println("Car cannot be issued.");

    }

    /**Method getRENTED_CARS() returns a collection of the cars that have been rented.
     * 
     */
    public Collection<Car> getRentedCars()
    {
        return RENTED_CARS.values();
    }

    /**Method getCar() returns the car rented out to a driving licence.
     * @param DrivingLicence dl: the driving licence associated with this rental.
     * 
     */
    public Car getCar(DrivingLicence dl)
    {
        if (dl==null)
        throw new IllegalArgumentException("This driving licence is null.");
        
        final Car c=RENTED_CARS.get(dl);
        if (c==null)
            System.out.println("No car associated with this driving licence.");
        return RENTED_CARS.get(dl);
    }

    /**Method availableCars() returns the number of available cars of the particular parameter type.
     * @param Car c: the type of Car. 
     * 
     */
    public int availableCars(Car c)
    {
        if (c==null)
        throw new IllegalArgumentException("The car cannot be null.");
        
        if (c instanceof SmallCar)
            return SMALL_CARS_AVAILABLE.size();
        else 
            return LARGE_CARS_AVAILABLE.size();
    }

    /**Method terminateRental() terminates the rental contract, sets the car rental status as 'not rented'
     * and returns the amount of fuel consumed and owed (if any.)
     * @param DrivingLicence dl:the DrivingLicence object associated with this rental.
     */
    public int terminateRental(DrivingLicence dl)

    {
        final Car c=RENTED_CARS.get(dl);
        if (c==null)
        System.out.println("No car rented to this driving licence.");
        RENTED_CARS.remove(dl);
        c.setRented(false);
        if (c instanceof SmallCar)
        SMALL_CARS_AVAILABLE.add(c);
        else
        LARGE_CARS_AVAILABLE.add(c);        
        int fuelliable=0;
        if (c.getFull()==false)
        fuelliable=c.getCapacity()-c.fuelLeft();
        return fuelliable;   
      
        
    }

    /**Static method getDiffYears() to calculate periods in number of years between two dates.
     * @param Date first: the first date.
     * @param Date second: the second date.
     */
    public static int getDiffYears(Date first, Date second)
    {
        if (first==null)
        throw new IllegalArgumentException();
        if (second==null)
        throw new IllegalArgumentException();
        
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(second);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) || 
        (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }

    /**Static method getCalendar()to create a new Calendar instance from a particular Date object.
     * @param Date date: the Date object for this calendar.
     */
    public static Calendar getCalendar(Date date)
    {
        if (date==null)
        throw new IllegalArgumentException();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
}
