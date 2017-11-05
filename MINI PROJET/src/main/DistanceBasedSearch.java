package main;
public class DistanceBasedSearch {

	/**
	 * Computes the mean absolute error between two RGB pixels, channel by channel.
	 * @param patternPixel : a integer, the second RGB pixel.
	 * @param imagePixel : a integer, the first RGB pixel.
	 * @return a double, the value of the error for the RGB pixel pair. (an integer in [0, 255])
	 */
	public static double pixelAbsoluteError(int patternPixel, int imagePixel) {

    	// TODO review
		//assert patternPixel>=0 && imagePixel>=0;
		
		// Errors (= difference between pattern's & image's pixel) for each rgb value of given pixel
		int AbsoluteError_red = ImageProcessing.getRed(patternPixel) - ImageProcessing.getRed(imagePixel);
		int AbsoluteError_green = ImageProcessing.getGreen(patternPixel) - ImageProcessing.getGreen(imagePixel);
		int AbsoluteError_blue = ImageProcessing.getBlue(patternPixel) - ImageProcessing.getBlue(imagePixel);
		
		// sum of the Absolute Errors of the rgb values
		double AbsoluteError = (AbsoluteError_red + AbsoluteError_green + AbsoluteError_blue) / 3;
		
		return AbsoluteError;
	}

	/**
	 * Computes the mean absolute error loss of a RGB pattern if positioned
	 * at the provided row, column-coordinates in a RGB image
	 * @param row : a integer, the row-coordinate of the upper left corner of the pattern in the image.
	 * @param column : a integer, the column-coordinate of the upper left corner of the pattern in the image.
	 * @param pattern : an 2D array of integers, the RGB pattern to find
	 * @param image : an 2D array of integers, the RGB image where to look for the pattern
	 * @return a double, the mean absolute error
	 * @return a double, mean absolute error value at position (row, col) between the pattern and the part of
	 * the base image that is covered by the pattern, if the pattern is shifted by x and y.
	 * should return -1 if the denominator is -1
	 */
	public static double meanAbsoluteError(int row, int col, int[][] pattern, int[][] image) {

    	// TODO almost done
		// 
		// assertion pattern & image . length
		assert row >= 0 && col >= 0 && pattern.length != 0 && image.length !=0 ; 	
		
		double MAE = 0; //Absolute Mean Error
		
		// adds the absolute errors of the overlayed pattern-image pixels
		for (int irow = row ; irow <  pattern.length - row; ++irow ) { 
			for (int icolumn = col ; icolumn <  pattern[0].length-col ; ++icolumn ) {
				MAE += pixelAbsoluteError(pattern[irow][icolumn], image[irow][icolumn]);
			}
		}
		// at this point MAE is the total absolute error, dividing by the number of pixels it becomes mean absolute error
		MAE /= pattern.length * pattern[0].length;
		return MAE; 
	}

	/**
	 * Compute the distanceMatrix between a RGB image and a RGB pattern
	 * @param pattern : an 2D array of integers, the RGB pattern to find
	 * @param image : an 2D array of integers, the RGB image where to look for the pattern
	 * @param distanceTable : the matrix of the mean absolute error between the pattern and the image.
	 * @return a 2D array of doubles, containing for each pixel of a original RGB image, 
	 * the distance (meanAbsoluteError) between the image's window and the pattern
	 * placed over this pixel (upper-left corner) 
	 */
	public static double[][] distanceMatrix(int[][] pattern, int[][] image) {
		
	   	// TODO almost done
		// DONE initialiser avec taille [imageLength - patterLength][imageWidth-patternWidth]
		// vérifier le return
		// assertion taille pattern et image (voir au dessus)
		
		// voir le pour aller plus loin pour cette methode!!
		
		double distanceTable[][] = new double[image.length-pattern.length][image[0].length-pattern[0].length] ; 
		
		// Distance (=MAE) between pattern and image at coordinates (i,j) is set in distanceTable[i][j];
		for (int irow = 0; irow < image.length - pattern.length; ++irow ) {
			for (int icolumn = 0 ; icolumn < image[0].length - pattern[0].length; ++icolumn ) {
				distanceTable[irow][icolumn] = meanAbsoluteError(irow, icolumn, pattern, image);
			}
		}
		
		return distanceTable;
	}
}