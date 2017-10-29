
    package main;
public final class ImageProcessing {
	
    /**
     * Returns red component from given packed color.
     * @param rgb : a 32-bits RGB color
     * @return an integer,  between 0 and 255
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB(int, int, int)
     */
    public static int getRed(int rgb) {

    	// to be controled, red version of the given pixel//
    	 assert ((rgb < 256) || (rgb >= 0)); // Is that usefull??????//
    	int red = rgb >> 16;
        
    	return red; 
     
    	// end //
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
    	
    	// to be controled, green value of the given pixel//
    	
    	int greenw = rgb >> 8;
    	int green = greenw << 17;
    	return green; 
      
    	// end //
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
    
    	// to be controled, blue value of the given pixel//
    
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
    	
    	// to be controled, Give the gray value of a given pixel//
    	
    	int blue = getBlue(rgb);
    	int red = getRed(rgb);
    	int green = getGreen(rgb);
    	
    	int gray = ((blue+red+green)/3);
    	
        return gray;
    
        // end //
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
    	
    	// to be controled, give the rgb value of a pixel for his red green and blue value//
    	assert ( ((red>=0)||(red<256)) || ((green>=0)||(green<256)) || ((blue>=0)||(blue<256)) );
    	int rgb = red <<8;
    	rgb = red + green;
    	rgb = rgb <<8;
    	rgb = rgb + blue;
    	return rgb; 

    	// end //
    }

    /**
     * Returns packed RGB components from given gray-scale value.
     * @param gray : a double 
     * @return a 32-bits RGB color
     * @see #getGray
     */
    public static int getRGB(double gray) {
    	
    	// to be controled, get the rgb value of a given pixel from the gray value of that one//
    	int rbg;
    	for (i=0; i<3; ++i) {
    	rgb = gray;
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

    	// to be controled, we want to get the the gray value of each pixel of the image //
    
    	double grayImage [][] = new double [728][1024];
    	for (int iligne =0; i<image.length ; ++i) {
    		for (int jcolonne =0; j<image.length ; ++j) {
    			grayImage[iligne][jcolonne] = getGray(image [iligne][jcolonne]);
    			image[iligne][jcolonne] = grayImage[iligne][jcolonne];
    		}
    		}
    	}
    	
    	return grayImage [][] ;
    	// besoins de "{}"????//
    }

    /**
     * Converts gray-scale image to packed RGB image.
     * @param channels : a HxW double array
     * @return a HxW integer array
     * @see #decode
     * @see #getRGB(double)
     */
    public static int[][] toRGB(double[][] gray) {
        
    	// to be controled  //
    	
    	double [][] imageRGB = new double [748][1024]; 
    	for (int iligne = 0; iligne < gray.length; ++iligne) {
    		for (int jcolonne =0; jcolonne < gray.length; ++jcolonne) {
    			for (int k=0; k<3; ++k) {
    			imageRGB [iligne][jcolonne] = gray;
    			imageRGB[iligne][jcolonne] = imageRGB[iligne][jcolonne] << 8;
    		}
    		}
    	}
    	return imageRGB [][];
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
    	
    	// TODO TOUT FAUT
    	// verify assertion
    	// la méthode show n'est pas a 'portée'...
    	
    	// assert (matrix.length > 0) && (matrix[0].length > 0) && (min >= 0 ) && (max >= 0) ;
    	int differenceImage[][] = new int[matrix.length][matrix[0].length];
    	for (int iligne = 0; iligne < matrix.length; ++iligne) {
    			for (int icolonne = 0; icolonne < matrix[0].length; ++icolonne) {
    				differenceImage[iligne][icolonne] = (int) ((matrix[iligne][icolonne] - min)/(max-min))*255 ;
    			}
    	}
    	show(differenceImage, "mapping de la différence en gray-scale");
    	return differenceImage;
    }
}
