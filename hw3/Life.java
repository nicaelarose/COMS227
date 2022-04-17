package hw3;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;


/**
 * Simulates a whole instance of Conway's Game of Life.
 * @author nicaela
 *
 */
public class Life 
{
	/**
	 * Cell position on grid
	 */
	private Cell grid[][];
	
	/**
	 * Born rules of Cell
	 */
	private int bornRule[];
	
	/**
	 * Survive rules of Cell
	 */
	private int surviveRule[];
	
	/**
	 * Constructs Conway's Game of Life give a starting grid (a "seed"), the born rules and the survive rules.  The grid is given as an array of strings as in the following example.
	 * 
	 * 0 0 0 0 0
	 * 0 0 1 0 0
	 * 0 0 1 0 0
	 * 0 0 1 0 0
	 * 0 0 0 0 0
	 * 
	 * @param initConfig an array of Strings encoding the starting grid
	 * @param born an array of integers encoding the rules for a cell being born. 
	 * @param survive an array of integers encoding the rules for a cell surviving. 
	 */
	public Life(String[] initConfig, int[] born, int[] survive) 
	{
		bornRule = new int[born.length];
		for (int i = 0; i < bornRule.length; i++)
		{
			bornRule[i] = born[i];
		}
		
		surviveRule = new int[survive.length];
		for (int i = 0; i < surviveRule.length; i++)
		{
			surviveRule[i] = survive[i];
		}
		gridPlacement(initConfig);
	}
	
	/**
	 * Constructs Conway's Game of Life give a starting grid (a "seed"), the born rules 
	 * and the survive rules.  The grid is given in a file containing a list of strings
	 * as in the following example.
	 * 
	 * 0 0 0 0 0
	 * 0 0 1 0 0
	 * 0 0 1 0 0
	 * 0 0 1 0 0
	 * 0 0 0 0 0
	 * 
	 * @param f the File encoding the starting grid
	 * @param born an array of integers encoding the rules for a cell being born. 
	 * @param survive an array of integers encoding the rules for a cell surviving. 
	 * @throws FileNotFoundException
	 */
	public Life(File f, int[] born, int[] survive) throws FileNotFoundException
	{
		Scanner file = new Scanner(f);
		ArrayList<String> list = new ArrayList<String>();
		while (file.hasNextLine())
		{
			String line = file.nextLine();
			list.add(line);
		}
		
		String[] arr = new String[list.size()];
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = list.get(i);
		}
		file.close();
		
		bornRule = new int[born.length];
		for (int i = 0; i < bornRule.length; i++)
		{
			bornRule[i] = born[i];
		}
		surviveRule = new int[survive.length];
		for (int i = 0; i < surviveRule.length; i++)
		{
			surviveRule[i] = survive[i];
		}
		gridPlacement(arr);
	}
	
	/**
	 * Returns cell at specified position
	 * @param row index of the row 
	 * @param col index of the column
	 * @return Cell at position (row, col)
	 */
	public Cell getCell(int row, int col)
	{
		return grid[row][col];
	}
	
	/**
	 * Returns the number of rows in the Game of Life
	 * @return number of rows in grid
	 */
	public int getRows() 
	{
		return grid.length;
	}
	
	
	/**
	 * Returns the number of columns in the Game of Life
	 * @return number of columns in grid
	 */
	public int getColumns() 
	{
		return grid[0].length;
	}
	
	/**
	 * Performs one generation of the game
	 * 
	 */
	public void nextGeneration() 
	{
		for (int i = 0; i < grid.length; i++)
		{
			for (int j = 0; j < grid[i].length; j++)
			{
				ArrayList<Cell> gen = new ArrayList<Cell>();
				
				for (int k = -1; k < 2; k++)
				{
					for (int l = -1; l < 2; l++)
					{
						if ((i + k >= 0) && (j + l >= 0) && (i + k < grid.length) && (j + l < grid[i].length))
						{
							if (!(k == 0 && l == 0))
							{
								gen.add(new Cell(grid[i + k][j + l].isAlive(), bornRule, surviveRule));
							}
						}
					}
				}
				grid[i][j].setNeighbors(gen);
			}
		}
		for (int i = 0; i < grid.length; i++)
		{
			for (int j = 0; j < grid[i].length; j++)
			{
				grid[i][j].setIsAliveNextGeneration();
				grid[i][j].nextGeneration();
			}
		}
	}
	
		
	/**
	 * Returns a String representation of the game. Returns the String representation of each element of the game in a grid.
	 * 
	 * @return a string representing the current state of the game.
	 */
	public String toString() 
	{
		String str = "";
		for (int i = 0; i < grid.length; i++)
		{
			for (int j = 0; j < grid[i].length; j++)
			{
				str = str + grid[i][j] + " ";
			}
			str = str + "\n";
		}
		return str;
	}
	
	/**
	 * Sets the grid with Cell positions
	 * @param initConfig an array of Strings encoding the starting grid
	 */
	private void gridPlacement(String[] initConfig)
	{
		grid = new Cell[initConfig.length][];
		for (int i = 0; i < initConfig.length; i++)
		{
			String[] set = initConfig[i].split(" ");
			grid[i] = new Cell[set.length];
		
			for (int j = 0; j < grid[i].length; j++)
			{
				if (set[j].equals("1"))
				{
					grid[i][j] = new Cell(true, bornRule, surviveRule);
				}
				else
				{
					grid[i][j] = new Cell(false, bornRule, surviveRule);
				}
			}
		}
	}
}
