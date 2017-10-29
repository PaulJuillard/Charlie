package main;

public class SimilarityBasedSearch {

	/**
	 * Computes the mean value of a gray-scale image given as a 2D array 
	 * @param image : a 2D double array, the gray-scale Image
	 * @return a double value between 0 and 255 which is the mean value
	 */
	public static double windowMean(double[][] image) {
		//TODO almost done
		//assertions
		
		double graySum = 0;
		for (int iligne = 0; iligne < image.length; ++iligne) {
			for (int icolonne = 0; icolonne < image[0].length; ++icolonne) {
				graySum += image[iligne][icolonne];
			}
		}
		double meanGray = graySum / (image.length * image[0].length);
		return meanGray; 
	}

	
	/**
	 * Computes the Normalized Cross Correlation of a gray-scale pattern if positioned
	 * at the provided row, column-coordinate in a gray-scale image
	 * @param row : a integer, the row-coordinate of the upper left corner of the pattern in the image.
	 * @param column : a integer, the column-coordinate of the upper left corner of the pattern in the image.
	 * @param pattern : an 2D array of doubles, the gray-scale pattern to find
	 * @param image : an 2D array of double, the gray-scale image where to look for the pattern
	 * @return a double, the Normalized Cross Correlation value at position (row, col) between the pattern and the part of
	 * the base image that is covered by the pattern, if the pattern is shifted by x and y.
	 * should return -1 if the denominator is 0
	 */
	public static double normalizedCrossCorrelation(int row, int col, double[][] pattern, double[][] image) {
		
		// TODO almost done
		// assertions!
		double NCC = new double; //Normalized Cross Correlation
		double imageVariance = new double;
		double imageMean = windowMean(image);
		double patternVariance = new double;
		double patternMean = windowMean(pattern);
		double covarianceSum = new double;
		
		for (int iligne = 0; iligne < pattern.length ; ++iligne) {
			for (int icolonne = 0 ; icolonne < pattern[0].length ; ++ icolonne) {
				imageVariance += image[iligne + row][icolonne + col] - imageMean;
				patternVariance += pattern[iligne][icolonne] - patternMean;	
				covarianceSum += (image[iligne + row][icolonne + col] - imageMean) * (pattern[iligne][icolonne] - patternMean);
				}
		}
		double ecartType = (Math.sqrt((imageVariance*imageVariance)*(patternVariance * patternVariance));
		if(ecartType == 0) { return -1;}
		else { NCC = covarianceSum / (Math.sqrt((imageVariance*imageVariance)*(patternVariance * patternVariance)); 
		return NCC; }
	}

	
	/**
	 * Compute the similarityMatrix between a gray-scale image and a gray-scale pattern
	 * @param pattern : an 2D array of doubles, the gray-scale pattern to find
	 * @param image : an 2D array of doubles, the gray-scale image where to look for the pattern
	 * @return a 2D array of doubles, containing for each pixel of a original gray-scale image, 
	 * the similarity (normalized cross-correlation) between the image's window and the pattern
	 * placed over this pixel (upper-left corner)
	 */
	public static double[][] similarityMatrix(double[][] pattern, double[][] image) {
		
		// TODO started
		double[][] similarityMatrix = new double[pattern.length][pattern[0].length];
		for (int iligne)
		return new double[][]{}; 
	}

}
