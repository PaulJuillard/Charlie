
package main;

public final class ImageProcessing {
	
	//TODO convertion Binaire rgb -> int ne fonctionne pas!!!
	// il faut utiliser la commande ' & 0x0ff' pour les shifts
	//TODO verifier que le shift '>>' marche!!
	
	
    /**
     * Returns red component from given packed color.
     * @param rgb : a 32-bits RGB color
     * @return an integer,  between 0 and 255
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB(int, int, int)
     */
    public static int getRed(int rgb) {

    	// TODO  be reviewed
    	assert ((rgb < 256) || (rgb >= 0)); // Is that useful?
    	int red = rgb >> 16;  
        
    	return red; 
     
    	// end
    }
    

    /**
     * Returns green component from given packed color.
     * @param rgb : a 32-bits RGB color
     * @return an integer between 0 and 255
     * @see #getRed
     * @see #getBlue
     * @see #getRGB(int, int, int)
     */
    public static int getGreen(int rgb) {
    	
    	//TODO review
    	
    	int greenw = rgb >> 8;
    	int green = greenw << 17;
    	return green; 
      
    	// end
    }

    /**
     * Returns blue component from given packed color.
     * @param rgb : a 32-bits RGB color
     * @return an integer between 0 and 255
     * @see #getRed
     * @see #getGreen
     * @see #getRGB(int, int, int)
     */
    public static int getBlue(int rgb) {
    
    	// TODO review
    
    	int blue = rgb << 9;
        return blue;
        
        // end//
    	
    }

   
    /**
     * Returns the average of red, green and blue components from given packed color.
     * @param rgb : 32-bits RGB color
     * @return a double between 0 and 255
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB(int)
     */
    public static double getGray(int rgb) {
    	
    	//TODO review
    	
    	int blue = getBlue(rgb);
    	int red = getRed(rgb);
    	int green = getGreen(rgb);
    	
    	double gray = ((blue+red+green)/3);
    	
        return gray;
    
        // end
    }

    /**
     * Returns packed RGB components from given red, green and blue components.
     * @param red : an integer 
     * @param green : an integer 
     * @param blue : an integer
     * @return a 32-bits RGB color
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     */
    public static int getRGB(int red, int green, int blue) {
    	
    	//TODO review
    	//give the rgb value of a pixel for his red green and blue value
    	assert ( ((red>=0)||(red<256)) || ((green>=0)||(green<256)) || ((blue>=0)||(blue<256)) );
    	int rgb = red <<8;
    	rgb = red + green;
    	rgb = rgb <<8;
    	rgb = rgb + blue;
    	return rgb; 

    	// end
    }

    /**
     * Returns packed RGB components from given gray-scale value.
     * @param gray : a double 
     * @return a 32-bits RGB color
     * @see #getGray
     */
    public static int getRGB(double gray) {
    	
    	// to be controled, get the rgb value of a given pixel from the gray value of that one//
    	int rgb = 0;
    	for (int i=0; i<3; ++i) {
    	rgb = (int) gray; // check transition between double and int
    	rgb = rgb << 8;
    	}
    	
    	return rgb;
    	
    	// end
    }

    /**
     * Converts packed RGB image to gray-scale image.
     * @param image : a HxW integer array
     * @return a HxW double array
     * @see #encode
     * @see #getGray
     */
    public static double[][] toGray(int[][] image) {

    	// TODO be reviewed, we want to get the the gray value of each pixel of the image //
    
    	double grayImage [][] = new double [image.length][image[0].length];
    	for (int irow =0; irow <image.length ; ++irow) {
    		for (int icolumn =0; icolumn <image.length ; ++icolumn) {
    			grayImage[irow][icolumn] = getGray(image [irow][icolumn]);
    			image[irow][icolumn] = (int) grayImage[irow][icolumn]; // check conversion from double to int
    		}
    		}
    	
    	return grayImage;
    	// besoins de "{}"????
    }

    /**
     * Converts gray-scale image to packed RGB image.
     * @param channels : a HxW double array
     * @return a HxW integer array
     * @see #decode
     * @see #getRGB(double)
     */
    public static int[][] toRGB(double[][] gray) {
        
    	// TODO review 
    	
    	int [][] imageRGB = new int [gray.length][gray[0].length]; 
    	for (int irow = 0; irow < gray.length; ++irow) {
    		for (int icolumn =0; icolumn < gray.length; ++icolumn) {
    			for (int k=0; k<3; ++k) {
    			imageRGB [irow][icolumn] = (int) gray[0][0];  // revoir ICI
    			imageRGB[irow][icolumn] = imageRGB[irow][icolumn] << 8;
    		}
    		}
    	}
    	return imageRGB;
    }

    /**
     * Convert an arbitrary 2D double matrix into a 2D integer matrix 
     * which can be used as RGB image
     * @param matrix : the arbitrary 2D double array to convert into integer
     * @param min : a double, the minimum value the matrix could theoretically contains
     * @param max : a double, the maximum value the matrix could theoretically contains
     * @return an 2D integer array, containing a RGB mapping of the matrix 
     */
    public static int[][] matrixToRGBImage(double[][] matrix, double min, double max) {
    	
    	// TODO fixed
    	// verify assertion
    	
    	// assert (matrix.length > 0) && (matrix[0].length > 0) && (min >= 0 ) && (max >= 0) ;
    	int matrixRGB[][] = new int[matrix.length][matrix[0].length];
    	for (int irow = 0; irow < matrix.length; ++irow) {
    			for (int icolumn = 0; icolumn < matrix[0].length; ++icolumn) {
    				matrixRGB[irow][icolumn] = (int) ((matrix[irow][icolumn] - min)/(max-min))*255 ;
    			}
    	}
    	// a matrix of integer ranging from 0 to 255: 
    	// can be used as RGB to create a gray-scale image
    	return matrixRGB;
    }
}
