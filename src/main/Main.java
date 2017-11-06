package main;

/**
 * 
 * @author Magron Antoine , Juillard Paul
 *
 *	Where is Charlie Project 
 *
 */

public final class Main {

	/* 
	 * This class is incomplete!!
	 * 
	 * You are expected to write at least one testcase for each required method.
	 * You will find some examples of testcases below.
	 */
	
    public static void main(String[] args) {
    	    	
    	
    	//findCharlie();
    	
    	// TESTS
    	
    		//scrap test
    			//testing.test();
    	
    		// ImageProcessing tests
    			//testGetRed_GetGreen_GetBlue_GetGray();
    			//testGrayscale();
    	
    		// Collector tests
    			//testFindBest();
    			//testFindNBest();
    	
    		// Distance Based Search tests
    			//testPixelAbsoluteError();
    			//testMeanAbsoluteError();
    			//testDistanceMatrix();
    	
    		// Similarity Based Search tests
    			//testWindowMean();
    			testNormalizedCrossCorrelation();
    			//testSimilarityBasedSearch();   
    	
    	
    }
    
    
    /*
     * Tests for Class ImageProcessing
     * TODO: ImageProcessing tests
     * test 1 : getRed + getGreen + getBlue + getGray - DONE - test passed
     * test 2 : getRGB (from RGB ints)
     * test 3 : get RGB (from gray double)
     * test 4 : toRGB + toGray
     * test 5 : matrixToRGBImage
     */  
    
    public static void testGetRed_GetGreen_GetBlue_GetGray() { 
    	
    	System.out.println("testing getRed, getGreen, getBlue, getGray");
    	
    	//setting test material
    	int color = 0b11110000_00001111_01010101;
    
    	int refR = 0b11110000;
    	int red = ImageProcessing.getRed(color);
    	
    	int refG = 0b00001111;
    	int green = ImageProcessing.getGreen(color);
    	
    	int refB = 0b01010101;
    	int blue = ImageProcessing.getBlue(color);
 
    	int refGray = 113;
    	double gray = ImageProcessing.getGray(color);
    	
    	// testing getRed
    	if (red == refR) {
    		System.out.println("Test passed");
    	} else {
    		System.out.println("Test failed. Returned value = " + red + " Expected value = " + refR);
    	}
    	
    	//testing getGreen
    	if ( green == refG) {
    		System.out.println("Test passed");
    	} else {
    		System.out.println("Test failed. Returned value = " + green + " Expected value = " + refG);
    	}
    	
    	//testing getBlue
    	if ( blue == refB) {
    		System.out.println("Test passed");
    	} else {
    		System.out.println("Test failed. Returned value = " + blue + " Expected value = " + refB);
    	}
    	
    	//testing getGray
    	if ( gray == refGray) {
    		System.out.println("Test passed");
    	} else {
    		System.out.println("Test failed. Returned value = " + gray + " Expected value = " + refGray);
    	}
    	
    	System.out.println("test finished");
    }
    
    public static void testGrayscale() {
    	
    	System.out.println("Test Grayscale");
    	
     	int[][] image = Helper.read("images/food.png");
     	System.out.println(image[0][0]);
     	System.out.println(image[15][15]);
     	
    	double[][] gray = ImageProcessing.toGray(image);
    	System.out.println(image[0][0]);
    	System.out.println(gray[50][50]);
    	
    	Helper.show(ImageProcessing.toRGB(gray), "food grayScale"); 
    	
    	System.out.println("test finished");
    }
    
       
    /*
     * Tests for Class Collector
     * TODO: Collector
     * test 1 : findBest - DONE - test passed
     * test 2 : findNbest (dependent of test1) - DONE - test not passed
     */  
    
    public static void testFindBest() { 
    	
    	System.out.println("Test findBest");
    	
    	//setting test material
    	double[][] t = new double[][] {{20, 30, 10, 50, 32}, {28, 39, 51, 78, 91}};
    	
    	//biggest first
    	int[] coords = Collector.findBest(t, false);  			
    	
	   	int r = coords[0];
	   	int c = coords[1];
	   	System.out.println("\nbiggest:  row = " + r + "; col = " + c);
	   	System.out.println("expected: row = 1; col = 4");
	   	
	   	//smallest first
	   	int[] coords2 = Collector.findBest(t, true);  			
	    	
	   	int a = coords2[0];
	   	int b = coords2[1];
	   	System.out.println("\nsmallest: row = " + a + "; col = " + b);
	   	System.out.println("expected: row = 0; col = 2");
	   	
	   	System.out.println("test finished");
    }
    
