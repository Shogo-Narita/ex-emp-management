package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {
	@Autowired
	private AdministratorService service;
	
	@Autowired
	private HttpSession session;
	
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
				}
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
//	@RequestMapping("")
//	public String index(InsertAdministratorForm form) {
//		return "InsertAdministratorForm";
//	}
//	
//	@RequestMapping("")
//	public String index(LoginForm form) {
//		return "LoginForm";
//	}
	
	
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert.html";
		
	}
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
	
		Administrator administrator=new Administrator();
		BeanUtils.copyProperties(form, administrator);
		
		//AdministratorService administratorService=new AdministratorService();
		service.insert(administrator);
		
			return "redirect:/ / ";
		
	}
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login.html";
	}
	
	@RequestMapping("/login")
	public String login(LoginForm form,Model model) {
		if(form==null) {
			System.out.println("メールアドレスまたはパスワードが不正です");
			return "login.html";
		}else {
				session.setAttribute("administratorName",form);
				return "forward:/employee/showList";
			
		}
		
	}
		

}
