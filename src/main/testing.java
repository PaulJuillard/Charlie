package main;

public class testing {
	public static double test() {

		System.out.println("scrap tets started");
		int redPixel = 0b00000000_11111111_00000000_00000000;
    	int greenPixel = 0b00000000_00000000_11111111_00000000;
    	int bluePixel = 0b00000000_00100000_00000000_11111111;
    	int whitePixel = 0b00000000__11111111_11111111_11111111;
    	int[][] basic4pixelImage = {{redPixel , greenPixel},{bluePixel , whitePixel}};
    	int[][] testPattern = {{bluePixel},{}};	
		
		double MAE = 0; //Absolute Mean Error
		double[] errorValues = { 0.0 , 170.0 , 159.33333333333334 , 170.0};
		// adds the absolute errors of the overlayed pattern-image pixels
		for (int irow = 0 ; irow < 2; ++irow ) { 
			for (int icolumn = 0 ; icolumn < 2; ++icolumn ) {
				MAE = MAE + errorValues[irow+icolumn];
			}
		}
		// at this point MAE is the total absolute error, dividing by the number of pixels it becomes mean absolute error
		System.out.println("absolute error : " + MAE);
		MAE = MAE / 4.0;
		System.out.println("mean absolute error: " + MAE);
		System.out.println("scrap test finished");
		return MAE; 
		
		
		
	}
}
