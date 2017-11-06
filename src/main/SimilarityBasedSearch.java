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
		
		//sums the gray values of the pixels of the image
		for (int irow = 0; irow < image.length; ++irow) {
			for (int icolumn = 0; icolumn < image[0].length; ++icolumn) {
				graySum += image[irow][icolumn];
			}
		}
		//converts sum to average
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
		
		double NCC = 0; //Normalized Cross Correlation
		
		//terminate conditions
		boolean lengthInRange = true;
		boolean widthInRange = true;
		
		//selects the pixels over which the pattern will hover
		double[][] imageWindow = new double[pattern.length][pattern[0].length];
		
		for (int irow = 0; (irow < pattern.length) && lengthInRange ; ++irow) {
			
			lengthInRange = (irow + row) < image.length-1;
			
			for (int icolumn = 0; (icolumn < pattern[0].length) && widthInRange; ++icolumn) {
				
				widthInRange = (icolumn + col < image[0].length-1);
				
				imageWindow[irow][icolumn] = image[irow + row][icolumn + col];
			}
		}
		
		double imageMean = windowMean(imageWindow); // imageWindow mean gray value
		double imageVariance = 0; // sum of the difference between the image pixels' gray value and the image's mean gray value
		
		double patternMean = windowMean(pattern); // pattern mean gray value
		double patternVariance = 0; // sum of the difference the pattern pixels' gray value and the image's mean gray value
				
		double covarianceSum = 0; // sum of the products of the given pixel's imageVariance and patternVariance
		
		
		// calculates imageVariance, patternVariance, CovarianceSum 
		for (int irow = 0; (irow < pattern.length); ++irow) {
			for (int icolumn = 0 ; (icolumn < pattern[0].length); ++ icolumn) {
				
				imageVariance += Math.pow(imageWindow[irow][icolumn] - imageMean , 2);
				
				patternVariance += Math.pow(pattern[irow][icolumn] - patternMean , 2);	
				
				covarianceSum += (imageWindow[irow][icolumn] - imageMean) * (pattern[irow][icolumn] - patternMean);
				
			}
		}
		
		//return cases
		double ecartType = (Math.sqrt((imageVariance)*(patternVariance)));
		if(ecartType == 0) { return -1;}
		else { NCC = covarianceSum / (Math.sqrt((imageVariance*imageVariance)*(patternVariance * patternVariance))); 
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
		
		// TODO almost done
		// assertions
		double[][] similarityMatrix = new double[image.length - pattern.length][image[0].length - pattern[0].length];
		
		for (int irow = 0; irow < similarityMatrix.length ; ++irow) {
			for (int icolumn = 0; icolumn < similarityMatrix[0].length ; ++icolumn) {
				similarityMatrix[irow][icolumn] = normalizedCrossCorrelation(irow, icolumn, pattern, image);
			}
		}
		return similarityMatrix;
	}

}
