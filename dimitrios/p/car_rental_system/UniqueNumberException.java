package dimitrios.p.car_rental_system;


/**
 * Class UniqueNumberException throws a UniqueNumberException when two randomly generated numbers are the identical.
 *
 * Dimitrios P.
 * @3-3-2013
 */
class UniqueNumberException extends Exception
{
    

    /**
     * Constructor for objects of class UniqueNumberException
     */
    public UniqueNumberException()
    {
        super("Number already exists.");
    }

    
}
