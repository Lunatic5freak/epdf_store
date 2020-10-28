package com.lsolution.Library_demo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lsolution.Library_demo.dao.BookRepository;
import com.lsolution.Library_demo.entity.BookDetails;
import com.lsolution.Library_demo.entity.CartItems;
import com.lsolution.Library_demo.service.CartServiceInterface;

@Controller
public class CartController {

	@Autowired
	private CartServiceInterface cart;
	
	@Autowired
	private BookRepository bookrepo;
	
	@GetMapping("/showCart")
	public String showCart(Model theModel) {
		List<CartItems> cartitem=cart.showallItems();
		theModel.addAttribute("singlecart",cartitem);
		return "cart";
	}
	
	@GetMapping("/showsingleCart/{userid}")
	public String showsinglecart(@PathVariable("userid")String userid,Model theModel) {
		int userid1=Integer.parseInt(userid);
		Optional<CartItems> option=cart.showbyuserid(userid1);
		CartItems cart=option.get();
		theModel.addAttribute("singlecart",cart);
		return "cart";
	}
	
	@GetMapping("/removeproduct/{productid}")
	public void remove_product(@PathVariable("productid")int id) {
		cart.removeitem(id);
	}
	
	@GetMapping("/addtocart/{productid}")
	public String addtoCart(@PathVariable("productid")int id) {
		Optional<BookDetails> book=bookrepo.findById(id);
		Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		BookDetails book1=book.get();
		String userid="";
		if(principal instanceof UserDetails) {
			userid=((UserDetails) principal).getUsername();
		}
		else {
			userid=principal.toString();
		}
		CartItems item = new CartItems();
		item.setPrice(book1.getPrice());
		item.setProductid(id);
		item.setQuantity(1);
		item.setUserid(userid);
		item.setOrder_date(LocalDate.now());
		cart.addoCart(item);
		return "redirect:/";
	}
	
}

