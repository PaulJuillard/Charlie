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
    	
    	int green = rgb >> 8;  
    green = green & 0b11111111;
    
    	
    	
    	    	
    	
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
    
    	
    	int blue= rgb & 0b11111111 ;
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
    	
    	int blue = getBlue(rgb)&0xff;
    	int red = getRed(rgb)&0xff;
    	int green = getGreen(rgb)&0xff;
    	
    	return ((int)Math.round(((red+green+blue)/3)&0xff)) ;
    
    	
    
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
    	//give the rgb value of a pixel for his red green and blue value//
    	assert ( ((red>=0)||(red<256)) || ((green>=0)||(green<256)) || ((blue>=0)||(blue<256)) );
    	int rgb= ( red * 256 *256 )+(green *256)+(blue);
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
    	
    	int grayy=(int) Math.round(gray);
    	int rgb= (grayy*256*256)+(grayy*256)+(grayy);
    	
    	
    	 
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
    		for (int icolumn =0; icolumn <image[0].length ; ++icolumn) {
    			//grayImage[irow][icolumn] = getGray(image [irow][icolumn]);
    			grayImage[irow][icolumn] = getGray(image[irow][icolumn]); // check conversion from double to int
    		    
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
    		for (int icolumn =0; icolumn < gray[0].length; ++icolumn) {
    		
    			imageRGB [irow][icolumn] =  getRGB(gray[irow][icolumn]); 
    		
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
    	
    	// TODO TOUT FAUT
    	// verify assertion
    	// la méthode show n'est pas a 'portée'...
    	
    	// assert (matrix.length > 0) && (matrix[0].length > 0) && (min >= 0 ) && (max >= 0) ;
    	int differenceImage[][] = new int[matrix.length][matrix[0].length];
    	for (int irow = 0; irow < matrix.length; ++irow) {
    			for (int icolumn = 0; icolumn < matrix[0].length; ++icolumn) {
    				differenceImage[irow][icolumn] = (int) ((matrix[irow][icolumn] - min)/(max-min))*255 ;
    			}
    	}
    	// show(differenceImage, "mapping de la différence en gray-scale");
    	return differenceImage;
    }
}