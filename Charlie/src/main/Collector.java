package main;

import java.util.ArrayList;

public class Collector {

	/**
	 * Find the row, column coordinates of the best element (biggest or smallest) for the given matrix
	 * @param matrix : an 2D array of doubles
	 * @param smallestFirst : a boolean, indicates if the smallest element is the best or not (biggest is then the best)
	 * @return an array of two integer coordinates, row first and then column
	 */
	public static int[] findBest(double[][] matrix, boolean smallestFirst) {

    	// TODO review
		
		double min = Double.POSITIVE_INFINITY;  // remplacer par la commande pour - l'infini
		double max = Double.NEGATIVE_INFINITY; // remplacer par la commande pour + l'infini
		int[] bestCoordinates = new int[2];
		
		// choisir+justifier en commentaires si mettre 2 if et 4 for (plus optimisée moins lisible)
			
			for (int irow = 0; irow < matrix.length ; ++irow ) {
				for(int icolumn = 0; icolumn < matrix[0].length; ++icolumn) {
					
					// for Distance Based Search
					if (smallestFirst && (matrix[irow][icolumn] < min) ) {
						
						min = matrix[irow][icolumn];
						bestCoordinates[0] = irow;
						bestCoordinates[1] = icolumn;
						
						}
					
					// for Similarity Based Search
					else if( !smallestFirst && matrix[irow][icolumn] > max) { 
						
						max = matrix[irow][icolumn];
						bestCoordinates[0] = irow;
						bestCoordinates[1] = icolumn;
						
						}
				}
			}
		
		return bestCoordinates;
	}

	
	/**
	 * Find the row, column coordinate-pairs of the n best (biggest or smallest) elements of the given matrix
	 * @param n : an integer, the number of best elements we want to find 
	 * @param matrix : an 2D array of doubles
	 * @param smallestFirst : a boolean,  indicates if the smallest element is the best or not (biggest is the best)
	 * @return an array of size n containing row, column-coordinate pairs
	 */
	public static int[][] findNBest(int n, double[][] matrix, boolean smallestFirst) {

    	// TODO review
		
		int[][] NbestCoordinates = new int[n][2];
		for (int i = 0 ; i < n ; ++i) {
			
			// the coordinates of the i-th best element of the matrix are stored in i-th row of returned matrix.
			NbestCoordinates[i] = findBest(matrix, smallestFirst);
			
			// i-th max term from the matrix is taken to a neutral value to be disregarded for next 'i's.
			matrix[ NbestCoordinates[i][0] ][ NbestCoordinates[i][1] ] = 0; //trouver une valeure neutre non nulle
		}
		
		return NbestCoordinates;
	}
	
	

	/**
	 * BONUS 
	 * Notice : Bonus points are underpriced ! 
	 * 
	 * Sorts all the row, column coordinates based on their pixel value
	 * Hint : Use recursion !
	 * @param matrix : an 2D array of doubles
	 * @return A list of points, each point is an array of length 2.
	 */
	public static ArrayList<int[]> quicksortPixelCoordinates(double[][] matrix) {

		// TODO implement me correctly for "underpriced" bonus!
		return new ArrayList<int[]>();
	}

	
	/**
	 * BONUS
	 * Notice : Bonus points are underpriced !
	 * 
	 * Use a quick sort to find the row, column coordinate-pairs of the n best (biggest or smallest) elements of the given matrix
	 * Hint : return the n first or n last elements of a sorted ArrayList  
	 * @param n : an integer, the number of best elements we want to find 
	 * @param matrix : an 2D array of doubles
	 * @param smallestFirst : a boolean, indicate if the smallest element is the best or not (biggest is the best)
	 * @return an array of size n containing row, column-coordinate pairs
	 */
	public static int[][] findNBestQuickSort(int n, double[][] matrix, boolean smallestFirst) {

    	// TODO implement me correctly for underpriced bonus!
		return new int[][]{};
	}
}
