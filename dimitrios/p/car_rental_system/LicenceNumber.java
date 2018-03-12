package dimitrios.p.car_rental_system;

import java.util.*;

/**
 * class LicenceNumber generates a random driving licence number.
 *
 * @Dimitrios P.
 * @21/02/2018
 */
final class LicenceNumber
{
    private final static Random RANDOM=new Random();
    private String initials;    
    private int yearofissue;
    private int serial;

    /**Constructor for objects of class LicenceNumber
     * 
     */
    public LicenceNumber ()
    {
    }

    /**Constructor for objects of class LicenceNumber
     * 
     */
    public LicenceNumber(String initials, int yearofissue, int serial)
    {
        if (initials.length()==0 || initials==null)
        throw new IllegalArgumentException("String empty.");
        if (yearofissue==0)
        throw new IllegalArgumentException("Year of issue cannot be 0.");    
        this.initials=initials;
        this.yearofissue=yearofissue;
        this.serial=serial;

    }

    /** Method generateInitials() to return a String representation of a Driver object's name initials.
     * @param Driver: the driver object.
     */
    public String getInitials(Driver d)
    {
        if (d==null)
            throw new IllegalArgumentException();        
        String initials="";
        char f=d.getFirstName().charAt(0);
        char l=d.getLastName().charAt(0);
        initials=String.valueOf(f)+String.valueOf(l);
        return initials;

    }

    /**Method getYear returns an int representing this Driver's year of birth.
     * @param Driver.
     */
    public int getYear(Date d)
    {
        if (d==null)
        throw new IllegalArgumentException();
        Calendar cal=Calendar.getInstance();
        cal.setTime(d);
        int year=cal.get(Calendar.YEAR);
        return year;
    }

    /** Method getSerial() to generate a random serial number (positive or negative.)
     * 
     */
    public int getSerial()
    {
        int serial=0;
        serial = RANDOM.nextInt();
        return serial;
    }

    /**Method toString().
     */
    public String toString () {
        String licrep=initials+"-"+yearofissue+"-"+serial;
        return licrep;

    }
}
