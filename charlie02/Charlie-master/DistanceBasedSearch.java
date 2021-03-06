package main;

public class DistanceBasedSearch {

	/**
	 * Computes the mean absolute error between two RGB pixels, channel by channel.
	 * @param patternPixel : a integer, the second RGB pixel.
	 * @param imagePixel : a integer, the first RGB pixel.
	 * @return a double, the value of the error for the RGB pixel pair. (an integer in [0, 255])
	 */
	public static double pixelAbsoluteError(int patternPixel, int imagePixel) {

    	// TODO finished
		assert patternPixel>=0 && imagePixel>=0;
		
		int AbsoluteError_red = getRed(patternPixel) - getRed(imagePixel);
		int AbsoluteError_green = getGreen(patternPixel) - getGreen(imagePixel);
		int AbsoluteError_blue = getBlue(patternPixel) - getBlue(imagePixel);
		
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
	 * @variable EAM: Erreur Absolue Moyenne
	 * @return a double, the mean absolute error
	 * @return a double, mean absolute error value at position (row, col) between the pattern and the part of
	 * the base image that is covered by the pattern, if the pattern is shifted by x and y.
	 * should return -1 if the denominator is -1
	 */
	public static double meanAbsoluteError(int row, int col, int[][] pattern, int[][] image) {

    	// TODO almost done
		// 
		// assertion tailles pattern & image
		assert row >= 0 && col >= 0 && pattern.length != 0 && image.length !=0 ;
		double EAM = 0;
		for (int iligne = row ; iligne < row + pattern.length ; ++iligne ) { 
			for (int icolonne = col ; icolonne < col + pattern[0].length ; ++icolonne ) {
				EAM += pixelAbsoluteError(pattern[iligne][icolonne], image[iligne][icolonne]);
			}
		}
		EAM /= pattern.length * pattern[0].length;
		return EAM; 
	}

	/**
	 * Compute the distanceMatrix between a RGB image and a RGB pattern
	 * @param pattern : an 2D array of integers, the RGB pattern to find
	 * @param image : an 2D array of integers, the RGB image where to look for the pattern
	 * @param tableDistance : the matrix of the mean absolute error between the pattern and the image.
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
		
		double tableDistance[][] = new double[image.length-pattern.length][image[0].length-pattern[0].length] ; 
		
		for (int iligne = 0; iligne < image.length - pattern.length; ++iligne ) {
			for (int icolonne = 0 ; icolonne < image[0].length - pattern[0].length; ++icolonne ) {
				tableDistance[iligne][icolonne] = meanAbsoluteError(iligne, icolonne, pattern, image);
			}
		}
		
		return tableDistance;
		// is this needed??
		// return new double[][]{}; 
	}
}
