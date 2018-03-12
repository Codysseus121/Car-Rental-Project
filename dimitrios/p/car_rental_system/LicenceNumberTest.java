package dimitrios.p.car_rental_system;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.Calendar;
import java.util.Random;

/**
 * The test class LicenceNumberTest.
 *
 * @Dimitrios P.
 * @3-3-2018
 */
public class LicenceNumberTest
{
    /**
     * Default constructor for test class LicenceNumberTest
     */
    public LicenceNumberTest()
    {
    }

    public static void main (String [] args)
    {
     
        LicenceNumberTest lntest=new LicenceNumberTest();
        
        lntest.testGetInitials();
        lntest.testGetYear();
        lntest.testGetSerial(); 
      
    }
    
    public void testGetInitials()
    {
        String fname="Tom";
        String lname="Smith";
        Date dob=new Date();        
        Calendar cal=Calendar.getInstance();
        cal.set(1972, 11, 12);
        dob=cal.getTime();
        Driver d=new Driver(fname, lname, dob);        
        final String testinitials="TS";
        final int yearofissue=2018;
        LicenceNumber lic=new LicenceNumber();
        assertEquals(testinitials, lic.getInitials(d));
        
    }
    
    public void testGetYear()
    {
        final LicenceNumber lic=new LicenceNumber();
        Calendar cal=Calendar.getInstance();
        cal.set(2004, 00, 01);
        Date dateofissue=new Date();
        dateofissue=cal.getTime();
        assertEquals(cal.get(Calendar.YEAR),lic.getYear(dateofissue));
        
    }
    
    public void testGetSerial()
    {
       final LicenceNumber lic=new LicenceNumber();
       int serial=0;
       Random r=new Random();
       serial=r.nextInt();
       assertNotEquals(serial, lic.getSerial());
       
       
    }
}
