package org.launchcode.stocks.models;

import org.launchcode.stocks.models.Picture;
import java.awt.Color;

public class MonoChromeFilter implements Filter{

	public Picture process(Picture original, int m, Color mono) {
		
		Picture processed = new Picture(original.width(), original.height());
        
	    //get each pixel one by one
	    for (int i = 0; i < original.width(); i++) {
	      for (int j = 0; j < original.height(); j++) {
	    	  
	    	  Color c = original.get(i, j);
	          
	          //get color components from each pixel
	          int r = c.getRed();
	          int g = c.getGreen();
	          int b = c.getBlue();
	          
	          int newRed = (r + mono.getRed()) / 2;
	          int newGreen = (g + mono.getGreen()) / 2;
	          int newBlue = (b + mono.getBlue()) / 2;
	          
	          processed.set(i, j, new Color(newRed, newGreen, newBlue));
	    	  
	      }
	    }
		
		return processed;
	}



}
