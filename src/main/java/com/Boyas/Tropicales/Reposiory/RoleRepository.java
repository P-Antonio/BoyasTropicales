package com.Boyas.Tropicales.Reposiory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Boyas.Tropicales.Entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	List<Role> findRolesByRolesIn (List<String> roleName);
}
