package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AddressController {
		@RequestMapping(value = "/address", method = RequestMethod.GET)
		public String address(){
			return "address";
		}
	

}