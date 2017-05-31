package org.launchcode.controllers;
import org.launchcode.stocks.models.Filter;

import java.awt.Color;
import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.launchcode.stocks.models.Picture;

import org.launchcode.stocks.models.BlueFilter;
import org.launchcode.stocks.models.BrightnessFilter;
import org.launchcode.stocks.models.FlipHorizontalFilter;
import org.launchcode.stocks.models.GrayScaleFilter;
import org.launchcode.stocks.models.InvertFilter;
import org.launchcode.stocks.models.MonoChromeFilter;
import org.launchcode.stocks.models.RedFilter;

@Controller
public class JavagramController {
	public static String[] filters = {"BlueFilter", "RedFilter", "InvertFilter", "BrightnessFilter", "GrayScaleFilter", "MonoChromeFilter", "FlipHorizontalFilter"};
	public static String[] imagepath = {"0.jpg", "1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg", "7.jpg"};
	
	@RequestMapping(value = "/javagram", method = RequestMethod.GET)
	public String javagram(Model model){
		return "javagram";
	}
	
	@RequestMapping(value = "/javagram", method = RequestMethod.POST)
	public String javagramRender(HttpServletRequest request, Model model) throws Exception{
		String image = request.getParameter("originalpicture");
		String filterFORM = request.getParameter("filter");
        int imageINT = Integer.valueOf(image);
        int filterINT = Integer.valueOf(filterFORM);
        System.out.println(imageINT);
        System.out.println(filterINT);
        System.out.println(imagepath[imageINT]);
        System.out.println(filters[filterINT]);
        
		// Create the base path for images		
		String[] baseParts = {System.getProperty("user.dir"), "src\\main\\resources\\static\\img"};
		String dir = String.join(File.separator, baseParts);
		String relPath = imagepath[imageINT];
		Picture picture = null;
		Picture processed = null;
		
		do {
			
			String imagePath = "path not set";
			
			// try to open the file
			try {
//				String[] relPathParts = relPath.split(File.separator); //bullshit launchCODE
//				imagePath = dir + File.separator + String.join(File.separator, Arrays.asList(relPathParts));  //bullshit launchCODE
				imagePath = (dir + "\\" + relPath);
				picture = new Picture(imagePath);
				
			} catch (RuntimeException e) {
				System.out.println("Could not open image: " + imagePath);
			}
			
		} while(picture == null);
		
		
		Filter filter = getFilter(filterINT);
		
		// TODO - pass filter ID int to getFilter, and get an instance of Filter back 
		if (filterINT == 4){
			//System.out.println("How much do you want to modify?");
			//int modify = in.nextInt();
			processed = filter.process(picture, 100, Color.white);				
		}
		else if (filterINT == 6){
			//System.out.println("What is the RED value?");
			//int red = in.nextInt();
			//if (red > 255)
			//	red = 255;
			//System.out.println("What is the GREEN value?");
			//int green = in.nextInt();
			//if (green > 255)
			//	green = 255;
			//System.out.println("What is the BLUE value?");
			//int blue = in.nextInt();
			//if (blue > 255)
			//	blue = 255;
			//processed = filter.process(picture, 0, new Color(red, green, blue));
			processed = filter.process(picture, 0, new Color(167, 12, 225));
			
			
		}
		else{
			processed = filter.process(picture, 0, Color.white);
		}
		//System.out.println("Do you want to apply another filter? enter \"y\" for yes!");
		//n = in.next();
		picture = processed;
		
		
		Random rand = new Random();

		int  n = rand.nextInt(10000) + 1;
		String f = String.valueOf(n);
		//String absFileName = dir + "\\" + f + ".jpg";
		//picture.save(absFileName);
		
//		Picture picture1 = null;
		
//		do {
//			
//			String imagePath = "path not set";
//			
//			// try to open the file
//			try {
//				imagePath = (absFileName);
//				picture1 = new Picture(imagePath);
//				
//			} catch (RuntimeException e) {
//				System.out.println("Could not open image: " + imagePath);
//			}
//			
//		} while(picture1 == null);
//		
//		
//		model.addAttribute("picture", "/img/"+ f + ".jpg");
		
		String absFileName = dir + "\\placeholder.jpg";
		picture.save(absFileName);
//		
		Picture picture1 = null;
		do {
			
			String imagePath = "path not set";
			
		// try to open the file
			try {
				imagePath = (absFileName);
				picture1 = new Picture(imagePath);
				
			} catch (RuntimeException e) {
				System.out.println("Could not open image: " + imagePath);
			}
			
		} while(picture1 == null);
		
		
		model.addAttribute("picture", "/img/placeholder.jpg");
		model.addAttribute("picture1", picture);

		//picture.show();
		return "javagramrender";
		//return "javagram";
	}
	
	@RequestMapping(value = "/javagramrender", method = RequestMethod.POST)
	public String javagramReRender(){
		return "javagramrender";
	}
	
	private static Filter getFilter(int f) throws Exception {//int f
//		System.out.println(Filter.class.getInterfaces().length);
//		ArrayList<Class<? extends Filter>> ClassList = new ArrayList<Class<? extends Filter>>(2);
//		System.out.println(ClassList.toString());
//		System.out.println(filters[0] + filters[1]);
		// TODO - create some more filters, and add logic to return the appropriate one
		if (f == 0){
			return new BlueFilter();
		}
		else if (f == 1){
			return new RedFilter();
		}
		else if (f==2){
			return new InvertFilter();	
		}
		else if (f==3){
			return new BrightnessFilter();
		}
		else if (f==4){
			return new GrayScaleFilter();
		}
		else if (f==5){
			return new MonoChromeFilter();
		}
		else if (f==6){
			return new FlipHorizontalFilter();
		}
		else{
			throw new Exception("Invalid Filter Exception");
		}
	}
}

