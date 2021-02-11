package com.lsolution.Library_demo.controller;

import java.util.Random;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lsolution.Library_demo.dao.UserRepo;
import com.lsolution.Library_demo.entity.Users;
import com.lsolution.Library_demo.service.MailService;
import com.lsolution.Library_demo.service.UserDetailsServiceInterface;

@Component
@Controller
@RequestMapping("/register")
@SessionAttributes("otp")
public class RegistrationController {

	@Autowired
	private UserDetailsServiceInterface ser;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private UserRepo ur;
	
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
	
	@GetMapping("/forgotpassword")
	public String changePassword() {
		return "forgotpass";
	}
	
	@PostMapping("/forgotpass")
	public String findchange(@RequestParam("userid")String userid,HttpSession h) {
		System.out.println("userid="+userid);
		Users u=ser.getUser(userid).get();
		System.out.println(u.getEmail());
//		int random=(int) Math.random();
//		System.out.println(random);
		Random rnd=new Random();
		int otp=rnd.nextInt(999999);
		h.setAttribute("otpas", otp);
		h.setAttribute("useride",userid );
		System.out.println(otp);
		String msg="Hello "+u.getFname()+"this is your one time password to change the Paswword. Please do not share with anyone else. The OTP is "+otp;
		try {
			mailService.sendMail(u.getEmail(), msg);
		}catch(Exception e) {
			System.out.println(e.getMessage()+"\n"+e.getCause());
		}
		//h.setAttribute("otp",otp);
		return "validate";
	}
	
	@PostMapping("/validate")
	public String validate(@RequestParam("otp")int otp,HttpSession session) {
		int o_t_p=(int) session.getAttribute("otpas");
		System.out.println(o_t_p);
		System.out.println(otp);
		if(otp==o_t_p) {
			System.out.println("otp matched");
			return "changepass";
		}
		else {
			session.setAttribute("wrong", "wrong otp");
			return "validate";
		}
	}
	
	@PostMapping("/changepass")
	public String changepass(@RequestParam("password")String password,HttpSession session) {
		Users u=ser.getUser(session.getAttribute("useride").toString()).get();
		password= "{noop}"+password;
		u.setPassword(password);
		ur.save(u);
		return "redirect:/login";
	}
}
