package dimitrios.p.car_rental_system;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.io.*;

/**
 * The test class CarRentalSystemTest.
 *
 * @Dimitrios P.
 * @3-32018
 */
public class CarRentalSystemTest
{

    /**
     * Default constructor for test class CarRentalSystemTest
     */
    public CarRentalSystemTest()
    {
    }

    public static void main (String [] args) throws UniqueNumberException, Exception
    {
        CarRentalSystemTest rtest=new CarRentalSystemTest();
        rtest.testIssueCar();
        rtest.testGetCar();
        rtest.testAvailableCars();
        rtest.testTerminateRental();

    }

    /** Method testIssueCar tests simultaneously methods issueCar(), getCar(), availableCars(), terminateRental() of the native class, 
     * and the unit as a whole (for example, methods of the Car class) using various test cases. Also, tests methods to calculate
     * eligibility based on age and current date.
     */
    public void testIssueCar() throws UniqueNumberException
    {
        CarRentalSystem crs=CarRentalSystem.getInstance();
        
        //setup different Driver objects to be used in the test
        //Driver d: driver fully qualifies to drive both small and large cars.
        final String fname="Nick";
        final String lname="Thomas";   
        final String fullname=fname+" "+lname;
        Calendar cal=Calendar.getInstance();
        cal.set(1972, 11, 12);
        final Date dob=cal.getTime();        
        Driver d=new Driver(fname, lname, dob); 
               
        //Driver d2: too young to drive
        final String fname2="Nick";
        final String lname2="Jonas";   
        final String fullname2=fname2+" "+lname2;
        //Calendar cal2=Calendar.getInstance();
        cal.set(2004, 11, 12);
        final Date dob2=cal.getTime();        
        Driver d2=new Driver(fname2, lname2, dob2);  
        
        //Driver d3: at the boundary. 
        
        final String fname3="Nick";
        final String lname3="Lonas";   
        final String fullname3=fname3+" "+lname3;
        //Calendar cal3=Calendar.getInstance();
        cal.set(1997, 02, 03);
        final Date dob3=cal.getTime();        
        Driver d3=new Driver(fname3, lname3, dob3);   

        //create Car test objects -one small & one large    
        final Car c = new SmallCar();
        final Car l=new LargeCar();
        boolean tankfull=true;

        //setup & create 3 different driving licences 
        //Licence 1
        Date dateofissue=new Date();
        cal.set(2004, 1, 1);       
        dateofissue=cal.getTime();
        boolean licfull=true;                
        DrivingLicence dl=DrivingLicence.issueDrivingLicence(d, dateofissue, licfull);
        
        //Licence 2
        Date dateofissue2=new Date();
        cal.set(2018, 1, 1);       
        dateofissue2=cal.getTime();
        DrivingLicence dl2=DrivingLicence.issueDrivingLicence(d2, dateofissue2, licfull);
        
        //Licence 3
        Date dateofissue3=new Date();
        cal.set(2017, 2, 3);       
        dateofissue3=cal.getTime();
        DrivingLicence dl3=DrivingLicence.issueDrivingLicence(d3, dateofissue3, licfull);
        
        //Licence 4 - provisional
        DrivingLicence prov=DrivingLicence.issueDrivingLicence(d, dateofissue, false);        
        

        //Test case 1
        boolean rented=false;
        Date now = new Date();
        final int dage=crs.getDiffYears(dl.getDriversDob(), now);
        final int timeheld=crs.getDiffYears(dl.getDateOfIssue(), now);
        int available=crs.availableCars(c);
        int availability=30-available;
        int availablelarge=crs.availableCars(l);

        //assertions for SmallCar instance. Testing normal cases.
        assertTrue(c instanceof SmallCar);
        assertTrue(tankfull== c.getFull());
        assertTrue(rented== c.getRented());
        assertTrue(licfull== dl.isFull());
        assertTrue(dage>21);
        assertTrue(timeheld>1);
        assertTrue(availability>0);
        //Issue small car to a fully qualified 45 year old driver.
        crs.issueCar(c, dl);
        assertEquals(crs.getCar(dl),c);
        assertFalse(crs.getCar(dl) instanceof LargeCar);
        assertEquals(crs.availableCars(c),available);
        assertTrue(true==c.getRented());
        assertEquals(availablelarge,crs.availableCars(l));
        assertTrue(crs.getRentedCars().contains(c));
        //Issue large car to same driver. Test success, car cannot be issued.
        crs.issueCar(l,dl);
        assertNotEquals(crs.getCar(dl),l);
        assertFalse(crs.getCar(dl) instanceof LargeCar);
        assertTrue(false==l.getRented());
        assertEquals(availablelarge,crs.availableCars(l));
        assertFalse(crs.getRentedCars().contains(l));
        
        //Terminate rental of small car 
        crs.terminateRental(dl);
        assertFalse(crs.getRentedCars().contains(c));
        assertTrue(false==c.getRented());
        assertFalse(crs.getCar(dl) instanceof LargeCar);
        assertNotEquals(crs.getCar(dl),c);
        
        //Try issuing large car again.
        crs.issueCar(l,dl);
        assertEquals(crs.getCar(dl),l);
        assertTrue(crs.getCar(dl) instanceof LargeCar);
        assertTrue(true==l.getRented());
        assertTrue(crs.getRentedCars().contains(l));
        

        //Testing boundary cases & exceptional cases.
        crs.issueCar(c, dl);//Try issuing same car. Output="Car cannot be issued."  
       
        //Test case 2. Issue small car to someone less than 21.

        SmallCar c2=new SmallCar();
        LargeCar l2=new LargeCar();
        crs.issueCar(c2, dl2);//Output: Car cannot be issued.
        assertTrue(false==c2.getRented());
        assertFalse(crs.getRentedCars().contains(c2));
        
        //Issue large car to someone less than 21.
        
        crs.issueCar(l2, dl2);
        assertTrue(false==l2.getRented());
        assertFalse(crs.getRentedCars().contains(l2));
        
        //Test case 3. Issue small car to someone who just had his 21st birthday.
        SmallCar c3=new SmallCar();
        crs.issueCar(c3, dl3);
        assertEquals(crs.getCar(dl3),c3);
        assertFalse(crs.getCar(dl3) instanceof LargeCar);
        assertTrue(true==c3.getRented());
        assertTrue(crs.getRentedCars().contains(c3));
        
        //terminate rental
        crs.terminateRental(dl3);
        assertFalse(crs.getRentedCars().contains(c3));
        assertTrue(false==c3.getRented());
        assertFalse(crs.getCar(dl3) instanceof LargeCar);
        assertNotEquals(crs.getCar(dl3),c3);
        
        //try issuing large car
        crs.issueCar(l2, dl3);
        assertNotEquals(crs.getCar(dl3),l2);
        assertFalse(crs.getCar(dl3) instanceof LargeCar);
        assertTrue(false==l2.getRented());
        assertFalse(crs.getRentedCars().contains(l2));
        
        //Test case. Issue to provisional licence
        crs.terminateRental(dl);
        assertFalse(crs.getRentedCars().contains(c));
        crs.issueCar(c, prov);
        assertFalse(crs.getRentedCars().contains(c));
        assertNotEquals(crs.getCar(prov),c);
        assertTrue(false==c.getRented());    
       
    }
    
   
    /** Testing method getCar() with mock driving licence
     * 
     */
    public void testGetCar() throws UniqueNumberException
    {
        CarRentalSystem crs=CarRentalSystem.getInstance();
        //test issue of small car
        //create test Driver object
        final String fname="Nick";
        final String lname="Thomas";   
        final String fullname=fname+" "+lname;
        Calendar cal=Calendar.getInstance();
        cal.set(1972, 11, 12);
        final Date dob=cal.getTime();        
        Driver d=new Driver(fname, lname, dob); 

        //create Car test objects    
        final Car c = new SmallCar();
        final Car l=new LargeCar();
        boolean tankfull=true;

        //setup driving licence dateofissue
        Date dateofissue=new Date();
        cal.set(2004, 1, 1);       
        dateofissue=cal.getTime();
        boolean licfull=true;

        //setup mock driving licence dateofissue
        Date dateofissue2=new Date();
        cal.set(20010, 1, 1);       
        dateofissue2=cal.getTime();
        boolean licfull2=true;

        //create driving licence        
        DrivingLicence dl=DrivingLicence.issueDrivingLicence(d, dateofissue, licfull);
        DrivingLicence dl2=DrivingLicence.issueDrivingLicence(d, dateofissue2, licfull2);

        //issue car according to specification.
        boolean rented=false;
        Date now = new Date();
        final int dage=crs.getDiffYears(dl.getDriversDob(), now);
        final int timeheld=crs.getDiffYears(dl.getDateOfIssue(), now);
        int available=crs.availableCars(c);
        int availability=30-available;
        int availablelarge=crs.availableCars(l);        
        crs.issueCar(c, dl);

        //test the method
        assertNotEquals(crs.getCar(dl2),c);
        assertFalse(crs.getCar(dl) instanceof LargeCar);      

    }
    public void testAvailableCars() throws UniqueNumberException
    {
        CarRentalSystem crs=CarRentalSystem.getInstance();            

        //create test Driver object
        final String fname="Nick";
        final String lname="Thomas";   
        final String fullname=fname+" "+lname;
        Calendar cal=Calendar.getInstance();
        cal.set(1972, 11, 12);
        final Date dob=cal.getTime();        
        Driver d=new Driver(fname, lname, dob); 

        //create Car test objects    
        final Car c = new SmallCar();
        final Car l=new LargeCar();
        boolean tankfull=true;

        //setup driving licence dateofissue
        Date dateofissue=new Date();
        cal.set(2004, 1, 1);       
        dateofissue=cal.getTime();
        boolean licfull=true;

        //create driving licence        
        DrivingLicence dl=DrivingLicence.issueDrivingLicence(d, dateofissue, licfull);

        //issue car according to specification.
        boolean rented=false;
        Date now = new Date();
        final int dage=crs.getDiffYears(dl.getDriversDob(), now);
        final int timeheld=crs.getDiffYears(dl.getDateOfIssue(), now);
        int available=crs.availableCars(c);
        int availability=30-available;
        int availablelarge=crs.availableCars(l);
        crs.issueCar(c, dl);

        //assertions when SmallCar instance has been issued.
        assertEquals(crs.availableCars(c),available);
        assertTrue(true==c.getRented());
        assertEquals(availablelarge,crs.availableCars(l));

        
    }

