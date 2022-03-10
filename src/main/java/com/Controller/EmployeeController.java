package com.Controller;

import java.util.List;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Pojo.Employee;
import com.Pojo.Users;
import com.Service.CredentialService;
import com.Service.EmployeeService;

@Controller

public class EmployeeController {
	private static final Logger LOGGER=LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeService es;
	
	@Autowired
	CredentialService cs;
	
	Users user=new Users();
	
    @RequestMapping(value="/check",method=RequestMethod.POST)
    public String Check(@ModelAttribute Users u) throws Exception
	{
	   String s=cs.checkprocess(u, u.getUsername(),u.getPassword());
     	user.setUsername(u.getUsername());
		user.setPassword(u.getPassword());
		LOGGER.trace("Entering method checkprocess");
        LOGGER.debug("Authenticating with Username: "+user.getUsername());
		if(s.equals("Admin Found"))
		{
			LOGGER.warn("Testing Authentication Process");
			LOGGER.info("Admin authenticated Successfully");
		   //return "redirect:/getEmployeesTables";
			return "AdminAccess";
		}
		else if(s.equals("User Found"))
		{
			LOGGER.warn("Testing Authentication Process");
			LOGGER.info("User authenticated Successfully");
			return "redirect:/getEmployee";
		}
		else if(s.equals("Not Found"));
		{
			LOGGER.warn("Testing Authentication Process");
			LOGGER.info("User authenticated Failed....!");
			LOGGER.error("User Not Found");
			throw new NullPointerException("Null Pointer Exception");
			//return "UserError";
		}
		
		
	}
    
	@RequestMapping("/getEmployee")
	public String getEmployeeInfo(Model model,Employee e)
	{
		Employee emp=es.FetchEmployeeDetails(user.getUsername());
		Optional<Employee> op=Optional.ofNullable(emp);
		  if(op.isPresent())
		  {
		  	model.addAttribute("emp", emp);
			return "EmployeeDetails";	    	         
		  }
		  else 
	      {
		    return "EmployeeError";
			  //model.addAttribute("e", e);
			  //sreturn "CreateEmp";
		  
		  }   
	}
	
    @PostMapping("/NewEmployee")
	public String NewEmployee(@Valid @ModelAttribute("e") Employee e,BindingResult b, Model model)
	{
		if(b.hasErrors())
		{
			
			return "CreateEmp";
		}
			Employee empl=new Employee();
			empl.setEmpid(e.getEmpid());
			empl.setUsername(user.getUsername());
			empl.setFullname(e.getFullname());
			empl.setAddress(e.getAddress());
			empl.setPhoneno(e.getPhoneno());
			empl.setEmail(e.getEmail());
			empl.setSalary(e.getSalary());
			
			Employee emp=es.saveEmp(empl);
			
			model.addAttribute("emp", emp);
			
			 return "EmployeeDetails";	
     }	
    @PostMapping("/UpdateEmployee")
    public String UpdateEmployee(Employee e, Model model)
    {
    	Employee emp=es.saveEmp(e);
		
		model.addAttribute("emp", emp);
		
		 return "EmployeeDetails";	
    }
	 @RequestMapping("/EmployeeForm")
	 public String EmployeeForm()
	 {
	     return "EmployeeForm"; 	
	 }
	 
	 
     @RequestMapping("/getEmployeesTables")
	 public String getAll(Model model)
	 {
		List<Employee> e= es.getEmployees();
			
		model.addAttribute("emp", e);
		return "AdminForm";	
	 }
	 @RequestMapping("/getEmployees")
	 public String getEmp( Model model)
	 {
		List<Employee> em= es.getEmployees();
			
		model.addAttribute("empl", em);
		return "EmployeeDetails";	
	 }
	 
	 @RequestMapping("/new")
	 public String showNewEmployeeForm(Employee emp,Model model)
	 {	
		model.addAttribute("emp", emp);
		return "EmployeeForm";	
	 }
	 
