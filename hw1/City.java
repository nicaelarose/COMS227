package hw1;

/**
 * Represents a city name along with a cost per night for hotels.
 * @author nicaela
 *
 */
public class City 
{
	/**
	 * The cost to mail a postcard from a city is this fraction
	 * times the city's hostel cost.
	 */
	public static final double POSTCARD_COST = 0.05;
	
	/**
	 * A new City name.
	 */
	private String cityName;
	
	/**
	 * Lodging cost per night.
	 */
	private int lodgingCost;
	
	
	/**
	 * Constructs a new City with the given name and lodging
	 * cost per night.
	 */
	public City(String givenCityName, int givenHostelCost)
	{
		cityName = givenCityName;
		lodgingCost = givenHostelCost;
	}
	
	/**
	 * Returns this city's name.
	 * @return
	 * 	the city's name
	 */
	public String getCityName()
	{
		return cityName;
	}
	
	/**
	 * Returns this city's hostel cost per night.
	 * @return
	 * 	the city's hostel cost per night
	 */
	public int hostelCost()
	{
		return lodgingCost;
	}
	
	/**
	 * Returns the cost to send a postcard from this city. The
	 * value is a percentage of the lodging cost specified
	 * by the constant POSTCARD_COST, rounded to the nearest
	 * integer.
	 * @return
	 * 	the cost to send a postcard from this city
	 */
	public int postcardCost()
	{
		int postcardAmount = (int)Math.round(POSTCARD_COST * lodgingCost);
		return postcardAmount;
	}
	
	/**
	 * Returns the number of nights of hostel stay in this city
	 * that a Backpacker could afford with the given amount of
	 * money.
	 * @return
	 * 	the number of nights of hostel stay in this city
	 */
	public int nightsStay(int moneyAvailable)
	{
		int currentAmount = moneyAvailable / lodgingCost;
		return currentAmount;
	}
	
	/**
	 * Returns the number of postcards that a Backpacker could
	 * afford to send from this city with the given amount of
	 * money.
	 * @return
	 * 	the number of postcards that a Backpacker could afford
	 */
	public int numPostcards(int moneyAvailable)
	{
		int numberOfPostcards = moneyAvailable / (int)Math.round(POSTCARD_COST * lodgingCost);
		return numberOfPostcards;
	}

}