    /**Tests that the method returns the correct amount of fuel owed.
     * 
     */
    public void testTerminateRental() throws UniqueNumberException, Exception
    {
        CarRentalSystem crs=CarRentalSystem.getInstance();            

        //create test Driver object
        final String fname="Nick";
        final String lname="Thomas";   
        final String fullname=fname+" "+lname;
        Calendar cal=Calendar.getInstance();
        cal.set(1972, 11, 12);
        final Date dob=cal.getTime();        
        Driver d=new Driver(fname, lname, dob); 

        //create Car test objects    
        final Car c = new SmallCar();
        final Car l=new LargeCar();
        boolean tankfull=true;

        //setup driving licence dateofissue
        Date dateofissue=new Date();
        cal.set(2004, 1, 1);       
        dateofissue=cal.getTime();
        boolean licfull=true;

        //create driving licence        
        DrivingLicence dl=DrivingLicence.issueDrivingLicence(d, dateofissue, licfull);

        //issue car according to specification.
        crs.issueCar(c, dl);
        c.drive(100);
        final int fuelliable=5;
        
        assertEquals(fuelliable,crs.terminateRental(dl));
        
        //issue large car
        crs.issueCar(l, dl);
        l.drive(250);
        final int fuelliable2=18;
        assertEquals(fuelliable2,crs.terminateRental(dl));
        
        
        
        
    }

}
