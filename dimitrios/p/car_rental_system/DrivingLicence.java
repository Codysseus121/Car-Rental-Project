package dimitrios.p.car_rental_system;

import java.util.*;
/**
 * Class DrivingLicence to generate immutable driving licence objects, which inlude the driver's full name,
 * date of birth, the date that the licence was issued, a unique and immutable driving licence number
 * and whether the licence is full or not.
 *
 * @Dimitrios P.
 * @21-2-2018
 */
final class DrivingLicence
{

    private final String driversname;
    private final Date dob;
    private final Date dateofissue;
    private boolean isfull;
    private LicenceNumber ln;
    private static final Map<LicenceNumber, DrivingLicence> LICENCES=new HashMap<LicenceNumber, DrivingLicence>();

    /**
     * Constructor for objects of class DrivingLicence
     */
    private DrivingLicence(String driversname, Date dob, Date dateofissue, LicenceNumber ln, boolean isfull) throws UniqueNumberException
    {
        if (driversname.length()==0)
            throw new IllegalArgumentException("Driver's name cannot be empty.");
        if (driversname==null)
            throw new IllegalArgumentException("Driver's name cannot null.");
        if (dob==null)
            throw new IllegalArgumentException("Driver's date of birth cannot be null");
        if (dateofissue==null)
            throw new IllegalArgumentException();
        if (ln==null)
            throw new IllegalArgumentException("Licence Number cannot be null.");
        this.driversname=driversname;
        this.dob=dob;
        this.dateofissue=dateofissue;
        this.ln=ln;
        this.isfull=isfull;        

    }

    /**
     * Method driversName returns a copy of the driver's full name.           
     */
    public String getDriversName()
    {
        return driversname;

    }

    /**
     * Method getDriversDob returns the driver's date of birth as a Date object
     *      
     */
    public Date getDriversDob()
    {
        return new Date(dob.getTime());
    }

    /**
     * Method isFull() returns whether this is a full licence or not.
     *      
     */
    public boolean isFull()
    {
        return isfull;
    }

    /**
     * Method getDateOfIssue() returns this licence's date of issue as a Date object.
     *      
     */
    public Date getDateOfIssue()    {
        return new Date (dateofissue.getTime());
    }

    /**
     * Method getLicNo() returns this licence's LicenceNumber object.
     *      
     */
    public LicenceNumber getLicNo()
    {
        return ln;
    }

    /** Static Factory Method issueDrivingLicence () to generate unique instances of DrivingLicence class.
     * @param Driver: the Driver object for whom this driving licence is being issued.
     * @param dateofissue: the date this driving licence was issued.
     * @param isfull: whether this is a full driving licence or not.
     */
    public static DrivingLicence issueDrivingLicence(Driver d, Date dateofissue, boolean isfull) throws UniqueNumberException
    {
        if (d==null)
            throw new IllegalArgumentException("Null driver.");
        if (dateofissue==null)
            throw new IllegalArgumentException("Null date of issue.");   

        //Create an instance of class LicenceNumber
        LicenceNumber temp=new LicenceNumber();
        String initials=temp.getInitials(d);
        int year=temp.getYear(dateofissue);
        int serial=temp.getSerial();
        LicenceNumber ln= new LicenceNumber(initials, year, serial);

        //Check if this driving licence object already exists and create unique object.
        DrivingLicence dl=LICENCES.get(ln);
        if (ln==null)
        throw new IllegalArgumentException("Null licence number.");
        String driversname=d.getFullName();        
        Date driverdob=d.getDob();
        if (dl==null)
        {
            dl=new DrivingLicence(driversname, driverdob, dateofissue, ln, isfull);
            LICENCES.put(ln, dl);

        }
        else
            throw new UniqueNumberException();
        return dl;

    }

}
