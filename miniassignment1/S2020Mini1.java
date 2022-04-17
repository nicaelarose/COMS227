package miniassignment1;
import java.util.Random;

/**
 * Set of problems involving writing loops.
 * @author nicaela
 *
 */
public class S2020Mini1 
{
	
	private S2020Mini1()
	{}
	
		/**
		 * Calculates the square root of d using Newton's Method.
		 * @param d
		 * 	a number
		 * @return
		 * 	the square root of d
		 * 	
		 */
		public static double squareRoot(double d)
		{
			double number = d / 2;
			double secondNumber = number;
			
			do 
			{
				number = secondNumber;
				secondNumber = (number + d / number) / 2;
			}
			
			while (number != secondNumber);
			{
				return number;
			}
			
		}
		
		/**
		 * Finds the first occurrence of the smallest character in the
		 * string s and returns its index.
		 * @param s
		 * 	the input string
		 * @return
		 * 	the index of the lexicographically smallest character in s
		 */
		public static int findSmallest(String s)
		{
			int smallest = 0;
			
			for (int i = 0; i < s.length(); i++)
			{
				if (s.charAt(smallest) > s.charAt(i))
				{
					smallest = i;
				}
			}
			return smallest;
		}
		
		/**
		 * Given an instance of Random, r, an upper bound, limit, and a 
		 * sequence length, length, returns the number of random numbers 
		 * generated after reading the first ascending (by 1) sequence of 
		 * numbers of length length.
		 * @param r
		 * 	an instance of Random
		 * @param limit
		 * 	a modulus (to be passed to Random.nextInt())
		 * @param length
		 * 	the length of the ascending sequence
		 * @return
		 * 	the number of random numbers generated immediately upon 
		 * completing the first sufficiently long sequence
		 */
		
		public static int sequenceOfLength(Random r, int limit, int length)
		{			
			if (length <= 0)
			{
				return 0;
			}
			
			int previous = r.nextInt(limit);
			int count = 1;
			int sequence = 1;
			
			while (sequence < length)
			{
				int next = r.nextInt(limit);
				count++;
				
				if (next > previous)
				{
					sequence++;
				}
				else
				{
					sequence = 1;
				}
				previous = next;
			}
			return count;
		}
		
		/**
		 * Returns the index of the start of the next instance of sub in s at or
		 * after index start, or -1 if the substring doesn't occur after the 
		 * given index.
		 * @param s
		 * 	a string to be searched
		 * @param sub
		 * 	a string to be searched for
		 * @param start
		 * 	an index at which to begin searching
		 * @return
		 * 	the index of the substring, or -1
		 */
		public static int nextIndexOf(String s, String sub, int start)
		{
			if (start < 0)
			{
				return -1;
			}
				for (int i = start; i < s.length() - sub.length() + 1; i++)
				{
					if (s.substring(i, i + sub.length()).equals(sub))
					{
						return i;
					}
				}
			return -1;
		}
			
		
		/**
		 * Returns the number of positions in which the input strings differ.
		 * @param s
		 * 	input string
		 * @param t
		 * 	input string
		 * @return
		 * 	the number of positions in which s differs from t
		 */
		public static int countDifferences(String s, String t)
		{
			int size = Math.min(s.length(), t.length());
			int count = 0;
			for (int i = 0; i < size; i++)
			{
				if (s.charAt(i) != t.charAt(i))
				{
					count++;
				}
			}
			count += Math.abs(s.length() - t.length());
			return count;
			
		}
		
		/**
		 * Returns true if and only if seq is a geometric sequence; 
		 * that is, the values in seq change by a constant multiple.
		 * @param seq
		 * 	a string containing a comma-delimited sequence of integers
		 * @return
		 * 	true if and only if the sequence is geometric
		 */
		public static boolean isGeometric(String seq)
		{
			String[] s = seq.split(",");
			for (int i = 0; i < s.length - 2; i++) 
			{
				int a = Integer.parseInt(s[i]);
				int b = Integer.parseInt(s[i + 1]);
				int c = Integer.parseInt(s[i + 2]);
				
				if ((b / a) != (c / b))
				{
					return false;
				}
			}
			return true;
		}
		
		/**
		 * Returns a new string constructed from the input such that all 
		 * instances of characters not next to an equivalent character in 
		 * the input are removed.
		 * @param s
		 * 	a string
		 * @return
		 * 	s with all "single" characters removed
		 */
		public static String removeSingles(String s)
		{
			String result = "";
			
			for (int i = 0; i + 1 < s.length(); i++)
			{
				if (s.charAt(i) == s.charAt(i + 1))
				{
					result += s.charAt(i);
					result += s.charAt(i + 1);
					i++;
				}
			}
			return result;
		}
}