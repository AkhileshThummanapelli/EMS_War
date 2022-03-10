package com.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Pojo.Users;

@Repository
public interface CredentialRepo extends JpaRepository<Users, String> {

	@Query(value = "select * from credential where username=?1 and password=?2", nativeQuery = true)
	public Users getUserByUsername(String username, String password);
    
	@Modifying
	@Query(value = "delete from credential where username =:user", nativeQuery = true)
	public void deleteUsername(String user);

	@Query(value = "select * from credential where username =:user", nativeQuery = true)
	public Users getUserByUser(String user);
}
