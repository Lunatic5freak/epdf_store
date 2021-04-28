package com.lsolution.Library_demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lsolution.Library_demo.dao.BookRepository;
import com.lsolution.Library_demo.dao.FilesInterface;
import com.lsolution.Library_demo.dao.IssuedBookRepository;
import com.lsolution.Library_demo.dao.RequestedBookRepo;
import com.lsolution.Library_demo.entity.BookDetails;
import com.lsolution.Library_demo.entity.Book_Images;
import com.lsolution.Library_demo.entity.File;
import com.lsolution.Library_demo.entity.IssuedBook;
import com.lsolution.Library_demo.entity.ProjectFile;
import com.lsolution.Library_demo.entity.Projects;
import com.lsolution.Library_demo.entity.QusAns;
import com.lsolution.Library_demo.entity.RequestedBook;
import com.lsolution.Library_demo.entity.Users;
import com.lsolution.Library_demo.service.ProjectService;
import com.lsolution.Library_demo.service.QustionService;
import com.lsolution.Library_demo.service.ServiceInterface;
import com.lsolution.Library_demo.service.UserDetailsServiceInterface;

@Controller
public class DemoController {
	
	@Autowired
	private BookRepository dao;
	
	@Autowired
	private ServiceInterface service;
	
	@Autowired
	private IssuedBookRepository issue;
	
	@Autowired
	private UserDetailsServiceInterface role;
	
	@Autowired
	private RequestedBookRepo req;
	
	@Autowired
	private FilesInterface fil;
	
	@Autowired
	private QustionService qus;

	@Autowired
	private ProjectService prj;
	
	Logger l=Logger.getLogger(DemoController.class.getName());

	@GetMapping("/home")
	public String showwelcome(final Model theModel, final String keyword) {
		List<BookDetails> theBook = null;
		if (keyword != null) {
			theBook = dao.findByName(keyword);
		} else {
			theBook = dao.findAll();
		}
		final List<IssuedBook> theissue = issue.findAll();
		final List<RequestedBook> thereqBook = req.findAll();
		theModel.addAttribute("theBooks", theBook);
		theModel.addAttribute("theissuedbook", theissue);
		theModel.addAttribute("therequestedbook", thereqBook);
		return "home";
	}
	
	@GetMapping("/home")
	public String showWelcome(final Model theModel, final String keyword) {
		List<BookDetails> theBook = null;
		if (keyword != null) {
			theBook = dao.findByName(keyword);
		} else {
			theBook = dao.findAll();
		}
		final List<IssuedBook> theissue = issue.findAll();
		final List<RequestedBook> thereqBook = req.findAll();
		theModel.addAttribute("theBooks", theBook);
		theModel.addAttribute("theissuedbook", theissue);
		theModel.addAttribute("therequestedbook", thereqBook);
		return "home";
	}

	@GetMapping("/deletebyId/{theid}")
	public String deleteById(@PathVariable final int theid, final Model theModel) {
		dao.deleteById(theid);
		return "redirect:/home";
	}

	@GetMapping("/addnewBook")
	public String addBook(final Model theModel) {
		final BookDetails theBook = new BookDetails();
		theModel.addAttribute("thebook", theBook);
		return "add-book";
	}

	@PostMapping("/addBook")
	public String addnewbook(@ModelAttribute("thebook") final BookDetails thebook,
			@RequestParam("files") final MultipartFile files, @RequestParam("img") final MultipartFile img,
			final Model theModel) throws IOException {
		final File f = new File(thebook.getId(), files.getOriginalFilename(), files.getContentType(), files.getBytes());
		final Book_Images bi = new Book_Images(thebook.getId(), img.getOriginalFilename(), img.getContentType(),
				img.getBytes());
		thebook.setImage(bi);
		thebook.setFile(f);
		dao.save(thebook);
		return "redirect:/home";
	}

	@GetMapping("/updateBook/{theid}")
	public String updatebook(@PathVariable("theid") final int theid, final Model theModel) {
		BookDetails theBook = new BookDetails();
		theBook = dao.getOne(theid);
		theModel.addAttribute("thebook", theBook);
		return "update-book";
	}

	@PostMapping("/updateBook/{theid}")
	public String updateBook(@PathVariable("theid") final int theid, @Validated final BookDetails thebook,
			final BindingResult theResult, final Model theModel) {
		if (theResult.hasErrors()) {
			thebook.setId(theid);
			return "update-book";
		}
		dao.save(thebook);
		// theModel.addAttribute("thebook",dao.findAll());
		return "redirect:/home";
	}

