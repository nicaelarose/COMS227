package miniassignment2;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class S2020Mini2 
{
	private S2020Mini2() {}
	
	public enum Direction 
	{
		UP,
		LEFT,
		DOWN,
		RIGHT
	}
	
	/**
	 * Calculates the coefficients of the expansion of the binomial
	 * expression (x + y)^n.
	 * @param n
	 * 	the degree of the polynomial
	 * @return
	 * 	an array of n+1 terms containing the nth degree binomial
	 *  coefficients
	 */
	public static int[] binomialCoefficients(int n)
	{
		int[] a = new int[n + 1];
		int[][] b = new int[n + 1][n + 1];
		b[0][0] = 1;
		
		for (int i = 1; i <= n; ++i)
		{
			b[i][0] = b[i][i] = 1;
			
			for (int k = 1; k < i; ++k)
			{
				b[i][k] = b[i - 1][k - 1] + b[i - 1][k];
			}
		}
		
		for (int i = n; i ==n; i++)
		{
			for(int j = 0; j <= n; j++)
			{
				a[j] = b[i][j];
			}
		}
		return a;
		
	}
	
	/**
	 * Factors n and return the factors in an array.
	 * @param n
	 * 	the number to be factored
	 * @return
	 * 	an array of the length number of prime factors of n containing
	 * each of the factors in non-decreasing order
	 */
	public static int[] factorization(int n)
	{
		int count = 0;
		int num = n;
		
		for (int i = 2; i <= n; i++)
		{
			while (n % i == 0)
			{
				++count;
				n /= i;
			}
		}
		n = num;
		int[] list = new int[count];
		int index = 0;
		
		for (int i = 2; i <= n; i++)
		{
			while (n % i == 0)
			{
				list[index++] = i;
				n /= i;
			}
		}
		return list;
	}
	
	/**
	 * Reorders a such that all odd values appear before any even values.
	 * @param a
	 * 	an array of integers
	 */
	public static void oddsToFront(int[] a)
	{
		int[] b = new int[a.length];
		
		for (int i = 0; i < a.length; i++)
		{
			b[i] = a[i];
		}
		int left = 0, right = a.length - 1;
		
		for (int i = 0; i < b.length; i++)
		{
			if (b[i] % 2 == 1)
			{
				a[left++] = b[i];
			}
		}
		
		for (int i = b.length - 1; i >= 0; i--)
		{
			if (b[i] % 2 == 0)
			{
				a[right--] = b[i];
			}
		}
		
	}
	
	/**
	 * Returns a new array containing all of the values from a and b
	 * interleaved (where n is the length of the shortest of the two
	 * input arrays) followed by the remaining, non-interleaved values
	 * from the longer (if either) input array.
	 * @param a
	 * 	an array of integers
	 * @param b
	 * 	an array of integers
	 * @return
	 * 	the interleaved output
	 */
	public static int[] interleave(int[] a, int[] b)
	{																
		int[] result = new int[a.length + b.length];
		int index = 0;
		
		for (int i = 0; i < a.length || i < b.length; i++)
		{
			if (i < a.length)
			{
				result[index++] = a[i];
			}
			if (i < b.length)
			{
				result[index++] = b[i];
			}
		}
		return result;
		
	}
	
	/**
	 * Shuffles the contents of a like, e.g., a deck of cards.
	 * @param r
	 * 	an instance of random
	 * @param a
	 * 	an array of integers
	 */
	public static void shuffle(Random r, int[] a)
	{
		for (int i = 0; i < a.length; i ++)
		{
			swap(a, i, r.nextInt(a.length) % a.length);
		}

	}
	
	private static void swap(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	/**
	 * Runs the Langton's Ant cellular automation on the input matrix m
	 * until the ant steps off the matrix or until the simulatipn loop
	 * completes maxSteps iterations.
	 * @param m
	 * 	a two-dimensional array of boolean
	 * @param c
	 * 	the ant's starting column
	 * @param r
	 * 	the ant's starting row
	 * @param d
	 * 	thhe ant's starting direction
	 * @param maxSteps
	 * 	the maximum number of iterations of the simulation
	 */
	public static void langtonsAnt(boolean[][] m, int c, int r, S2020Mini2.Direction d, int maxSteps)
	{
		while(maxSteps-- > 0)
		{
			boolean white = m[r][c];
			
			if (white)
			{
				if (d == Direction.UP)
				{
					d = Direction.RIGHT;
				}
				else if (d == Direction.RIGHT)
				{
					d = Direction.DOWN;
				}
				else if (d == Direction.DOWN)
				{
					d = Direction.LEFT;
				}
				else if (d == Direction.LEFT)
				{
					d = Direction.UP;
				}
			}
			else
			{
				if (d == Direction.DOWN)
				{
					d = Direction.RIGHT;
				}
				else if (d == Direction.LEFT)
				{
					d = Direction.DOWN;
				}
				else if (d == Direction.UP)
				{
					d = Direction.LEFT;
				}
				else if (d == Direction.RIGHT)
				{
					d = Direction.UP;
				}
			}
			
			m[r][c] = !m[r][c];
			
			if (d == Direction.UP)
			{
				r--;
			}
			else if (d == Direction.DOWN)
			{
				r++;
			}
			else if (d == Direction.LEFT)
			{
				c--;
			}
			else if (d == Direction.RIGHT)
			{
				c++;
			}
			
			for (int i = 0; i < m.length; i++)
			{
				for (int j = 0; j < m[i].length; j++)
				{
					System.out.print(m[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			
			if (r < 0 || r >= m.length || c < 0 || c >= m[0].length)
			{
				break;
			}
		}
	}
}
