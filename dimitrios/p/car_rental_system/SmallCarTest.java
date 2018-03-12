package dimitrios.p.car_rental_system;


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SmallCarTest.
 *
 * @Dimitrios P.
 * @3-3-2018
 */
public class SmallCarTest
{
    /**
     * Default constructor for test class SmallCarTest
     */
    public SmallCarTest()
    {
    }

    public static void main (String [] args) throws UniqueNumberException, Exception
    {
        SmallCarTest testsmall=new SmallCarTest();
        testsmall.testConstructor();
        testsmall.testRegNo();
        testsmall.testCap();
        testsmall.testGetFull();
        testsmall.testGetRented();
        testsmall.testSetRented();
        testsmall.testFuelLeft();
        testsmall.testAddFuel();
        testsmall.testDrive();        

    }

    public void testConstructor() throws UniqueNumberException
    {
        RegistrationNumber r=RegistrationNumber.generateRegNo();
        final int capacity=49;
        boolean full=true;
        boolean rented=false;
        Car sc=new SmallCar();
        assertEquals(capacity, sc.getCapacity());
        assertEquals(full, sc.getFull());
        assertEquals(rented, sc.getRented());
        assertNotEquals(r, sc.getRegNo());
        assertEquals(sc.getRegNo(), sc.getRegNo());
        assertNotNull(sc.getRegNo());

    }
    public void testRegNo() throws UniqueNumberException
    {
        Car sc=new SmallCar();
        assertEquals(sc.getRegNo(), sc.getRegNo());
        assertNotNull(sc.getRegNo());
    }

    public void testCap() throws UniqueNumberException
    {
        Car sc=new SmallCar();
        final int cap=49;
        assertEquals(cap, sc.getCapacity());

    }

    public void testGetFull() throws UniqueNumberException
    {
        Car sc=new SmallCar();
        boolean full=true;
        assertEquals(full, sc.getFull());

    }
    public void testGetRented() throws UniqueNumberException
    {
        Car sc=new SmallCar();
        boolean rented=false;
        assertEquals(rented, sc.getRented());

    }

    public void testSetRented() throws UniqueNumberException
    {
        Car sc=new SmallCar();
        boolean rented=true;
        sc.setRented(rented);
        assertEquals(rented, sc.getRented());
    }

    public void testFuelLeft() throws UniqueNumberException
    {
        Car sc=new SmallCar();
        final int fuel=49;
        int fuelleft=sc.fuelLeft();
        assertEquals(fuel, fuelleft);

    }

    public void testAddFuel() throws UniqueNumberException
    {
        Car sc=new SmallCar();
        final int fuel=10;
        sc.addFuel(fuel);
        assertEquals(49, sc.fuelLeft());
    }

    public void testDrive() throws UniqueNumberException, Exception
    {
        Car sc=new SmallCar();
        //test method if car is not rented and exception caught.        

        try
        {
            sc.setRented(false);
            sc.drive(100);
            fail ("Expected Exception.");
        }
        catch (Exception i)
        {
            assertEquals(i.getMessage(),"Car is not rented.");
        }

        //test method if argument is 0, less than zero and maximum and minimum ranges
        try
        {
            sc.setRented(true);
            sc.drive(0);
            fail ("Expected IllegalArgumentException.");
        }
        catch (IllegalArgumentException i)
        {
            assertEquals(i.getMessage(), "Car cannot be driven for 0 or less kms."); 
        }

        try
        {
            sc.setRented(true);
            sc.drive(49*25);
            sc.drive(100);
            fail ("Expected Exception.");
        }
        catch (Exception i)
        {
            assertEquals(i.getMessage(), "Not enough fuel."); 
        }

        try
        {
            sc.setRented(true);
            sc.drive(-100);
            fail ("Expected IllegalArgumentException.");
        }
        catch (IllegalArgumentException i)
        {
            assertEquals(i.getMessage(),"Car cannot be driven for 0 or less kms."); 
        }

        try
        {
            sc.setRented(true);
            sc.drive(Integer.MAX_VALUE);
            fail ("Expected Exception.");
        }
        catch (Exception i)
        {
            assertEquals(i.getMessage(), "Not enough fuel."); 
        }
        try
        {
            sc.setRented(true);
            sc.drive(Integer.MIN_VALUE);
           fail ("Expected IllegalArgumentException.");
        }
        catch (IllegalArgumentException i)
        {
            assertEquals(i.getMessage(),"Car cannot be driven for 0 or less kms."); 
        }

        //test method for normal cases
        sc.addFuel(100);        
        final int fuel=2;
        final int fuelconsumed=sc.drive(50);
        assertEquals(fuel,fuelconsumed );
        //test fuel left
        final int fuelleft=45;
        sc.drive(50);
        assertEquals(fuelleft, sc.fuelLeft());        

    }
}
