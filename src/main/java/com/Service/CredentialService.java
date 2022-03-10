package com.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.Pojo.Users;
import com.Repo.CredentialRepo;

@Service
public class CredentialService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CredentialService.class);

	@Autowired
	CredentialRepo cr;

	// Add Employee Details
	public Users saveUser(Users u) {
		return cr.save(u);

	}

	// Get All Employee Details
	public List<Users> getUsers() {
		return cr.findAll();

	}

	// Update Employee Details
	public Users updateEmp(Users u) {
		return cr.save(u);
	}

	public Users getUsersByUsername(String username, String password) {
		return cr.getUserByUsername(username, password);

	}

	public Users getUsersByUser(String user) {
		return cr.getUserByUser(user);

	}
    @Transactional
	public void deleteUser(String user) {
		cr.deleteUsername(user);
	}

	public String checkprocess(@ModelAttribute Users Users, String username, String password) {
		Users u = cr.getUserByUsername(username, password);
		String uname = u.getUsername();
		String pass = u.getPassword();

		// System.out.println(uname);
		if (uname.isEmpty()) {

			return "Not Found";
		} else if (uname.equals("admin") && pass.equals("admin")) {
			return "Admin Found";
		}

		else if (uname.equals(username) && pass.equals(password) && uname != "admin") {
			return "User Found";
		}

		return "Not Found";
	}
	public String check(@ModelAttribute Users Users, String username, String password) {
		Users u = cr.getUserByUsername(username, password);
		String uname = u.getUsername();
		String pass = u.getPassword();

		// System.out.println(uname);
		if (uname.isEmpty()) {

			return "Not Found";
		} else if (uname.equals("admin") && pass.equals("admin")) {
			return "Admin Found";
		}

		else if (uname.equals(username) && pass.equals(password) && uname != "admin") {
			return "User Found";
		}

		return "Not Found";
	}
	

}
