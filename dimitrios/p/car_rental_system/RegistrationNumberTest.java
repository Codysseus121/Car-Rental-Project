package dimitrios.p.car_rental_system;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;

/**
 * The test class RegistrationNumberTest.
 *
 * @Dimitrios P.
 * @3-3-2018
 */
public class RegistrationNumberTest
{
    /**
     * Default constructor for test class RegistrationNumberTest. The RegistratioNumber class itself tests for uniqueness anyway.
     * This test class just tests that two numbers are not the same.
     */
    public RegistrationNumberTest()
    {
    }

    
    public static void main (String [] args) throws UniqueNumberException
    {

        RegistrationNumberTest rntest=new RegistrationNumberTest();
        rntest.testGenerateRegNo();
        

    }

    /**A test method to test that the class RegistrationNumber generates unique numbers.
     * 
     */
    public void testGenerateRegNo() throws UniqueNumberException
    {
        final RegistrationNumber r=RegistrationNumber.generateRegNo();
        String digits="";
        final int min='0';
        final int max='9';
        final Random randomNumbers= new Random();
        final int amount=4;
        for (int i=0;i<amount;i++)
        {
            char c=(char) (randomNumbers.nextInt((max-min)+1)+min);
            digits +=c;
        }
        final String testRegNo="X"+digits;
        assertNotEquals(testRegNo,r);

    }
    
        
}
