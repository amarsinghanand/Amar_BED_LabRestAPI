package com.gl.lab.restapi.studentmgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gl.lab.restapi.studentmgmt.entity.Student;

@Service
public interface StudentService {
	List<Student> getAllStudents();

	List<Student> findByCourse(String course);

	List<Student> findByCountry(String country);

	Student findById(Long id);

	Student save(Student student);

	Student update(Student studentForUpdate, Long id);

	void delete(Long id);
}