	@GetMapping("/addUsers")
	public String addUser(final Model theModel) {
		final Users theUser = new Users();
		theModel.addAttribute("users", theUser);
		return "add-user";
	}

	@PostMapping("/addUsers")
	public String saveUser(@ModelAttribute("users") final Users users, final Model theModel) {
		role.saveUser(users);
		return "redirect:/home";
	}

	@GetMapping("/issueBook")
	public String issueBook(final Model theModel) {
		final IssuedBook theIssuedBook = new IssuedBook();
		theModel.addAttribute("theissuedbook", theIssuedBook);
		return "issue-book";
	}

	@PostMapping("/issueBook")
	public String issuebook(@ModelAttribute("theissuedbook") final IssuedBook theBook, final Model theModel) {
		issue.save(theBook);
		return "redirect:/";
	}

	@GetMapping("/showissuedbook")
	public String showIssuedBook(final Model theModel) {
		final List<IssuedBook> theBook = issue.findAll();
		theModel.addAttribute("theissuedbook", theBook);
		return "home";
	}

	@GetMapping("/requestBook")
	public String reqBook(final Model theModel) {
		final RequestedBook thereq = new RequestedBook();
		theModel.addAttribute("therequestedbook", thereq);
		return "request-book";
	}

	@PostMapping("/requestBook")
	public String requestBook(@ModelAttribute("therequestedbook") final RequestedBook theBook, final Model theModel,@RequestParam("username")String username) {
		theBook.setUserid(username);
		System.out.println(username);
		req.save(theBook);
		return "redirect:/";
	}

	@GetMapping("/deletebyname/{name}")
	public String deletebyname(@PathVariable final String name, final Model theModel) {
		req.deleteById(name);
		return "redirect:/";
	}

	@GetMapping("/download/{bookid}")
	public ResponseEntity<Resource> download(@PathVariable final int bookid) {
		// int id1=Integer.parseInt(id);
		final BookDetails document = dao.findById(bookid).get();
		// BookDetails book=document.get();
		final File f = document.getFile();
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(f.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + f.getFilename() + "\"")
				.body(new ByteArrayResource(f.getData()));
	}

	@GetMapping("/showImage/{id}")
	public ResponseEntity<byte[]> showImage(@PathVariable("id") final int id) {
		final BookDetails book = dao.findById(id).get();
		final Book_Images image = book.getImage();
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFiletype())).body(image.getImage());
	}

	@PostMapping("/postQuestion")
	public String poatQuestion(@RequestParam("question") final String qust, @RequestParam("userid") final String userid){
		final QusAns q = new QusAns();
		q.setQuestion(qust);
		q.setUserid(userid);
		qus.postQuestion(q);
		return "redirect:/";
	}

	@GetMapping("/showAnswers")
	public String showans(final Model theModel) {
		final List<QusAns> qusans = qus.showqusans();
		theModel.addAttribute("theans", qusans);
		return "qusans";
	}

	@GetMapping("/showProject")
	public String showProjects(final Model theModel, final String keyword) {
		List<Projects> projects=null;
		if(keyword!=null){
			projects=prj.findByname(keyword);
			theModel.addAttribute("projects", projects);
		}
			projects=prj.allProjects();
			theModel.addAttribute("projects", projects);
		return "project";
	}
	
	@PostMapping("/saveProject")
	public String postProject(@RequestParam("userid")String userid,@RequestParam("description")String des,
			@RequestParam("name")String name,@RequestParam("lan")String lan,@RequestParam("file")MultipartFile file) throws IOException {
		Projects p=new Projects();
		ProjectFile pf=new ProjectFile(p.getId(),file.getOriginalFilename(),file.getContentType(),file.getBytes());
		p.setUserid(userid);
		p.setPname(name);
		p.setDescription(des);
		p.setLan(lan);
		p.setFile(pf);
		prj.saveProject(p);
		return "redirect:/showProject";
	}

	@GetMapping("/projectdownload/{projectid}")
	public ResponseEntity<Resource> downloadProject(@PathVariable int projectid) {
		Projects p=prj.findbyId(projectid).get();
		ProjectFile pf=p.getFile();
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(pf.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+pf.getFilename()+"\"")
				.body(new ByteArrayResource(pf.getData()));
				
	}

}
