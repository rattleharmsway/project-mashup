package org.launchcode.controllers;

import org.launchcode.stocks.models.AbstractEntity;
import org.launchcode.stocks.models.Stock;
import org.launchcode.stocks.models.StockLookupException;
import org.launchcode.stocks.models.StockTransaction;
import org.launchcode.stocks.models.User;
import org.launchcode.stocks.models.dao.StockHoldingDao;
import org.launchcode.stocks.models.dao.StockTransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.launchcode.stocks.models.StockHolding;

import java.io.Console;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Chris Bay on 5/17/15.
 */
@Controller
public class StockController extends AbstractController {

    @Autowired
    StockHoldingDao stockHoldingDao;

    @RequestMapping(value = "/stocks/quote", method = RequestMethod.GET)
    public String quoteForm(Model model) {

        // pass data to template
        model.addAttribute("title", "Quote");
        model.addAttribute("quoteNavClass", "active");
        return "stocksquote_form";
    }

    @RequestMapping(value = "/stocks/quote", method = RequestMethod.POST)
    public String quote(String symbol, Model model) throws StockLookupException {

        // TODO - Implement quote lookup
    	Stock s = null;
    	try {
    		s = Stock.lookupStock(symbol);
		} catch (StockLookupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());//"Invalid Stock Name!"
			return "stocksquote_form";
		}
    	String stockDesc = s.getName();
    	String stockPrice = Float.toString(s.getPrice());

        // pass data to template
        model.addAttribute("stock_desc", stockDesc);
        model.addAttribute("stock_price", stockPrice);
        model.addAttribute("title", "Quote");
        model.addAttribute("quoteNavClass", "active");

        return "stocksquote_display";
    }

    @RequestMapping(value = "/stocks/buy", method = RequestMethod.GET)
    public String buyForm(Model model) {

        model.addAttribute("title", "Buy");
        model.addAttribute("action", "/stocks/buy");
        model.addAttribute("buyNavClass", "active");
        return "stockstransaction_form";
    }

    @RequestMapping(value = "/stocks/buy", method = RequestMethod.POST)
    public String buy(String symbol, int numberOfShares, HttpServletRequest request, Model model) {

        // TODO - Implement buy action
    	User u =getUserFromSession(request);
    	StockHolding h = null;
    	
    	try {
			h = StockHolding.buyShares(u, symbol, numberOfShares);
		} catch (StockLookupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        model.addAttribute("title", "Buy");
	        model.addAttribute("action", "/stocks/buy");
	        model.addAttribute("buyNavClass", "active");
	        model.addAttribute("error", e.getMessage());
	        return "stockstransaction_form";
		}
    	stockHoldingDao.save(h);

    	model.addAttribute("confirmMessage", "You now own " + h.getSharesOwned() + " shares of " + symbol);
        model.addAttribute("title", "Buy");
        model.addAttribute("action", "/stocks/buy");
        model.addAttribute("buyNavClass", "active");

        return "stockstransaction_confirm";
    }

    @RequestMapping(value = "/stocks/sell", method = RequestMethod.GET)
    public String sellForm(Model model) {
        model.addAttribute("title", "Sell");
        model.addAttribute("action", "/stocks/sell");
        model.addAttribute("sellNavClass", "active");
        return "stockstransaction_form";
    }

    @RequestMapping(value = "/stocks/sell", method = RequestMethod.POST)
    public String sell(String symbol, int numberOfShares, HttpServletRequest request, Model model) throws StockLookupException {

        // TODO - Implement sell action    	
    	User u =getUserFromSession(request);
    	StockHolding h = null;
    	try {
			h = StockHolding.sellShares(u, symbol, numberOfShares);
		} catch (StockLookupException ee) {
			ee.printStackTrace();
	        model.addAttribute("title", "Sell");
			model.addAttribute("error", ee.getMessage());
	        model.addAttribute("action", "/stocks/sell");
	        model.addAttribute("sellNavClass", "active");
			// TODO Auto-generated catch block
			return "stockstransaction_form";
		}
    	//StockTransaction T = new StockTransaction(h, numberOfShares, StockTransaction.TransactionType.SELL);
    	//stockTransactionDao.save(T, u.getUid());
    	stockHoldingDao.save(h);

    	model.addAttribute("confirmMessage", "You just sold " + numberOfShares + " shares of " + symbol + " and have " + h.getSharesOwned() + " left." );    	
        model.addAttribute("title", "Sell");
        model.addAttribute("action", "/stocks/sell");
        model.addAttribute("sellNavClass", "active");

        return "stockstransaction_confirm";
    }

}
