
/**
 * CS1: Assignment 3 The ArrayExplorer class provides utility methods for
 * analyzing and manipulating integer arrays. It includes methods for finding
 * minimum and maximum values, checking for duplicates, calculating sums and
 * averages, and more.
 * 
 * The main() method contains the code for testing your implementation. The
 * main() method should not be changed under any circumstances.
 */

public class ArrayExplorer {

	/**
	 * Finds the minimum value in the array.
	 * 
	 * @param array the input array
	 * @return the minimum value in the array
	 * @throws IllegalArgumentException if the array is null or empty
	 */
	public static int min(int[] array) {
		// To Be implemented
		return -1;
	}

	/**
	 * Finds the maximum value in the array.
	 * 
	 * @param array the input array
	 * @return the maximum value in the array
	 * @throws IllegalArgumentException if the array is null or empty
	 */
	public static int max(int[] array) {
		// To Be implemented
		return -1;
	}

	/**
	 * Checks if the array contains duplicate values.
	 * 
	 * @param array the input array
	 * @return true if duplicates are found, false otherwise
	 * @throws IllegalArgumentException if the array is null
	 */
	public static boolean containsDuplicates(int[] array) {
		// To Be implemented
		return false;
	}

	/**
	 * Calculates the sum of all elements in the array.
	 * 
	 * @param array the input array
	 * @return the sum of the elements
	 * @throws IllegalArgumentException if the array is null
	 */
	public static int sum(int[] array) {
		// To Be implemented
		return -1;
	}

	/**
	 * Calculates the average of the elements in the array.
	 * 
	 * @param array the input array
	 * @return the average of the elements
	 * @throws IllegalArgumentException if the array is null or empty
	 */
	public static double average(int[] array) {
		// To Be implemented
		return -1;
	}

	/**
	 * Returns the length of the array.
	 * 
	 * @param array the input array
	 * @return the length of the array
	 * @throws IllegalArgumentException if the array is null
	 */
	public static int length(int[] array) {
		// To Be implemented
		return -1;
	}

	/**
	 * Counts the occurrences of a specific element in the array.
	 * 
	 * @param array   the input array
	 * @param element the element to count
	 * @return the count of occurrences
	 * @throws IllegalArgumentException if the array is null
	 */
	public static int count(int[] array, int element) {
		// To Be implemented
		return -1;
	}

	/**
	 * Calculates the sum of all even numbers in the array.
	 * 
	 * @param array the input array
	 * @return the sum of even numbers
	 * @throws IllegalArgumentException if the array is null
	 */
	public static int sumOfEvens(int[] array) {
		// To Be implemented
		return -1;
	}

	/**
	 * Checks if the array contains a specific element.
	 * 
	 * @param array   the input array
	 * @param element the element to check
	 * @return true if the element is found, false otherwise
	 * @throws IllegalArgumentException if the array is null
	 */
	public static boolean contains(int[] array, int element) {
		// To Be implemented
		return false;
	}

	/**
	 * Finds the index of a specific element in the array.
	 * 
	 * @param array   the input array
	 * @param element the element to find
	 * @return the index of the element, or -1 if not found
	 * @throws IllegalArgumentException if the array is null
	 */
	public static int index(int[] array, int element) {
		// To Be implemented
		return 100;
	}

	/**
	 * Retrieves the element at the specified index.
	 * 
	 * @param array the input array
	 * @param index the index of the element
	 * @return the element at the specified index
	 * @throws IllegalArgumentException if the array is null or index is out of
	 *                                  bounds
	 */
	public static int getItem(int[] array, int index) {
		// To Be implemented
		return -1;
	}

	/**
	 * Converts the array to a string representation.
	 * 
	 * @param array the input array
	 * @return a string representation of the array
	 * @throws IllegalArgumentException if the array is null
	 */
	public static String toString(int[] array) {
		// To Be implemented
		return "";
	}

