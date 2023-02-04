package com.gl.lab.restapi.studentmgmt.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gl.lab.restapi.studentmgmt.entity.Student;
import com.gl.lab.restapi.studentmgmt.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	StudentService studentService;

	@RequestMapping({ "/list", "/" })
	public String getStudentList(Model model, Principal user) {
		List<Student> students = studentService.getAllStudents();
		model.addAttribute("students", students);
		return "list-students";
	}

	@RequestMapping("/showFormForAdd")
	public String showAddStudentForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "student-form";
	}

	@RequestMapping("/save")
	public String saveStudent(Model model, Student student) {
		studentService.save(student);
		return "redirect:/student/list";
	}

	@RequestMapping("/showFormForUpdate")
	public String showUpdateStudentForm(@RequestParam("studentId") Long studentId, Model model) {
		Student student = studentService.findById(studentId);
		model.addAttribute("student", student);
		return "student-form";
	}

	@RequestMapping("/delete")
	public String deleteById(@RequestParam("studentId") Long studentId, Model model) {
		studentService.delete(studentId);
		return "redirect:/student/list";
	}

	@RequestMapping("/403")
	public ModelAndView accessDenied(Principal user) {

		ModelAndView model = new ModelAndView();
		if (user != null) {
			model.addObject("Msg", "Hi" + user.getName() + "You dont have permission to access this page!");
		} else {
			model.addObject("Msg", "You dont have permission to access this page!");
		}
		model.setViewName("403");
		return model;
	}

}