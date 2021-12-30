package com.greatlearning.employeemanageapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.employeemanageapp.entity.Role;

public interface RolesRepository extends JpaRepository<Role, Integer>{

}
