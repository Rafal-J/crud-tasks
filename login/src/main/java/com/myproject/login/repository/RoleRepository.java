package com.myproject.login.repository;

import com.myproject.login.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}