	// Main method for testing
	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 3, 2 };

		int score = 0;

		System.out.println("Min Test: Expected: 1");
		System.out.println("Min Test: Actual: " + min(array));
		if (min(array) == 1) {
			System.out.println("Min Test: Passed");
			score += 2;
		} else {
			System.out.println("Min Test: Failed");
		}
		System.out.println();

		System.out.println("Max Test: Expected: 5");
		System.out.println("Max Test: Actual: " + max(array));
		if (max(array) == 5) {
			System.out.println("Max Test: Passed");
			score += 2;
		} else {
			System.out.println("Max Test: Failed");
		}
		System.out.println();

		System.out.println("Contains Duplicates Test: Expected: true");
		System.out.println("Contains Duplicates Test: Actual: " + containsDuplicates(array));
		if (containsDuplicates(array)) {
			System.out.println("Contains Duplicates Test: Passed");
			score += 2;
		} else {
			System.out.println("Contains Duplicates Test: Failed");
		}
		System.out.println();

		System.out.println("Sum Test: Expected: 20");
		System.out.println("Sum Test: Actual: " + sum(array));
		if (sum(array) == 20) {
			System.out.println("Sum Test: Passed");
			score += 2;
		} else {
			System.out.println("Sum Test: Failed");
		}
		System.out.println();

		System.out.println("Average Test: Expected: 2.857142857142857");
		System.out.println("Average Test: Actual: " + average(array));
		if (average(array) == 2.857142857142857) {
			System.out.println("Average Test: Passed");
			score += 2;
		} else {
			System.out.println("Average Test: Failed");
		}
		System.out.println();

		System.out.println("Length Test: Expected: 7");
		System.out.println("Length Test: Actual: " + length(array));
		if (length(array) == 7) {
			System.out.println("Length Test: Passed");
			score += 2;
		} else {
			System.out.println("Length Test: Failed");
		}
		System.out.println();

		System.out.println("Count Test for 3: Expected: 2");
		System.out.println("Count Test for 3: Actual: " + count(array, 3));
		if (count(array, 3) == 2) {
			System.out.println("Count Test for 3: Passed");
			score += 1;
		} else {
			System.out.println("Count Test for 3: Failed");
		}
		System.out.println();

		System.out.println("Count Test for 100: Expected: 0");
		System.out.println("Count Test for 100: Actual: " + count(array, 100));
		if (count(array, 100) == 0) {
			System.out.println("Count Test for 100: Passed");
			score += 1;
		} else {
			System.out.println("Count Test for 100: Failed");
		}
		System.out.println();

		System.out.println("Sum of Evens Test: Expected: 8");
		System.out.println("Sum of Evens Test: Actual: " + sumOfEvens(array));
		if (sumOfEvens(array) == 8) {
			System.out.println("Sum of Evens Test: Passed");
			score += 2;
		} else {
			System.out.println("Sum of Evens Test: Failed");
		}
		System.out.println();

		System.out.println("Contains Test for 4: Expected: true");
		System.out.println("Contains Test for 4: Actual: " + contains(array, 4));
		if (contains(array, 4)) {
			System.out.println("Contains Test for 4: Passed");
			score += 2;
		} else {
			System.out.println("Contains Test for 4: Failed");
		}
		System.out.println();

		System.out.println("Index Test for 4: Expected: 3");
		System.out.println("Index Test for 4: Actual: " + index(array, 4));
		if (index(array, 4) == 3) {
			System.out.println("Index Test for 4: Passed");
			score += 1;
		} else {
			System.out.println("Index Test for 4: Failed");
		}
		System.out.println();

		System.out.println("Index Test for 100: Expected: -1");
		System.out.println("Index Test for 100: Actual: " + index(array, 100));
		if (index(array, 100) == -1) {
			System.out.println("Index Test for 100: Passed");
			score += 1;
		} else {
			System.out.println("Index Test for 100: Failed");
		}
		System.out.println();

		System.out.println("Get Item Test at index 2: Expected: 3");
		System.out.println("Get Item Test at index 2: Actual: " + getItem(array, 2));
		if (getItem(array, 2) == 3) {
			System.out.println("Get Item Test: Passed");
			score += 2;
		} else {
			System.out.println("Get Item Test: Failed");
		}
		System.out.println();

		System.out.println("ToString Test: Expected: [1, 2, 3, 4, 5, 3, 2]");
		System.out.println("ToString Test: Actual: " + toString(array));
		if (toString(array).equals("[1, 2, 3, 4, 5, 3, 2]")) {
			System.out.println("ToString Test: Passed");
			score += 3;
		} else {
			System.out.println("ToString Test: Failed");
		}
		System.out.println();

		System.out.println("Total Score: " + score + "/25");
	}
}
