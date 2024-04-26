package ohara.ac.jp.test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ohara.ac.jp.test.daoimpl.StudentDaoImpl;
import ohara.ac.jp.test.model.Student;
import ohara.ac.jp.test.repository.StudentRepository;

@Service
@Transactional
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private StudentDaoImpl studentDaoImpl;
	
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
	
	public List<Student> search(Integer ent_year,String class_num,Boolean is_attend){
		List<Student> result = new ArrayList<Student>();
		
		if (ent_year == null && class_num == null && is_attend == null) {
			result = studentRepository.findAll();
		}else {
			result = studentDaoImpl.search(ent_year,class_num,is_attend);
		}
		return result;
		
	}
}
