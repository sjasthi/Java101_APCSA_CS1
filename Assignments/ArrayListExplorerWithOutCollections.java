import java.util.ArrayList;
//Note 1: You should NOT be importing any other classes other than java.util.ArrayList
//Note 1: The main() method contains the code for testing your implementation.
//        The main() method should not be changed under any circumstances.

/**
 * CS1: Assignment 4 The ArrayListExplorer class provides utility methods for
 * analyzing and manipulating integer lists. It includes methods for finding
 * minimum and maximum values, checking for duplicates, calculating sums and
 * averages, and more.
 * 
 */
public class ArrayListExplorerWithOutCollections {

	/**
	 * Finds the minimum value in the list.
	 * 
	 * @param list the input list
	 * @return the minimum value in the list
	 * @throws IllegalArgumentException if the list is null or empty
	 */
	public static int min(ArrayList<Integer> list) {
		// To Be implemented
		return -1;
	}

	/**
	 * Finds the maximum value in the list.
	 * 
	 * @param list the input list
	 * @return the maximum value in the list
	 * @throws IllegalArgumentException if the list is null or empty
	 */
	public static int max(ArrayList<Integer> list) {
		// To Be implemented
		return -1;
	}

	/**
	 * Checks if the list contains duplicate values.
	 * 
	 * @param list the input list
	 * @return true if duplicates are found, false otherwise
	 * @throws IllegalArgumentException if the list is null
	 */
	public static boolean containsDuplicates(ArrayList<Integer> list) {
		// To Be implemented
		return false;
	}

	/**
	 * Calculates the sum of all elements in the list.
	 * 
	 * @param list the input list
	 * @return the sum of the elements
	 * @throws IllegalArgumentException if the list is null
	 */
	public static int sum(ArrayList<Integer> list) {
		// To Be implemented
		return -1;
	}

	/**
	 * Calculates the average of the elements in the list.
	 * 
	 * @param list the input list
	 * @return the average of the elements
	 * @throws IllegalArgumentException if the list is null or empty
	 */
	public static double average(ArrayList<Integer> list) {
		// To Be implemented
		return -1;
	}

	/**
	 * Returns the length of the list.
	 * 
	 * @param list the input list
	 * @return the length of the list
	 * @throws IllegalArgumentException if the list is null
	 */
	public static int length(ArrayList<Integer> list) {
		// To Be implemented
		return -1;
	}

	/**
	 * Counts the occurrences of a specific element in the list.
	 * 
	 * @param list    the input list
	 * @param element the element to count
	 * @return the count of occurrences
	 * @throws IllegalArgumentException if the list is null
	 */
	public static int count(ArrayList<Integer> list, int element) {
		// To Be implemented
		return -1;
	}

	/**
	 * Calculates the sum of all even numbers in the list.
	 * 
	 * @param list the input list
	 * @return the sum of even numbers
	 * @throws IllegalArgumentException if the list is null
	 */
	public static int sumOfEvens(ArrayList<Integer> list) {
		// To Be implemented
		return -1;
	}

	/**
	 * Checks if the list contains a specific element.
	 * 
	 * @param list    the input list
	 * @param element the element to check
	 * @return true if the element is found, false otherwise
	 * @throws IllegalArgumentException if the list is null
	 */
	public static boolean contains(ArrayList<Integer> list, int element) {
		// To Be implemented
		return false;
	}

	/**
	 * Finds the index of a specific element in the list.
	 * 
	 * @param list    the input list
	 * @param element the element to find
	 * @return the index of the element, or -1 if not found
	 * @throws IllegalArgumentException if the list is null
	 */
	public static int index(ArrayList<Integer> list, int element) {
		// To Be implemented
		return 1;
	}

	/**
	 * Retrieves the element at the specified index.
	 * 
	 * @param list  the input list
	 * @param index the index of the element
	 * @return the element at the specified index
	 * @throws IllegalArgumentException if the list is null or index is out of
	 *                                  bounds
	 */
	public static int getItem(ArrayList<Integer> list, int index) {
		// To Be implemented
		return -1;
	}

	/**
	 * Converts the list to a string representation.
	 * 
	 * @param list the input list
	 * @return a string representation of the list
	 * @throws IllegalArgumentException if the list is null
	 */
	public static String toString(ArrayList<Integer> list) {
		// To Be implemented
		return "";
	}

