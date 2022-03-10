package com.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Pojo.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer>{
	
	@Query(value="select * from employee where username=?1",nativeQuery=true)
	public Employee FetchUsername(String username);
	
	@Query(value="insert into employee values('username'=?1,'fullname'=?2,'address'=?3,'phoneno'=?4,'email'=?5,salary=?6)",nativeQuery=true)
	public Employee Fetch(String username,String fullname,String address,String phoneno,String email,int salary);


}
