package dimitrios.p.car_rental_system;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.Calendar;

/**
 * The test class DriverTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DriverTest
{
    /**
     * Default constructor for test class DriverTest
     */
    public DriverTest()
    {
    }

    public static void main(String [] args)
    {
        final DriverTest dt=new DriverTest();
        dt.testDriver();       

    }

    /**Tests constructor, exceptions (empty and null Strings and Dates) and accessor methods.
     * 
     */
    public void testDriver()
    {
        //Test constructor and exceptions 
        final String fname="Nick";
        final String lname="Thomas";   
        final String fullname=fname+" "+lname;
        Calendar cal=Calendar.getInstance();
        cal.set(1972, 11, 12);
        final Date dob=cal.getTime();        
        Driver d=new Driver(fname, lname, dob);
        assertEquals(fname, d.getFirstName());
        assertEquals(lname, d.getLastName());
        assertEquals(fullname, d.getFullName());
        assertEquals(dob, d.getDob());   
        //test empty Strings
        final String emptyfname="";
        final String emptylname="";
        try
        {
            Driver emptyd=new Driver(emptyfname, emptylname, dob);
            fail("Expected IllegalArgumentException.");
        }
        catch (IllegalArgumentException e)
        {
            assertEquals(e.getMessage(),"empty string");
        }
        //test null Strings
        try
        {
            Driver emptyd=new Driver(null, lname, dob);
            fail("Expected NullPointerException.");
        }
        catch (NullPointerException e)
        {
            System.out.println("Exception caught.");
        }
        try
        {
            Driver emptyd=new Driver(fname, null, dob);
            fail("Expected NullPointerException.");
        }
        catch (NullPointerException e)
        {
            System.out.println("Exception caught.");
        }
        //test null date of birth

        try
        {
            Driver nulldob=new Driver(fname, lname, null);
            fail("Expected NullPointerException.");
        }
        catch (NullPointerException e)
        {
            assertEquals(e.getMessage(),"null date");
        }

    }

    
}
