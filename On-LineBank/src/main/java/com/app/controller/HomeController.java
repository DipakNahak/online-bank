package com.app.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.dao.RoleDao;
import com.app.model.CurrentAccount;
import com.app.model.SavingAccount;
import com.app.model.User;
import com.app.model.security.UserRole;
import com.app.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	@Autowired
    private RoleDao roleDao;
	
	@RequestMapping(value={"/","/index"})
	public String index(){
	return "index";	
	}
	
	@RequestMapping(value="/signup",method=RequestMethod.GET)
	public String signUp(Model model){
		User user=new User();
		model.addAttribute("user", user);
		return "signup";
	}
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public String saveUpPost(@ModelAttribute("user")User user,Model model){
		if (userService.checkUserExists(user.getUsername(),user.getEmail())) {
			if (userService.checkEmailExists(user.getEmail())) {
				model.addAttribute("emailExists", true);
			}
			if (userService.checkUsernameExists(user.getUsername())) {
				model.addAttribute("usernameExists",true);
			}
			return "signup";
		}else {
			 Set<UserRole> userRoles = new HashSet<>();
             userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));

            userService.createUser(user, userRoles);
			
            return "redirect:/";
		}
	}
	@RequestMapping("/on-LineBank") 
	public String onLineBank(Principal principal,Model model){
		User user=userService.findByUsername(principal.getName());
		CurrentAccount currentAccount=user.getCurrentAccount();
		SavingAccount savingAccount=user.getSavingAccount();
		
		model.addAttribute("currentAccount", currentAccount);
		model.addAttribute("savingAccount", savingAccount);
		return "on-LineBank";
	}
}
