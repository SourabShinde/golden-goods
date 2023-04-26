package com.app.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.Role;
import com.app.entities.UserRole;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class TestRoleRepository {
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Test
	@Rollback(false)
	void testAddRoleAdmin() {
		System.out.println(roleRepo.save(new Role(UserRole.ROLE_ADMIN)));

	}
	

	@Test
	@Rollback(false)
	void testAddRoleCustomer() {
		System.out.println(roleRepo.save(new Role(UserRole.ROLE_CUSTOMER)));

	}
	
//
//	@Test
//	@Rollback(false)
//	void testAddRoleSupplier() {
//		System.out.println(roleRepo.save(new Role(UserRole.ROLE_SUPPLIER)));
//
//	}

}
