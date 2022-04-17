package hw4;
import java.util.Scanner;

/**
 * The class implements the player character (PC).
 * @author nicaela
 */
public class PlayerCharacter extends Character 
{
	/**
	 * PC is alive.
	 */
	private boolean alive;
	
	/**
	 * Constructs a new PC at position (x, y) in the tableau.
	 *
	 * @param x The PCs initial x position.
	 * @param y The PCs initial y position.
	 */
	public PlayerCharacter(int x, int y)
	{
		super(x, y);
		alive = true;
	  
	}

	/**
	 * Returns true if and only if the PC is still alive.
	 *
	 * @return Whether or not the PC lives
	 */
	public boolean isAlive()
	{
		return alive;
	}

	/**
	 * Marks the PC as no longer being alive.
	 */
	public void die()
	{
		alive = false;
	}

	/**
	 * Stringifies the PC.
	 *
	 * @return The stringified PC
	 */
	@Override
	public String toString()
	{
		return "@";
	}
	
	/**
	 * This method should never be called.
	 *
	 * @param c Never called
	 * @param t Never called
	 * @return Never called
	 */
	public Cell collideWith(Cell c, Tableau t)
	{
		return this;
	}

	/**
	 * Handles player I/O.  Returns the new (possibly unchanged) position of
	 * the PC.
	 *
	 * @param t The tableau
	 * @return The new position of the PC
	 */
	public Pair moveTo(Tableau t) 
	{
		Scanner sc = new Scanner(System.in);
		boolean goodPosition = false;
		int moveX, moveY;
		do 
		{
			moveX = xPos;
			moveY = yPos;

			System.out.print("; your move: ");

			switch(sc.next().charAt(0)) 
			{
				case 'h':
					moveX--;
					break;
				case 'j':
					moveY++;
					break;
				case 'k':
					moveY--;
					break;
				case 'l':
					moveX++;
					break;
				case 'y':
					moveX--;
					moveY--;
					break;
				case 'u':
					moveX++;
					moveY--;
					break;
				case 'b':
					moveX--;
					moveY++;
					break;
				case 'n':
					moveX++;
					moveY++;
					break;
				case '.':
					goodPosition = true;
					break;
				case 'z':
					if (t.hasZap()) 
					{
						doZap(t);
						goodPosition = true;
					}
					break;
				case 'w':
					goodPosition = t.startWait();
					break;
				case 'q':
					System.exit(0);
					break;
				default:
			}

			if (moveX >= 0 && moveX < t.getX() && moveY >= 0 && moveY < t.getY() &&
					t.getCell(moveX, moveY) == null) 
			{
				goodPosition = true;
			}

			if (!goodPosition) 
			{
				System.out.print(t + " Invalid move");
			}
		} while (!goodPosition);

		return new Pair(moveX, moveY);
	}

	/**
	 * Gets a valid direction from the player and zaps a ray in that
	 * direction.  Rays destroy all robots and rubble (but not rock) in a
	 * straight line in a single direction from the PC to the edge of the
	 * tableau.
	 *
	 * @param The tableau
	 */
	void doZap(Tableau t)
	{
		if (t.hasZap())
		{
			Scanner sc = new Scanner(System.in);
			boolean goodPosition = false;
			boolean north = false;
			boolean south = false;
			boolean east = false;
			boolean west = false;
			
			do {
				System.out.print("; your zap direction: ");
				
				switch (sc.next().charAt(0)) {
					case 'h':
						west = true;
						goodPosition = true;
						break;
					case 'j':
						north = true;
						goodPosition = true;
						break;
					case 'l':
						east = true;
						goodPosition = true;
						break;
					case 'q':
						System.exit(0);
						break;
					default:
				}
				if (!goodPosition)
				{
					System.out.print(t + " Invalid move");
				}
			}
			while (!goodPosition);
			
			if (west)
			{
				for (int x = xPos; x >= 0; x--)
				{
					t.getCell(x,yPos).getZapped();
				}
			}
			if (east)
			{
				for (int x = xPos; x < t.getX(); x++)
				{
					t.getCell(x,yPos).getZapped();
				}
			}
			if (south)
			{
				for (int y = yPos; y >= 0; y--)
				{
					t.getCell(xPos,y).getZapped();
				}
			}
			if (north)
			{
				for (int y = yPos; y < t.getY(); y++)
				{
					t.getCell(xPos,y).getZapped();
				}
			}
			t.useZap();
		}
		else
		{
			System.out.print(t + "No more zaps left");
		}
	}


	/**
	 * Handles the situation where a Cell is zapped (by a ray or an
	 * exploding robot).  Zapping vaporizes (no rubble) everything except
	 * PermanentRock (which isn't effected.  Returns true if and only if the
	 * value of the cell should be changed to null.  The PC is killed by a zap.
	 *
	 * @return Whether or not the cell should be nullified
	 */
	@Override
	public boolean getZapped()
	{
		this.die();
		return true;
	}

	/**
	 * Handles the situation where a Cell is hit by a (non-exploding) robot.
	 * Getting hit will leave rock or rubble if cell was rock or rubble, will
	 * leave rubble if cell was a robot.  Will cause an explosion if cell is
	 * exploding robot.  Returns the value that should be placed in cell after
	 * hit, and marks the PC as dead.
	 *
	 * @param t The tableau
	 * @param by The thing doing the hitting
	 * @return New value for cell
	 */
	@Override
	public Cell getHit(Tableau t, Robot by)
	{
		alive = false;

		return by;
	}

	/**
	 * Signals whether or not a cell can be removed (from Tableau's robot
	 * list).  Robots return true; everything else false.  The PC should be
	 * marked dead.
	 *
	 * @return false
	 */
	@Override
	public boolean removable()
	{
		alive = false;

		return false;
	}
}