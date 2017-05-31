package org.launchcode.controllers;

import org.launchcode.stocks.models.User;
import org.launchcode.stocks.models.util.PasswordHash;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cbay on 5/15/15.
 */


/**
 * Controller class for handling user login, logout and registration
 */
@Controller
public class AuthenticationController extends AbstractController {

    @RequestMapping(value = "/stocks")
    public String index(){
        return "redirect:/stocks/portfolio";
    }

    @RequestMapping(value = "/stocks/register", method = RequestMethod.GET)
    public String register() {
        return "stocksregister";
    }

    @RequestMapping(value = "/stocks/register", method = RequestMethod.POST)
    public String register(String userName, String password, String confirmPassword, Model model) {

        // Perform some validation
        User existingUser = userDao.findByUserName(userName);
        if (existingUser != null) {
            return this.displayError(
                    "The username " + userName + " already exits in the system. Please select a different username", model);
        }
        else if (!password.equals(confirmPassword)) {
            return this.displayError("Passwords do not match. Try again.", model);
        }

        // Validation passed. Create and persist a new User entity
        User newUser = new User(userName, password);
        userDao.save(newUser);

        return "stocksindex";
    }

    @RequestMapping(value = "/stocks/login", method = RequestMethod.GET)
    public String login(){
        return "stockslogin";
    }

    @RequestMapping(value = "/stocks/login", method = RequestMethod.POST)
    public String login(String userName, String password, HttpServletRequest request, Model model){

        User user = userDao.findByUserName(userName);

        // User is invalid
        if (user == null) {
            return this.displayError("Invalid username.", model);
        } else if (!PasswordHash.isValidPassword(password, user.getHash())) {
            return this.displayError("Invalid password.", model);
        }

        // User is valid; set in session
        request.getSession().setAttribute(userSessionKey, user.getUid());

        return "redirect:/stocks/portfolio";
    }


    @RequestMapping(value = "/stocks/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "stockslogin";
    }

}