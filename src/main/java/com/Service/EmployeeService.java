package com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.Pojo.Employee;
import com.Pojo.Users;
import com.Repo.CredentialRepo;
import com.Repo.EmployeeRepo;
@Service
public class EmployeeService {
	@Autowired
	EmployeeRepo er;
    //CredentialRepo cr;
	
	    //Add Employee Details
		public Employee saveEmp(Employee e)
		{
			return er.save(e);
			
		}
		public Employee save(Employee e)
		{
			return er.save(e);
			
		}
		//Get All Employee Details
		public List<Employee> getEmployees()
		{
			return er.findAll();
			
		}
		//Update Employee Details
		public Employee get(int empid)
		{
		  return er.findById(empid).get();
		}
		public void delete(int empid)
		{
			er.deleteById(empid);
			
		}
		public Employee FetchEmployeeDetails(String username)
		{
			return er.FetchUsername(username);
			
		}
		public Employee Fetch(String username,String fullname,String address,String phoneno,String email,int salary)
		{
			return er.Fetch(username, fullname, address, phoneno, email, salary);
			
		}
		


}