    public static void testFindNBest() {
    	
    	System.out.println("Test findNBest");
    	
    	//setting test material
    	double[][] t = new double[][] {{20, 30, 10, 50, 32}, {28, 39, 51, 78, 91}};
    	
    	//biggest first
    	int[][] coords = Collector.findNBest(5, t, false);  
    	System.out.print("\n");
    	for (int[] a : coords) {
    		int r = a[0];
    		int c = a[1];
    		System.out.println("Row=" + r + " Col=" + c);
    		
    	}
    	System.out.println("expected sequence: 1-4; 1-3; 1-2 ; 0-3; 1-1;");
    	
    	//smallest first
    	int[][] coords2 = Collector.findNBest(5, t, true);  
    	System.out.print("\n");
    	for (int[] b : coords2) {
    		int p = b[0];
    		int q = b[1];
    		System.out.println("Row=" + p + " Col=" + q);
    	}
    	System.out.println("expected sequence: 0-2; 0-0; 1-0; 0-1; 0-4;");
    	
    	System.out.println("test finished");
    }

    
    /*
     * Tests for Class DistanceBasedSearch
     * TODO: DistanceBasedSearch
     * test 1 : pixelAbsoluteError - DONE renvoi des trucs coherents
     * test 2 : meanAbsoluteError (dependent of test1) - toujours nul...
     * test 3 : distanceMatrix (dependent of test1&2) - DONE
     * 
     */
    
    public static void testPixelAbsoluteError() { 
    	System.out.println("Test pixelAbsoluteError");
    	
    	int redPixel = 0b00000000_11111111_00000000_00000000;
    	int greenPixel = 0b00000000_00000000_11111111_00000000;
    	int bluePixel = 0b00000000_00100000_00000000_11111111;
    	int whitePixel = 0b00000000__11111111_11111111_11111111;
    	
    	
    	System.out.println("Returned values: "	+ 
    		DistanceBasedSearch.pixelAbsoluteError(redPixel, redPixel) + " ; " +
    		DistanceBasedSearch.pixelAbsoluteError(redPixel, greenPixel) + " ; " +
    		DistanceBasedSearch.pixelAbsoluteError(redPixel, bluePixel) + " ; " +
    		DistanceBasedSearch.pixelAbsoluteError(redPixel, whitePixel));
}
    
    public static void testMeanAbsoluteError() {
    	
    	System.out.println("Test MeanAbsoluteError");
    	
    	int[][] food_A = Helper.read("images/food.png");
    	int[][] onions_B = Helper.read("images/onions.png");
    	
    	//initialising test material
    	int redPixel = 0b00000000_11111111_00000000_00000000;
    	int greenPixel = 0b00000000_00000000_11111111_00000000;
    	int bluePixel = 0b00000000_00100000_00000000_11111111;
    	int whitePixel = 0b00000000__11111111_11111111_11111111;
    	int[][] basic4pixelImage = {{redPixel , greenPixel},{bluePixel , whitePixel}};
    	int[][] testPattern = {{bluePixel},{bluePixel}};
    	
    	for (int irow = 0; irow < testPattern.length; ++irow) {
    		for (int icolumn = 0 ; icolumn < testPattern[0].length ; ++icolumn) {
    			DistanceBasedSearch.meanAbsoluteError(0, 0, testPattern, basic4pixelImage);
    		}
    	}
    	System.out.println("Test finished");
    	
    	
    }
    
    public static void testDistanceMatrix() {
    	
    	System.out.println("Test DistanceMatrix");
    	int[][] food = Helper.read("images/food.png");
    	int[][] onions = Helper.read("images/onions.png");
    	
    	double[][] distance = DistanceBasedSearch.distanceMatrix(onions, food);
    	
    	int[][] distanceVisualisation = ImageProcessing.toRGB(distance);
    	Helper.show(distanceVisualisation, "distanceImage");
    	
    	int[] p = Collector.findBest(distance, true);
    	Helper.drawBox(p[0], p[1], onions[0].length, onions.length, food);
    	Helper.show(food, "Found!");
    	
    	System.out.println("test finished");
    	
    }
    
    
    /*
     * Tests for Class SimilarityBasedSearch
     * TODO: SimilarityBasedSearch
     * test 1 : windowMean
     * test 2 : normalizedCrossCorrelation
     * test 3 : similarityMatrix
     */

