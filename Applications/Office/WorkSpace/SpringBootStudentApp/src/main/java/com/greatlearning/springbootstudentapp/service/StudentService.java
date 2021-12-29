package com.greatlearning.springbootstudentapp.service;

import java.util.List;
import com.greatlearning.springbootstudentapp.entity.Student;

public interface StudentService {
	public List<Student> findAll();
	public Student findById(int id);
	public void save(Student student);
	public void deleteById(int id);
	public List<Student> searchBy(String firstName, String lastName);
}
