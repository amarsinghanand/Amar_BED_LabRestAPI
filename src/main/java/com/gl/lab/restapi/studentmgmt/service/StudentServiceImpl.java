package com.gl.lab.restapi.studentmgmt.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.lab.restapi.studentmgmt.entity.Student;
import com.gl.lab.restapi.studentmgmt.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	StudentRepository studentRepsoitory;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Student> getAllStudents() {
		return studentRepsoitory.findAll();
	}

	@Override
	public List<Student> findByCourse(String course) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Student> cq = cb.createQuery(Student.class);
		Root<Student> from = cq.from(Student.class);
		cq.select(from).where(cb.equal(from.get("course"), course));
		TypedQuery<Student> tq = entityManager.createQuery(cq);
		return tq.getResultList();
	}

	@Override
	public List<Student> findByCountry(String country) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Student> cq = cb.createQuery(Student.class);
		Root<Student> from = cq.from(Student.class);
		cq.select(from).where(cb.equal(from.get("country"), country));
		TypedQuery<Student> tq = entityManager.createQuery(cq);
		return tq.getResultList();
	}

	@Override
	public Student findById(Long id) {
		return studentRepsoitory.findById(id).get();
	}

	@Override
	public Student save(Student student) {
		return studentRepsoitory.save(student);
	}

	@Override
	public Student update(Student studentForUpdate, Long id) {
		Student student = studentRepsoitory.findById(id).get();
		student.setFirstName(studentForUpdate.getFirstName());
		student.setLastName(studentForUpdate.getLastName());
		student.setCountry(studentForUpdate.getCountry());
		student.setCourse(studentForUpdate.getCourse());
		studentRepsoitory.save(student);
		return student;
	}

	@Override
	public void delete(Long id) {
		Student student = studentRepsoitory.findById(id).get();
		studentRepsoitory.delete(student);
	}

}
