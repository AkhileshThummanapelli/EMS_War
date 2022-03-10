package com.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Pojo.Users;
import com.Service.CredentialService;

@Controller
public class UserController {
	private static final Logger LOGGER=LoggerFactory.getLogger(UserController.class);
	@Autowired
	CredentialService cs;
	/*@RequestMapping("/")
	public String Login()
	{
		//return "Login";
		//return "LoginForm";
		return "LoginSlide";
	}*/
	@RequestMapping("/")
	public String Login(Model model)
	{
		Users u=new Users();
		model.addAttribute("u", u);
		return "LoginSlide";	
	}
	
	@RequestMapping("/AdminAccess")
	public String Home()
	{
		return "AdminAccess";
	}
	@RequestMapping("/alert")
	public String Alert()
	{
		return "Alert.html";
	}
	
	@RequestMapping("/usertable")
	public String UserTable()
	{
		return "AdminUsers";
	}
	
	@RequestMapping("/newUser")
	public String showNewUserForm(Model model)
	{
		Users u=new Users();
		model.addAttribute("u", u);
		return "UserRegi";	
	}
	
	@RequestMapping(value = "/saveUser", method= RequestMethod.POST)
	public String saveUser(@ModelAttribute("u") Users u)
	{
		cs.saveUser(u);	
		return "LoginSlide";	
	}
	@RequestMapping(value = "/edit", method= RequestMethod.POST)
	public String EditUser(@ModelAttribute("u") Users u)
	{
		cs.saveUser(u);	
		return "redirect:/getUsersTables";	
	}
	@RequestMapping(value = "/getUsersTables", method= RequestMethod.GET)
	public String getAllUsers(Model model)
	{
		List<Users> u= cs.getUsers();
		model.addAttribute("u", u);
		return "AdminUsers";	
	}
	
	@RequestMapping("/del/{username}")
	public String delUser(@PathVariable(name = "username")String user)
	{
		cs.deleteUser(user);
		return "redirect:/getUsersTables";
	}
	@RequestMapping("/update/{username}")
	public String del(@PathVariable(name = "username")String user, Model model,Users u)
	{
		Users us= cs.getUsersByUser(user);
		model.addAttribute("us", us);
		return "UserEdit";
	}

	/*@RequestMapping(value="/check",method=RequestMethod.POST)
	public String Check(@ModelAttribute Users Users, String username,String password)
	{
		String s=cs.checkprocess(Users, username, password);
		if(s.equals("1"))
		{
			//return "redirect:/getTables";
			 return "AdminAccess";
		}
		else if(s.equals("2"))
		{
			return "redirect:/show/"+username+"";
			//return "redirect:/newEmp";
		}
		else if(s.equals("-1"))
		{
			return "UserError";
		}
		return "newUser";
		
	}*/
	@ResponseBody
	@PostMapping("/addUser")
	public Users addUser( @RequestBody Users u)
	{
		return cs.saveUser(u);	
	}
	
	@ResponseBody
	@GetMapping("/getUsers")
	public List<Users> getEmployees(@RequestBody Users u)
	{
		return cs.getUsers();
	}
}
