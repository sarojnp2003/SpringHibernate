/**
 * 
 */
package com.tshirtdesign.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tshirtdesign.model.User;
import com.tshirtdesign.service.UserService;

/**
 * @author saroj-gautam
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

	@GetMapping(value = "/message")
	public String getMessage() {
		return "Got Message";
	}

	@Autowired
	private UserService userService;

	// Register a new user
	@PostMapping(value = "/register")
	public ResponseEntity<Void> addUser() {
		User user = new User();
		user.setFirstName("Saroj2");
		user.setLastName("Gautam2");
		user.setEmail("sarojnp2003+2@gmail.com");
		user.setPassword("test123");
		user.setAddress("nepal");
		user.setTelephone("1234567890");
		user.setRole("ROLE_ADMIN");

		userService.saveUser(user);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	// Get all users
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> user = userService.findAllUser();
		return new ResponseEntity<List<User>>(user, HttpStatus.OK);
	}
	
	@GetMapping(value="/basic")
    public ModelAndView visitHome() {
		ModelAndView model = new ModelAndView("basic");
		model.addObject("title", "Index Page");
		model.addObject("message", "This is index page");
		return model;
    }
         
	@GetMapping(value="/admin")
    public ModelAndView visitAdmin() {
        ModelAndView model = new ModelAndView("admin");
        model.addObject("title", "Admministrator Control Panel");
        model.addObject("message", "This page demonstrates how to use Spring security.");
         
        return model;
    }
}