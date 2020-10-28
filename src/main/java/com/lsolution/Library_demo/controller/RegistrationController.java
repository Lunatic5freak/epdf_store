package com.lsolution.Library_demo.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lsolution.Library_demo.entity.Users;
import com.lsolution.Library_demo.service.MailService;
import com.lsolution.Library_demo.service.UserDetailsServiceInterface;

@Component
@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private UserDetailsServiceInterface ser;
	
	@Autowired
	private MailService mailService;
	
	Logger logger=Logger.getLogger(getClass().getName());
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/showRegdform")
	public String ShowRegform(Model theModel) {
		Users user=new Users();
		theModel.addAttribute("users",user);
		return "new-reg";
	}
	
	@PostMapping("/processForm")
	public String processForm(@ModelAttribute("users") Users theUsers
			,BindingResult theResult,Model theModel) {
		String name=theUsers.getFname();
		logger.info(">>processing form for "+name);
//		if(theResult.hasErrors()) {
//			return "registration-form";
//		}
//		List<Usersentity> user=ser
//		if(user!=null) {
//			theModel.addAttribute("saveusers", new Usersentity());
//			theModel.addAttribute("registration error", "User already exist");
//			return "registration-form";
//		}
		String msg="Hello "+theUsers.getFname()+", Thanks For Registering With us. Now you can Download pdfs, ask for help on any project or question and you can also download available projects from our website for free of cost.";
		ser.saveUser(theUsers);
		try {
			mailService.sendMail(theUsers.getEmail(),msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(">>success");
		return "redirect:/login";
	}
}
