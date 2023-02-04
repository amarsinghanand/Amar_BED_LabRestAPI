package com.gl.lab.restapi.studentmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.lab.restapi.studentmgmt.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	List<Student> getAllByCourse(String course);

}
