package com.app.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.model.CurrentAccount;
import com.app.model.CurrentTransaction;
import com.app.model.SavingAccount;
import com.app.model.SavingTransaction;
import com.app.model.User;
import com.app.service.AccountService;
import com.app.service.TransactionService;
import com.app.service.UserService;

@Controller
@RequestMapping(value="/account")
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private AccountService accountService;
	@Autowired 
	private TransactionService transactionService;
	
	@RequestMapping("/currentAccount")
	public String currentAccount(Model model,Principal principal){
		List<CurrentTransaction> currentTransactionList = transactionService.findCurrentTransactionList(principal.getName());
	
		User user=userService.findByUsername(principal.getName());
		CurrentAccount currentAccount=user.getCurrentAccount();
		
		model.addAttribute("currentAccount",currentAccount);
		model.addAttribute("currentTransactionList", currentTransactionList);
		return "currentAccount";
	}
	
	@RequestMapping("/savingAccount")
	public String savingAccount(Model model,Principal principal){
		List<SavingTransaction> savingTransactionList = transactionService.findSavingTransactionList(principal.getName());
		
		User user=userService.findByUsername(principal.getName());
		SavingAccount savingAccount=user.getSavingAccount();
		
		model.addAttribute("savingAccount",savingAccount);
		model.addAttribute("savingTransactionList", savingTransactionList);
		return "savingAccount";
	}
	@RequestMapping(value="/deposit" ,method=RequestMethod.GET)
	public String deposit(Model model){
		model.addAttribute("accountType", "");
		model.addAttribute("amount", "");
		return "deposit";
	}
	 @RequestMapping(value = "/deposit", method = RequestMethod.POST)
	    public String depositPOST(@ModelAttribute("amount") String amount, @ModelAttribute("accountType") String accountType, Principal principal) {
	        accountService.deposit(accountType, Double.parseDouble(amount), principal);

	        return "redirect:/on-LineBank";
	    }
	    
	    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
	    public String withdraw(Model model) {
	        model.addAttribute("accountType", "");
	        model.addAttribute("amount", "");

	        return "withdraw";
	    }

	    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	    public String withdrawPOST(@ModelAttribute("amount") String amount, @ModelAttribute("accountType") String accountType, Principal principal) {
	        accountService.withdraw(accountType, Double.parseDouble(amount), principal);

	        return "redirect:/on-LineBank";
	    }
	
}
