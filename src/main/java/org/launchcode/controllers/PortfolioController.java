package org.launchcode.controllers;

import org.launchcode.stocks.models.Stock;
import org.launchcode.stocks.models.StockHolding;
import org.launchcode.stocks.models.StockLookupException;
import org.launchcode.stocks.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Console;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Chris Bay on 5/17/15.
 */
@Controller
public class PortfolioController extends AbstractController {

    @RequestMapping(value = "/stocks/portfolio")
    public String portfolio(HttpServletRequest request, Model model){

        // TODO - Implement portfolio display
    	User u =getUserFromSession(request);
    	//Map<String, StockHolding> p = u.getPortfolio();
    	//String nnn = p.get(1).getSymbol();
    	
    	model.addAttribute("user", u);
    	model.addAttribute("portfolio", u.getPortfolio());

        model.addAttribute("Stock", Stock.class);
        model.addAttribute("title", "Portfolio");
        model.addAttribute("portfolioNavClass", "active");

        return "stocksportfolio";
    }

}
