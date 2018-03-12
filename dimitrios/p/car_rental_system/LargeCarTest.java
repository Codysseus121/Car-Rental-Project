package dimitrios.p.car_rental_system;


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class LargeCarTest.
 *
 * @Dimitrios P.
 * @3-3-2018
 */
public class LargeCarTest
{
    /**
     * Default constructor for test class SmallCarTest
     */
    public LargeCarTest()
    {
    }

    public static void main (String [] args) throws UniqueNumberException, Exception
    {
        LargeCarTest testlarge=new LargeCarTest();
        testlarge.testConstructor();
        testlarge.testRegNo();
        testlarge.testCap();
        testlarge.testGetFull();
        testlarge.testGetRented();
        testlarge.testSetRented();
        testlarge.testFuelLeft();
        testlarge.testAddFuel();
        testlarge.testDrive();        

    }

    public void testConstructor() throws UniqueNumberException
    {
        RegistrationNumber r=RegistrationNumber.generateRegNo();
        final int capacity=60;
        final boolean full=true;
        final boolean rented=false;
        Car lc= new LargeCar();
        assertEquals(capacity, lc.getCapacity());
        assertEquals(full, lc.getFull());
        assertEquals(rented, lc.getRented());
        assertNotEquals(r, lc.getRegNo());
        assertEquals(lc.getRegNo(), lc.getRegNo());
        assertNotNull(lc.getRegNo());

    }
    public void testRegNo() throws UniqueNumberException
    {
        Car lc= new LargeCar();
        assertEquals(lc.getRegNo(), lc.getRegNo());
        assertNotNull(lc.getRegNo());
    }

    public void testCap() throws UniqueNumberException
    {
        Car lc=new LargeCar();
        final int cap=60;
        assertEquals(cap, lc.getCapacity());

    }

    public void testGetFull() throws UniqueNumberException
    {
        Car lc=new LargeCar();
        final boolean full=true;
        assertEquals(full, lc.getFull());

    }
    public void testGetRented() throws UniqueNumberException
    {
        Car lc=new LargeCar();
        final boolean rented=false;
        assertEquals(rented, lc.getRented());

    }

    public void testSetRented() throws UniqueNumberException
    {
        Car lc=new LargeCar();
        final boolean rented=true;
        lc.setRented(rented);
        assertEquals(rented, lc.getRented());
    }

    public void testFuelLeft() throws UniqueNumberException
    {
        Car lc=new LargeCar();
        final int fuel=60;
        final int fuelleft=lc.fuelLeft();
        assertEquals(fuel, fuelleft);

    }

    public void testAddFuel() throws UniqueNumberException
    {
        Car lc=new LargeCar();
        final int fuel=10;
        lc.addFuel(fuel);
        assertEquals(60, lc.fuelLeft());
    }

    public void testDrive() throws UniqueNumberException, Exception
    {
          Car lc=new LargeCar();
        //test method if car is not rented and exception caught.        

        try
        {
            lc.setRented(false);
            lc.drive(100);
            fail ("Expected Exception.");
        }
        catch (Exception i)
        {
            assertEquals(i.getMessage(),"Car is not rented.");
        }

        //test method if argument is 0, less than zero and maximum and minimum ranges
        try
        {
            lc.setRented(true);
            lc.drive(0);
            fail ("Expected IllegalArgumentException.");
        }
        catch (IllegalArgumentException i)
        {
            assertEquals(i.getMessage(), "Car cannot be driven for 0 or less kms."); 
        }

        try
        {
            lc.setRented(true);
            lc.drive(60*25);
            lc.drive(100);
            fail ("Expected Exception.");
        }
        catch (Exception i)
        {
            assertEquals(i.getMessage(), "Not enough fuel."); 
        }

        try
        {
            lc.setRented(true);
            lc.drive(-100);
            fail ("Expected IllegalArgumentException.");
        }
        catch (IllegalArgumentException i)
        {
            assertEquals(i.getMessage(),"Car cannot be driven for 0 or less kms."); 
        }

        try
        {
            lc.setRented(true);
            lc.drive(Integer.MAX_VALUE);
            fail ("Expected Exception.");
        }
        catch (Exception i)
        {
            assertEquals(i.getMessage(), "Not enough fuel."); 
        }
        try
        {
            lc.setRented(true);
            lc.drive(Integer.MIN_VALUE);
           fail ("Expected IllegalArgumentException.");
        }
        catch (IllegalArgumentException i)
        {
            assertEquals(i.getMessage(),"Car cannot be driven for 0 or less kms."); 
        }

        //test method for normal case, drive 50 or less kms.
        lc.addFuel(500);        
        final int fuel=5;
        final int fuelconsumed=lc.drive(50);
        assertEquals(fuel,fuelconsumed );
        //test method for normal case, drive 51 or more kms.
        lc.addFuel(500);        
        final int fuel2=6;
        final int fuelconsumed2=lc.drive(51);
        assertEquals(fuel,fuelconsumed );
        
        //test fuel left
        lc.addFuel(500);       
        final int fuelleft=54;
        lc.drive(60);
        assertEquals(fuelleft, lc.fuelLeft());        

    }
}
