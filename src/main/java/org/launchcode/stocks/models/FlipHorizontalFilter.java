package org.launchcode.stocks.models;

import org.launchcode.stocks.models.Picture;
import java.awt.Color;

public class FlipHorizontalFilter implements Filter{
	
	public Picture process(Picture original, int m, Color mono) {
		
		Picture processed = new Picture(original.width(), original.height());
        
	    //get each pixel one by one
	    for (int x = 0; x < original.width(); x++) {
	      for (int y = 0; y  < original.height(); y++) {	          
			  int otherX = original.width() - 1 - x;  // otherX is the mirror of x
			  Color c = original.get(otherX, y);      // get the Color at the mirror point of the source
	          processed.set(x, y, c);	  
	      }
	    }
		
		return processed;
	}



}
