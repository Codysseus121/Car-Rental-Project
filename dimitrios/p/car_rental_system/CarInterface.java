package dimitrios.p.car_rental_system;

/**
 * Interface CarInterface is an interface providing functionality to different types of Car objects.
 *
 * @Dimitrios P.
 * @26-02-2018
 */
public interface CarInterface
{
    public RegistrationNumber getRegNo();

    public int getCapacity();

    public boolean getFull();

    public boolean getRented();

    public void setRented(boolean b);

    public int fuelLeft();

    public int addFuel(int extraFuel);

    public int drive(int kms) throws Exception;
}
