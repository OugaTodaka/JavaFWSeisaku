package ohara.ac.jp.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ohara.ac.jp.test.model.Student;
import ohara.ac.jp.test.repository.StudentRepository;

@Service
@Transactional
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> searchAll(){
		return studentRepository.findAll();
	}

	
	public void insert(Student student) {
		studentRepository.save(student);
	}
	
	public void update(Student student) {
		studentRepository.save(student);
	}
	
	public void delete(Long id) {
		studentRepository.deleteById(id);
	}
	
	public Student get(Long id) {
		Student stu = this.studentRepository.findById(id).orElse(new Student());
		return stu;
	}
	
}
