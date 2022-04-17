package hw4;

/**
 * Class to represent smart robots.  Smart robots will not collide with
 * rubble or other robots (however, other robots can collide with smart
 * robots.
 * @author nicaela
 */
public class SmartRobot extends Robot 
{
	/**
	 * Stringifies a smart robot.
	 *
	 * @return The stringified robot
	 */
	@Override
	public String toString()
	{
		return "S";
	}

	/**
	 * Constructs a new smart robot at position (x, y).
	 *
	 * @param x The x position of the robot
	 * @param y The y position of the robot
	 */
	public SmartRobot(int x, int y)
	{
		super(x, y);
	}

	/**
	 * Smart robots move toward the PC is at least one dimension, both if
	 * possible, but only if there exists a move which doesn't result in a
	 * collision.  A smart robot will never collide with obstrutions or other
	 * robots.  It can get stuck behind an obstruction (or, optionally, you can
	 * implement some more intelligent pathfinding around obsructions, but that
	 * is harder to code, and make an already very hard game harder, as well).
	 *
	 * @param t The tableau
	 * @return The new position
	 */
	public Pair moveTo(Tableau t) 
	{
		Cell PC = t.getPC();
		int pcX = PC.getX() - xPos;
		int pcY = PC.getY() - yPos;
		int newX;
		int newY;
		
		newX = xPos;
		newY = yPos;
		if (pcX > 0 && (!(t.getCell(newX + 1, newY) instanceof Obstruction) || !(t.getCell(newX + 1, newY) instanceof Robot)))
		{
			newX++;
		}
		if (pcX < 0 && (!(t.getCell(newX - 1, newY) instanceof Obstruction) || !(t.getCell(newX - 1, newY) instanceof Robot)))
		{
			newX--;
		}
		if (pcY > 0 && (!(t.getCell(newX, newY + 1) instanceof Obstruction) || !(t.getCell(newX, newY + 1) instanceof Robot)))
		{
			newY++;
		}
		if (pcY < 0 && (!(t.getCell(newX, newY - 1) instanceof Obstruction) || !(t.getCell(newX, newY - 1) instanceof Robot)))
		{
			newY--;
		}
		return new Pair(newX, newY);
	}
}