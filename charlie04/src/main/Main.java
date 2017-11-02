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
    	testGetRed_GetGreen_GetBlue_GetGray();
    	testGrayscale();
    	testFindNBest();
    	testDistanceMatrix();
    	testSimilarityBasedSearch();   
    	findCharlie();
    }
    
    /*
     * Tests for Class ImageProcessing
     * TODO: ImageProcessing tests
     * test 1 : getRed + getGreen + getBlue + getGray - DONE
     * test 2 : getRGB (from RGB ints)
     * test 3 : get RGB (from gray double)
     * test 4 : toRGB + toGray
     * test 5 : matrixToRGBImage
     */  
    public static void testGetRed_GetGreen_GetBlue_GetGray() { 
    	int color = 0b11110000_00001111_01010101; //test rgb value
    	
    	int refR = 0b11110000;
    	int red = ImageProcessing.getRed(color);
    	
    	int refG = 0b00001111;
    	int green = ImageProcessing.getGreen(color);
    	
    	int refB = 0b01010101;
    	int blue = ImageProcessing.getBlue(color);
    	
    	double refGray = (refR + refG + refB)/3;
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
    }
    
    public static void testGrayscale() {
    	System.out.println("Test Grayscale");
     	int[][] image = Helper.read("images/food.png");
    	double[][] gray = ImageProcessing.toGray(image);
    	Helper.show(ImageProcessing.toRGB(gray), "test bw"); // ??? 
    }
    
       
    /*
     * Tests for Class Collector
     * TODO: Collector
     * test 1 : findBest - DONE
     * test 2 : findNbest (dependent of test1) - DONE
     */  
    public static void testFindBest() { 
    	System.out.println("Test findBest");
    	double[][] t = new double[][] {{20, 30, 10, 50, 32}, {28, 39, 51, 78, 91}};
    	int[] coords = Collector.findBest(t, true);  			
    	
    		int r = coords[0];
    		int c = coords[1];
    		if (r == 1 && c == 4) {
    			System.out.println("Test passed");
    		} else {
    		System.out.println("Row=" + r + " Col=" + c + " \n expected Row=1 Col=4"); 
    		}  
    }
    
    public static void testFindNBest() {
    	System.out.println("Test findNBest");
    	double[][] t = new double[][] {{20, 30, 10, 50, 32}, {28, 39, 51, 78, 91}};
    	int[][] coords = Collector.findNBest(10, t, true);    			
    	for (int[] a : coords) {
    		int r = a[0];
    		int c = a[1];
    		System.out.println("Row=" + r + " Col=" + c + " Val=" + t[r][c]);
    	}    
    }

    
    /*
     * Tests for Class DistanceBasedSearch
     * TODO: DistanceBasedSearch
     * test 1 : pixelAbsoluteError - DONE
     * test 2 : meanAbsoluteError (dependent of test1)
     * test 3 : distanceMatrix (dependent of test1&2) - DONE
     * 
     */
    public static void testPixelAbsoluteError() {
    	System.out.println("Test pixelAbsoluteError");
    	
    	int pixelA = 0b11110000_00001111_01010101;
    	int pixelB = 0b00001111_11110000_10101010; //binary complement to A
    	int pixelC = 0b11110000_11110000_01010101; //first half is binary complement to A
    	
    	double expectedDistanceAB = 1; // A and B binary complement so 100% distance
    	double expectedDistanceAC = 0.5; // A and C have 50% binary distance
    	double expectedDistanceBC = 0.5; // same for B and C
    	double expectedDistanceAA = 0; // Distance between the same pixels should be 0
    	
    	//without an error margin the '==' will probably always return False
    	//because of the 2 consecutive approximations (from getColor and from division of pixelAbsoluteError
    	if (DistanceBasedSearch.pixelAbsoluteError(pixelA, pixelB) == expectedDistanceAB
    			&& DistanceBasedSearch.pixelAbsoluteError(pixelA, pixelC) == expectedDistanceAC
    			&& DistanceBasedSearch.pixelAbsoluteError(pixelB, pixelC) == expectedDistanceBC
    			&& DistanceBasedSearch.pixelAbsoluteError(pixelA, pixelA) == expectedDistanceAA) {
    		System.out.println("Test passed");
    	} 
    	
    	else {
    		System.out.println("Test failed. Returned values: " +
    				DistanceBasedSearch.pixelAbsoluteError(pixelA, pixelB) + " ; " +
    				DistanceBasedSearch.pixelAbsoluteError(pixelA, pixelC) + " ; " + 
    				DistanceBasedSearch.pixelAbsoluteError(pixelB, pixelC) + " ; " +
    				DistanceBasedSearch.pixelAbsoluteError(pixelA, pixelA) +
    				"\nInstead of expected: " + 
    				" 1 ; 0.5 ; 0.5 ; 0.5");
    	}
}
    
    
    public static void testDistanceMatrix() {
    	System.out.println("Test DistanceMatrix");
    	int[][] food = Helper.read("images/food.png");
    	int[][] onions = Helper.read("images/onions.png");
    	double[][] distance = DistanceBasedSearch.distanceMatrix(onions, food); 			
    	int[] p = Collector.findBest(distance, true);
    	Helper.drawBox(p[0], p[1], onions[0].length, onions.length, food);
    	Helper.show(food, "Found!");
    }
    
    
    /*
     * Tests for Class SimilarityBasedSearch
     * TODO: SimilarityBasedSearch
     * test 1 : windowMean
     * test 2 : normalizedCrossCorrelation
     * test 3 : similarityMatrix
     */

    public static void testSimilarityBasedSearch() {
    	System.out.println("Test SimilarityBasedSearch");
		int[][] food = Helper.read("images/food.png");
    	int[][] onions = Helper.read("images/onions.png");
    	double[][] foodGray = ImageProcessing.toGray(food);
    	double[][] onionsGray = ImageProcessing.toGray(onions);    	
    	double[][] similarity = SimilarityBasedSearch.similarityMatrix(onionsGray, foodGray);
    	int[][] best = Collector.findNBest(8, similarity, false);    			
    	for (int[] a : best) {
    		int r = a[0];
    		int c = a[1];
        	Helper.drawBox(r, c, onions[0].length, onions.length, food);
    	}
    	Helper.show(food, "Found again!");    	
    }
    
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
    }
}
    

 
    

