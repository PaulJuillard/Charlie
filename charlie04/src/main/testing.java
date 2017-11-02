package main;

public class testing {
		public static void main(String args[]) {
			
			int rgb = 0b11110000_00001111_01010101;
			
			System.out.println(rgb);
			
			//int red = rgb >> 16;
			
			int red = (rgb >> 16) & 0x0ff;
			
			/*int greenw = rgb >> 8;
	    	int green = greenw << 17;
	    	int blue = rgb << 9;
	    	
	    	System.out.println(red);
			System.out.println(green);
			System.out.println(blue);
			
			rgb = red <<8;
			System.out.println(rgb); 
	    	rgb = red + green;
	    	System.out.println(rgb);
	    	rgb = rgb <<8;
	    	System.out.println(rgb);
	    	rgb = rgb + blue;
	    	System.out.println("\n");
			
			*/

	    	System.out.print(red); 
			
		}
}
