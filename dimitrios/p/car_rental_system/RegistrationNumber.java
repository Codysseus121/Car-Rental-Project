package dimitrios.p.car_rental_system;
import java.util.Random;
import java.util.*;

/**class RegistrationNumber is a class for generating random, unique and immutable
 *car registration numbers by using the static factory method generateRegNo().
 *@author:Dimitrios P.
 *@Date:21/02/2018
 * 
 */
final class RegistrationNumber {

    private final String registrationnumber, letters, digits;
    private static Map <String, RegistrationNumber> REG_NO = new HashMap <String, RegistrationNumber>();
    private final static Random RANDOM=new Random();

    private RegistrationNumber(String letters, String digits, String registrationnumber)
    {
        if (registrationnumber.length()==0 || registrationnumber==null)
        throw new IllegalArgumentException("String empty.");
        if (letters.length()==0 || letters==null)
        throw new IllegalArgumentException("String empty.");
        if (digits.length()==0 || digits==null)
        throw new IllegalArgumentException("String empty.");       
        
        this.registrationnumber=registrationnumber;
        this.letters=letters;
        this.digits=digits;
        
    }

    /** A method to generate a random sequence of letters
     * 
     */
    private static String generateLetters(int amount) {
        if (amount==0)
        throw new IllegalArgumentException();
        String letters = "";
        final int min = 'A';
        final int max = 'Z';
                for (int i=0;i<amount;i++)
        {
            char c = (char) (RANDOM.nextInt((max-min)+1)+min);
            letters += c;
        }
        return letters;
    }

    /** A method to generate a random sequence of digits
     * 
     */
    private static String generateDigits(int amount)
    {
        if (amount==0)
        throw new IllegalArgumentException();
        String digits="";
        final int min='0';
        final int max='9';       
        for (int i=0;i<amount;i++)
        {
            char c=(char) (RANDOM.nextInt((max-min)+1)+min);
            digits +=c;
        }
        return digits;
    }

    /** A static factory method to generate a unique car registration number,
     * consisting of one letter and four digits, as per the specification.
     * 
     */
    static RegistrationNumber generateRegNo() throws UniqueNumberException
    {
        final String letters=generateLetters(1);
        final String digits=generateDigits(4);
        final String registrationnumber=letters+digits;
        RegistrationNumber r=REG_NO.get(registrationnumber);
        if (r==null)
        {
            r=new RegistrationNumber(letters, digits, registrationnumber);
            REG_NO.put(registrationnumber, r);
        }
        else
        throw new UniqueNumberException();
        return r;

    }

    /**A method to return this object's letter sequence
     * 
     */
    public String getLetters()
    {
        return letters;
    }

    /**A method to return this object's digit sequence
     * 
     */
    public String getdigits()
    {
        return digits;
    }

    /**A toString method to return this object's String representation of the registration number object.
     * 
     */
    public String toString()
    {
        return registrationnumber;
    }

}
