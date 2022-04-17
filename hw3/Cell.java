package hw3;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Conway's Game of Life Cell Class.
 * The game begins with a two dimensional grid of cells (known as the "seed").
 * Each cell is in one of two states, "alive" or "dead".
 * @author nicaela
 *
 */
public class Cell 
{
	/**
	 * Initial state of cell
	 */
	private boolean alive;
	
	/**
	 * Status of cell in next generation
	 */
	private boolean aliveAfterNextGen;
	
	/**
	 * Born rules of Cell
	 */
	private int bornRule[];
	
	/**
	 * Survive rules of Cell
	 */
	private int surviveRule[];
	
	/**
	 * ArrayList of Cells of the neighboring Cells
	 */
	private ArrayList<Cell> neighbors = new ArrayList<>();
	
	/**
	 * Constructs a new cell in a Game of Life
	 * @param alive the initial state of the cell
	 * @param b an array of integers encoding the born rules for the cell
	 * @param s an array of integers encoding the survive rules for the cell
	 */
	public Cell(boolean alive, int[] b, int[] s)
	{
		this.neighbors = new ArrayList<>();
		this.alive = alive;
		this.bornRule = b;
		this.surviveRule = s;
	}
	
	/**
	 * Returns the boolean value of whether the cell is dead or alive. 
	 * 
	 * @return the boolean value of whether the cell is dead or alive.
	 */
	public boolean isAlive() 
	{
		return alive;
	}
	
	/**
	 * Returns the boolean value of whether the cell is dead or alive in the next generation.
	 * 
	 * @return the boolean value of whether the cell is dead or alive in the next generation.
	 */
	public boolean isAliveAfterNextGeneration() 
	{
		return aliveAfterNextGen;
	}
	
	/**
	 * Sets the status of the cell (dead or alive) in the next generation. The status is determined by the born and alive rules.
	 */
	public void setIsAliveNextGeneration() 
	{
		if (alive)
		{
			for (int i = 0; i < surviveRule.length; i++)
			{
				if (surviveRule[i] == getNumAliveNeighbors())
				{
					aliveAfterNextGen = true;
					return;
				}
			}
			aliveAfterNextGen = false;
		}
		else
		{
			for (int i = 0; i < bornRule.length; i++)
			{
				if (bornRule[i] == getNumAliveNeighbors())
				{
					aliveAfterNextGen = true;
					return;
				}
			}
			aliveAfterNextGen = false;
		}
	}
	
	/**
	 * Sets the neighbors of the cell. Note that the cell is not neighbors with itself.
	 * 
	 *  @param ArrayList of Cells of the neighboring cells.
	 * 
	 */
	public void setNeighbors(ArrayList<Cell> n) 
	{
		this.neighbors = n;
	}
	
	/**
	 * Gets the neighbors of the cell. Note that the cell is not neighbors with itself.
	 * 
	 *  @return number of the neighboring cells.
	 * 
	 */
	public int getNumNeighbors() 
	{
		return this.neighbors.size();
	}
	
	/**
	 * Gets the number of neighbors which are alive.
	 * 
	 * @return number of neighboring cells which are alive
	 */
	public int getNumAliveNeighbors() 
	{
		int numOfNeighbors = 0;
		for (int i = 0; i < this.neighbors.size(); i++)
		{
			if (this.neighbors.get(i).isAlive())
			{
				numOfNeighbors++;
			}
		}
		return numOfNeighbors;
	}
	
	/**
	 * Sets the current status of the cell equal to the status of the cell after one generation of the game. 
	 */
	public void nextGeneration() 
	{
		alive = aliveAfterNextGen;
	}
	
	/**
	 * Returns the survive rule of the cell encoded in an array.
	 * 
	 * @return a copy of the born rule this cell is initialized with.
	 */
	public int[] getBornRule() 
	{
		int[] rules = new int[bornRule.length];
		for (int i = 0; i < bornRule.length; i++)
		{
			rules[i] = bornRule[i];
		}
		return rules;
	}
	
	/**
	 * Returns the survive rule of the cell encoded in an array.
	 * 
	 * @return a copy of the survive rule this cell is initialized with.
	 */
	public int[] getSurviveRule() 
	{
		int[] rules = new int[surviveRule.length];
		for (int i = 0; i < surviveRule.length; i++)
		{
			rules[i] = surviveRule[i];
		}
		return rules;
	}
	
	/**
	 * Returns a String representation of the cell. Returns "0" if the cell is dead or "1" if the cell is alive.
	 * 
	 * @return a string representing the current state of the cell.
	 */
	public String toString() {
		if(alive) {
			return "1";
		}
		else {
			return "0";
		}
	}
	
}

