package com.lsolution.Library_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lsolution.Library_demo.dao.BookRepository;
import com.lsolution.Library_demo.dao.IssuedBookRepository;
import com.lsolution.Library_demo.dao.RequestedBookRepo;
import com.lsolution.Library_demo.dao.RoleRepository;
import com.lsolution.Library_demo.entity.BookDetails;
import com.lsolution.Library_demo.entity.IssuedBook;
import com.lsolution.Library_demo.entity.RequestedBook;

@Controller
public class LoginCntroller {
	
	@Autowired
	private RoleRepository role;
	
	@Autowired
	private IssuedBookRepository issue;
	
	@Autowired
	private BookRepository dao;
	
	@Autowired
	private RequestedBookRepo req;

	@GetMapping("/login")
	public String showLogin(String keyword,Model theModel) {
		
		 List<BookDetails> theBook = null; if (keyword != null) { theBook =
		 dao.findByName(keyword); } else { theBook = dao.findAll(); } final
		 List<IssuedBook> theissue = issue.findAll(); final List<RequestedBook>
		 thereqBook = req.findAll(); theModel.addAttribute("theBooks", theBook);
		 theModel.addAttribute("theissuedbook", theissue);
		 theModel.addAttribute("therequestedbook", thereqBook);
		return "login-page";
	}
	
	
//	@GetMapping("/registerUser")
//	public String addUser(Model theModel) {
//		Users theUser=new Users();
//		theModel.addAttribute("users",theUser);
//		return "add-user";
//	}
//	
//	@PostMapping("/registerUsers")
//	public String saveUser(@ModelAttribute("users") Users users,Model theModel) {	
//		RoleEntity theRole=new RoleEntity();
//		theRole.setUser(users);
//		role.save(theRole);
//		return "redirect:/login";
//	}
}
