package dimitrios.p.car_rental_system;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.Calendar;

/**
 * The test class DrivingLicenceTest.
 *
 * @Dimitrios P.
 * @3-3-2018
 */
public class DrivingLicenceTest
{
    /**
     * Default constructor for test class DrivingLicenceTest
     */
    public DrivingLicenceTest()
    {
    }

    public static void main (String [] args) throws UniqueNumberException
    {
      DrivingLicenceTest dl=new DrivingLicenceTest();  
      dl.testDriLic();      
      
    }

    
    /**Method that tests the creation of unique Driving Licence objects and their accessor methods.
     * 
     */
    public void testDriLic() throws UniqueNumberException
    {
        //test Constructor        
        //setup Driver object      
        final String fname="Tom";
        final String lname="Jones";
        final String driversname=fname+" "+lname;
        Date driverdob=new Date();
        Calendar cal=Calendar.getInstance();
        cal.set(1972, 11, 12);
        driverdob=cal.getTime();
              
        Driver d=new Driver(fname, lname, driverdob);
        
        //setup dateofissue
        Date dateofissue=new Date();
        cal.set(2018, 1, 1);       
        dateofissue=cal.getTime();
        
        //setup boolean value
        final boolean isfull=false;
        
        //setup new licence number object
        final String initials="TJ";
        final int year=2018;
        final int serial=4634525;
        final LicenceNumber ln=new LicenceNumber(initials, year, serial);         
        
        //test static factory method and object fields
        final DrivingLicence dlic=DrivingLicence.issueDrivingLicence(d, dateofissue, isfull);        
       
        assertEquals(driversname, dlic.getDriversName());
        assertEquals(driverdob, dlic.getDriversDob());
        assertEquals(dateofissue, dlic.getDateOfIssue());
        assertEquals(isfull, dlic.isFull());
        assertEquals(dlic.getLicNo(), dlic.getLicNo());
        assertNotEquals(ln, dlic.getLicNo());
        
              
        //Testing exceptions and parameter validity      
       
        //test null driver   
        
        try
        {
           final DrivingLicence nulldriver=DrivingLicence.issueDrivingLicence(null, dateofissue, isfull);  
           fail("Expected IllegalArgumentException.");
        }
        catch (IllegalArgumentException e)
        {
            assertEquals(e.getMessage(),"Null driver.");
        }
        
        
        //test null date of issue
        
        try
        {
            final DrivingLicence nulldateofissue=DrivingLicence.issueDrivingLicence(d, null, isfull);  
            fail("Expected IllegalArgumentException.");
        }
        catch (IllegalArgumentException e)
        {
            assertEquals(e.getMessage(),"Null date of issue.");
        }
        
        
        
        
        
        
        
       
    }
    
    
}