    public static void testWindowMean() {
    	
    	System.out.println("testing window mean");
    	//initialising test material
    	int redPixel = 0b00000000_11111111_00000000_00000000;
    	int greenPixel = 0b00000000_00000000_11111111_00000000;
    	int bluePixel = 0b00000000_00100000_00000000_11111111;
    	int whitePixel = 0b00000000__11111111_11111111_11111111;
    	int[][] basic4pixelImage = {{redPixel , greenPixel},{bluePixel , whitePixel}};
    	
    	//testing method and calculating by 'hand' the expected returned value
    	double imageWindowMean = SimilarityBasedSearch.windowMean(ImageProcessing.toGray(basic4pixelImage));
    	double expected = (ImageProcessing.getGray(redPixel) + ImageProcessing.getGray(greenPixel)
    	+ ImageProcessing.getGray(bluePixel) + ImageProcessing.getGray(whitePixel))/4.0 ;
    	
    	System.out.println("basic4pixel image's window mean = " + imageWindowMean);
    	System.out.println("expected value = " + expected);
    	
    	System.out.println("test finished");
    }
    
    public static void testNormalizedCrossCorrelation() {
    	
    	System.out.println("testing normalised Cross Correlation");
    	
    	//setting testing material
    	double[][] simpleSequence1 = {{ 0 , 1 , 2} , { 0 , 1 , 2 }}; //average is 2
    	double[][] simpleSequence2 = {{ 1 , 2 , 2 , 3} , { 1 , 2 , 2 , 3}}; //average is 2
    	
    	double crossCorrelationCoef = SimilarityBasedSearch.normalizedCrossCorrelation(1, 1, simpleSequence1, simpleSequence2);
    	//hand calculation of the cross correlation coefficient starting at row=col=1 for simpleSequence2;
    	double expectedCoef = ((2-2)*(0-2)+(2-2)*(1-2)+(3-2)*(2-2))/
    			Math.sqrt(Math.pow((2-2)+(2-2)+(3-2), 2)*Math.pow((0-2)+(1-2)+(2-2),2));
    
    	System.out.println("crossCorelationCoef = " + crossCorrelationCoef);
    	System.out.println("expected: " + expectedCoef);
    	
    	System.out.println("test finished");
    
    }
    // TODO la similarity martix retournée est toute noire!!!!!!
    //mais on trouve l'oignon
    public static void testSimilarityBasedSearch() {
    	System.out.println("Test SimilarityBasedSearch");
		int[][] food = Helper.read("images/food.png");
    	int[][] onions = Helper.read("images/onions.png");
    	double[][] foodGray = ImageProcessing.toGray(food);
    	double[][] onionsGray = ImageProcessing.toGray(onions);    	
    	double[][] similarity = SimilarityBasedSearch.similarityMatrix(onionsGray, foodGray);
    	
    	int[] best = Collector.findBest(similarity, false);  
    	double max = similarity[best[0]][best[1]];
    	Helper.show(ImageProcessing.matrixToRGBImage(similarity, -1, max), "Similarity");
    	//for (int[] a : best) {
    		//int r = a[0];
    		//int c = a[1];
        Helper.drawBox(best[0], best[1], onions[0].length, onions.length, food);
    	//}
    	Helper.show(food, "Found again!");  
    	
    	System.out.println("test finished");
    }
    
    // TODO la similarity martix retournée est toute noire! on arrive dans le ciel
    public static void findCharlie() {
    	System.out.println("Find Charlie");
		int[][] beach = Helper.read("images/charlie_beach.png");
    	int[][] charlie = Helper.read("images/charlie.png");
    	double[][] beachGray = ImageProcessing.toGray(beach);
    	double[][] charlieGray = ImageProcessing.toGray(charlie);    	

    	System.out.println("Compute Similarity Matrix: expected time about 2 min");
    	double[][] similarity = SimilarityBasedSearch.similarityMatrix(charlieGray, beachGray);

    	System.out.println("Find N Best");
    	int[] best = Collector.findBest(similarity, false);   
    	double max = similarity[best[0]][best[1]];
    	
    	Helper.show(ImageProcessing.matrixToRGBImage(similarity, -1, max), "Similarity");
    	
    	Helper.drawBox(best[0], best[1], charlie[0].length, charlie.length, beach);
    	System.out.println("drawBox at (" + best[0] + "," + best[1] + ")");
    	Helper.show(beach, "Found again!");   
    	
    	System.out.println("find Charlie finished");
    }
}
    

 
    


    

 
    

