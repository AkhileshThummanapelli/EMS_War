package com.Pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name="employee")
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//@Size(min=5)
	int empid;
	//@Column
	//@NotNull
	@Size(min=6)
	String username;
    @Column
	String fullname;
	//@NotNull
	String address;
	@NotNull(message="Phone Number Cannot Be Empty.")
	@Column
	@Size(min=10, max=10, message="Phone Number should be 10 digits")
	String phoneno;
	@NotNull(message="Email Cannot Be Empty.")
	@Column
	@Email(message="Email Must be a Valid")
	String email;
	@NotNull(message="Salary Cannot Be Empty.")
	@Column
	@Size(min=5, message="Salary must contains 5 digits")
	String salary;
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}	
}
