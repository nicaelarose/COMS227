package hw1;

/**
 * Models a student with a certain amount of money and staying
 * at a current city for a certain amount of nights.
 * @author nicaela
 *
 */
public class Backpacker
{
	/**
	 * Proportionally constant when calling home for more money:
	 * the amount of money received is this constant multiplied
	 * by the number of postcards the Backpacker has sent home.
	 */
	public static final int SYMPATHY_FACTOR = 30;
	
	/**
	 * Name of current City.
	 */
	private City c;
	
	/**
	 * Backpackers journal.
	 */
	private String journal;

	/**
	 * Amount of money Backpacker currently has.
	 */
	private int funds;
	
	/**
	 * Number of nights Backpacker is staying at current City.
	 */
	private int numberOfNights;
	
	/**
	 * Number of postcards sent home.
	 */
	private int postcardSent;
	
	/**
	 * Constructs a Backpacker starting out with the given
	 * amount of money in the given city. 
	 */
	public Backpacker(int initialFunds, City initialCity)
	{
		funds = initialFunds;
		c = initialCity;
		journal = c.getCityName() + "(start)";
	}
	
	/**
	 * Returns the name of the Backpacker's current city.
	 * @return
	 * 	the name of the Backpacker's current city
	 */
	public String getCurrentCity()
	{
		return c.getCityName();
	}
	
	/**
	 * Returns the amount of money the Backpacker currently has.
	 * @return
	 * 	the amount of money the Backpacker currently has
	 */
	public int getCurrentMoney()
	{
		return funds;
	}
	
	/**
	 * Returns the Backpacker's journal.
	 * @return	
	 * 	the Backpacker's journal
	 */
	public String getJournal()
	{
		return journal;
	}
	
	/**
	 * Returns true if Backpacker doesn't have enough money to
	 * send postcard from the current city.
	 * @return	
	 * 	true if Backpacker doesn't have enough money to send
	 * postcard
	 */
	public boolean isSOL()
	{
		boolean sol = false;
		
			if (c.postcardCost() > funds)
			{
				sol = true;
			}
			else
			{
				return sol;
			}
			return sol;
	}
	
	/**
	 * Returns the number of nights the Backpacker has spent in
	 * a train station when visiting a city without enough
	 * money for hostels.
	 * @return
	 * 	the number of nights the Backpacker has spend in a 
	 * 	train station
	 */
	public int getNightsInStation()
	{
		return numberOfNights;
	}
	
	/**
	 * Simulates a visit by this Backpacker to the given city
	 * for the given number of nights.
	 */
	public void visit(City c, int numNights)
	{
		//Update journal variable
		journal = journal + "," + c.getCityName() + "(" + numNights + ")";
		
		this.c = c;

		if (c.hostelCost() * numNights > funds)
		{
			int possibleNights = funds / c.hostelCost();
			funds = funds - (possibleNights * c.hostelCost());
			numberOfNights += numNights - possibleNights;
		}
		else
		{
			funds = funds - numNights * c.hostelCost();
		}
	}
	
	/**
	 * Sends the given number of postcards, if possible, reducing
	 * the Backpacker's funds appropriately and increasing the
	 * count of postcards sent.
	 */
	public void sendPostcardsHome(int howMany)
	{
			int maxCards = funds / c.postcardCost();
			
			int postcardSendingHome = Math.min(maxCards, howMany);
				
			postcardSent = postcardSent + postcardSendingHome;

			funds = funds - postcardSendingHome * c.postcardCost();
	}
	
	/**
	 * Increases the Backpacker's funds by an amount equal to
	 * SYMPATHY_FACTOR times the number of postcards sent
	 * since the last call to this method, and resets the count
	 * of the number of postcards back to zero.
	 */
	public void callHomeForMoney()
	{
		int moneyFromHome = postcardSent * SYMPATHY_FACTOR;
		postcardSent = 0;
		funds = funds + moneyFromHome;
		
	}

}
