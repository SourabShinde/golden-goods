package com.app.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Role;
import com.app.entities.UserRole;

public interface RoleRepository extends JpaRepository<Role,Long> {
	Role findByRoleName(Role role);
	
	Set<Role> findByRoleNameIn(Set<UserRole> set);
}