   	 @RequestMapping("/newEmp")
	 public String EmployeeForm1(Employee e,Model model)  
	 {
   		model.addAttribute("e", e);
		return "CreateEmp";
	 }
   	 
	 @RequestMapping(value = "/save", method= RequestMethod.POST)
	 public String saveEmployee(@ModelAttribute("Employee") Employee e)
	 {
	    es.save(e);
		return "redirect:/getEmployeesTables";
	    //return "EditAdminSuccess";
	 }
	 
	 @RequestMapping(value = "/saveEmp", method= RequestMethod.POST)
	 public String saveEmployee1(@ModelAttribute("Employee") Employee employee)
	 {
		es.saveEmp(employee);
		//return "redirect:/getEmployees";	
		return "EditEmployeeSuccess";
	 }
	 
	 @RequestMapping("/edit/{id}")
	 public String showEditEmployeeForm(@PathVariable(name="id")int id,Employee e,Model model )
	 {
		if(user.getUsername().equals("admin"))
		{
			/*if(b.hasErrors())
			{
				return("EditEmployeeByAdmin");
			}*/
		    //ModelAndView mav=new ModelAndView("EditEmployeeByAdmin");
		    Employee em=es.get(id);
		   // mav.addObject("e",e);
		   // return mav;
		    model.addAttribute("em", em);
		    return("EditEmployeeByAdmin");
		}
		else
		{
			/*if(b.hasErrors())
			{
				return("EditEmployee");
			}*/
			//ModelAndView mav=new ModelAndView("EditEmployee");
			Employee em=es.get(id);
			//mav.addObject("e",e);
			//return mav;
			model.addAttribute("em", em);
			return("EditEmployee");
			
		}
	 }
	 
	 @RequestMapping("/delete/{empid}")
	 public String deleteEmployee(@PathVariable(name = "empid")int empid)
	 {
		es.delete(empid);
		return "redirect:/getEmployeesTables";
	 }
	@RequestMapping("/New/{username}")
	public String EmployeeForm(@PathVariable(name="username") String username,@RequestParam("fullname") String fullname,@RequestParam("address") String address,@RequestParam("phoneno") String phoneno,@RequestParam("email") String email,@RequestParam("salary") int salary,Model model)
	{
		Employee e=es.Fetch(username, fullname, address, phoneno, email, salary);
		//model.addAttribute("e", e);
		//return "EmployeeDetails";
		return "UserError";
	}
		
	@ResponseBody
    @PostMapping("/addEmployee")
	public Employee addEmp( @RequestBody Employee e)
	{
		return es.saveEmp(e);	
	}
	//Get All Employee Details
	@ResponseBody
	@GetMapping("/getEmployees")
	public List<Employee> getEmployees( Employee a)
	{
		return es.getEmployees();
	}
	
	@ExceptionHandler(value= NullPointerException.class)
	public String handleNullPointerException(Exception e)
	{
		System.out.println("Null Pointer Exception Occured: "+e);
		return "UserError";
		
	}
	 /*@RequestMapping("/edit/{fullname}")
	 public ModelAndView showEditEmployeeForm(@PathVariable(name="fullname")String fullname)
	 {
		if(user.getUsername().equals("admin"))
		{
		    ModelAndView mav=new ModelAndView("EditEmployeeByAdmin");
		    Employee e=es.FetchEmployeeDetails(fullname);
		    mav.addObject("e",e);
		    return mav;
		}
		else
		{
			ModelAndView mav=new ModelAndView("EditEmp");
			Employee e=es.FetchEmployeeDetails(fullname);
			mav.addObject("e",e);
			return mav;
			
		}
	 }*/
   /*@RequestMapping("/show/{username}")
    public String showEmployeeForm(@PathVariable(name="username")String username,Model model)
	{

		Employee e=es.FetchEmployeeDetails(username);
	    if(e.getUsername().isEmpty())
	    {
		  return "redirect:/new";
		}
	    else
	    {
		  model.addAttribute("e", e);
		  return "EmployeeDetails";	
		}	
	}*/
		
}
