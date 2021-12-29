package com.greatlearning.springbootstudentapp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.springbootstudentapp.entity.Student;
import com.greatlearning.springbootstudentapp.service.StudentService;

@Controller
public class StudentsController {
	@Autowired
	private StudentService studentService;

	@RequestMapping("/list")
	public String listStudents(Model theModel) {
		System.out.println("inside list");
		List<Student> students = studentService.findAll();
		theModel.addAttribute("Students", students);
		return "list-Students";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		System.out.println("inside showFormForAdd");
		try {
			Student theStudent = new Student();
			theModel.addAttribute("Student", theStudent);

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return "Student-form";

	}

	@RequestMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable("id") int theId, Model theModel) {
		Student theStudent = studentService.findById(theId);
		theModel.addAttribute("Student", theStudent);
		return "Student-form";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveStudent(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("country") String country,
			@RequestParam("department") String department) {

		System.out.println(id);
		Student theStudent;
		if (id != 0) {
			theStudent = studentService.findById(id);
			theStudent.setFirstName(firstName);
			theStudent.setLastName(lastName);
			theStudent.setCountry(country);
			theStudent.setDepartment(department);
		} else
			theStudent = new Student(firstName, lastName, country, department, id);

		studentService.save(theStudent);
		return "redirect:/list";

	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") int theId) {
		studentService.deleteById(theId);
		return "redirect:/list";

	}

	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {
		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", "You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;
	}
}