	/**
	 * The main() method contains the code for testing your implementation. The
	 * main() method should not be changed under any circumstances.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Integer> a_list = new ArrayList<>();
		a_list.add(1);
		a_list.add(2);
		a_list.add(3);
		a_list.add(4);
		a_list.add(6);
		a_list.add(3);
		a_list.add(2);

		int score = 0;

		System.out.println("Min Test: Expected: 1");
		System.out.println("Min Test: Actual: " + min(a_list));
		if (min(a_list) == 1) {
			System.out.println("Min Test: Passed");
			score += 2;
		} else {
			System.out.println("Min Test: Failed");
		}
		System.out.println();

		System.out.println("Max Test: Expected: 6");
		System.out.println("Max Test: Actual: " + max(a_list));
		if (max(a_list) == 6) {
			System.out.println("Max Test: Passed");
			score += 2;
		} else {
			System.out.println("Max Test: Failed");
		}
		System.out.println();

		System.out.println("Contains Duplicates Test: Expected: true");
		System.out.println("Contains Duplicates Test: Actual: " + containsDuplicates(a_list));
		if (containsDuplicates(a_list)) {
			System.out.println("Contains Duplicates Test: Passed");
			score += 2;
		} else {
			System.out.println("Contains Duplicates Test: Failed");
		}
		System.out.println();

		System.out.println("Sum Test: Expected: 21");
		System.out.println("Sum Test: Actual: " + sum(a_list));
		if (sum(a_list) == 21) {
			System.out.println("Sum Test: Passed");
			score += 2;
		} else {
			System.out.println("Sum Test: Failed");
		}
		System.out.println();

		System.out.println("Average Test: Expected: 3.0");
		System.out.println("Average Test: Actual: " + average(a_list));
		if (average(a_list) == 3.0) {
			System.out.println("Average Test: Passed");
			score += 2;
		} else {
			System.out.println("Average Test: Failed");
		}
		System.out.println();

		System.out.println("Length Test: Expected: 7");
		System.out.println("Length Test: Actual: " + length(a_list));
		if (length(a_list) == 7) {
			System.out.println("Length Test: Passed");
			score += 2;
		} else {
			System.out.println("Length Test: Failed");
		}
		System.out.println();

		System.out.println("Count Test for 3: Expected: 2");
		System.out.println("Count Test for 3: Actual: " + count(a_list, 3));
		if (count(a_list, 3) == 2) {
			System.out.println("Count Test for 3: Passed");
			score += 1;
		} else {
			System.out.println("Count Test for 3: Failed");
		}
		System.out.println();

		System.out.println("Count Test for 100: Expected: 0");
		System.out.println("Count Test for 100: Actual: " + count(a_list, 100));
		if (count(a_list, 100) == 0) {
			System.out.println("Count Test for 100: Passed");
			score += 1;
		} else {
			System.out.println("Count Test for 100: Failed");
		}
		System.out.println();

		System.out.println("Sum of Evens Test: Expected: 14");
		System.out.println("Sum of Evens Test: Actual: " + sumOfEvens(a_list));
		if (sumOfEvens(a_list) == 14) {
			System.out.println("Sum of Evens Test: Passed");
			score += 2;
		} else {
			System.out.println("Sum of Evens Test: Failed");
		}
		System.out.println();

		System.out.println("Contains Test for 4: Expected: true");
		System.out.println("Contains Test for 4: Actual: " + contains(a_list, 4));
		if (contains(a_list, 4)) {
			System.out.println("Contains Test for 4: Passed");
			score += 2;
		} else {
			System.out.println("Contains Test for 4: Failed");
		}
		System.out.println();

		System.out.println("Index Test for 4: Expected: 3");
		System.out.println("Index Test for 4: Actual: " + index(a_list, 4));
		if (index(a_list, 4) == 3) {
			System.out.println("Index Test for 4: Passed");
			score += 1;
		} else {
			System.out.println("Index Test for 4: Failed");
		}
		System.out.println();

		System.out.println("Index Test for 100: Expected: -1");
		System.out.println("Index Test for 100: Actual: " + index(a_list, 100));
		if (index(a_list, 100) == -1) {
			System.out.println("Index Test for 100: Passed");
			score += 1;
		} else {
			System.out.println("Index Test for 100: Failed");
		}
		System.out.println();

		System.out.println("Get Item Test at index 2: Expected: 3");
		System.out.println("Get Item Test at index 2: Actual: " + getItem(a_list, 2));
		if (getItem(a_list, 2) == 3) {
			System.out.println("Get Item Test: Passed");
			score += 2;
		} else {
			System.out.println("Get Item Test: Failed");
		}
		System.out.println();

		System.out.println("ToString Test: Expected: [1, 2, 3, 4, 6, 3, 2]");
		System.out.println("ToString Test: Actual: " + toString(a_list));
		if (toString(a_list).equals("[1, 2, 3, 4, 6, 3, 2]")) {
			System.out.println("ToString Test: Passed");
			score += 3;
		} else {
			System.out.println("ToString Test: Failed");
		}
		System.out.println();

		System.out.println("Total Score: " + score + "/25");
	}
}
