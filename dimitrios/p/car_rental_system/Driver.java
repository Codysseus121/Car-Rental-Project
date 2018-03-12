package dimitrios.p.car_rental_system;


import java.util.Date;
import java.util.Calendar;

/**
 * Class Driver: a class providing objects to be used for generating driving licences.
 *
 * @Dimitrios P.
 * @21-2-2018
 */
final class Driver 
{
    private final String firstname;
    private final String lastname;
    private Date dob;

    /**
     * Constructor for objects of class Driver
     */
    public Driver(String firstname, String lastname, Date dob)
    {
        if (firstname.length()==0)
        throw new IllegalArgumentException("empty string");
        if (lastname.length()==0) 
        throw new IllegalArgumentException("empty string");
        if (firstname==null)
        throw new NullPointerException("null string");
        if (lastname==null)
        throw new NullPointerException("null string");
        if (dob==null)
        throw new NullPointerException("null date");        
        this.firstname = firstname;
        this.lastname= lastname;
        this.dob=new Date(dob.getTime());
        
    }

    /**Method getFirstName() to return this driver's first name.
     * 
     */
    public String getFirstName()
    {

        return firstname;
    }

    /**Method getLastName() to return this driver's last name.
     * 
     */
    public String getLastName()
    {

        return lastname;
    }

    /**Method getFullName() to return this driver's full name.
     * 
     */
    public String getFullName()
    {
        return firstname + " "+ lastname;
    }

    /**Method getDob to return an immutable copy of this driver's date of birth
     * 
     */
    public Date getDob()
    {

        return (Date) dob.clone();
        
    }

}
