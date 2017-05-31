package org.launchcode.stocks.models;

import org.launchcode.stocks.models.Picture;
import java.awt.Color;

public class BrightnessFilter implements Filter{

	public Picture process(Picture original, int brightness, Color mono) {
		
		Picture processed = new Picture(original.width(), original.height());
        
	    //get each pixel one by one
	    for (int i = 0; i < original.width(); i++) {
	      for (int j = 0; j < original.height(); j++) {
	    	  
	    	  Color c = original.get(i, j);
	          
	          //get color components from each pixel
	          int r = c.getRed();
	          int g = c.getGreen();
	          int b = c.getBlue();
	          
	          int newRed = brightness+r;
	          int newGreen = brightness+g;
	          int newBlue = brightness+b;
	          
	          if (newRed > 255){
	        	  newRed = 255;
	          }
	          if (newGreen > 255){
	        	  newGreen = 255;
	          }
	          if (newBlue > 255){
	        	  newBlue = 255;
	          }
	          
	          processed.set(i, j, new Color(newRed, newGreen, newBlue));
	    	  
	      }
	    }
		
		return processed;
	}



}
